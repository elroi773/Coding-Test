fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }

    var count = 0

    for (i in 1..n) {
        if (n % i == 0) {
            count++
            if (count == k) {
                println(i)
                return
            }
        }
    }

    println(0)
}