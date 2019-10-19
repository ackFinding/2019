package chapter32;

public class StringMatcher {

    /**
     * bf算法（暴力匹配算法）
     * @param str
     * @param pattern
     * @return
     */
    public int bf(String str, String pattern) {
        char[] target = str.toCharArray();
        char[] p = pattern.toCharArray();
        int count = 0;
        int tLen = target.length;
        int pLen = p.length;
        for (int i = 0; i < (tLen - pLen + 1); i++) {
            count = 0;
            for (int j = 0; j < pLen; j++) {
                if (target[i + j] == p[j]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == p.length) {
                return i;
            }
        }
        return -1;
    }

    /**
     * RK算法
     * @param str
     * @param pattern
     * @return
     */
    public int rk(String str, String pattern) {
        char[] target = str.toCharArray();
        char[] p = pattern.toCharArray();
        int n = target.length;
        int m = p.length;
        int[] p26 = new int[m];
        //缓存26进制
        int sum = 1;
        for (int k = 0; k < p26.length; k++) {
            p26[k] = sum;
            sum *= 26;
        }
        //计算主串中各个子串hash
        int[] hash = new int[n - m + 1];
        for (int i = 0; i < n - m + 1; i++) {
            sum = 0;
            for (int j = 0; j < m; j++) {
                sum += (target[j + i] - 'a') * p26[m-j-1];
            }
            hash[i] = sum;
        }
        //计算模式串hash,和主串比较
        sum = 0;
        for (int i = 0; i < m; i++) {
            sum += (p[i] - 'a') * p26[m-i-1];
        }
        for (int i = 0; i < hash.length; i++) {
            //如果出现hash冲突，还需要继续判断对比子串和模式串是否相等
            if (sum == hash.length) {
                return i;
            }
        }
        return -1;
    }
}
