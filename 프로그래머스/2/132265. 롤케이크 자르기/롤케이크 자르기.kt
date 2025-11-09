class Solution {
    fun solution(topping: IntArray): Int {
        val n = topping.size
        var answer = 0

        val left = IntArray(n)
        val right = IntArray(n)

        val leftSet = mutableSetOf<Int>()
        val rightSet = mutableSetOf<Int>()

        // 1️⃣ 왼쪽 누적
        for (i in topping.indices) {
            leftSet.add(topping[i])
            left[i] = leftSet.size
        }

        // 2️⃣ 오른쪽 누적
        for (i in n - 1 downTo 0) {
            rightSet.add(topping[i])
            right[i] = rightSet.size
        }

        // 3️⃣ 자를 위치 비교
        for (i in 0 until n - 1) {
            if (left[i] == right[i + 1]) answer++
        }

        return answer
    }
}