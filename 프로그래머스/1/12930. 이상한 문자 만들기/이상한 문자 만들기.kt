class Solution {
    fun solution(s: String): String {
        return s.split(" ").joinToString(" ") { word ->
            word.mapIndexed { index, c ->
                if (index % 2 == 0) c.uppercaseChar() else c.lowercaseChar()
            }.joinToString("")
        }
    }
}
