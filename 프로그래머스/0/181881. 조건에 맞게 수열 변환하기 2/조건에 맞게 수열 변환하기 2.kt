class Solution {
    fun solution(arr: IntArray): Int {
        var count = 0
        var changed = true
        var current = arr.copyOf()

        while (changed) {
            changed = false
            val next = current.copyOf()

            for (i in current.indices) {
                val value = current[i]
                if (value >= 50 && value % 2 == 0) {
                    next[i] = value / 2
                } else if (value < 50 && value % 2 == 1) {
                    next[i] = value * 2 + 1
                }
            }

            for (i in current.indices) {
                if (current[i] != next[i]) {
                    changed = true
                    break
                }
            }

            if (changed) {
                current = next
                count++
            }
        }

        return count
    }
}
