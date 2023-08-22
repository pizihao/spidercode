package com.example.druidtest.conrtroller;

import com.example.druidtest.mapper.DeriveMapper;
import com.example.druidtest.model.DeriveDO;
import com.example.druidtest.model.Model;
import com.example.druidtest.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;

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
    @Autowired
    DataSource dataSource;

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

    @GetMapping("/testTime")
    public long testTime() {
        String sql = "SELECT * FROM `dept`;";
        String sql1 = "SHOW TABLE STATUS LIKE 'dept';";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("EXPLAIN " + sql);
            // 统计每个步骤所需的时间
            long scanRows = 0;
            long ioOperations = 0;
            while (rs.next()) {
                String type = rs.getString("type");
                long rows = rs.getLong("rows");
                if ("range".equals(type) || "ref".equals(type) || "index".equals(type)) {
                    scanRows += rows;
                }
                ioOperations += rows;
            }
            // 计算预估的 SQL 执行时间
            long estimatedTime = (scanRows * 10 + ioOperations * 50) / 1000;
            System.out.println("Estimated execution time: " + estimatedTime);
            return estimatedTime;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
    }

    @GetMapping("/async")
    public String async() {
        deriveMapper.async();
                   //           1000000000L
        LockSupport.parkNanos(600000000000L);

        return "test";
    }

    public static void main(String[] args) {
        String s = "?????.xlsx";
        System.out.println(s.lastIndexOf("."));

        System.out.println(s.substring(0,s.lastIndexOf(".")));
        System.out.println(s.substring(s.lastIndexOf(".")));
    }
}