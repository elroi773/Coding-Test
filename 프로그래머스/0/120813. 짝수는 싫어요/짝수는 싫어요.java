class Solution {
    public int[] solution(int n) {
        // n 이하의 홀수의 개수를 계산
        int size = (n + 1) / 2;
        int[] answer = new int[size];
        
        // 홀수 채우기
        int index = 0;
        for (int i = 1; i <= n; i += 2) {
            answer[index++] = i;
        }
        
        return answer;
    }
}
