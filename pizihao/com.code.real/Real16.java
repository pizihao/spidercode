import java.util.HashMap;
import java.util.Map;

/**
 * <h2>464. 我能赢吗</h2>
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到或超过100的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * 给定两个整数maxChoosableInteger（整数池中可选择的最大数）和desiredTotal（累计和），
 * 若先出手的玩家是否能稳赢则返回 true，否则返回 false 。
 * 假设两位玩家游戏时都表现 最佳 。
 *
 * @author Create by liuwenhao on 2022/5/22 17:37
 */
public class Real16 {
    public static void main(String[] args) {
        Real16 real16 = new Real16();
        System.out.println(real16.canIWin(3, 4));
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // maxChoosableInteger的和
        if ((maxChoosableInteger + 1) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        /*
            通过一个二进制数和一个布尔值标记某个数字是否被使用过：
            本题中 maxChoosableInteger = 20；即map最大有20个元素
            所以该数字有20位，0001表示1被用了，00010表示2被用了。
            如果想要将数字5置为已使用则：0 | (1 << 4) = 10000
            如果想要判断数字5是否已经被使用则：(10000 >> 4) & 1 = 1
            注意是用上一位判断当前位，或者用当前位判断下一位
         */
        Map<Integer, Boolean> map = new HashMap<>();
        return getWinner(map, 0, maxChoosableInteger, desiredTotal, 0);
    }

    private boolean getWinner(Map<Integer, Boolean> map,
                              Integer signNum,
                              int maxChoosableInteger,
                              int desiredTotal,
                              int currentTotal) {

        if (!map.containsKey(signNum)) {
            boolean result = false;
            for (int i = 0; i < maxChoosableInteger; i++) {
                if ((signNum >> i & 1) == 0) {
                    // '我'选的
                    int total = i + 1 + currentTotal;
                    if (total >= desiredTotal) {
                        result = true;
                        break;
                    }
                    // 如果'我'选完没有赢
                    if (!getWinner(map, signNum | (1 << i), maxChoosableInteger, desiredTotal, total)) {
                        result = true;
                        break;
                    }
                }
            }
            map.put(signNum, result);
        }
        return map.get(signNum);
    }

}