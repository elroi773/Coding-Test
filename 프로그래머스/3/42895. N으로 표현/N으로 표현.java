import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;

        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) dp.add(new HashSet<>());

        for (int i = 1; i <= 8; i++) {
            // 이어붙인 수: N, NN, NNN...
            int concat = 0;
            for (int k = 0; k < i; k++) concat = concat * 10 + N;
            dp.get(i).add(concat);

            // j + (i-j)로 분할해서 조합
            for (int j = 1; j < i; j++) {
                Set<Integer> A = dp.get(j);
                Set<Integer> B = dp.get(i - j);

                for (int a : A) {
                    for (int b : B) {
                        dp.get(i).add(a + b);
                        dp.get(i).add(a - b);
                        dp.get(i).add(a * b);
                        if (b != 0) dp.get(i).add(a / b); // 정수 나눗셈(나머지 버림)
                    }
                }
            }

            if (dp.get(i).contains(number)) return i;
        }

        return -1;
    }
}