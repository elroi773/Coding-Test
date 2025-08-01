class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var index1 = 0  // cards1 인덱스
        var index2 = 0  // cards2 인덱스

        for (word in goal) {
            when {
                index1 < cards1.size && cards1[index1] == word -> index1++
                index2 < cards2.size && cards2[index2] == word -> index2++
                else -> return "No"  // 둘 다 해당 안 되면 실패
            }
        }

        return "Yes"
    }
}
