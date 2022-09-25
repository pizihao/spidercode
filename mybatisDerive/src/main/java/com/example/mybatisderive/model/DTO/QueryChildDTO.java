package com.example.mybatisderive.model.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Create by liuwenhao on 2022/9/25 14:30
 */
@Getter
@Setter
public class QueryChildDTO <T extends Comparable<? super T>>  {

    T id;

    String name;
}
