import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        // 각 문자별 최소 입력 횟수를 저장할 HashMap
        Map<Character, Integer> minKeyPress = new HashMap<>();
        
        // keymap을 순회하면서 각 문자의 최소 입력 횟수를 구한다.
        for (int i = 0; i < keymap.length; i++) {
            String key = keymap[i];
            for (int j = 0; j < key.length(); j++) {
                char c = key.charAt(j);
                // 현재 문자의 최소 입력 횟수를 저장 또는 업데이트
                minKeyPress.put(c, Math.min(minKeyPress.getOrDefault(c, Integer.MAX_VALUE), j + 1));
            }
        }
        
        // 결과를 담을 배열
        int[] answer = new int[targets.length];
        
        // 각 타겟 문자열에 대해 최소 입력 횟수 계산
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int keyPressCount = 0; // 누적된 키 입력 횟수
            boolean isPossible = true; // 문자열을 작성할 수 있는지 여부
            
            for (int j = 0; j < target.length(); j++) {
                char c = target.charAt(j);
                if (minKeyPress.containsKey(c)) {
                    keyPressCount += minKeyPress.get(c); // 해당 문자의 최소 입력 횟수를 더함
                } else {
                    isPossible = false; // 문자를 작성할 수 없음
                    break;
                }
            }
            
            if (isPossible) {
                answer[i] = keyPressCount;
            } else {
                answer[i] = -1; // 작성할 수 없는 경우
            }
        }
        
        return answer;
    }
}
