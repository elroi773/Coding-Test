class Solution {
    fun solution(my_string: String, alp: String): String {
        var answer = ""
        for (c in my_string) {
            if (c.toString() == alp) {
                answer += c.uppercase()
            } else {
                answer += c
            }
        }
        return answer
    }
}
