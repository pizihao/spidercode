package cn.spider;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstanceAttributeRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class AliYunSingleTest {

    ThreadPoolExecutor rdsInfoWorkerExecutor = RdsInfoThreadPoolConfig.rdsInfoWorkerExecutor;
    ThreadPoolExecutor rdsInfoBossExecutor = RdsInfoThreadPoolConfig.rdsInfoBossExecutor;
    private static final AtomicLong millimeterCount = new AtomicLong(System.currentTimeMillis() + 300000);

    public static void main(String[] args) throws InterruptedException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AliYunSingleTest aliYunTest = new AliYunSingleTest();
            TimeUnit.MILLISECONDS.sleep(300);
            futures.add(CompletableFuture.runAsync(aliYunTest::get, aliYunTest.rdsInfoBossExecutor));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();


        RdsInfoThreadPoolConfig.rdsInfoWorkerExecutor.shutdown();
        RdsInfoThreadPoolConfig.rdsInfoBossExecutor.shutdown();
    }

    private void get(){
        long currentTimeMillis = System.currentTimeMillis();
        long oldValue = millimeterCount.get();
        // 如果多个线程都在使用，则只有一个会成功，其他的会同步到成功的结果，并且快速返回
        if (currentTimeMillis > oldValue - 60000 && millimeterCount.compareAndSet(oldValue, currentTimeMillis + 300000)) {
            sortDetailRdsInfoList();
        } else {
            if ((oldValue = millimeterCount.get()) > System.currentTimeMillis()){
                millimeterCount.compareAndSet(oldValue, oldValue - 60000);
            }
            System.out.println(millimeterCount.get() - System.currentTimeMillis());
        }
    }

    private void sortDetailRdsInfoList() {
        IAcsClient towerClient = TestUtil.towerClient();
        try {
            long t = System.currentTimeMillis();
            List<DescribeDBInstancesResponse.DBInstance> dbInstances = instances(towerClient);
            int segmentSize = getSegmentSize(dbInstances.size());
            int limitSize = (dbInstances.size() + segmentSize - 1) / segmentSize;
            List<String> instances = Stream.iterate(0, i -> i + 1)
                    .limit(limitSize)
                    .map(a -> dbInstances
                            .parallelStream()
                            .map(DescribeDBInstancesResponse.DBInstance::getDBInstanceId)
                            .skip((long) a * segmentSize)
                            .limit(segmentSize)
                            .collect(Collectors.toList()))
                    .filter(b -> !b.isEmpty())
                    .map(d -> String.valueOf(d).replace(" ", "").replace("[", "").replace("]", ""))
                    .collect(Collectors.toList());
            instances.parallelStream()
                    .map(s -> getTowerRdsDetailInfoList(towerClient, s))
                    .forEach(CompletableFuture::join);
            long e = System.currentTimeMillis();
            System.out.println(e - t);
        } finally {
            towerClient.shutdown();
        }
    }

    private CompletableFuture<Void> getTowerRdsDetailInfoList(IAcsClient towerClient, String s) {
        return CompletableFuture.runAsync(() -> {
            DescribeDBInstanceAttributeRequest attributeRequest = new DescribeDBInstanceAttributeRequest();
            attributeRequest.setDBInstanceId(s);
            TestUtil.getTowerResponse(attributeRequest, towerClient);
        }, this.rdsInfoWorkerExecutor);
    }

    /**
     * <b>注意：该值最大为 30 </b>
     *
     * @param total 总数
     * @return 分段数
     */
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

}
