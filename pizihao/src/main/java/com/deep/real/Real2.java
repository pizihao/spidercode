package com.deep.real;

/**
 * <h2>504，七进制数</h2>
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。l
 * 任意一个数n在k进制下的最低位都等于n%k；而除了最低位的部分单独拿出来就是n/k；
 * <p>
 * 如：10的二进制是1010，那么10就是n，2就是k，即1010的最后一位0就是 10%2=0，剩下的101就是10/2=5的二进制数，
 * 以此类推，101最后的1是5%2=1，剩下的10是5/2=4的二进制
 *
 * @author Create by liuwenhao on 2022/5/1 12:30
 */
public class Real2 {

    public static void main(String[] args) {
        Real2 real2 = new Real2();
        real2.convertToBase7(7);
        Integer.toString(10,7);
    }

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        boolean sign = false;
        if (num < 0) {
            sign = true;
            num = -num;
        }
        while (num > 0) {
            builder.append(num % 7);
            num = num / 7;
        }
        return sign ? builder.append("-").reverse().toString() : builder.reverse().toString();
    }
}