class Solution {
    public int solution(int[] stones, int k) {
        // m명이 건널 수 있는지 판별
        // m명이 지나가면 각 돌은 stones[i] - m 만큼 남고,
        // stones[i] < m 이면 m번째 사람 기준으로 밟을 수 없는 돌(0 이하)이 됨.
        // 밟을 수 없는 돌이 연속으로 k개 이상이면 실패.
        java.util.function.IntPredicate canCross = (m) -> {
            int consecutive = 0;
            for (int s : stones) {
                if (s < m) {
                    consecutive++;
                    if (consecutive >= k) return false;
                } else {
                    consecutive = 0;
                }
            }
            return true;
        };

        int left = 1;
        int right = 0;
        for (int s : stones) right = Math.max(right, s);

        int answer = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canCross.test(mid)) {
                answer = mid;      // mid명 가능 -> 더 큰 값 탐색
                left = mid + 1;
            } else {
                right = mid - 1;   // mid명 불가능 -> 더 작은 값 탐색
            }
        }
        return answer;
    }
}