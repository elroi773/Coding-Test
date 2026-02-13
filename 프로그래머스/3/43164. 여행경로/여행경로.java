import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        // 1) 그래프 구성: 출발 -> 목적지들
        Map<String, ArrayList<String>> g = new HashMap<>();
        for (String[] t : tickets) {
            g.computeIfAbsent(t[0], k -> new ArrayList<>()).add(t[1]);
        }

        // 2) 내림차순 정렬해두고, remove(size-1)로 꺼내면 실제 선택은 오름차순(사전순) 우선
        for (ArrayList<String> list : g.values()) {
            list.sort(Collections.reverseOrder());
        }

        // 3) Hierholzer (스택 기반)
        Deque<String> st = new ArrayDeque<>();
        st.push("ICN");

        List<String> route = new ArrayList<>(tickets.length + 1);

        while (!st.isEmpty()) {
            String cur = st.peek();
            ArrayList<String> nexts = g.get(cur);

            if (nexts != null && !nexts.isEmpty()) {
                // 다음 목적지 하나 사용
                String nxt = nexts.remove(nexts.size() - 1);
                st.push(nxt);
            } else {
                // 더 갈 곳 없으면 경로 확정(뒤에서부터 쌓임)
                route.add(st.pop());
            }
        }

        Collections.reverse(route);
        return route.toArray(new String[0]);
    }
}