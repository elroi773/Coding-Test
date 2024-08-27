class Solution {
    public int[] solution(int[] num_list) {
        // 짝수와 홀수의 개수를 담을 변수 초기화
        int evenCount = 0;
        int oddCount = 0;
        
        // num_list의 각 원소를 확인
        for (int num : num_list) {
            if (num % 2 == 0) {
                evenCount++; // 짝수이면 evenCount 증가
            } else {
                oddCount++; // 홀수이면 oddCount 증가
            }
        }
        
        // 짝수와 홀수의 개수를 배열에 담아 반환
        int[] answer = {evenCount, oddCount};
        return answer;
    }
}
