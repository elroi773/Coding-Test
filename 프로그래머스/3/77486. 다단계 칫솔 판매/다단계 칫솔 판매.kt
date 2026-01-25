class Solution {
    fun solution(
        enroll: Array<String>,
        referral: Array<String>,
        seller: Array<String>,
        amount: IntArray
    ): IntArray {
        val n = enroll.size

        // 이름 -> 인덱스
        val idx = HashMap<String, Int>(n * 2)
        for (i in 0 until n) idx[enroll[i]] = i

        // 부모(추천인) 인덱스: 없으면 -1(센터)
        val parent = IntArray(n)
        for (i in 0 until n) {
            parent[i] = if (referral[i] == "-") -1 else idx[referral[i]]!!
        }

        val profit = IntArray(n)

        // 판매 기록 처리
        for (i in seller.indices) {
            var cur = idx[seller[i]]!!
            var money = amount[i] * 100

            while (cur != -1 && money > 0) {
                val give = money / 10        // 10% (원 단위 절사)
                val keep = money - give
                profit[cur] += keep

                cur = parent[cur]
                money = give                 // 위로 전달
            }
        }

        return profit
    }
}