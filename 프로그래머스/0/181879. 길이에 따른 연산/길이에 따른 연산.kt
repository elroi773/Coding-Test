class Solution {
    fun solution(num_list: IntArray): Int {
        return if (num_list.size >= 11) {
            num_list.sum()  // 리스트의 합을 구함
        } else {
            num_list.reduce { acc, curr -> acc * curr }  // 리스트의 곱을 구함
        }
    }
}

