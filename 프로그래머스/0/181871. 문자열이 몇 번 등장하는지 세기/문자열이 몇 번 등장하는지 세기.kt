class Solution {
    fun solution(myString: String, pat: String): Int {
        var answer = 0
        for (i in 0..myString.length - pat.length) {
            if (myString.substring(i, i + pat.length) == pat) {
                answer++
            }
        }
        return answer
    }
}
