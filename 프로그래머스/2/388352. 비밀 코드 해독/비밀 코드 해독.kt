class Solution {
    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        var count = 0
        val m = q.size                 // 시도 횟수
        val code = IntArray(5)         // 현재 만들고 있는 비밀 코드 후보 5개 숫자

        // code[5]와 row[5] 사이의 공통 원소 개수 (둘 다 오름차순)
        fun intersectionCount(code: IntArray, row: IntArray): Int {
            var i = 0
            var j = 0
            var cnt = 0
            while (i < 5 && j < 5) {
                when {
                    code[i] == row[j] -> {
                        cnt++
                        i++
                        j++
                    }
                    code[i] < row[j] -> i++
                    else -> j++
                }
            }
            return cnt
        }

        // 현재 code[]가 모든 시도(q, ans)를 만족하는지 확인
        fun isValid(): Boolean {
            for (i in 0 until m) {
                val matches = intersectionCount(code, q[i])
                if (matches != ans[i]) return false
            }
            return true
        }

        // DFS로 1~n 중 5개의 조합 생성
        fun dfs(idx: Int, start: Int) {
            if (idx == 5) {
                if (isValid()) count++
                return
            }

            // 남은 자리를 채울 수 있는 최대 숫자까지만 반복
            val maxStart = n - (5 - idx) + 1
            for (num in start..maxStart) {
                code[idx] = num
                dfs(idx + 1, num + 1)
            }
        }

        dfs(0, 1)
        return count
    }
}