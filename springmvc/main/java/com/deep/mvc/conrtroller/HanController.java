package com.deep.mvc.conrtroller;

import com.deep.mvc.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/9/10 17:11
 */
@RestController
@RequestMapping("hand")
public class HanController {

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


    @PutMapping({"/handler", "/change","/moreHanlder"})
    public Model moreHandler(Integer id) {
        Model model = new Model();
        model.setId(id);
        System.out.println(222);
        return model;
    }

}