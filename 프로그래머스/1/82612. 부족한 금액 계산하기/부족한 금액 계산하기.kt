class Solution {
    fun solution(price: Int, money: Int, count: Int): Long {
        val total = price.toLong() * count * (count + 1) / 2
        val needed = total - money
        return if (needed > 0) needed else 0
    }
}
