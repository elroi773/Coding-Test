class Solution {
    fun solution(citations: IntArray): String {

        val arr = citations.map { it.toString() }

        val sorted = arr.sortedWith { a, b ->
            (b + a).compareTo(a + b)
        }

        if (sorted[0] == "0") return "0"

        return sorted.joinToString("")
    }
}
