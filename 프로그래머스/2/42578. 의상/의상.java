import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        // 종류별 개수 세기
        for (String[] c : clothes) {
            String kind = c[1];
            map.put(kind, map.getOrDefault(kind, 0) + 1);
        }

        int answer = 1;

        // (개수 + 1)씩 곱하기
        for (int count : map.values()) {
            answer *= (count + 1);
        }

        // 아무것도 안 입은 경우 제외
        return answer - 1;
    }
}
