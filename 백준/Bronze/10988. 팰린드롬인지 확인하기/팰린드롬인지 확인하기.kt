import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s = br.readLine().trim()
    var l = 0
    var r = s.length - 1

    while (l < r) {
        if (s[l] != s[r]) {
            println(0)
            return
        }
        l++
        r--
    }
    println(1)
}