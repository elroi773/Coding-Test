class Solution {
    fun solution(arr: IntArray): IntArray {
        val answer = IntArray(arr.size)
        for (i in arr.indices) {
            answer[i] = when {
                arr[i] >= 50 && arr[i] % 2 == 0 -> arr[i] / 2
                arr[i] < 50 && arr[i] % 2 == 1 -> arr[i] * 2
                else -> arr[i]
            }
        }
        return answer
    }
}
