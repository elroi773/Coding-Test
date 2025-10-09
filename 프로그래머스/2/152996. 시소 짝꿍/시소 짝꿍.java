import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Long> map = new HashMap<>();

        // 1️⃣ 각 몸무게 등장 횟수 세기
        for (int w : weights) {
            map.put(w, map.getOrDefault(w, 0L) + 1);
        }

        // 2️⃣ 정수 연산을 위한 분수 형태의 비율들
        int[][] ratios = {
            {1, 1},  // same
            {3, 2},  // 1.5
            {2, 1},  // 2.0
            {4, 3}   // 1.333...
        };

        // 3️⃣ 모든 몸무게 w에 대해 가능한 짝 계산
        for (int w : map.keySet()) {
            long countW = map.get(w);

            // 같은 무게끼리 짝꿍 (조합 nC2)
            if (countW > 1) {
                answer += (countW * (countW - 1)) / 2;
            }

            // 다른 비율로 짝꿍 찾기
            for (int i = 1; i < ratios.length; i++) { // ratio[0]은 1:1이므로 제외
                int num = ratios[i][0];
                int den = ratios[i][1];

                if ((w * num) % den != 0) continue; // 정수 아니면 skip
                int target = (w * num) / den;

                if (map.containsKey(target)) {
                    answer += countW * map.get(target);
                }
            }
        }

        return answer;
    }
}
