class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var sum1 = queue1.sum().toLong()
        var sum2 = queue2.sum().toLong()
        val total = sum1 + sum2

        if (total % 2 != 0L) return -1  // 불가능
        val target = total / 2

        val n = queue1.size
        val combined = queue1 + queue2 + queue1 + queue2  // 2배 반복

        var start = 0
        var end = n
        var currSum = sum1
        var cnt = 0
        val maxCnt = n * 3  // 안전 마진

        while (cnt <= maxCnt) {
            if (currSum == target) return cnt

            if (currSum > target) {
                currSum -= combined[start++]
            } else {
                currSum += combined[end++]
            }
            cnt++
        }

        return -1
    }
}
