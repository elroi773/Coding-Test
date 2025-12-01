class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        val total = brown + yellow

        for (h in 1..total) {
            if (total % h == 0) { // h가 세로일 때, 가로 w는 total / h
                val w = total / h
                if ((w - 2) * (h - 2) == yellow && w >= h) {
                    return intArrayOf(w, h)
                }
            }
        }
        return intArrayOf() // 조건에 맞는 값이 항상 있으므로 사실 이 부분은 도달하지 않음
    }
}
