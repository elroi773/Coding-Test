class Solution {
    fun solution(elements: IntArray): Int {
        val n = elements.size

        // 원형 처리를 위해 배열을 2배로 늘림
        val arr = IntArray(2 * n) { i -> elements[i % n] }

        // prefix sum 배열
        val prefix = IntArray(2 * n + 1)
        for (i in 0 until 2 * n) {
            prefix[i + 1] = prefix[i] + arr[i]
        }

        val sums = HashSet<Int>()

        // 부분 수열 길이: 1 ~ n
        for (length in 1..n) {
            for (start in 0 until n) {
                val sum = prefix[start + length] - prefix[start]
                sums.add(sum)
            }
        }

        return sums.size
    }
}
