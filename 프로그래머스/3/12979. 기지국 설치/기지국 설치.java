class Solution {
    public int solution(int n, int[] stations, int w) {
        long answer = 0;
        long cover = 2L * w + 1;   // 기지국 1개가 커버하는 길이
        long position = 1;         // 아직 커버 안 된 시작 아파트(1-based)

        for (int s : stations) {
            long left = (long) s - w;   // 이 기지국 커버 시작
            long right = (long) s + w;  // 이 기지국 커버 끝

            // position ~ (left-1) 구간이 비어있으면 채우기
            if (position < left) {
                long gap = left - position;
                // ceil(gap / cover)
                answer += (gap + cover - 1) / cover;
            }

            // 이 기지국이 커버하는 구간 다음으로 이동
            position = Math.max(position, right + 1);

            if (position > n) break;
        }

        // 마지막 기지국 이후 남은 구간 처리
        if (position <= n) {
            long gap = (long) n - position + 1;
            answer += (gap + cover - 1) / cover;
        }

        return (int) answer;
    }
}