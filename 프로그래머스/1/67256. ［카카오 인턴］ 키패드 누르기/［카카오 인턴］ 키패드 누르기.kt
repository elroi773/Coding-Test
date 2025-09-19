class Solution {
    fun solution(numbers: IntArray, hand: String): String {
        val pos = mapOf(
            1 to Pair(0,0), 2 to Pair(0,1), 3 to Pair(0,2),
            4 to Pair(1,0), 5 to Pair(1,1), 6 to Pair(1,2),
            7 to Pair(2,0), 8 to Pair(2,1), 9 to Pair(2,2),
            -1 to Pair(3,0), // * = -1
            0 to Pair(3,1),
            -2 to Pair(3,2)  // # = -2
        )

        var left = pos[-1]!!  // * 위치
        var right = pos[-2]!! // # 위치
        val sb = StringBuilder()

        for (num in numbers) {
            when (num) {
                1,4,7 -> {
                    sb.append("L")
                    left = pos[num]!!
                }
                3,6,9 -> {
                    sb.append("R")
                    right = pos[num]!!
                }
                else -> {
                    val target = pos[num]!!
                    val leftDist = Math.abs(left.first - target.first) + Math.abs(left.second - target.second)
                    val rightDist = Math.abs(right.first - target.first) + Math.abs(right.second - target.second)

                    if (leftDist < rightDist) {
                        sb.append("L")
                        left = target
                    } else if (rightDist < leftDist) {
                        sb.append("R")
                        right = target
                    } else {
                        if (hand == "left") {
                            sb.append("L")
                            left = target
                        } else {
                            sb.append("R")
                            right = target
                        }
                    }
                }
            }
        }

        return sb.toString()
    }
}
