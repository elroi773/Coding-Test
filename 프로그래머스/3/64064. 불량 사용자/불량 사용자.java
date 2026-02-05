import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        // banned_id 각 패턴에 매칭되는 user 인덱스 후보 목록
        List<List<Integer>> candidates = new ArrayList<>();
        for (String ban : banned_id) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < user_id.length; i++) {
                if (match(user_id[i], ban)) list.add(i);
            }
            candidates.add(list);
        }

        // 순서 무관 집합을 비트마스크로 저장(유저 최대 8명)
        Set<Integer> results = new HashSet<>();
        dfs(0, 0, candidates, results);

        return results.size();
    }

    private void dfs(int idx, int usedMask, List<List<Integer>> candidates, Set<Integer> results) {
        if (idx == candidates.size()) {
            results.add(usedMask);
            return;
        }
        for (int ui : candidates.get(idx)) {
            int bit = 1 << ui;
            if ((usedMask & bit) != 0) continue; // 이미 사용한 유저
            dfs(idx + 1, usedMask | bit, candidates, results);
        }
    }

    private boolean match(String user, String ban) {
        if (user.length() != ban.length()) return false;
        for (int i = 0; i < ban.length(); i++) {
            char b = ban.charAt(i);
            if (b == '*') continue;
            if (user.charAt(i) != b) return false;
        }
        return true;
    }
}