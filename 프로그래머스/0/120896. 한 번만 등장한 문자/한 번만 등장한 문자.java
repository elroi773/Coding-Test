import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public String solution(String s) {
        // 각 문자의 등장 횟수를 저장할 해시맵
        Map<Character, Integer> countMap = new HashMap<>();
        
        // 문자열 s의 각 문자를 순회하며 등장 횟수 카운트
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        
        // 등장 횟수가 한 번인 문자만 리스트에 추가
        ArrayList<Character> uniqueChars = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueChars.add(entry.getKey());
            }
        }
        
        // 리스트를 사전 순으로 정렬
        Collections.sort(uniqueChars);
        
        // 정렬된 문자를 하나의 문자열로 합침
        StringBuilder answer = new StringBuilder();
        for (char c : uniqueChars) {
            answer.append(c);
        }
        
        return answer.toString();
    }
}
