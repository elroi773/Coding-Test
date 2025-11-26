import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 1. 원하는 상품과 수량을 Map으로 저장
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 2. 할인 목록을 10일씩 체크
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> windowMap = new HashMap<>();

            // 10일 동안 상품 수량 카운트
            for (int j = i; j < i + 10; j++) {
                windowMap.put(discount[j], windowMap.getOrDefault(discount[j], 0) + 1);
            }

            // 3. Map 비교
            if (windowMap.equals(wantMap)) {
                answer++;
            }
        }

        return answer;
    }
}
