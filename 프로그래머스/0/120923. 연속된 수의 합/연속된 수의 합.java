class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        
        // 중간값을 구합니다.
        int mid = total / num;
        
        // 첫 번째 숫자를 계산합니다.
        int start = mid - (num - 1) / 2;
        
        // 연속된 숫자들을 배열에 채웁니다.
        for (int i = 0; i < num; i++) {
            answer[i] = start + i;
        }
        
        return answer;
    }
}
