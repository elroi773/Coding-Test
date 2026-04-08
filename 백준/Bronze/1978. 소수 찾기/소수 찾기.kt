import kotlin.math.sqrt

fun isPrime(x: Int): Boolean {
    if (x == 1) return false
    for (i in 2..sqrt(x.toDouble()).toInt()) {
        if (x % i == 0) return false
    }
    return true
}

fun main() {
    val n = readLine()!!.toInt()
    val numbers = readLine()!!.split(" ").map { it.toInt() }

    var count = 0
    for (num in numbers) {
        if (isPrime(num)) count++
    }

    println(count)
}