class Solution {
    fun solution(start_num: Int, end_num: Int): IntArray {
        // start_num부터 end_num까지 숫자를 리스트로 만들고 IntArray로 변환
        return (start_num..end_num).toList().toIntArray()
    }
}
