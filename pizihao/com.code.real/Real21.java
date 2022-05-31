/**
 * 17.11. 单词距离
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * @author Create by liuwenhao on 2022/5/27 22:14
 */
public class Real21 {
    public static void main(String[] args) {

    }

    public int findClosest(String[] words, String word1, String word2) {
        int wordIndex1 = -1;
        int wordIndex2 = -1;
        int distance = words.length;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                wordIndex1 = i;
            } else if (word.equals(word2)) {
                wordIndex2 = i;
            }
            if (wordIndex1 > -1 && wordIndex2 > -1) {
                distance = Math.min(distance, Math.abs(wordIndex1 - wordIndex2));
            }
        }
        return distance;
    }
}