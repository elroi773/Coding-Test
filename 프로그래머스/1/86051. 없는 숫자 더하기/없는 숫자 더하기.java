class Solution {
    public int solution(int[] numbers) {
        int totalSum = 45;  // 0부터 9까지 숫자의 합
        int numbersSum = 0;  // numbers 배열에 있는 숫자들의 합
        
        // numbers 배열의 숫자들을 모두 더함
        for (int num : numbers) {
            numbersSum += num;
        }
        
        // 없는 숫자들의 합은 totalSum에서 numbersSum을 뺀 값
        return totalSum - numbersSum;
    }
}
