class Solution {
    fun solution(my_string: String, overwrite_string: String, s: Int): String {
        return my_string.substring(0, s) + overwrite_string + my_string.substring(s + overwrite_string.length)
    }
}
