class Solution {
    fun solution(relation: Array<Array<String>>): Int {
        val rowLen = relation.size
        val colLen = relation[0].size

        val candidateKeys = mutableListOf<Int>()

        // 1) 모든 컬럼 조합(비트마스크)
        for (bit in 1 until (1 shl colLen)) {

            // 2) 유일성 검사
            val set = HashSet<String>()
            for (r in 0 until rowLen) {
                val sb = StringBuilder()
                for (c in 0 until colLen) {
                    if ((bit and (1 shl c)) != 0) {
                        sb.append(relation[r][c]).append(",")
                    }
                }
                set.add(sb.toString())
            }

            // 유일성 실패 → skip
            if (set.size != rowLen) continue

            // 3) 최소성 검사
            var minimal = true
            for (key in candidateKeys) {
                if ((key and bit) == key) {
                    minimal = false
                    break
                }
            }

            if (minimal) {
                candidateKeys.add(bit)
            }
        }

        return candidateKeys.size
    }
}
