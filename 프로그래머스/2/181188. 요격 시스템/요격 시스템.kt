class Solution {
    fun solution(targets: Array<IntArray>): Int {
        // 끝나는 지점(e) 기준 오름차순 정렬
        targets.sortWith(compareBy({ it[1] }, { it[0] }))

        var answer = 0
        var last = Int.MIN_VALUE   // 마지막 요격 미사일을 쏜 x 좌표 (논리적으로는 e 바로 앞)

        for (t in targets) {
            val s = t[0]
            val e = t[1]

            // 현재 구간 (s, e)의 시작이 last 이상이면
            // 기존 미사일로는 커버 불가 → 새로 발사
            if (s >= last) {
                answer++
                last = e   // 이 구간의 끝점 바로 앞에 쏜 것으로 간주
            }
        }

        return answer
    }
}