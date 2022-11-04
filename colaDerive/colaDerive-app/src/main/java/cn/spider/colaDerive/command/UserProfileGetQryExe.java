package cn.spider.colaDerive.command;

import com.alibaba.cola.dto.SingleResponse;
import cn.spider.colaDerive.dto.UserProfileGetQry;
import cn.spider.colaDerive.dto.UserProfileCO;
import cn.spider.colaDerive.gatewayimpl.rpc.UserProfileMapper;
import cn.spider.colaDerive.gatewayimpl.rpc.UserProfileDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileGetQryExe {

    @Resource
    private UserProfileMapper userProfileMapper;

    public SingleResponse<UserProfileCO> execute(UserProfileGetQry qry) {
        UserProfileDO userProfileDO = userProfileMapper.getByUserId(qry.getUserId());
        UserProfileCO userProfileCO = new UserProfileCO();
        BeanUtils.copyProperties(userProfileDO, userProfileCO);
        return SingleResponse.of(userProfileCO);
    }

}
