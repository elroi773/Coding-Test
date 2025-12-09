import kotlin.math.abs

class Solution {
    fun solution(expression: String): Long {
        // 숫자와 연산자 분리
        val nums = expression.split(Regex("[+\\-*]")).map { it.toLong() }.toMutableList()
        val ops = Regex("[+\\-*]").findAll(expression).map { it.value[0] }.toMutableList()

        // 등장한 연산자 추출
        val uniqueOps = ops.toSet().toList()

        // 연산자 순열 생성
        val permutations = mutableListOf<List<Char>>()
        generatePermutations(uniqueOps, 0, permutations)

        var maxResult = 0L

        // 각 우선순위 조합으로 계산
        for (priority in permutations) {
            val result = evaluate(nums, ops, priority)
            maxResult = maxOf(maxResult, abs(result))
        }

        return maxResult
    }

    // 우선순위별 계산
    private fun evaluate(nums: List<Long>, ops: List<Char>, priority: List<Char>): Long {
        var tmpNums = nums.toMutableList()
        var tmpOps = ops.toMutableList()

        for (op in priority) {
            val newNums = mutableListOf<Long>()
            val newOps = mutableListOf<Char>()

            newNums.add(tmpNums[0])

            for (i in tmpOps.indices) {
                if (tmpOps[i] == op) {
                    val a = newNums.removeLast()
                    val b = tmpNums[i + 1]
                    newNums.add(calc(a, b, op))
                } else {
                    newNums.add(tmpNums[i + 1])
                    newOps.add(tmpOps[i])
                }
            }
            tmpNums = newNums
            tmpOps = newOps
        }

        return tmpNums[0]
    }

    // 연산 함수
    private fun calc(a: Long, b: Long, op: Char): Long {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            else -> 0L
        }
    }

    // 연산자 순열 생성
    private fun generatePermutations(list: List<Char>, depth: Int, result: MutableList<List<Char>>) {
        if (depth == list.size) {
            result.add(list.toList())
            return
        }

        for (i in depth until list.size) {
            val swapped = list.toMutableList()
            val temp = swapped[depth]
            swapped[depth] = swapped[i]
            swapped[i] = temp

            generatePermutations(swapped, depth + 1, result)
        }
    }
}
