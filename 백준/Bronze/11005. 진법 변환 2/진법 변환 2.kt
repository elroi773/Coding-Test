import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    var n = sc.nextInt()
    val b = sc.nextInt()

    val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val sb = StringBuilder()

    while (n > 0) {
        sb.append(digits[n % b])
        n /= b
    }

    println(sb.reverse().toString())
}