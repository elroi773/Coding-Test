class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d_idx = n - 1; // 배달이 남은 마지막 집 인덱스
        int p_idx = n - 1; // 수거가 남은 마지막 집 인덱스

        while (d_idx >= 0 || p_idx >= 0) {
            // 배달이 남은 마지막 집 찾기
            while (d_idx >= 0 && deliveries[d_idx] == 0) d_idx--;
            // 수거가 남은 마지막 집 찾기
            while (p_idx >= 0 && pickups[p_idx] == 0) p_idx--;

            // 모두 처리 완료 시 종료
            if (d_idx < 0 && p_idx < 0) break;

            // 이번 회차 최대 거리
            int distance = Math.max(d_idx, p_idx) + 1;
            answer += (long) distance * 2; // 왕복 거리

            // 배달 처리
            int load = cap;
            for (int i = d_idx; i >= 0 && load > 0; i--) {
                if (deliveries[i] <= load) {
                    load -= deliveries[i];
                    deliveries[i] = 0;
                    d_idx = i - 1;
                } else {
                    deliveries[i] -= load;
                    load = 0;
                }
            }

            // 수거 처리
            load = cap;
            for (int i = p_idx; i >= 0 && load > 0; i--) {
                if (pickups[i] <= load) {
                    load -= pickups[i];
                    pickups[i] = 0;
                    p_idx = i - 1;
                } else {
                    pickups[i] -= load;
                    load = 0;
                }
            }
        }

        return answer;
    }
}
