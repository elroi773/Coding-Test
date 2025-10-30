class Solution {
    // 최대공약수 (GCD) 함수
    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val tmp = x % y
            x = y
            y = tmp
        }
        return x
    }

    // 배열의 전체 GCD 구하기
    private fun getGCD(arr: IntArray): Int {
        var result = arr[0]
        for (i in 1 until arr.size) {
            result = gcd(result, arr[i])
        }
        return result
    }

    // arr의 모든 원소가 x로 나누어지지 않는지 검사
    private fun notDivisible(arr: IntArray, x: Int): Boolean {
        for (n in arr) {
            if (n % x == 0) return false
        }
        return true
    }

    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        val gcdA = getGCD(arrayA)
        val gcdB = getGCD(arrayB)

        var candidateA = 0
        var candidateB = 0

        // 조건 1: A의 모든 수를 나누고, B의 어떤 수도 나누지 않음
        if (notDivisible(arrayB, gcdA)) candidateA = gcdA

        // 조건 2: B의 모든 수를 나누고, A의 어떤 수도 나누지 않음
        if (notDivisible(arrayA, gcdB)) candidateB = gcdB

        return maxOf(candidateA, candidateB)
    }
}
