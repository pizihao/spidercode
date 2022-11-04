package cn.spider.colaDerive.domain.user;

import cn.spider.colaDerive.domain.user.UserProfile;

public class DomainFactory {

    public static UserProfile getUserProfile(){
        return new UserProfile();
    }

}
