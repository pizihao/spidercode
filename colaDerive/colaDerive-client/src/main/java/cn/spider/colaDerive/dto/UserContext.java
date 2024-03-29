package cn.spider.colaDerive.dto;

import lombok.Data;

/**
 * UserContext
 *
 * @author Frank Zhang
 * @date 2019-02-28 7:08 PM
 */
@Data
public class UserContext {
    private String operator;
    private String loginUserId;
    private String loginUserName;
    private String loginUserRole;
    private String loginUserPrivilege;
}
