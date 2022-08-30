package com.deep.pool.conrtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/14 14:12
 */

@RestController
@RequestMapping("Test")
public class TestController {

    @Autowired
    ExecutorService masterExecutorService;

    @PutMapping("/{id}")
    public Model testPut(@RequestBody Model model, @PathVariable Integer id) {
        masterExecutorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        model.setId(id);
        return model;
    }


}