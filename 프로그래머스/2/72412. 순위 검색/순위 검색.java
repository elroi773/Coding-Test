import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> db = new HashMap<>();

        // 1. info 전처리: 각 지원자에 대해 2^4 = 16가지 조합의 key에 점수 추가
        for (String s : info) {
            String[] parts = s.split(" ");
            String[] attrs = new String[] { parts[0], parts[1], parts[2], parts[3] };
            int score = Integer.parseInt(parts[4]);

            // 0000 ~ 1111 (0 ~ 15)
            for (int mask = 0; mask < 16; mask++) {
                StringBuilder keyBuilder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if ((mask & (1 << i)) != 0) {
                        keyBuilder.append("-");   // 해당 속성 무시
                    } else {
                        keyBuilder.append(attrs[i]);
                    }
                    if (i < 3) keyBuilder.append(" ");
                }
                String key = keyBuilder.toString();
                db.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            }
        }

        // 2. 각 key별 점수 리스트 정렬
        for (List<Integer> list : db.values()) {
            Collections.sort(list);
        }

        // 3. query 처리
        int[] answer = new int[query.length];
        for (int qi = 0; qi < query.length; qi++) {
            String q = query[qi].replaceAll(" and ", " ");
            String[] parts = q.split(" ");

            String key = parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3];
            int score = Integer.parseInt(parts[4]);

            List<Integer> list = db.getOrDefault(key, Collections.emptyList());
            if (list.isEmpty()) {
                answer[qi] = 0;
                continue;
            }

            // score 이상인 사람 수 = 전체 길이 - lowerBound 위치
            int idx = lowerBound(list, score);
            answer[qi] = list.size() - idx;
        }

        return answer;
    }

    // list에서 target 이상이 처음 나오는 인덱스 (lower bound)
    private int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size(); // [left, right)

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}