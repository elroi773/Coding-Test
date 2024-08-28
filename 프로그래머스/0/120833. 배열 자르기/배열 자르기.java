import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        // 배열의 일부를 복사하여 반환
        int[] answer = Arrays.copyOfRange(numbers, num1, num2 + 1);
        return answer;
    }
}
