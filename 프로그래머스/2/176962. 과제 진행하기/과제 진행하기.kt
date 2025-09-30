class Solution {
    data class Task(var name: String, var start: Int, var playtime: Int)

    private fun toMinutes(time: String): Int {
        val (h, m) = time.split(":").map { it.toInt() }
        return h * 60 + m
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val tasks = plans.map { Task(it[0], toMinutes(it[1]), it[2].toInt()) }
            .sortedBy { it.start }

        val answer = mutableListOf<String>()
        val stack = ArrayDeque<Task>()

        for (i in tasks.indices) {
            val cur = tasks[i]
            val now = cur.start
            var playtime = cur.playtime

            val nextStart = if (i < tasks.size - 1) tasks[i + 1].start else Int.MAX_VALUE

            if (now + playtime <= nextStart) {
                // 현재 과제를 끝낼 수 있음
                answer.add(cur.name)
                var freeTime = nextStart - (now + playtime)

                // 멈춘 과제 이어서 처리
                while (freeTime > 0 && stack.isNotEmpty()) {
                    val prev = stack.removeLast()
                    if (prev.playtime <= freeTime) {
                        freeTime -= prev.playtime
                        answer.add(prev.name)
                    } else {
                        prev.playtime -= freeTime
                        stack.addLast(prev)
                        break
                    }
                }
            } else {
                // 다 못 끝내고 멈춤
                cur.playtime = playtime - (nextStart - now)
                stack.addLast(cur)
            }
        }

        // 스택에 남은 과제 처리
        while (stack.isNotEmpty()) {
            answer.add(stack.removeLast().name)
        }

        return answer.toTypedArray()
    }
}
