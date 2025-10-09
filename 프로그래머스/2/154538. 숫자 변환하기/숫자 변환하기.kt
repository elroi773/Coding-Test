import java.util.*

class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        if (x == y) return 0

        val visited = BooleanArray(y + 1)
        val queue: Queue<Pair<Int, Int>> = LinkedList()

        queue.add(Pair(x, 0))
        visited[x] = true

        while (queue.isNotEmpty()) {
            val (cur, cnt) = queue.poll()

            val nextValues = listOf(cur + n, cur * 2, cur * 3)
            for (next in nextValues) {
                if (next > y || visited[next]) continue
                if (next == y) return cnt + 1

                visited[next] = true
                queue.add(Pair(next, cnt + 1))
            }
        }

        return -1
    }
}
