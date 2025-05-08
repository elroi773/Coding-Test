class Solution {
    fun solution(t: String, p: String): Int {
        var answer = 0
        val len = p.length
        val pNum = p.toBigInteger()  // 긴 숫자를 위해 BigInteger 사용

        for (i in 0..t.length - len) {
            val sub = t.substring(i, i + len)
            val subNum = sub.toBigInteger()
            if (subNum <= pNum) {
                answer++
            }
        }

        return answer
    }
}
