class Solution {
    fun solution(storage: Array<String>, requests: Array<String>): Int {
        val n = storage.size
        val m = storage[0].length

        // 창고 상태를 2차원 배열로 복사 (빈 칸은 '.' 로 표시)
        val grid = Array(n) { i -> storage[i].toCharArray() }

        // 4방향 (상, 하, 좌, 우)
        val dr = intArrayOf(-1, 1, 0, 0)
        val dc = intArrayOf(0, 0, -1, 1)

        for (req in requests) {
            val target = req[0]

            // 크레인 요청: 해당 알파벳 모두 제거
            if (req.length == 2) {
                for (i in 0 until n) {
                    for (j in 0 until m) {
                        if (grid[i][j] == target) {
                            grid[i][j] = '.'
                        }
                    }
                }
                continue
            }

            // 지게차 요청: 외부와 연결된 빈칸을 BFS로 탐색 (확장 격자 사용)
            val H = n + 2
            val W = m + 2
            val visited = Array(H) { BooleanArray(W) }
            val q: ArrayDeque<Pair<Int, Int>> = ArrayDeque()

            // (0,0)은 완전 바깥 영역
            visited[0][0] = true
            q.addLast(0 to 0)

            while (q.isNotEmpty()) {
                val (r, c) = q.removeFirst()

                for (d in 0 until 4) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]

                    if (nr !in 0 until H || nc !in 0 until W) continue
                    if (visited[nr][nc]) continue

                    // 실제 창고 범위 (1..n, 1..m) 안인지 확인
                    if (nr in 1..n && nc in 1..m) {
                        // 창고 안에서 빈칸이면 이동 가능
                        if (grid[nr - 1][nc - 1] == '.') {
                            visited[nr][nc] = true
                            q.addLast(nr to nc)
                        }
                    } else {
                        // 창고 밖은 항상 이동 가능
                        visited[nr][nc] = true
                        q.addLast(nr to nc)
                    }
                }
            }

            // 외부와 연결된 빈칸(또는 외부)에 인접한 target 컨테이너 제거
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (grid[i][j] != target) continue

                    val er = i + 1  // 확장 격자 기준 좌표
                    val ec = j + 1
                    var removable = false

                    for (d in 0 until 4) {
                        val nr = er + dr[d]
                        val nc = ec + dc[d]

                        if (nr !in 0 until H || nc !in 0 until W) continue
                        if (visited[nr][nc]) {
                            removable = true
                            break
                        }
                    }

                    if (removable) {
                        grid[i][j] = '.'
                    }
                }
            }
        }

        // 남은 컨테이너 개수 세기
        var answer = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] != '.') answer++
            }
        }

        return answer
    }
}