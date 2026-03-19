fun main() {
    val (n, bStr) = readLine()!!.split(" ")
    val b = bStr.toInt()

    var result = 0

    for (ch in n) {
        val value = if (ch in '0'..'9') {
            ch - '0'
        } else {
            ch - 'A' + 10
        }

        result = result * b + value
    }

    println(result)
}