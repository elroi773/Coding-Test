class Solution {
    fun solution(date1: IntArray, date2: IntArray): Int {
        for (i in 0..2) {
            if (date1[i] < date2[i]) return 1
            if (date1[i] > date2[i]) return 0
        }
        return 0
    }
}
