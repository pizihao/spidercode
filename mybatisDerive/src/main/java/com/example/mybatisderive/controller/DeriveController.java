package com.example.mybatisderive.controller;

import com.example.mybatisderive.model.DO.DeriveDO;
import com.example.mybatisderive.model.DTO.QueryDTO;
import com.example.mybatisderive.service.DeriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/derive")
public class DeriveController {

    final DeriveService deriveService;

    @PostMapping("/save")
    public Integer save(@RequestBody DeriveDO deriveDO) {
        return deriveService.save(deriveDO);
    }

    @GetMapping("/select")
    public List<DeriveDO> select(QueryDTO dto) {
        return deriveService.select(dto);
    }

    @GetMapping("/selectList")
    @Transactional(propagation = Propagation.MANDATORY)
    public List<DeriveDO> select() {
        return null;
    }

}
