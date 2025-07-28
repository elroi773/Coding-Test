class Solution {
    fun solution(s: String): IntArray {
        val answer = IntArray(s.length)
        val lastIndexMap = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            val c = s[i]
            if (lastIndexMap.containsKey(c)) {
                answer[i] = i - lastIndexMap[c]!!
            } else {
                answer[i] = -1
            }
            lastIndexMap[c] = i
        }

        return answer
    }
}
