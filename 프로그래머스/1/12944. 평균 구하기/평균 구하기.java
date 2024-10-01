class Solution {
    public double solution(int[] arr) {
        double sum = 0; // 합계를 저장할 변수
        
        // 배열의 모든 요소를 더함
        for (int num : arr) {
            sum += num;
        }
        
        // 평균값을 계산하고 반환
        double answer = sum / arr.length;
        return answer;
    }
}
