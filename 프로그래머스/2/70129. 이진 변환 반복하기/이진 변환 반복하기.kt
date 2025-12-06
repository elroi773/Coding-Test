class Solution {
    fun solution(s: String): IntArray {
        var str = s
        var transformCnt = 0
        var removedZeroCnt = 0

        while (str != "1") {
            val len = str.length
            val ones = str.count { it == '1' }

            removedZeroCnt += (len - ones)
            transformCnt++

            // 다음 문자열 = 1의 개수의 2진수 표현
            str = Integer.toBinaryString(ones)
        }

        return intArrayOf(transformCnt, removedZeroCnt)
    }
}