class Solution {

    // level로 모든 퍼즐을 제한 시간 내에 풀 수 있는지 계산하는 함수
    private long calcTime(int[] diffs, int[] times, int level, long limit) {
        long total = 0;

        // 첫 퍼즐은 무조건 times[0]
        total += times[0];
        if (total > limit) return total;

        for (int i = 1; i < diffs.length; i++) {
            if (level >= diffs[i]) {
                total += times[i];
            } else {
                long fail = (long)(diffs[i] - level);
                total += fail * (times[i] + times[i - 1]) + times[i];
            }

            // 이미 넘어가면 더 볼 필요 없음 → 시간 최적화
            if (total > limit) return total;
        }

        return total;
    }

    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;

        // 최대 난이도 찾기 (이분 탐색 상한선)
        int maxDiff = 0;
        for (int d : diffs) {
            if (d > maxDiff) maxDiff = d;
        }

        int left = 1;
        int right = maxDiff;
        int answer = maxDiff;

        // 이분 탐색으로 최소 level 찾기
        while (left <= right) {
            int mid = (left + right) / 2;

            long t = calcTime(diffs, times, mid, limit);

            if (t <= limit) {
                answer = mid;
                right = mid - 1;   // 더 낮은 level 가능성 탐색
            } else {
                left = mid + 1;    // level 더 높여야 함
            }
        }

        return answer;
    }
}
