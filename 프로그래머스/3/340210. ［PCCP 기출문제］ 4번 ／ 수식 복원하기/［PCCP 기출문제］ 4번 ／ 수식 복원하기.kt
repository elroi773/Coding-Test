class Solution {

    fun solution(expressions: Array<String>): Array<String> {
        val parsed = expressions.map {
            val parts = it.split(" ")
            Triple(parts[0], parts[2], Pair(parts[1], parts[4]))
            // A, B, (op, C)
        }

        // 문자열을 base 진법 정수로 변환 (불가능하면 null)
        fun toDecimal(s: String, base: Int): Int? {
            var value = 0
            for (ch in s) {
                val d = ch - '0'
                if (d < 0 || d >= base) return null
                value = value * base + d
            }
            return value
        }

        // 가능한 진법 후보
        val possibleBases = mutableListOf<Int>()

        for (base in 2..9) {
            var ok = true
            for ((A, B, pair) in parsed) {
                val (op, C) = pair
                val a = toDecimal(A, base)
                val b = toDecimal(B, base)
                if (a == null || b == null) {
                    ok = false
                    break
                }

                if (C != "X") {
                    val c = toDecimal(C, base)
                    if (c == null) {
                        ok = false
                        break
                    }
                    val res = if (op == "+") a + b else a - b
                    if (res != c) {
                        ok = false
                        break
                    }
                }
            }
            if (ok) possibleBases.add(base)
        }

        val answer = mutableListOf<String>()

        // X가 있는 수식 처리
        for (exp in expressions) {
            if (!exp.endsWith("X")) continue

            val parts = exp.split(" ")
            val A = parts[0]
            val op = parts[1]
            val B = parts[2]

            val results = mutableSetOf<String>()

            for (base in possibleBases) {
                val a = toDecimal(A, base)
                val b = toDecimal(B, base)
                if (a == null || b == null) continue

                val value = if (op == "+") a + b else a - b
                results.add(value.toString(base))
            }

            val result = if (results.size == 1) results.first() else "?"
            answer.add("$A $op $B = $result")
        }

        return answer.toTypedArray()
    }
}
