class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        val map = HashMap<String, Int>()

        // 종류별 개수 세기
        for (c in clothes) {
            val kind = c[1]
            map[kind] = map.getOrDefault(kind, 0) + 1
        }

        var answer = 1

        // (개수 + 1)씩 곱하기
        for (count in map.values) {
            answer *= (count + 1)
        }

        // 아무것도 안 입은 경우 제외
        return answer - 1
    }
}
