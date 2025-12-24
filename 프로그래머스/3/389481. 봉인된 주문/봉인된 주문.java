import java.util.*;

class Solution {

    static long[] pow26 = new long[12];

    // 문자열 → 순서 번호 (1-based)
    static long getRank(String s) {
        int L = s.length();
        long r = 0;

        // 더 짧은 문자열 개수
        for (int l = 1; l < L; l++) {
            r += pow26[l];
        }

        // 같은 길이에서 사전순
        for (int i = 0; i < L; i++) {
            r += (s.charAt(i) - 'a') * pow26[L - i - 1];
        }

        return r + 1;
    }

    // 순서 번호 → 문자열
    static String getString(long k) {
        int L = 1;
        while (k > pow26[L]) {
            k -= pow26[L];
            L++;
        }

        k--; // 0-based
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < L; i++) {
            long div = pow26[L - i - 1];
            sb.append((char) ('a' + (k / div)));
            k %= div;
        }

        return sb.toString();
    }

    public String solution(long n, String[] bans) {

        // 26의 거듭제곱 미리 계산
        pow26[0] = 1;
        for (int i = 1; i <= 11; i++) {
            pow26[i] = pow26[i - 1] * 26;
        }

        // bans → rank 배열
        long[] banned = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banned[i] = getRank(bans[i]);
        }

        // 정렬
        Arrays.sort(banned);

        // n 보정
        long target = n;
        for (long r : banned) {
            if (r <= target) {
                target++;
            } else {
                break;
            }
        }

        // 최종 문자열 반환
        return getString(target);
    }
}
