class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        // 빈 병이 a개 이상일 때만 교환 가능
        while (n >= a) {
            // 교환해서 얻은 콜라 병 수
            int newBottles = (n / a) * b;
            // 현재 상태에서 남는 빈 병
            int remainingBottles = n % a;
            
            // 받은 콜라 병 수 누적
            answer += newBottles;
            
            // 새로 얻은 빈 병 수로 다시 반복
            n = newBottles + remainingBottles;
        }
        
        return answer;
    }
}
