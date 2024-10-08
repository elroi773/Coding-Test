import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {
        HashSet<Integer> sums = new HashSet<>(); // 중복 제거를 위한 HashSet 사용
        
        // 서로 다른 인덱스의 두 수를 선택하여 합을 구함
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                sums.add(numbers[i] + numbers[j]); // 합을 HashSet에 추가
            }
        }
        
        // HashSet을 배열로 변환하고 정렬
        int[] answer = new int[sums.size()];
        int index = 0;
        for (int sum : sums) {
            answer[index++] = sum;
        }
        
        Arrays.sort(answer); // 오름차순 정렬
        return answer; // 결과 반환
    }
}
