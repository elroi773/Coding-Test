import java.util.PriorityQueue

class Solution {
    fun solution(book_time: Array<Array<String>>): Int {
        // "HH:MM" → 분으로 변환
        fun toMinutes(time: String): Int {
            val (h, m) = time.split(":").map { it.toInt() }
            return h * 60 + m
        }

        // 1️⃣ 예약 시간 [start, end+10] 형태로 변환
        val times = book_time.map { arrayOf(toMinutes(it[0]), toMinutes(it[1]) + 10) }

        // 2️⃣ 시작 시각 기준 정렬
        val sorted = times.sortedBy { it[0] }

        // 3️⃣ 방의 청소 완료 시각 관리 (최소 힙)
        val pq = PriorityQueue<Int>()

        for ((start, end) in sorted) {
            // 가장 빨리 비는 방이 이번 예약보다 일찍 끝났으면 재사용
            if (pq.isNotEmpty() && pq.peek() <= start) {
                pq.poll() // 기존 방 비우기
            }
            pq.offer(end) // 새 예약 추가
        }

        // 4️⃣ 필요한 최소 객실 수
        return pq.size
    }
}
