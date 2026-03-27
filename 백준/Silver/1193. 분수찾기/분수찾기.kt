fun main() {
    var x = readln().toInt()
    var line = 1

    while (x > line) {
        x -= line
        line++
    }

    if (line % 2 == 0) {
        println("${x}/${line - x + 1}")
    } else {
        println("${line - x + 1}/${x}")
    }
}