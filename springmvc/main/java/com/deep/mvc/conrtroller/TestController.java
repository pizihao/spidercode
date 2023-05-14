package com.deep.mvc.conrtroller;

import com.deep.mvc.mapper.DeriveMapper;
import com.deep.mvc.model.DeriveDO;
import com.deep.mvc.model.Model;
import com.deep.mvc.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/DeferredResult")
    public DeferredResult<String> testDeferredResult() {
        System.out.println(Thread.currentThread().getName());
        DeferredResult<String> result = new DeferredResult<>(10000L, () -> {
            System.out.println("超时了");
            return "A + B";
        });
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result.setResult("a + b");
        }).start();
        return result;
    }

    public static void main(String[] args) throws UnknownHostException {

        String address = InetAddress.getLocalHost().getHostAddress();
        System.out.println(address);
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

//        deriveMapper.add(list);

        deriveMapper.insert(new DeriveDO("1", "2"));

        return "test";
    }
}