import java.util.*;

class Solution {

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        // 주문들을 미리 알파벳 순으로 정렬해 둔다
        String[] sortedOrders = new String[orders.length];
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            sortedOrders[i] = new String(arr);
        }

        // 각 코스 길이에 대해 처리
        for (int c : course) {
            Map<String, Integer> countMap = new HashMap<>();
            int maxCount = 0;

            // 각 주문에서 길이 c짜리 조합 생성
            for (String order : sortedOrders) {
                if (order.length() < c) continue;

                makeCombinations(order.toCharArray(), 0, c,
                        new StringBuilder(), countMap);
            }

            // 이번 코스 길이에서 가장 많이 주문된(>=2번) 메뉴 찾기
            for (int cnt : countMap.values()) {
                if (cnt >= 2 && cnt > maxCount) {
                    maxCount = cnt;
                }
            }

            if (maxCount < 2) continue; // 최소 2명 이상 조건

            // 최대 빈도수를 가진 조합들만 결과에 추가
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == maxCount) {
                    result.add(entry.getKey());
                }
            }
        }

        // 사전 순 정렬
        Collections.sort(result);

        return result.toArray(new String[0]);
    }

    // 조합 생성: 길이 targetLen인 모든 조합을 만들고 countMap에 카운트
    private void makeCombinations(char[] arr, int start, int targetLen,
                                  StringBuilder sb, Map<String, Integer> countMap) {
        if (sb.length() == targetLen) {
            String key = sb.toString();
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            sb.append(arr[i]);
            makeCombinations(arr, i + 1, targetLen, sb, countMap);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}