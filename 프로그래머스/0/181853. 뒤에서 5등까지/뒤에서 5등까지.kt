class Solution {
    fun solution(num_list: IntArray): IntArray {
        return num_list.sorted().take(5).toIntArray()
    }
}
