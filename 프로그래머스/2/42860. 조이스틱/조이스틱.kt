class Solution {
    fun solution(name: String): Int {
        var answer = 0
        val n = name.length

        // 1. 위/아래(알파벳 변경) 최소 이동
        for (ch in name) {
            val up = ch - 'A'
            val down = 'Z' - ch + 1
            answer += minOf(up, down)
        }

        // 2. 좌/우 커서 이동 최소값 계산
        var move = n - 1  // 기본적으로 오른쪽 끝까지 이동

        for (i in 0 until n) {
            var next = i + 1

            // 연속되는 A 구간 찾기
            while (next < n && name[next] == 'A') {
                next++
            }

            // 1) i까지 갔다가 뒤로 돌아서 끝으로 가기
            move = minOf(move, i * 2 + (n - next))

            // 2) 뒤쪽부터 먼저 갔다가 i로 돌아오기
            move = minOf(move, i + (n - next) * 2)
        }

        return answer + move
    }
}
