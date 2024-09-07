import java.util.HashSet;

class Solution {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder();  // 결과를 저장할 StringBuilder 객체
        HashSet<Character> seen = new HashSet<>();   // 이미 처리한 문자를 저장할 집합

        for (char c : my_string.toCharArray()) {    // 문자열을 문자 배열로 변환하여 반복
            if (!seen.contains(c)) {                // 문자가 중복되지 않은 경우
                answer.append(c);                   // 결과 문자열에 추가
                seen.add(c);                        // 해당 문자를 처리된 문자 집합에 추가
            }
        }

        return answer.toString();  // 결과 문자열 반환
    }
}
