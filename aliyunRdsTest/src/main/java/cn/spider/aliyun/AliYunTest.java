package cn.spider.aliyun;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeResponse;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AliYunTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            AliYunTest aliYunTest = new AliYunTest();
            aliYunTest.test(14);
        }
        ThreadPool.EXECUTOR.shutdown();
    }

    public List<DescribeDBInstancesResponse.DBInstance> instances(IAcsClient acsClient) {
        DescribeDBInstancesRequest request = new DescribeDBInstancesRequest();
        int pageSize = 100;
        request.setPageSize(pageSize);
        request.setPageNumber(1);
        DescribeDBInstancesResponse response = TestUtil.getTowerResponse(request, acsClient);
        List<DescribeDBInstancesResponse.DBInstance> instances = Optional.ofNullable(response)
                .map(DescribeDBInstancesResponse::getItems)
                .orElse(Collections.emptyList());
        request.setPageNumber(2);
        DescribeDBInstancesResponse responsePageTwo = TestUtil.getTowerResponse(request, acsClient);
        request.setPageNumber(3);
        DescribeDBInstancesResponse responsePageThree = TestUtil.getTowerResponse(request, acsClient);
        instances.addAll(responsePageTwo.getItems());
        instances.addAll(responsePageThree.getItems());
        return instances;
    }

    /**
     * @param segmentSize 每组的个数
     */
    public void test(int segmentSize) {
        long t = System.currentTimeMillis();
        IAcsClient acsClient = TestUtil.towerClient();
        List<DescribeDBInstancesResponse.DBInstance> instances = instances(acsClient);
        System.out.println(instances.size());
        int size = (instances.size() + segmentSize - 1) / segmentSize;
        List<String> instance = Stream.iterate(0, i -> i + 1)
                .limit(size)
                .map(a -> instances
                        .parallelStream()
                        .map(DescribeDBInstancesResponse.DBInstance::getDBInstanceId)
                        .skip((long) a * segmentSize)
                        .limit(segmentSize)
                        .collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .map(d -> String.valueOf(d).replace(" ", "").replace("[", "").replace("]", ""))
                .collect(Collectors.toList());

        // 为了避免任务没有完成就结束执行，使用 allOf 进行控制，而不是线程休眠
        List<CompletableFuture<DescribeDBInstanceAttributeResponse>> completableFutures = new ArrayList<>();
        // 使用 CompletableFuture 并通过 ThreadPool.EXECUTOR 执行
        for (String s : instance) {
            CompletableFuture<DescribeDBInstanceAttributeResponse> completableFuture = CompletableFuture.supplyAsync(() -> {
                DescribeDBInstanceAttributeRequest attributeRequest = new DescribeDBInstanceAttributeRequest();
                attributeRequest.setDBInstanceId(s);
                return TestUtil.getTowerResponse(attributeRequest, acsClient);
            }, ThreadPool.EXECUTOR);
            completableFutures.add(completableFuture);
        }

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
        long e = System.currentTimeMillis();

        List<DescribeDBInstanceAttributeResponse> collect = completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());


        System.out.println(e - t);

        acsClient.shutdown();
    }

    public List<String> subsection(List<DescribeDBInstancesResponse.DBInstance> instances, int segmentSize) {
        int size = (instances.size() + segmentSize - 1) / segmentSize;
        return Stream.iterate(0, i -> i + 1)
                .limit(size)
                .map(a -> instances
                        .parallelStream()
                        .map(DescribeDBInstancesResponse.DBInstance::getDBInstanceId)
                        .skip((long) a * segmentSize)
                        .limit(segmentSize)
                        .collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .map(d -> String.valueOf(d).replace(" ", "").replace("[", "").replace("]", ""))
                .collect(Collectors.toList());
    }

}
