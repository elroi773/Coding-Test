import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 1️⃣ 크기별 개수 세기
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int t : tangerine) {
            countMap.put(t, countMap.getOrDefault(t, 0) + 1);
        }

        // 2️⃣ 개수만 모아서 내림차순 정렬
        List<Integer> counts = new ArrayList<>(countMap.values());
        counts.sort(Collections.reverseOrder());

        // 3️⃣ 큰 개수부터 더하면서 k개 채우기
        int total = 0;
        int answer = 0;
        for (int c : counts) {
            total += c;
            answer++;
            if (total >= k) break;
        }

        return answer;
    }
}
