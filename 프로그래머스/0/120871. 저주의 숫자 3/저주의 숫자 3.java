class Solution {
    public int solution(int n) {
        int count = 0;  // 3x 마을에서 사용할 수 있는 숫자의 개수를 셈
        int current = 0;  // 현재 숫자를 추적
        
        while (count < n) {
            current++;
            
            // 숫자에 3이 포함되어 있거나 3의 배수인 경우 스킵
            if (current % 3 == 0 || String.valueOf(current).contains("3")) {
                continue;
            }
            
            count++;  // 3x 마을에서 사용할 수 있는 숫자일 경우에만 카운트 증가
        }
        
        return current;  // n번째 3x 마을 숫자 반환
    }
}
