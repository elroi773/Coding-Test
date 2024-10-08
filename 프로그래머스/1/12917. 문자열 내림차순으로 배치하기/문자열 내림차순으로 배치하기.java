import java.util.Arrays;
import java.util.Collections;

class Solution {
    public String solution(String s) {
        // 문자열을 문자 배열로 변환
        Character[] charArray = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            charArray[i] = s.charAt(i);
        }

        // 배열 정렬: Collections.reverseOrder()를 사용하여 내림차순 정렬
        Arrays.sort(charArray, Collections.reverseOrder());

        // 정렬된 문자 배열을 StringBuilder를 사용하여 문자열로 변환
        StringBuilder answer = new StringBuilder();
        for (char c : charArray) {
            answer.append(c);
        }

        return answer.toString(); // 결과 문자열 반환
    }
}
