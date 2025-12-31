import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s = br.readLine().trim()
    val cnt = IntArray(26)

    for (ch in s) {
        val u = ch.uppercaseChar()
        cnt[u - 'A']++
    }

    val mx = cnt.maxOrNull()!!
    val indices = cnt.indices.filter { cnt[it] == mx }

    if (indices.size > 1) {
        println("?")
    } else {
        println(('A'.code + indices[0]).toChar())
    }
}