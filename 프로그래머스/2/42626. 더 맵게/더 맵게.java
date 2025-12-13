import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 1. 모든 음식 넣기
        for (int s : scoville) {
            pq.add(s);
        }

        int count = 0;

        // 2. 가장 작은 값이 K 이상이 될 때까지
        while (pq.peek() < K) {
            // 3. 더 이상 섞을 수 없는 경우
            if (pq.size() < 2) {
                return -1;
            }

            int first = pq.poll();   // 가장 작은 값
            int second = pq.poll();  // 두 번째로 작은 값

            int mixed = first + (second * 2);
            pq.add(mixed);

            count++;
        }

        return count;
    }
}
