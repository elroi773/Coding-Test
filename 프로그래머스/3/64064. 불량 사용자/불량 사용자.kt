class Solution {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {

        fun match(user: String, ban: String): Boolean {
            if (user.length != ban.length) return false
            for (i in ban.indices) {
                val b = ban[i]
                if (b == '*') continue
                if (user[i] != b) return false
            }
            return true
        }

        // banned_id 각 패턴별로 매칭 가능한 user 인덱스 후보 리스트
        val candidates: List<List<Int>> = banned_id.map { ban ->
            val list = mutableListOf<Int>()
            for (i in user_id.indices) {
                if (match(user_id[i], ban)) list.add(i)
            }
            list
        }

        // 유저 최대 8명 -> 비트마스크로 순서 무관 집합 표현
        val results = HashSet<Int>()

        fun dfs(idx: Int, usedMask: Int) {
            if (idx == banned_id.size) {
                results.add(usedMask)
                return
            }
            for (ui in candidates[idx]) {
                val bit = 1 shl ui
                if ((usedMask and bit) != 0) continue
                dfs(idx + 1, usedMask or bit)
            }
        }

        dfs(0, 0)
        return results.size
    }
}