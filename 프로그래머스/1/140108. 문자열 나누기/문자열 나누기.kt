class Solution {
    fun solution(s: String): Int {
        var answer = 0
        var sameCount = 0
        var diffCount = 0
        var firstChar: Char? = null

        for (c in s) {
            if (sameCount == 0) {
                firstChar = c
                sameCount = 1
                diffCount = 0
            } else {
                if (c == firstChar) {
                    sameCount++
                } else {
                    diffCount++
                }
            }

            if (sameCount == diffCount) {
                answer++
                sameCount = 0
                diffCount = 0
            }
        }

        if (sameCount != 0 || diffCount != 0) {
            answer++
        }

        return answer
    }
}
