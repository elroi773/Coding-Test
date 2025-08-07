class Solution {
    fun solution(answers: IntArray): IntArray {
        val one = intArrayOf(1, 2, 3, 4, 5)
        val two = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
        val three = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

        val scores = intArrayOf(0, 0, 0)

        for (i in answers.indices) {
            if (answers[i] == one[i % one.size]) scores[0]++
            if (answers[i] == two[i % two.size]) scores[1]++
            if (answers[i] == three[i % three.size]) scores[2]++
        }

        val maxScore = scores.maxOrNull() ?: 0

        val result = mutableListOf<Int>()
        for (i in scores.indices) {
            if (scores[i] == maxScore) result.add(i + 1)
        }

        return result.toIntArray()
    }
}
