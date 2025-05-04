class Solution {
    fun solution(str_list: Array<String>): Array<String> {
        for (i in str_list.indices) {
            when (str_list[i]) {
                "l" -> return str_list.sliceArray(0 until i)
                "r" -> return str_list.sliceArray(i + 1 until str_list.size)
            }
        }
        return arrayOf()
    }
}
