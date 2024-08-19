class Solution {
    public double solution(int[] numbers) {
        int sum = 0;
        
        // numbers 배열의 모든 원소 합산
        for (int num : numbers) {
            sum += num;
        }
        
        // 평균 계산
        double answer = (double) sum / numbers.length;
        
        return answer;
    }
}
