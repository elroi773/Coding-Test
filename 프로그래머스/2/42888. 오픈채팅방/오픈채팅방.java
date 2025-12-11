import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> user = new HashMap<>(); // uid → nickname 저장

        // 1) 마지막 닉네임 저장
        for (String rec : record) {
            String[] parts = rec.split(" ");
            String action = parts[0];
            String uid = parts[1];

            if (action.equals("Enter") || action.equals("Change")) {
                String nickname = parts[2];
                user.put(uid, nickname);
            }
        }

        // 2) 메시지 출력 (Enter, Leave만)
        List<String> result = new ArrayList<>();

        for (String rec : record) {
            String[] parts = rec.split(" ");
            String action = parts[0];
            String uid = parts[1];

            if (action.equals("Enter")) {
                result.add(user.get(uid) + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                result.add(user.get(uid) + "님이 나갔습니다.");
            }
        }

        return result.toArray(new String[0]);
    }
}
