class Solution {
    fun solution(a: Int, b: Int, n: Int): Int {
        var bottles = n
        var total = 0

        while (bottles >= a) {
            val exchanged = (bottles / a) * b  // 이번에 받은 콜라
            total += exchanged                 // 총 받은 콜라에 더함
            bottles = (bottles % a) + exchanged  // 남은 병 + 받은 콜라 마신 병
        }

        return total
    }
}
