import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();

        // [우선순위, 원래 위치] 형태로 큐에 삽입
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{priorities[i], i});
        }

        int order = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            boolean hasHigher = false;

            // 큐에 더 높은 우선순위가 있는지 확인
            for (int[] q : queue) {
                if (q[0] > current[0]) {
                    hasHigher = true;
                    break;
                }
            }

            if (hasHigher) {
                // 다시 큐 뒤로
                queue.offer(current);
            } else {
                // 실행
                order++;
                if (current[1] == location) {
                    return order;
                }
            }
        }

        return -1; // 도달하지 않음
    }
}
