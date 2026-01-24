import java.util.ArrayDeque

class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val prev = IntArray(n) { it - 1 }
        val next = IntArray(n) { it + 1 }
        next[n - 1] = -1

        val removed = BooleanArray(n)
        val stack = ArrayDeque<IntArray>() // [idx, prevIdx, nextIdx]

        var cur = k

        for (s in cmd) {
            when (s[0]) {
                'U' -> {
                    val x = s.substring(2).toInt()
                    repeat(x) { cur = prev[cur] }
                }
                'D' -> {
                    val x = s.substring(2).toInt()
                    repeat(x) { cur = next[cur] }
                }
                'C' -> {
                    removed[cur] = true
                    val p = prev[cur]
                    val nx = next[cur]
                    stack.addLast(intArrayOf(cur, p, nx))

                    // unlink
                    if (p != -1) next[p] = nx
                    if (nx != -1) prev[nx] = p

                    // move selection
                    cur = if (nx != -1) nx else p
                }
                'Z' -> {
                    val rec = stack.removeLast()
                    val idx = rec[0]
                    val p = rec[1]
                    val nx = rec[2]
                    removed[idx] = false

                    // relink
                    if (p != -1) next[p] = idx
                    if (nx != -1) prev[nx] = idx
                    prev[idx] = p
                    next[idx] = nx
                }
            }
        }

        val sb = StringBuilder(n)
        for (i in 0 until n) sb.append(if (removed[i]) 'X' else 'O')
        return sb.toString()
    }
}