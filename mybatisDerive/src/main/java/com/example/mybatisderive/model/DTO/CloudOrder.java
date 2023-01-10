package com.example.mybatisderive.model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by wang kai tian on 2020/11/18
 */
@Data
public class CloudOrder {

    private String id;//id

    private String brandId;//品牌id

    private String platformId;//平台id

    private String poolId;//资源池id

    private String projectId;//项目id，cmdb 自己数据库表（cloud_project）id

    private String areaId;//区域id，cmdb 自己数据库表（cloud_area）id

    private String availableAreaId;//可用区id，cmdb 自己数据库表（cloud_available_area）id

    private String orderNumber;//订单编号

    private String tenantId;//租户ID

    private Integer tenantAcount;//租户账号

    private String userId;//用户ID

    private String jobId;//job ID，这个来自于华为云，或者阿里云，创建资源的时候只会返回job ID，通过 id 获取资源详情信息

    private String institutionsId;//机构id

    private BigDecimal directoryPrice;//目录价
    private BigDecimal discountPrice;//折扣价

    private String couponsId;//优惠卷ID

    private String activityId;//营销活动ID

    private String mealPrice;//套餐价

//    @Basic
//    @Column(name = "order_direction")
//    private String orderDirection;//当前订单方向
//    @Basic
//    @Column(name = "dispose_type")
//    private String disposeType;//处理类型 group 组、single 单个人
//
//    @Basic
//    @Column(name = "service_process")
//    private String serviceProcess;//服务流程
//
//    @Basic
//    @Column(name = "current_link")
//    private String currentLink;//当前环节
//
//    @Basic
//    @Column(name = "current_dispose_person_id")
//    private String currentDisposePersonId;//当前处理人id

    private String orderType;//订单类型 change_order 变更订单、 new_order 新申请订单、unsubscribe_order 退订订单、renew_order 续订订单

    private String productId;//产品id

    private String status;//状态 （pass 通过、onepending、twopending 代表一级等待、二级、三级，依次类推）

    private String resourceType;//资源类型 vm 虚拟机、disk 磁盘、network 网络、security_group 安全组、eip 公网ip、lsb 负载均衡

    private String custom;//自定义字段

    private LocalDateTime createtime;//创建时间

    private String describe;//描述

    private String orderValue;//订单类容

    private Integer number;//实例数量

    private String orderUuid;//第三方订单id

    private Timestamp updatetime;//更新时间

    private String contractUsed;//第三方订单id

    private BigDecimal totalPrice;//订单总价格，包含续订，变更累加
}
