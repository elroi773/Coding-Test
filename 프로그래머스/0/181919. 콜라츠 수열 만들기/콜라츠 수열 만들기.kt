class Solution {
    fun solution(n: Int): IntArray {
        val result = mutableListOf<Int>()
        var current = n

        while (current != 1) {
            result.add(current)
            current = if (current % 2 == 0) {
                current / 2
            } else {
                3 * current + 1
            }
        }

        result.add(1) // 마지막 1 추가
        return result.toIntArray()
    }
}
