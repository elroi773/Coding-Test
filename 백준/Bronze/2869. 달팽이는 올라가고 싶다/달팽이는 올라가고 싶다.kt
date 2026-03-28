fun main() {
    val (A, B, V) = readLine()!!.split(" ").map { it.toLong() }

    val day = (V - A + (A - B) - 1) / (A - B) + 1
    println(day)
}