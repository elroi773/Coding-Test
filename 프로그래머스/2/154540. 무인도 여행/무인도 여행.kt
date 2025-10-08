class Solution {
    fun solution(maps: Array<String>): IntArray {
        val n = maps.size
        val m = maps[0].length
        val visited = Array(n) { BooleanArray(m) }
        val result = mutableListOf<Int>()

        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)

        fun dfs(x: Int, y: Int): Int {
            visited[x][y] = true
            var sum = maps[x][y].digitToInt() // 문자를 숫자로 변환

            for (dir in 0 until 4) {
                val nx = x + dx[dir]
                val ny = y + dy[dir]

                if (nx in 0 until n && ny in 0 until m &&
                    !visited[nx][ny] && maps[nx][ny] != 'X'
                ) {
                    sum += dfs(nx, ny)
                }
            }
            return sum
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (!visited[i][j] && maps[i][j] != 'X') {
                    result.add(dfs(i, j))
                }
            }
        }

        return if (result.isEmpty()) intArrayOf(-1)
        else result.sorted().toIntArray()
    }
}
