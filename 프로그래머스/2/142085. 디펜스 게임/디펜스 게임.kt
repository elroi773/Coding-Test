import java.util.PriorityQueue
import java.util.Collections

class Solution {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        var soldiers = n
        var shields = k
        val maxHeap = PriorityQueue(Collections.reverseOrder<Int>())

        for (i in enemy.indices) {
            soldiers -= enemy[i]
            maxHeap.add(enemy[i])

            if (soldiers < 0) {
                if (shields > 0) {
                    val largest = maxHeap.poll() // 가장 큰 적 수
                    soldiers += largest          // 무적권으로 대체
                    shields--
                } else {
                    return i // 막은 라운드 개수
                }
            }
        }

        return enemy.size
    }
}
