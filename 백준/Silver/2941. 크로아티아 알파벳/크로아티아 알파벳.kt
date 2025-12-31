fun main() {
    val s = readLine()!!.trim()
    var i = 0
    var count = 0

    while (i < s.length) {
        if (i + 2 < s.length && s[i] == 'd' && s[i + 1] == 'z' && s[i + 2] == '=') {
            count++
            i += 3
        } else if (i + 1 < s.length) {
            val a = s[i]
            val b = s[i + 1]
            if ((a == 'c' && (b == '=' || b == '-')) ||
                (a == 'd' && b == '-') ||
                (a == 'l' && b == 'j') ||
                (a == 'n' && b == 'j') ||
                (a == 's' && b == '=') ||
                (a == 'z' && b == '=')) {
                count++
                i += 2
            } else {
                count++
                i += 1
            }
        } else {
            count++
            i += 1
        }
    }

    print(count)
}