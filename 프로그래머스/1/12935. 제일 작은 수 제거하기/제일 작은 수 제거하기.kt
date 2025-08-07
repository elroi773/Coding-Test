class Solution {
    fun solution(arr: IntArray): IntArray {
        // 배열의 크기가 1이면 [-1] 반환
        if (arr.size == 1) return intArrayOf(-1)
        
        // 최소값 찾기
        val minValue = arr.minOrNull()!!
        
        // 최소값 제거하고 새로운 배열 생성
        return arr.filter { it != minValue }.toIntArray()
    }
}
