class Solution {
    fun solution(record: Array<String>): Array<String> {
        val nicknameMap = mutableMapOf<String, String>()  // uid → nickname 최신값 저장

        // 1) Enter, Change 명령으로 닉네임 최신화
        for (r in record) {
            val parts = r.split(" ")
            val action = parts[0]
            val uid = parts[1]

            if (action == "Enter" || action == "Change") {
                val nickname = parts[2]
                nicknameMap[uid] = nickname
            }
        }

        // 2) Enter, Leave에 해당 메시지 생성
        val result = mutableListOf<String>()

        for (r in record) {
            val parts = r.split(" ")
            val action = parts[0]
            val uid = parts[1]

            when (action) {
                "Enter" -> result.add("${nicknameMap[uid]}님이 들어왔습니다.")
                "Leave" -> result.add("${nicknameMap[uid]}님이 나갔습니다.")
            }
        }

        return result.toTypedArray()
    }
}
