package cn.spider;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeResponse;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesResponse;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AliYunDiskTest {
    ThreadPoolExecutor rdsInfoWorkerExecutor = RdsInfoThreadPoolConfig.rdsInfoWorkerExecutor;

    public static void main(String[] args) {
        AliYunDiskTest aliYunDiskTest = new AliYunDiskTest();
        IAcsClient towerClient = TestUtil.towerClient();
        try {
            Map<String, RdsPropertyInfo> newRdsPropertys = aliYunDiskTest.sortSpProperty(aliYunDiskTest.getTowerRdsInfoList(towerClient));
            Map<String, RdsPropertyInfo> detailPropertys = aliYunDiskTest.sortDetailRdsInfoList(towerClient, newRdsPropertys);
            System.out.println(JSON.toJSONString(detailPropertys));
        } catch (Exception e) {
            throw new ServiceException(e);
        } finally {
            // 如果发生异常则停止
            towerClient.shutdown();
        }
    }

    private Map<String, RdsPropertyInfo> sortSpProperty(Map<String, RdsPropertyInfo> totalProperty) {
        if (totalProperty == null) {
            return null;
        }

        Map<String, RdsPropertyInfo> ret = new HashMap<>();

        totalProperty.forEach((key, value) -> {
            if (value.getName().contains("J系列")) {
                ret.put(key, value);
            }
        });

        return ret;
    }

    private Map<String, RdsPropertyInfo> sortDetailRdsInfoList(IAcsClient towerClient, Map<String, RdsPropertyInfo> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Map<String, RdsPropertyInfo> rdsPropertyInfoMap = new ConcurrentHashMap<>(map);

        Map<String, RdsPropertyInfo> rdsCalibrationMap = new HashMap<>(map);

        int finalSegmentSize = getSegmentSize(map.size());
        CompletableFuture.allOf(Stream.iterate(0, i -> i + 1)
                .limit(rdsInfoWorkerExecutor.getCorePoolSize())
                .map(a -> map.keySet().stream()
                        .skip((long) a * finalSegmentSize)
                        .limit(finalSegmentSize)
                        .collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .map(d -> String.valueOf(d).replace(" ", "").replace("[", "").replace("]", ""))
                .map(s -> getTowerRdsDetailInfoList(towerClient, map, s))
                // 当请求以异常情况返回时直接忽略
                .filter(c -> !c.isCompletedExceptionally())
                .toArray(CompletableFuture[]::new)).join();
        return rdsPropertyInfoMap;
    }

    private CompletableFuture<DescribeDBInstanceAttributeResponse> getTowerRdsDetailInfoList(IAcsClient towerClient,
                                                                                             Map<String, RdsPropertyInfo> towerRdsInfoListMap,
                                                                                             String dbInstanceIdList) {
        return CompletableFuture.supplyAsync(() -> {
            DescribeDBInstanceAttributeRequest attributeRequest = new DescribeDBInstanceAttributeRequest();
            attributeRequest.setDBInstanceId(dbInstanceIdList);
            DescribeDBInstanceAttributeResponse attributeResponse = TestUtil.getTowerResponse(attributeRequest, towerClient);
            if (Objects.isNull(attributeResponse)) {
                return null;
            }
            System.out.println(dbInstanceIdList);
            for (DescribeDBInstanceAttributeResponse.DBInstanceAttribute detailInfo : attributeResponse.getItems()) {
                towerRdsInfoListMap.get(detailInfo.getDBInstanceId()).setRdsDetailProperty(
                        Integer.parseInt(detailInfo.getDBInstanceCPU()),
                        detailInfo.getDBInstanceMemory(),
                        detailInfo.getDBInstanceStorage(),
                        detailInfo.getMaxIOPS(),
                        detailInfo.getMaxConnections()
                );
            }
            return attributeResponse;
        }, this.rdsInfoWorkerExecutor);
    }

    private int getSegmentSize(int total) {
        int maxSegmentSize = 30;
        // 将数据分段是为了更符合当前及其最优线程池的线程数情况
        int processorSize = rdsInfoWorkerExecutor.getCorePoolSize();
        // 每段的大小
        int segmentSize = (total + processorSize - 1) / processorSize;
        // 将每段的大小缩小到maxSegmentSize之下
        while (segmentSize > maxSegmentSize) {
            segmentSize = (total + processorSize - 1) / processorSize;
            processorSize++;
        }
        return segmentSize;
    }

    private Map<String, RdsPropertyInfo> getTowerRdsInfoList(IAcsClient towerClient) {
        Map<String, RdsPropertyInfo> rdsPropertyInfoListMap = new HashMap<String, RdsPropertyInfo>();
        // 请求
        DescribeDBInstancesRequest request = new DescribeDBInstancesRequest();
        // 每页最大数据量
        request.setPageSize(100);
        // 默认页码
        int pageNum = 1;
        request.setPageNumber(pageNum);
        // 响应和数据整理
        DescribeDBInstancesResponse response = getTowerResponseAndDataAdjust(rdsPropertyInfoListMap, towerClient, request);
        if (Objects.isNull(response)) {
            return null;
        }

        // 总数
        Float valueCount = Float.valueOf(response.getTotalRecordCount());
        // 遍历获取信息
        double totalNum = Math.ceil(valueCount / 100);
        for (int i = 2; i <= totalNum; ++i) {
            request.setPageNumber(i);
            // 响应和数据整理
            getTowerResponseAndDataAdjust(rdsPropertyInfoListMap, towerClient, request);
        }
        return rdsPropertyInfoListMap;
    }

    private DescribeDBInstancesResponse getTowerResponseAndDataAdjust(Map<String, RdsPropertyInfo> rdsPropertyInfoListMap,
                                                                      IAcsClient towerClient, DescribeDBInstancesRequest request) {
        DescribeDBInstancesResponse response = TestUtil.getTowerResponse(request, towerClient);
        List<DescribeDBInstancesResponse.DBInstance> instances = Optional.ofNullable(response)
                .map(DescribeDBInstancesResponse::getItems)
                .orElse(Collections.emptyList());

        if (instances.isEmpty()) {
            return null;
        }

        instances.forEach(i -> {
            RdsPropertyInfo rdsPropertyInfo = new RdsPropertyInfo(
                    0l,
                    i.getDBInstanceId(),
                    i.getDBInstanceDescription(),
                    1,
                    2,
                    i.getEngineVersion(),
                    utcTimeToGmtTime(i.getExpireTime()));
            rdsPropertyInfoListMap.put(i.getDBInstanceId(), rdsPropertyInfo);
        });
        return response;
    }


    public static String utcTimeToGmtTime(String utcTime) {
        if (StringUtils.isBlank(utcTime)) return null;
        String time = "";

        utcTime = utcTime.replace("T", " ");
        utcTime = utcTime.replace("Z", "");
        try {
            Long utcTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(utcTime).getTime();
            Long timeStamp = utcTimeStamp + 28800000L;
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp));
        } catch (Exception e) {
            time = utcTime;
        }
        return time;
    }
}
