package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.Response;
import cn.spider.colaDerive.gatewayimpl.rpc.UserProfileConvertor;
import cn.spider.colaDerive.domain.user.UserProfile;
import cn.spider.colaDerive.dto.UserProfileUpdateCmd;
import cn.spider.colaDerive.domain.user.UserProfileGateway;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileUpdateCmdExe{

    @Resource
    private UserProfileGateway userProfileGateway;

    public Response execute(UserProfileUpdateCmd cmd) {
        UserProfile userProfile = UserProfileConvertor.toEntity(cmd.getUserProfileCO());
        userProfileGateway.update(userProfile);
        return Response.buildSuccess();
    }
}