class Solution {
    fun solution(ingredient: IntArray): Int {
        var answer = 0
        val stack = mutableListOf<Int>()

        for (i in ingredient) {
            stack.add(i)

            if (stack.size >= 4) {
                val len = stack.size
                if (stack[len-4] == 1 && stack[len-3] == 2 &&
                    stack[len-2] == 3 && stack[len-1] == 1) {
                    repeat(4) { stack.removeAt(stack.size - 1) }
                    answer++
                }
            }
        }
        return answer
    }
}
