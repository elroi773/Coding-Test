import java.util.ArrayDeque

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        // target이 words에 없으면 불가능
        if (!words.contains(target)) return 0

        val n = words.size
        val dist = IntArray(n) { -1 }
        val q = ArrayDeque<Int>()

        fun diffOne(a: String, b: String): Boolean {
            var diff = 0
            for (i in a.indices) {
                if (a[i] != b[i]) {
                    diff++
                    if (diff > 1) return false
                }
            }
            return diff == 1
        }

        // begin에서 시작: begin과 1글자 다른 단어들을 dist=1로 큐에 넣기
        for (i in 0 until n) {
            if (diffOne(begin, words[i])) {
                dist[i] = 1
                q.addLast(i)
            }
        }

        while (q.isNotEmpty()) {
            val cur = q.removeFirst()

            if (words[cur] == target) return dist[cur]

            for (nxt in 0 until n) {
                if (dist[nxt] == -1 && diffOne(words[cur], words[nxt])) {
                    dist[nxt] = dist[cur] + 1
                    q.addLast(nxt)
                }
            }
        }

        return 0
    }
}