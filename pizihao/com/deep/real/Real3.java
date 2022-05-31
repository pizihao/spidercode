package deep.real;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>1305. 两棵二叉搜索树中的所有元素</h2>
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 *
 * @author Create by liuwenhao on 2022/5/1 13:35
 */
public class Real3 {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // 遍历树
        List<Integer> l1 = list(root1);
        List<Integer> l2 = list(root2);
        List<Integer> list = new ArrayList<>();
        int p1 = 0, p2 = 0;
        while (true) {
            if (p1 == l1.size()) {
                list.addAll(l2.subList(p2, l2.size()));
                break;
            }
            if (p2 == l2.size()) {
                list.addAll(l1.subList(p1, l1.size()));
                break;
            }
            if (l1.get(p1) < l2.get(p2)) {
                list.add(l1.get(p1));
                p1++;
            } else {
                list.add(l2.get(p2));
                p2++;
            }
        }
        return list;
    }

    public List<Integer> list(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>(list(node.left));
        list.add(node.val);
        list.addAll(list(node.right));
        return list;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}