class Solution {
    fun solution(k: Int, score: IntArray): IntArray {
        val hallOfFame = mutableListOf<Int>()
        val answer = mutableListOf<Int>()

        for (s in score) {
            hallOfFame.add(s)
            hallOfFame.sortDescending()

            if (hallOfFame.size > k) {
                hallOfFame.removeLast()  // 가장 낮은 점수 제거
            }

            answer.add(hallOfFame.last()) // 현재 명예의 전당 최하위 점수
        }

        return answer.toIntArray()
    }
}
