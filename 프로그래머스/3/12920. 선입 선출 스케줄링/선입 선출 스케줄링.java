class Solution {
    public int solution(int n, int[] cores) {
        int m = cores.length;
        
        // 처음에는 각 코어가 바로 하나씩 작업을 시작
        if (n <= m) return n;
        
        long left = 0;
        long right = (long) getMax(cores) * n;
        
        // n개 이상 처리 가능한 최소 시간 찾기
        while (left < right) {
            long mid = (left + right) / 2;
            if (countJobs(mid, cores) >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        long time = left;
        long done = countJobs(time - 1, cores);
        
        // time 시점에 작업을 새로 받을 수 있는 코어를 앞에서부터 확인
        for (int i = 0; i < m; i++) {
            if (time % cores[i] == 0) {
                done++;
                if (done == n) {
                    return i + 1;
                }
            }
        }
        
        return -1;
    }
    
    private long countJobs(long time, int[] cores) {
        long total = 0;
        for (int core : cores) {
            total += (time / core) + 1;
        }
        return total;
    }
    
    private int getMax(int[] cores) {
        int max = cores[0];
        for (int core : cores) {
            if (core > max) {
                max = core;
            }
        }
        return max;
    }
}