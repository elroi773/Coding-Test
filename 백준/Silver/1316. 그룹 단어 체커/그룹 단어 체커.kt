class Main {
    companion object {
        fun isGroupWord(word: String): Boolean {
            val visited = BooleanArray(26)
            var prev = ' '

            for (ch in word) {
                if (ch != prev) {
                    if (visited[ch - 'a']) {
                        return false
                    }
                    visited[ch - 'a'] = true
                }
                prev = ch
            }

            return true
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val n = readLine()!!.toInt()
            var count = 0

            repeat(n) {
                val word = readLine()!!
                if (isGroupWord(word)) {
                    count++
                }
            }

            println(count)
        }
    }
}