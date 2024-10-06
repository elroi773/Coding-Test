class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int sum = 0;  // 실제 정수들의 합을 저장할 변수
        
        for (int i = 0; i < absolutes.length; i++) {
            if (signs[i]) {
                sum += absolutes[i];  // signs[i]가 true이면 양수로 더함
            } else {
                sum -= absolutes[i];  // signs[i]가 false이면 음수로 더함
            }
        }
        
        return sum;  // 최종 합 반환
    }
}
