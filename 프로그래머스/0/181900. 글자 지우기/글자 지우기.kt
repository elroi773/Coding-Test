class Solution {
    fun solution(my_string: String, indices: IntArray): String {
        val removeSet = indices.toSet() // 제거할 인덱스를 Set으로
        val result = StringBuilder()    // 문자열을 효율적으로 이어붙이기 위해 사용

        for (i in my_string.indices) {
            if (i !in removeSet) {
                result.append(my_string[i])
            }
        }

        return result.toString()
    }
}
