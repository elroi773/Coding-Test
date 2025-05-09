class Solution {
    fun solution(arr: IntArray, query: IntArray): IntArray {
        var result = arr.toList()
        for (i in query.indices) {
            result = if (i % 2 == 0) {
                result.subList(0, query[i] + 1)
            } else {
                result.subList(query[i], result.size)
            }
        }
        return result.toIntArray()
    }
}
