import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        // 알파벳 중 skip에 포함되지 않은 알파벳을 리스트로 만든다.
        List<Character> validChars = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (!skip.contains(String.valueOf(c))) {
                validChars.add(c);
            }
        }
        
        // 결과를 저장할 StringBuilder
        StringBuilder answer = new StringBuilder();
        
        // 각 문자에 대해 변환을 수행
        for (char c : s.toCharArray()) {
            // 현재 문자가 validChars에서 몇 번째 인덱스인지 찾음
            int currentIdx = validChars.indexOf(c);
            // index만큼 뒤의 문자를 구함 (순환 처리)
            int newIdx = (currentIdx + index) % validChars.size();
            // 변환된 문자 추가
            answer.append(validChars.get(newIdx));
        }
        
        return answer.toString();
    }
}
