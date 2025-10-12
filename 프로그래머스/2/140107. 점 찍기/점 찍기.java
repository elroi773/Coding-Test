class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        for (long x = 0; x <= d; x += k) {
            // 가능한 y의 최대 거리 계산 (원 내부 조건)
            long maxY = (long) Math.sqrt((long) d * d - x * x);
            // y는 0, k, 2k, ... maxY 이하까지 가능하므로 개수는 (maxY / k) + 1
            answer += (maxY / k) + 1;
        }

        return answer;
    }
}