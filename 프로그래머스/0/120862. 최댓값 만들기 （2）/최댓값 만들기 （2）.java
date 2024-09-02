import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        // 배열을 오름차순으로 정렬
        Arrays.sort(numbers);
        
        // 배열에서 가장 큰 두 숫자를 곱한 값
        int max1 = numbers[numbers.length - 1] * numbers[numbers.length - 2];
        
        // 배열에서 가장 작은 두 숫자를 곱한 값
        int max2 = numbers[0] * numbers[1];
        
        // 위의 두 값 중 더 큰 값을 반환
        return Math.max(max1, max2);
    }
}
