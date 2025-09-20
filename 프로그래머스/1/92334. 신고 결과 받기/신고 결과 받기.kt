class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val answer = IntArray(id_list.size)

        // 1. 중복 신고 제거
        val uniqueReports = report.toSet()

        // 2. 신고 관계 저장 (신고당한 사람 -> 신고한 사람들 집합)
        val reportedBy = mutableMapOf<String, MutableSet<String>>()
        id_list.forEach { id ->
            reportedBy[id] = mutableSetOf()
        }

        uniqueReports.forEach { r ->
            val (reporter, target) = r.split(" ")
            reportedBy[target]?.add(reporter)
        }

        // 3. 정지된 유저 찾기
        val banned = reportedBy.filter { it.value.size >= k }.keys

        // 4. 메일 횟수 계산
        val idIndex = id_list.withIndex().associate { it.value to it.index }
        banned.forEach { user ->
            reportedBy[user]?.forEach { reporter ->
                val idx = idIndex[reporter]!!
                answer[idx]++
            }
        }

        return answer
    }
}
