class Solution {
    fun solution(n: Int): IntArray {
        val total = n * (n + 1) / 2          // 채워야 할 숫자 개수
        val answer = IntArray(total)

        // 삼각 달팽이를 채울 임시 2차원 배열
        val tri = Array(n) { IntArray(n) }

        // 방향: 아래(0), 오른쪽(1), 왼쪽 위(2)
        val dr = intArrayOf(1, 0, -1)
        val dc = intArrayOf(0, 1, -1)

        var r = 0
        var c = 0
        var dir = 0

        for (num in 1..total) {
            tri[r][c] = num

            var nr = r + dr[dir]
            var nc = c + dc[dir]

            // 범위를 벗어나거나 이미 값이 있으면 방향 전환
            if (nr !in 0 until n || nc !in 0 until n || tri[nr][nc] != 0) {
                dir = (dir + 1) % 3
                nr = r + dr[dir]
                nc = c + dc[dir]
            }

            r = nr
            c = nc
        }

        // 삼각형 부분만 1차원 배열로 옮기기
        var idx = 0
        for (i in 0 until n) {
            for (j in 0..i) {
                answer[idx++] = tri[i][j]
            }
        }

        return answer
    }
}