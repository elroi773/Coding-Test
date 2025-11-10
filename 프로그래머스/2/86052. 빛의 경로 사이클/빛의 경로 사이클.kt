class Solution {
    fun solution(grid: Array<String>): IntArray {
        val n = grid.size
        val m = grid[0].length

        // 방향: 상(0), 우(1), 하(2), 좌(3)
        val dx = intArrayOf(-1, 0, 1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        // 방문 체크: [행][열][방향]
        val visited = Array(n) { Array(m) { BooleanArray(4) } }
        val result = mutableListOf<Int>()

        for (i in 0 until n) {
            for (j in 0 until m) {
                for (d in 0 until 4) {
                    if (!visited[i][j][d]) {
                        var x = i
                        var y = j
                        var dir = d
                        var count = 0

                        while (!visited[x][y][dir]) {
                            visited[x][y][dir] = true
                            count++

                            // 다음 위치 (토러스 구조)
                            x = (x + dx[dir] + n) % n
                            y = (y + dy[dir] + m) % m

                            // 다음 칸에서 방향 회전
                            when (grid[x][y]) {
                                'L' -> dir = (dir + 3) % 4  // 왼쪽 회전
                                'R' -> dir = (dir + 1) % 4  // 오른쪽 회전
                            }
                        }

                        result.add(count)
                    }
                }
            }
        }

        return result.sorted().toIntArray()
    }
}
