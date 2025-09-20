import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];

        // 1. 중복 제거
        HashSet<String> reportSet = new HashSet<>(Arrays.asList(report));

        // 2. 신고 관계 저장
        Map<String, HashSet<String>> reportedBy = new HashMap<>();
        for (String id : id_list) {
            reportedBy.put(id, new HashSet<>());
        }

        for (String rep : reportSet) {
            String[] parts = rep.split(" ");
            String reporter = parts[0];
            String target = parts[1];
            reportedBy.get(target).add(reporter);
        }

        // 3. 정지된 유저 찾기
        HashSet<String> banned = new HashSet<>();
        for (String user : id_list) {
            if (reportedBy.get(user).size() >= k) {
                banned.add(user);
            }
        }

        // 4. 메일 횟수 계산
        Map<String, Integer> idIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idIndex.put(id_list[i], i);
        }

        for (String b : banned) {
            for (String reporter : reportedBy.get(b)) {
                answer[idIndex.get(reporter)]++;
            }
        }

        return answer;
    }
}
