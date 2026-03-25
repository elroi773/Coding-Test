fun main() {
    val n = readLine()!!.toInt()
    val side = (1 shl n) + 1
    println(side * side)
}