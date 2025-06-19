class Solution {
    fun solution(order: Array<String>): Int {
        var answer = 0
        for (menu in order) {
            answer += if (menu.contains("cafelatte")) 5000 else 4500
        }
        return answer
    }
}
