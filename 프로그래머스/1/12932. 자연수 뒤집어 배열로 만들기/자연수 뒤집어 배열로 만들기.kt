class Solution {
    fun solution(n: Long): IntArray {
        return n.toString()          // "12345"
            .reversed()              // "54321"
            .map { it.digitToInt() } // [5,4,3,2,1]
            .toIntArray()
    }
}
