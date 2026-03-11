import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long total = 0;
        
        for (int work : works) {
            total += work;
        }
        
        // 남은 시간 안에 일을 전부 끝낼 수 있으면 야근 피로도는 0
        if (total <= n) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            pq.offer(work);
        }
        
        while (n-- > 0) {
            int max = pq.poll();
            pq.offer(max - 1);
        }
        
        long answer = 0;
        
        while (!pq.isEmpty()) {
            long x = pq.poll();
            answer += x * x;
        }
        
        return answer;
    }
}