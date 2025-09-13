class Solution {
    fun solution(babbling: Array<String>): Int {
        val words = arrayOf("aya", "ye", "woo", "ma")
        var answer = 0

        for (s in babbling) {
            var pos = 0
            var last = ""
            var valid = true

            while (pos < s.length) {
                var matched = false

                for (w in words) {
                    if (s.startsWith(w, pos) && w != last) {
                        pos += w.length
                        last = w
                        matched = true
                        break
                    }
                }

                if (!matched) {
                    valid = false
                    break
                }
            }

            if (valid) answer++
        }

        return answer
    }
}
