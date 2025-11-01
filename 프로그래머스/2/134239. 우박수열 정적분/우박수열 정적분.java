class Solution {
    public double[] solution(int k, int[][] ranges) {
        // 1) 우박수열 생성 (long 사용)
        java.util.ArrayList<Long> seq = new java.util.ArrayList<>();
        long cur = k;
        seq.add(cur);
        while (cur != 1L) {
            if ((cur & 1L) == 0L) cur = cur / 2L;
            else cur = cur * 3L + 1L;
            seq.add(cur);
        }
        int n = seq.size() - 1; // 세그먼트(사다리꼴) 수

        // 2) 각 세그먼트 넓이 계산 및 누적합
        double[] prefix = new double[n + 1]; // prefix[0]=0, prefix[i]=합(area[0..i-1])
        prefix[0] = 0.0;
        for (int i = 0; i < n; ++i) {
            double a = seq.get(i);
            double b = seq.get(i + 1);
            double area = (a + b) / 2.0;
            prefix[i + 1] = prefix[i] + area;
        }

        // 3) 각 쿼리 처리
        double[] ans = new double[ranges.length];
        for (int i = 0; i < ranges.length; ++i) {
            int a = ranges[i][0];
            int b = ranges[i][1]; // b <= 0
            int right = n + b; // 실제 오른쪽 인덱스
            if (a > right || a < 0 || right < 0 || a > n) {
                ans[i] = -1.0;
            } else {
                // a와 right은 0..n 사이(정수)여야 함. (right==n 가능)
                // prefix[right] - prefix[a]
                ans[i] = prefix[right] - prefix[a];
            }
        }
        return ans;
    }
}
