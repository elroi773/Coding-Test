class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        for (int i = left; i <= right; i++) {
            // i가 제곱수인지 확인
            if (Math.sqrt(i) == (int)Math.sqrt(i)) {
                answer -= i;
            } else {
                answer += i;
            }
        }
        
        return answer;
    }
}
