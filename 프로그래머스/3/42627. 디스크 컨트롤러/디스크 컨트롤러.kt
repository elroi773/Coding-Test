import java.util.PriorityQueue
import java.util.Arrays

class Solution {
    fun solution(jobs: Array<IntArray>): Int {
        val n = jobs.size
        if (n == 0) return 0

        // (req, dur, id)
        val arr = Array(n) { i -> intArrayOf(jobs[i][0], jobs[i][1], i) }
        Arrays.sort(arr) { a, b ->
            if (a[0] != b[0]) a[0] - b[0]       // req
            else if (a[1] != b[1]) a[1] - b[1]  // dur
            else a[2] - b[2]                    // id
        }

        // 우선순위: dur ↑, req ↑, id ↑
        val pq = PriorityQueue<IntArray> { a, b ->
            if (a[1] != b[1]) a[1] - b[1]       // dur
            else if (a[0] != b[0]) a[0] - b[0]  // req
            else a[2] - b[2]                    // id
        }

        var time = 0L
        var idx = 0
        var done = 0
        var total = 0L

        while (done < n) {
            while (idx < n && arr[idx][0].toLong() <= time) {
                pq.add(arr[idx])
                idx++
            }

            if (pq.isEmpty()) {
                time = maxOf(time, arr[idx][0].toLong())
                continue
            }

            val cur = pq.poll()
            val req = cur[0]
            val dur = cur[1]

            time += dur.toLong()
            total += (time - req.toLong())
            done++
        }

        return (total / n).toInt()
    }
}