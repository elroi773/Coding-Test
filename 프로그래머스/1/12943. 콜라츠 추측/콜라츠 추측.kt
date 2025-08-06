class Solution {
    fun solution(num: Int): Int {
        var n = num.toLong()  // overflow 방지
        var count = 0

        if (n == 1L) return 0

        while (n != 1L && count < 500) {
            n = if (n % 2 == 0L) {
                n / 2
            } else {
                n * 3 + 1
            }
            count++
        }

        return if (n == 1L) count else -1
    }
}
