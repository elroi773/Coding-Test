import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val sb = StringBuilder()

    repeat(t) {
        var c = br.readLine().toInt()

        val q = c / 25
        c %= 25

        val d = c / 10
        c %= 10

        val n = c / 5
        c %= 5

        val p = c

        sb.append("$q $d $n $p\n")
    }

    print(sb.toString())
}