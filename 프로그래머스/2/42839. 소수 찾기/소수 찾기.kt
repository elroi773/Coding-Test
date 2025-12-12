import kotlin.math.sqrt

class Solution {

    // 소수 판별 함수
    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

    // DFS로 모든 숫자 조합 만들기
    private fun dfs(numbers: String, visited: BooleanArray, current: String, set: MutableSet<Int>) {
        if (current.isNotEmpty()) {
            set.add(current.toInt())
        }

        for (i in numbers.indices) {
            if (!visited[i]) {
                visited[i] = true
                dfs(numbers, visited, current + numbers[i], set)
                visited[i] = false
            }
        }
    }

    fun solution(numbers: String): Int {
        val visited = BooleanArray(numbers.length)
        val set = mutableSetOf<Int>()

        // 모든 조합 생성
        dfs(numbers, visited, "", set)

        // 소수 개수 세기
        return set.count { isPrime(it) }
    }
}
