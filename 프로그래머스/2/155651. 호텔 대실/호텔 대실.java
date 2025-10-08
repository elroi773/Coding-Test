import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 1. 시간을 분 단위로 변환
        int[][] times = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            times[i][0] = toMinutes(book_time[i][0]);
            times[i][1] = toMinutes(book_time[i][1]);
        }

        // 2. 시작 시각 기준 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        // 3. 방의 퇴실 + 청소 완료 시간을 저장할 우선순위 큐 (가장 빨리 비는 방이 맨 위)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] t : times) {
            int start = t[0];
            int end = t[1] + 10; // 청소 시간 포함

            // 만약 현재 가장 빨리 비는 방이 이번 예약 시작 이전에 청소 끝났다면 재사용
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll(); // 기존 방 제거 (재사용)
            }

            // 현재 예약의 퇴실 + 청소 완료 시간 추가
            pq.offer(end);
        }

        // 남은 방 개수 = 필요한 최소 객실 수
        return pq.size();
    }

    // HH:MM → 분 단위로 변환하는 함수
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
