class Solution {
    fun solution(arr: IntArray): Int {
        var lcm = arr[0]

        for (i in 1 until arr.size) {
            lcm = lcm * arr[i] / gcd(lcm, arr[i])
        }

        return lcm
    }

    // 최대공약수 (유클리드 호제법)
    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = x % y
            x = y
            y = temp
        }
        return x
    }
}
