package deep.real;

/**
 * <h2>848. 字母移位</h2>
 * 有一个由小写字母组成的字符串 s，和一个长度相同的整数数组 shifts。
 * 我们将字母表中的下一个字母称为原字母的 移位shift()（由于字母表是环绕的， 'z'将会变成'a'）。
 * 例如，shift('a') = 'b',shift('t') = 'u',以及shift('z') = 'a'。
 * 对于每个shifts[i] = x， 我们会将 s中的前i + 1个字母移位x次。
 * 返回 将所有这些移位都应用到 s 后最终得到的字符串 。
 *
 * @author Create by liuwenhao on 2022/5/7 12:41
 */
public class Real12 {
    public static void main(String[] args) {
        int a = 'a' - 97;
        int z = 'z' - 97;
        System.out.println(a);
        System.out.println(z);

        System.out.println((148291585 - 97) % 26);
        Real12 real12 = new Real12();

        int[] ints = new int[]{505870226, 437526072, 266740649, 224336793, 532917782, 311122363, 567754492, 595798950, 81520022, 684110326, 137742843, 275267355, 856903962, 148291585, 919054234, 467541837, 622939912, 116899933, 983296461, 536563513};
        System.out.println(real12.shiftingLetters("mkgfzkkuxownxvfvxasy", ints));
    }

    public String shiftingLetters(String s, int[] shifts) {
        long count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = shifts.length - 1; i >= 0; i--) {
            count += shifts[i];
            int c = s.charAt(i);
            char c1 = (char) ((c + count - 97) % 26 + 97);
            builder.append(c1);
        }
        return builder.reverse().toString();
    }
}