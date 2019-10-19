package chapter35;

public class Trie {

    private TreeNode root = new TreeNode('/');

    public void insert(char[] text) {
        TreeNode p = root;

        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                p.children[index] = new TreeNode(text[i]);
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] text) {
        TreeNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        if (!p.isEndingChar) {//// 不能完全匹配，只是前缀
            return false;
        }
        return true;
    }

    class TreeNode {

        public char data;

        public TreeNode[] children = new TreeNode[26];

        public boolean isEndingChar = false;

        public TreeNode(char data) {
            this.data = data;
        }
    }
}
