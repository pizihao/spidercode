package com.deep.mvc.conrtroller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/14 14:12
 */

@RestController
@RequestMapping("Test")
public class TestController {

    @GetMapping(value = "/{id}")
    public DeferredResult<String> test(@PathVariable String id,HttpServletRequest request) throws InterruptedException {
        DeferredResult<String> result = new DeferredResult<>();
        result.onCompletion(() -> System.out.println("调用完成"));
//        new Thread(() -> result.setResult("handler")).start();
        return result;
    }

    @GetMapping("/body")
    public Model test(@RequestBody Model id){
        return id;
    }

    @PutMapping("/{id}")
    public Model testPut(@RequestBody Model model, @PathVariable Integer id){
        model.setId(id);
        return model;
    }


}