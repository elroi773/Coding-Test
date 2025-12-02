class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        int n = queue1.length;

        for (int num : queue1) sum1 += num;
        for (int num : queue2) sum2 += num;

        long total = sum1 + sum2;
        if (total % 2 != 0) return -1;  // 합이 홀수면 불가능

        long target = total / 2;

        // 두 큐를 하나로 합쳐서 반복 접근 가능하도록 배열 생성
        int[] combined = new int[2 * n * 2]; // 안전하게 2배 길이로
        for (int i = 0; i < n; i++) {
            combined[i] = queue1[i];
            combined[n + i] = queue2[i];
            combined[2 * n + i] = queue1[i]; // 반복
            combined[3 * n + i] = queue2[i]; // 반복
        }

        int start = 0;
        int end = n;
        long currSum = sum1;
        int cnt = 0;
        int maxCnt = n * 3; // 안전 마진

        while (cnt <= maxCnt) {
            if (currSum == target) return cnt;

            if (currSum > target) {
                currSum -= combined[start++];
            } else {
                currSum += combined[end++];
            }
            cnt++;
        }

        return -1;
    }
}
