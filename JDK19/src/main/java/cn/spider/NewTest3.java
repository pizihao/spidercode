package cn.spider;

import java.util.function.Function;

public class NewTest3 {

    public static void main(String[] args) {
        var a = "a";

        Function function = (var o) -> {
            System.out.println(o);
            return o.getClass();
        };

    }
}
