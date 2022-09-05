package com.deep.mvc.conrtroller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/14 14:12
 */

@RestController
@RequestMapping("Test")
public class TestController {

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

    @RequestMapping(
        name = "requestMappingTest",
        value = "/request",
        headers = "content-type=text/*",
        consumes = "text/plain",
        method = RequestMethod.GET,
        params = "myParam=myValue",
        path = "/request",
        produces = "application/*"
    )
    public Model requestMappingTest(@RequestParam String name) {
        return new Model();
    }

    @GetMapping(value = "/order",produces = "test/plain")
    public Order test(@RequestBody Order order){
        return order;
    }


}