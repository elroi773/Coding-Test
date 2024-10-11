import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        // 나누어 떨어지는 요소를 저장할 리스트 생성
        ArrayList<Integer> list = new ArrayList<>();

        // arr의 각 요소를 검사
        for (int num : arr) {
            if (num % divisor == 0) {
                list.add(num); // 나누어 떨어지면 리스트에 추가
            }
        }

        // 리스트가 비어있다면 -1을 담아 반환
        if (list.isEmpty()) {
            return new int[]{-1};
        }

        // 리스트를 오름차순으로 정렬
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        Arrays.sort(answer); // 정렬
        return answer; // 결과 반환
    }
}
