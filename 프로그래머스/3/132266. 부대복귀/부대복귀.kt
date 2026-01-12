import java.util.ArrayDeque

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        // 1) 인접 리스트
        val graph = Array(n + 1) { IntArray(0) }

        // roads가 최대 50만이라 ArrayList<Int>로도 되지만, 성능 위해 2-pass로 IntArray 구성
        val deg = IntArray(n + 1)
        for (e in roads) {
            val a = e[0]
            val b = e[1]
            deg[a]++
            deg[b]++
        }

        val temp = Array(n + 1) { IntArray(deg[it]) }
        val idx = IntArray(n + 1)

        for (e in roads) {
            val a = e[0]
            val b = e[1]
            temp[a][idx[a]++] = b
            temp[b][idx[b]++] = a
        }

        for (i in 1..n) {
            graph[i] = temp[i]
        }

        // 2) destination 기준 BFS 거리
        val dist = IntArray(n + 1) { -1 }
        dist[destination] = 0

        val q = ArrayDeque<Int>()
        q.add(destination)

        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            val nextDist = dist[cur] + 1

            for (nxt in graph[cur]) {
                if (dist[nxt] == -1) {
                    dist[nxt] = nextDist
                    q.add(nxt)
                }
            }
        }

        // 3) sources 순서대로 답 반환
        val answer = IntArray(sources.size)
        for (i in sources.indices) {
            answer[i] = dist[sources[i]]
        }
        return answer
    }
}