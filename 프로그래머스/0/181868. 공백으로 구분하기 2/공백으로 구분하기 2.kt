class Solution {
    fun solution(my_string: String): Array<String> {
        return my_string.trim().split("\\s+".toRegex()).toTypedArray()
    }
}
