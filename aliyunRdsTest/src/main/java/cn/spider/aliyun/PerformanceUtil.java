package cn.spider.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancePerformanceRequest;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancePerformanceResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PerformanceUtil {
    /**
     * RDS的键值
     */
    public static final String MYSQL_MEM_CPU_USAGE = "MySQL_MemCpuUsage";

    public static final String MYSQL_SESSIONS = "MySQL_Sessions";

    public static final String MYSQL_IOPS = "MySQL_IOPS";

    public static final String MYSQL_SPACE_USAGE = "MySQL_DetailedSpaceUsage";
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String id = "rm-k2ji2j1f08i965835";

    public static void main(String[] args) throws ClientException {
        IAcsClient towerClient = TestUtil.towerClient();
        // 检测键值
        String checkKey = String.format("%s,%s,%s,%s", MYSQL_MEM_CPU_USAGE, MYSQL_SESSIONS, MYSQL_IOPS, MYSQL_SPACE_USAGE);
        // 检测时间
        long nowTimeStamp = System.currentTimeMillis();
        String endTime = toUtcTime(nowTimeStamp);
        String startTime = toUtcTime(nowTimeStamp - 5 * 60 * 1000);

        DescribeDBInstancePerformanceRequest request = new DescribeDBInstancePerformanceRequest();
        request.setDBInstanceId(id);
        request.setStartTime(startTime);
        request.setEndTime(endTime);
        request.setKey(checkKey);

        DescribeDBInstancePerformanceResponse ret = towerClient.getAcsResponse(request);
        System.out.println(JSON.toJSONString(ret));
    }

    public static String long2DateStr(long timeStamp) {
        Date date = new Date(timeStamp);
        return sDateFormat.format(date);
    }

    public static String toUtcTime(Long time) {
        // 相差时间
        long toUtcTime = 28800000L;
        time -= toUtcTime;
        // UTC时间格式
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat minTime = new SimpleDateFormat("HH:mm");
        // 时间格式转换成（2019-09-16T03:10Z）
        return dayTime.format(new Date(time)) + "T" + minTime.format(new Date(time)) + "Z";
    }

}
