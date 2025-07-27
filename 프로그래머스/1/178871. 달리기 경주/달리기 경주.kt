class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val nameToIndex = HashMap<String, Int>()
        
        // 선수 이름 -> 인덱스 매핑
        for (i in players.indices) {
            nameToIndex[players[i]] = i
        }

        for (name in callings) {
            val idx = nameToIndex[name]!!
            val prevIdx = idx - 1
            val prevPlayer = players[prevIdx]

            // 선수 위치 스왑
            players[prevIdx] = name
            players[idx] = prevPlayer

            // 인덱스 업데이트
            nameToIndex[name] = prevIdx
            nameToIndex[prevPlayer] = idx
        }

        return players
    }
}
