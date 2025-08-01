class Solution {
    fun solution(food: IntArray): String {
        val left = StringBuilder()

        for (i in 1 until food.size) {
            val count = food[i] / 2  // 양쪽에 똑같이 배치할 수 있는 개수
            repeat(count) {
                left.append(i)
            }
        }

        val right = left.toString().reversed()

        return left.toString() + "0" + right
    }
}
