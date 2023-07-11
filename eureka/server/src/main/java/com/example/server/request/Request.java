package com.example.server.request;

import com.example.server.model.Model;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/sync")
    public int sync() {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(4);
        });

        return 4;
    }
}
