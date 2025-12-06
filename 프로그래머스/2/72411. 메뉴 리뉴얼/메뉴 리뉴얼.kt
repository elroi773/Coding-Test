import java.util.*

class Solution {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val result = mutableListOf<String>()

        // 주문 문자열들을 미리 정렬 (알파벳 순)
        val sortedOrders = orders.map { order ->
            val chars = order.toCharArray()
            chars.sort()
            String(chars)
        }

        for (c in course) {
            val counter = mutableMapOf<String, Int>()
            var maxCount = 0

            // 각 주문에서 길이 c짜리 조합 생성
            for (order in sortedOrders) {
                if (order.length < c) continue
                generateCombinations(order.toCharArray(), 0, c, StringBuilder(), counter)
            }

            // 최소 2번 이상 등장한 조합 중 최대 빈도 찾기
            for (cnt in counter.values) {
                if (cnt >= 2 && cnt > maxCount) {
                    maxCount = cnt
                }
            }

            if (maxCount < 2) continue

            // 최대 빈도를 가진 조합들만 결과에 추가
            for ((comb, cnt) in counter) {
                if (cnt == maxCount) {
                    result.add(comb)
                }
            }
        }

        result.sort()
        return result.toTypedArray()
    }

    // 조합 생성: chars에서 길이 targetLen인 조합을 만들고 counter에 카운트
    private fun generateCombinations(
        chars: CharArray,
        start: Int,
        targetLen: Int,
        sb: StringBuilder,
        counter: MutableMap<String, Int>
    ) {
        if (sb.length == targetLen) {
            val key = sb.toString()
            counter[key] = (counter[key] ?: 0) + 1
            return
        }

        for (i in start until chars.size) {
            sb.append(chars[i])
            generateCombinations(chars, i + 1, targetLen, sb, counter)
            sb.deleteCharAt(sb.length - 1)
        }
    }
}