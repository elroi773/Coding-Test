class Solution {
    fun solution(n: Int, tops: IntArray): Int {
        val MOD = 10007

        var free = 1  // 현재 정점이 아직 매칭되지 않아 오른쪽과 붙을 수 있는 상태
        var occ = 0   // 현재 정점이 이미 왼쪽/위와 붙어서 점유된 상태

        // 경로 정점 총 2n+1개: v1..v(2n+1)
        // j=2..2n+1 처리
        for (j in 2..(2 * n + 1)) {
            val hasTop = if (j % 2 == 0) tops[j / 2 - 1] else 0

            val newFree = (free + occ) % MOD          // 단독(정삼각형)
            var newOcc = free % MOD                   // 왼쪽과 마름모(경로 간선)

            if (hasTop == 1) {
                newOcc = (newOcc + free + occ) % MOD  // 위 가지와 마름모
            }

            free = newFree
            occ = newOcc
        }

        return (free + occ) % MOD
    }
}