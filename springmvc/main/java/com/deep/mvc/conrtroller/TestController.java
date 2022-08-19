package com.deep.mvc.conrtroller;

import org.springframework.web.bind.annotation.*;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/14 14:12
 */

@RestController
@RequestMapping("Test")
public class TestController {

    @GetMapping("/{id}")
    public String test(@PathVariable String id){
        return id;
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