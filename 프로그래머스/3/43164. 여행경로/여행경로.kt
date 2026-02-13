import java.util.ArrayDeque

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        // 1) 그래프 구성: from -> destinations
        val g = HashMap<String, MutableList<String>>()
        for (t in tickets) {
            val from = t[0]
            val to = t[1]
            g.getOrPut(from) { mutableListOf() }.add(to)
        }

        // 2) 내림차순 정렬 후, 마지막을 꺼내면(삭제) 사전순(오름차순) 우선 사용
        for (list in g.values) {
            list.sortDescending()
        }

        // 3) Hierholzer (스택)
        val st = ArrayDeque<String>()
        st.addLast("ICN")

        val route = ArrayList<String>(tickets.size + 1)

        while (st.isNotEmpty()) {
            val cur = st.last()
            val nexts = g[cur]
            if (nexts != null && nexts.isNotEmpty()) {
                val nxt = nexts.removeAt(nexts.lastIndex)
                st.addLast(nxt)
            } else {
                route.add(st.removeLast())
            }
        }

        route.reverse()
        return route.toTypedArray()
    }
}