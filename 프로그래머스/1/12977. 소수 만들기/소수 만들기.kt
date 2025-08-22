class Solution {
    fun solution(nums: IntArray): Int {
        var answer = 0

        // 소수 판별 함수
        fun isPrime(n: Int): Boolean {
            if (n < 2) return false
            for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
                if (n % i == 0) return false
            }
            return true
        }

        // 3중 for문 조합 탐색
        for (i in 0 until nums.size - 2) {
            for (j in i + 1 until nums.size - 1) {
                for (k in j + 1 until nums.size) {
                    val sum = nums[i] + nums[j] + nums[k]
                    if (isPrime(sum)) {
                        answer++
                    }
                }
            }
        }

        return answer
    }
}
