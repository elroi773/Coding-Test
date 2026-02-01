class Solution {
    fun solution(a: IntArray): Int {
        val n = a.size
        if (n <= 1) return n

        val INF = Int.MAX_VALUE
        val rightMin = IntArray(n)

        // rightMin[i] = min(a[i+1..n-1]) (없으면 INF)
        var cur = INF
        for (i in n - 1 downTo 0) {
            rightMin[i] = cur
            if (a[i] < cur) cur = a[i]
        }

        var answer = 0
        var leftMin = INF
        for (i in 0 until n) {
            if (a[i] <= leftMin || a[i] <= rightMin[i]) answer++
            if (a[i] < leftMin) leftMin = a[i]
        }

        return answer
    }
}