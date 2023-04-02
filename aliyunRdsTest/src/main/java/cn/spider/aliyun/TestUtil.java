package cn.spider.aliyun;

import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class TestUtil {

    private static final String rdsAlarmRegionId = "cn-zhangjiakou";
    // F23A2587D34C26ABA0938**********************EC1106E42F3D2899FAD75
    private static final String rdsAlarmAccessKeyId = "";
    // 3F582D8A737C87596219B**********************59F33F15D5E909D44C4A6
    private static final String rdsAlarmSecret = "";

    public static <T extends AcsResponse> T getTowerResponse(AcsRequest<T> request, IAcsClient client) {
        T response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw ServiceException.exception(e);
        } finally {
            client.shutdown();
        }
        return response;
    }

    public static IAcsClient towerClient() {
        DefaultProfile profile = DefaultProfile.getProfile(rdsAlarmRegionId, AesUtils.decrypt(rdsAlarmAccessKeyId, "monitorRdsAlarmId"), AesUtils.decrypt(rdsAlarmSecret, "monitorRdsAlarmSecret"));
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(stopwatch);
    }

}
