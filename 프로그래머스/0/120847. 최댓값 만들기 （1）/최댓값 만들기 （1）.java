import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        // 배열을 오름차순으로 정렬
        Arrays.sort(numbers);
        
        // 가장 큰 두 수를 곱한 값을 반환
        int n = numbers.length;
        int maxProduct = numbers[n-1] * numbers[n-2];
        
        return maxProduct;
    }
}
