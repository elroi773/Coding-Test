import java.util.LinkedList
import java.util.Queue

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        val queue: Queue<Pair<Int, Int>> = LinkedList()

        // (우선순위, 원래 위치)
        for (i in priorities.indices) {
            queue.offer(Pair(priorities[i], i))
        }

        var order = 0

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            // 더 높은 우선순위가 있는지 확인
            val hasHigher = queue.any { it.first > current.first }

            if (hasHigher) {
                queue.offer(current)
            } else {
                // 실행
                order++
                if (current.second == location) {
                    return order
                }
            }
        }

        return -1
    }
}
