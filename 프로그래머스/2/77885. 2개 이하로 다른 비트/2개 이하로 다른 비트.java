class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];
            
            if (x % 2 == 0) {
                // 짝수이면 그냥 +1
                answer[i] = x + 1;
            } else {
                // 홀수이면
                long bit = 1L;
                while ((x & bit) != 0) {
                    bit <<= 1; // 오른쪽에서 첫 0 위치 찾기
                }
                // 그 위치 바로 오른쪽 1비트만 더함
                answer[i] = x + (bit >> 1);
            }
        }
        
        return answer;
    }
}