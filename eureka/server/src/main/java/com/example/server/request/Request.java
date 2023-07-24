package com.example.server.request;

import com.example.server.model.ModelTest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/request/")
public class Request {

    @PostMapping("/post")
    public void request(@RequestBody ModelTest modelTest) {
        System.out.println(modelTest.getFixParam());
        System.out.println(modelTest.getBizType());
        System.out.println(modelTest.getGoodsIdList());
        System.out.println(modelTest.getSkuIdList());

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

    public static void main(String[] src) {
        List<ModelTest> toBeReturned = new ArrayList<>();

        ModelTest buffer = new ModelTest("25", "25", "25", "25");
        boolean f = true;
        while (f) {
            toBeReturned.add(buffer);
            buffer = new ModelTest("30", "30", "30", "30");
            System.out.println(toBeReturned);
            f = false;
        }
    }
}
