class Solution {
    fun solution(word: String): Int {
        val vowels = listOf('A', 'E', 'I', 'O', 'U')
        val weights = listOf(781, 156, 31, 6, 1) // 자리수별 가중치
        var answer = 0
        
        for (i in word.indices) {
            val index = vowels.indexOf(word[i]) // 해당 문자 인덱스 가져오기
            answer += index * weights[i] + 1 // 해당 자리 계산
        }
        
        return answer
    }
}
