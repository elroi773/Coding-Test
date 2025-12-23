class Solution {
    private var index = 0

    fun solution(n: Int): Array<IntArray> {
        val moveCount = (1 shl n) - 1
        val answer = Array(moveCount) { IntArray(2) }

        hanoi(n, 1, 2, 3, answer)
        return answer
    }

    private fun hanoi(
        n: Int,
        start: Int,
        via: Int,
        end: Int,
        answer: Array<IntArray>
    ) {
        if (n == 1) {
            answer[index][0] = start
            answer[index][1] = end
            index++
            return
        }

        // 1. n-1개를 보조 기둥으로
        hanoi(n - 1, start, end, via, answer)

        // 2. 가장 큰 원판 이동
        answer[index][0] = start
        answer[index][1] = end
        index++

        // 3. n-1개를 목표 기둥으로
        hanoi(n - 1, via, start, end, answer)
    }
}
