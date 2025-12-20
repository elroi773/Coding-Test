import java.util.PriorityQueue
import java.util.Arrays

class Solution {
    fun solution(N: Int, road: Array<IntArray>, K: Int): Int {

        // 1. 그래프 생성 (인접 리스트)
        val graph = Array(N + 1) { mutableListOf<Pair<Int, Int>>() }
        for (r in road) {
            val a = r[0]
            val b = r[1]
            val c = r[2]
            graph[a].add(Pair(b, c))
            graph[b].add(Pair(a, c))
        }

        // 2. 거리 배열 초기화
        val dist = IntArray(N + 1) { Int.MAX_VALUE }
        dist[1] = 0

        // 3. 우선순위 큐 (거리 기준 최소 힙)
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        pq.offer(Pair(0, 1)) // (거리, 마을)

        // 4. 다익스트라 실행
        while (pq.isNotEmpty()) {
            val (curDist, curNode) = pq.poll()

            if (curDist > dist[curNode]) continue

            for ((nextNode, cost) in graph[curNode]) {
                val newDist = curDist + cost
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist
                    pq.offer(Pair(newDist, nextNode))
                }
            }
        }

        // 5. K 이하로 도달 가능한 마을 수
        var answer = 0
        for (i in 1..N) {
            if (dist[i] <= K) answer++
        }

        return answer
    }
}
