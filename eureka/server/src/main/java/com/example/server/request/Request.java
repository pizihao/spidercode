package com.example.server.request;

import com.example.server.model.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request/")
public class Request {

    @PostMapping("/post")
    public void request(@RequestBody Model model) {
        System.out.println(model.getFixParam());
        System.out.println(model.getBizType());
        System.out.println(model.getGoodsIdList());
        System.out.println(model.getSkuIdList());

    }
}
