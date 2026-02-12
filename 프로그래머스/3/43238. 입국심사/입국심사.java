import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long left = 1L;
        long right = (long) times[times.length - 1] * (long) n; // 최악의 경우
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long processed = 0L;
            for (int t : times) {
                processed += mid / (long) t;
                if (processed >= n) break; // 충분하면 더 계산 X
            }

            if (processed >= n) {
                answer = mid;
                right = mid - 1; // 더 작은 시간 탐색
            } else {
                left = mid + 1;  // 더 큰 시간 필요
            }
        }

        return answer;
    }
}