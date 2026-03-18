fun main() {
    val chess = listOf(1, 1, 2, 2, 2, 8)
    val input = readLine()!!.split(" ").map { it.toInt() }

    for (i in 0..5) {
        print("${chess[i] - input[i]} ")
    }
}