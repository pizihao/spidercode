package com.example.druidtest.conrtroller;

import com.example.druidtest.mapper.DeriveMapper;
import com.example.druidtest.model.DeriveDO;
import com.example.druidtest.model.Model;
import com.example.druidtest.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/14 14:12
 */

@RestController
@RequestMapping("Test")
public class TestController {

    @Autowired
    DeriveMapper deriveMapper;

    @GetMapping(value = "/{id}")
    public Callable<String> test(@PathVariable String id, HttpServletRequest request) throws InterruptedException {
        return () -> "handler";
    }

    @GetMapping("/body")
    public Model test(@RequestBody Model id) {
        return id;
    }

    @PutMapping("/{id}")
    public Model testPut(@RequestBody Model model, @PathVariable Integer id) {
        model.setId(id);
        return model;
    }

    //    @RequestMapping(
//        name = "requestMappingTest",
//        value = "/request",
//        headers = "content-type=text/*",
//        consumes = "text/plain",
//        method = RequestMethod.GET,
//        params = "myParam=myValue",
//        path = "/request",
//        produces = "application/*"
//    )
//    public Model requestMappingTest(@RequestParam String name) {
//        return new Model();
//    }
//
    @PutMapping(value = {"/order", "/begin-order"})
    public Order test(@RequestBody Order order) {
        System.out.println(order);
        return order;
    }

    @GetMapping("/test")
    public String test() {

        List<DeriveDO> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(new DeriveDO(String.valueOf(i), String.valueOf(i + System.currentTimeMillis())));
        }

        deriveMapper.add(list);


        return "test";
    }
}