import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    while (true) {
        val n = br.readLine().toInt()
        if (n == -1) break

        val divisors = mutableListOf<Int>()
        divisors.add(1)

        var i = 2
        while (i * i <= n) {
            if (n % i == 0) {
                divisors.add(i)
                if (i != n / i) divisors.add(n / i)
            }
            i++
        }

        divisors.sort()

        val sum = divisors.sum()

        if (sum == n) {
            sb.append(n).append(" = ")
            sb.append(divisors.joinToString(" + "))
            sb.append('\n')
        } else {
            sb.append(n).append(" is NOT perfect.\n")
        }
    }

    print(sb.toString())
}