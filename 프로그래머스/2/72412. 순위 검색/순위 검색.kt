import java.util.*
import kotlin.collections.HashMap

class Solution {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val db = HashMap<String, MutableList<Int>>()

        // 1. info 전처리: 각 지원자에 대해 2^4 = 16가지 조합의 key에 점수 추가
        for (line in info) {
            val parts = line.split(" ")
            val attrs = parts.subList(0, 4) // 언어, 직군, 경력, 소울푸드
            val score = parts[4].toInt()

            // 0 ~ 15 (2^4)
            for (mask in 0 until 16) {
                val keyParts = ArrayList<String>(4)
                for (i in 0 until 4) {
                    if ((mask and (1 shl i)) != 0) {
                        keyParts.add("-")      // 해당 조건 무시
                    } else {
                        keyParts.add(attrs[i]) // 원래 값 사용
                    }
                }
                val key = keyParts.joinToString(" ")
                val list = db.getOrPut(key) { mutableListOf() }
                list.add(score)
            }
        }

        // 2. 각 key별 점수 리스트 정렬
        for ((_, list) in db) {
            list.sort()
        }

        // 3. query 처리
        val answer = IntArray(query.size)

        for (idx in query.indices) {
            // "java and backend and junior and pizza 100"
            // -> "java backend junior pizza 100"
            val cleaned = query[idx].replace(" and ", " ")
            val parts = cleaned.split(" ")

            val key = parts.subList(0, 4).joinToString(" ")
            val score = parts[4].toInt()

            val list = db[key]

            if (list == null || list.isEmpty()) {
                answer[idx] = 0
                continue
            }

            // score 이상인 사람 수 = 전체 길이 - lowerBound 위치
            val lb = lowerBound(list, score)
            answer[idx] = list.size - lb
        }

        return answer
    }

    // list에서 target 이상이 처음 나오는 인덱스 (lower bound)
    private fun lowerBound(list: List<Int>, target: Int): Int {
        var left = 0
        var right = list.size  // [left, right)

        while (left < right) {
            val mid = (left + right) / 2
            if (list[mid] >= target) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}