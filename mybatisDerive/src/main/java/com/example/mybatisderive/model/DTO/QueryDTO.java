package com.example.mybatisderive.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Create by liuwenhao on 2022/9/25 13:50
 */
@Getter
@Setter
public class QueryDTO{

    QueryChildDTO<LocalDateTime> dto;

}
