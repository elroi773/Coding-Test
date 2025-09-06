class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var maxW = 0
        var maxH = 0

        for (card in sizes) {
            val w = maxOf(card[0], card[1])  // 큰 쪽
            val h = minOf(card[0], card[1])  // 작은 쪽

            maxW = maxOf(maxW, w)
            maxH = maxOf(maxH, h)
        }

        return maxW * maxH
    }
}
