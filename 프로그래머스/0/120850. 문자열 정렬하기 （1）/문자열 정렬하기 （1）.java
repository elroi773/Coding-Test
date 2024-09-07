import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(String my_string) {
        ArrayList<Integer> numbers = new ArrayList<>(); // 숫자를 저장할 리스트

        // 문자열을 하나씩 확인하며 숫자인 경우 리스트에 추가
        for (char c : my_string.toCharArray()) {
            if (Character.isDigit(c)) {  // 문자가 숫자인지 확인
                numbers.add(Character.getNumericValue(c)); // 숫자로 변환하여 추가
            }
        }

        // 리스트를 오름차순으로 정렬
        Collections.sort(numbers);

        // 리스트를 배열로 변환하여 반환
        int[] answer = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            answer[i] = numbers.get(i);
        }

        return answer;
    }
}
