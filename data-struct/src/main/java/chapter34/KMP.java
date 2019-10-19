package chapter34;

public class KMP {

    public static int kmp(char[] target, char[] pattern) {
        int pLen = pattern.length;
        int tLen = target.length;
        int[] next = getNexts(pattern, pLen);
        int j = 0;
        for (int i = 0; i < tLen; i++) {
            while (j > 0 && target[i] != pattern[j]) {
                j = next[j - 1] + 1;
            }
            if (target[i] == pattern[j]) {
                ++j;
            }
            if (j == pLen) {
                return i - pLen + 1;
            }
        }
        return -1;
    }

    private static int[] getNexts(char[] pattern, int pLen) {
        int[] next = new int[pLen];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < pLen; i++) {
            while (k!=-1 && pattern[k+1]!=pattern[i]){
                k = next[k];
            }
            if (pattern[k + 1] == pattern[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}
