class Solution {
    fun solution(storey: Int): Int {
        var answer = 0
        var num = storey

        while (num > 0) {
            val digit = num % 10

            when {
                digit >= 6 -> {
                    answer += 10 - digit
                    num += 10  // 다음 자리 올림
                }
                digit <= 4 -> {
                    answer += digit
                }
                else -> { // digit == 5
                    val nextDigit = (num / 10) % 10
                    if (nextDigit >= 5) {
                        answer += 5
                        num += 10
                    } else {
                        answer += 5
                    }
                }
            }

            num /= 10
        }

        return answer
    }
}
