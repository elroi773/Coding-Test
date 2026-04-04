import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class FastScanner {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var st: StringTokenizer? = null

    fun nextInt(): Int {
        while (st == null || !st!!.hasMoreTokens()) {
            st = StringTokenizer(br.readLine())
        }
        return st!!.nextToken().toInt()
    }
}

fun main() {
    val fs = FastScanner()
    val sb = StringBuilder()

    while (true) {
        val a = fs.nextInt()
        val b = fs.nextInt()

        if (a == 0 && b == 0) break

        when {
            b % a == 0 -> sb.append("factor\n")
            a % b == 0 -> sb.append("multiple\n")
            else -> sb.append("neither\n")
        }
    }

    print(sb.toString())
}