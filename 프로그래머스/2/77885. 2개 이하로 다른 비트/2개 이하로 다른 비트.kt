class Solution {
    fun solution(numbers: LongArray): LongArray {
        val answer = LongArray(numbers.size)

        for (i in numbers.indices) {
            val x = numbers[i]
            
            answer[i] = if (x % 2L == 0L) {
                // 짝수면 +1
                x + 1
            } else {
                // 홀수면 오른쪽 첫 0 비트 찾기
                var bit = 1L
                while ((x and bit) != 0L) {
                    bit = bit shl 1
                }
                // 찾은 0 위치 바로 오른쪽 1비트만 더함
                x + (bit shr 1)
            }
        }

        return answer
    }
}
