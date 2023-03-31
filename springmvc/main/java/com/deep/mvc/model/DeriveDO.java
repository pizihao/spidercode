package com.deep.mvc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Create by liuwenhao on 2022/9/25 13:35
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("dept")
@NoArgsConstructor
public class DeriveDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * name
     */
    @TableField("name")
    private String name;

    /**
     * address
     */
    @TableField("address")
    private String address;

    public DeriveDO(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
