class Solution {
    fun solution(num_list: IntArray, n: Int): IntArray {
        val front = num_list.slice(0 until n)
        val back = num_list.slice(n until num_list.size)
        return (back + front).toIntArray()
    }
}
