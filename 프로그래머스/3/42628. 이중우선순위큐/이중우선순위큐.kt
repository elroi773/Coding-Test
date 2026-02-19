import java.util.PriorityQueue

class Solution {
    fun solution(operations: Array<String>): IntArray {
        // (value, id)
        val minH = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        val maxH = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.first })

        val alive = BooleanArray(operations.size)
        var nextId = 0
        var validCount = 0

        fun cleanMin() {
            while (minH.isNotEmpty() && !alive[minH.peek().second]) minH.poll()
        }
        fun cleanMax() {
            while (maxH.isNotEmpty() && !alive[maxH.peek().second]) maxH.poll()
        }

        for (op in operations) {
            if (op[0] == 'I') {
                val x = op.substring(2).toInt()
                val id = nextId++
                alive[id] = true
                minH.add(Pair(x, id))
                maxH.add(Pair(x, id))
                validCount++
            } else { // 'D'
                if (validCount == 0) continue

                if (op[2] == '1') { // delete max
                    cleanMax()
                    if (maxH.isNotEmpty()) {
                        val id = maxH.poll().second
                        alive[id] = false
                        validCount--
                    }
                } else { // delete min
                    cleanMin()
                    if (minH.isNotEmpty()) {
                        val id = minH.poll().second
                        alive[id] = false
                        validCount--
                    }
                }
            }
        }

        if (validCount == 0) return intArrayOf(0, 0)

        cleanMin()
        cleanMax()
        return intArrayOf(maxH.peek().first, minH.peek().first)
    }
}