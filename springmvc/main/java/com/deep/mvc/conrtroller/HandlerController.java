package com.deep.mvc.conrtroller;

import com.deep.mvc.model.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/9/9 15:25
 */
@RestController
@RequestMapping("handler")
public class HandlerController {

    @GetMapping({"/body", "/handler"})
    public Model test(Integer id) {
        System.out.println(111);
        return new Model();
    }

    @PutMapping({"/handler", "/change"})
    public Model handler(Integer id) {
        Model model = new Model();
        model.setId(id);
        System.out.println(222);
        return model;
    }

}