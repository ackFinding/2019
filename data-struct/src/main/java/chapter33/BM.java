package chapter33;

/**
 * BM算法
 */
public class BM {

    private static final int SIZE = 256;


    public int bm(char[] target, char[] pattern) {
        int[] bc = new int[SIZE];// 记录模式串中每个字符最后出现的位置
        int pLen = pattern.length;
        int tLen = target.length;
        generateBC(pattern, pLen, bc);
        int[] suffix = new int[pLen];
        boolean[] prefix = new boolean[pLen];
        generateGS(pattern, pLen, suffix, prefix);
        int i = 0; // i 表示主串与模式串对齐的第一个字符
        while (i <= tLen - pLen) {
            int j;
            for (j = pLen - 1; j >= 0; j--) {
                if (target[i + j] != pattern[j]) {// 坏字符对应模式串中的下标是 j
                    break;
                }
            }
            if (j < 0) {
                return i;// 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            // 这里等同于将模式串往后滑动
            int x = j - ((bc[(int) target[i + j]]));
            int y = 0;
            // 如果有好后缀的话（至少一个）
            if (j < pLen - 1) {
                y = moveByGS(j, pLen, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j 表示坏字符对应的模式串中的字符下标
    private int moveByGS(int j, int pLen, int[] suffix, boolean[] prefix) {
        int k = pLen - j - 1;//好后缀长度
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r <= pLen - 1; r++) {
            if (prefix[pLen - r] == true) {
                return r;
            }
        }
        return pLen;
    }

    /**
     * 构建坏字符哈希表
     *
     * @param pattern
     * @param bc
     */
    private void generateBC(char[] pattern, int pLen, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        int ascii;
        for (int i = 0; i < pLen; i++) {
            ascii = (int) pattern[i];//计算 pattern[i] 的 ASCII 值
            bc[ascii] = i;
        }
    }

    /**
     * 好后缀规则
     *
     * @param pattern
     * @param pLen
     * @param suffix
     * @param prefix
     */
    private void generateGS(char[] pattern, int pLen, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < pLen; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < pLen; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && pattern[j] == pattern[pLen - k - 1]) {
                ++k;
                suffix[k] = j;
                --j;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }
}
