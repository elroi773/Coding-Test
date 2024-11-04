import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        // 결과를 저장할 리스트
        List<Integer> result = new ArrayList<>();
        
        // 배열 순회하며 연속된 숫자 제거
        int previous = -1; // 이전 숫자를 저장하기 위한 변수
        for (int num : arr) {
            // 직전 숫자와 다를 경우에만 추가
            if (num != previous) {
                result.add(num);
                previous = num;
            }
        }
        
        // 리스트를 배열로 변환하여 반환
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
