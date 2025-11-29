class Solution {
    var answer = 0

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(numbers, target, 0, 0)
        return answer
    }

    private fun dfs(numbers: IntArray, target: Int, index: Int, sum: Int) {
        // 모든 숫자를 사용한 경우
        if (index == numbers.size) {
            if (sum == target) answer++
            return
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, index + 1, sum + numbers[index])

        // 현재 숫자를 빼는 경우
        dfs(numbers, target, index + 1, sum - numbers[index])
    }
}
