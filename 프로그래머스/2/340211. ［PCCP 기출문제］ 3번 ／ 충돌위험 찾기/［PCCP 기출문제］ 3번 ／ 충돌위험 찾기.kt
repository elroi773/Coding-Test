class Solution {
    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        val x = routes.size          // 로봇 수
        val m = routes[0].size       // 각 로봇의 경로 길이

        // 로봇 상태
        val rs = IntArray(x)         // 현재 r
        val cs = IntArray(x)         // 현재 c
        val segIdx = IntArray(x)     // 현재 "도착 포인트" 인덱스 (1 ~ m-1)
        val active = BooleanArray(x) // 센터 안에 있는지 여부

        // 초기 상태 설정: 각 로봇은 routes[i][0] 포인트에서 시작, routes[i][1]으로 이동 시작
        for (i in 0 until x) {
            val startPointId = routes[i][0] - 1  // 0-based
            rs[i] = points[startPointId][0]
            cs[i] = points[startPointId][1]
            segIdx[i] = 1                       // routes[i][0] -> routes[i][1]
            active[i] = true
        }

        var answer = 0

        while (true) {
            var anyActive = false
            var collisionsThisTime = 0

            // 이번 시각(t)의 좌표별 로봇 개수
            val count = HashMap<Pair<Int, Int>, Int>()

            for (i in 0 until x) {
                if (!active[i]) continue

                anyActive = true
                val r = rs[i]
                val c = cs[i]
                val key = Pair(r, c)

                val prev = count[key] ?: 0
                val cur = prev + 1
                count[key] = cur

                // 1 -> 2가 되는 순간만 위험 상황 1회 추가
                if (cur == 2) {
                    collisionsThisTime++
                }
            }

            // 더 이상 센터 안에 로봇이 없으면 종료
            if (!anyActive) break

            // 이번 시각의 위험 상황 합산
            answer += collisionsThisTime

            // 다음 시각(t+1)을 위해 로봇 이동
            for (i in 0 until x) {
                if (!active[i]) continue

                var idx = segIdx[i]  // 현재 도착 포인트 인덱스

                if (idx >= m) {
                    // 이론상 거의 안 오는 상황이지만, 안전장치
                    active[i] = false
                    continue
                }

                var targetId = routes[i][idx] - 1
                var tr = points[targetId][0]
                var tc = points[targetId][1]

                // 이미 목표 포인트에 서 있는 경우
                if (rs[i] == tr && cs[i] == tc) {
                    if (idx == m - 1) {
                        // 마지막 포인트에 도착 → 다음 시각부터 센터 밖
                        active[i] = false
                        continue
                    } else {
                        // 다음 구간으로 넘어감 (routes[i][idx] -> routes[i][idx+1])
                        idx++
                        segIdx[i] = idx
                        targetId = routes[i][idx] - 1
                        tr = points[targetId][0]
                        tc = points[targetId][1]
                    }
                }

                if (!active[i]) continue // 바로 위에서 나간 경우 방지

                // r 우선, 그 다음 c 방향으로 한 칸 이동
                when {
                    rs[i] != tr -> rs[i] += if (tr > rs[i]) 1 else -1
                    cs[i] != tc -> cs[i] += if (tc > cs[i]) 1 else -1
                    // 둘 다 같을 경우는 위에서 처리됨
                }
            }
        }

        return answer
    }
}