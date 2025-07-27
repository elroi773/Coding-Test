class Solution {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        val nameToScore = mutableMapOf<String, Int>()
        
        // 이름 → 점수 맵핑
        for (i in name.indices) {
            nameToScore[name[i]] = yearning[i]
        }

        // 각 사진별 점수 계산
        val result = mutableListOf<Int>()
        for (pic in photo) {
            var total = 0
            for (person in pic) {
                total += nameToScore[person] ?: 0 // 없으면 0점
            }
            result.add(total)
        }

        return result.toIntArray()
    }
}
