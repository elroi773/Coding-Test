class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer = 0

        // 1. 원하는 상품과 수량을 Map으로 저장
        val wantMap = mutableMapOf<String, Int>()
        for (i in want.indices) {
            wantMap[want[i]] = number[i]
        }

        // 2. discount 배열에서 10일씩 확인
        for (i in 0..discount.size - 10) {
            val windowMap = mutableMapOf<String, Int>()

            // 10일 동안 각 상품 수량 카운트
            for (j in i until i + 10) {
                val item = discount[j]
                windowMap[item] = windowMap.getOrDefault(item, 0) + 1
            }

            // 3. Map 비교
            var match = true
            for ((item, qty) in wantMap) {
                if (windowMap[item] != qty) {
                    match = false
                    break
                }
            }

            if (match) answer++
        }

        return answer
    }
}
