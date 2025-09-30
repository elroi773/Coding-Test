import java.util.*;

class Solution {
    static class Task {
        String name;
        int start;
        int playtime;

        Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }

    // "hh:mm" -> 분 단위로 변환
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    public String[] solution(String[][] plans) {
        int n = plans.length;
        List<Task> tasks = new ArrayList<>();

        // 입력을 Task 리스트로 변환
        for (String[] plan : plans) {
            String name = plan[0];
            int start = toMinutes(plan[1]);
            int playtime = Integer.parseInt(plan[2]);
            tasks.add(new Task(name, start, playtime));
        }

        // 시작 시각 기준 정렬
        tasks.sort(Comparator.comparingInt(t -> t.start));

        List<String> result = new ArrayList<>();
        Stack<Task> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Task cur = tasks.get(i);
            int now = cur.start;
            int playtime = cur.playtime;

            // 다음 과제 시작 시각 (없으면 매우 큰 값)
            int nextStart = (i < n - 1) ? tasks.get(i + 1).start : Integer.MAX_VALUE;

            if (now + playtime <= nextStart) {
                // 현재 과제 끝낼 수 있음
                result.add(cur.name);

                int freeTime = nextStart - (now + playtime);

                // 멈춰둔 과제 이어서 하기
                while (freeTime > 0 && !stack.isEmpty()) {
                    Task prev = stack.pop();
                    if (prev.playtime <= freeTime) {
                        freeTime -= prev.playtime;
                        result.add(prev.name);
                    } else {
                        prev.playtime -= freeTime;
                        stack.push(prev);
                        break;
                    }
                }
            } else {
                // 다 못 끝내고 멈춤
                cur.playtime = playtime - (nextStart - now);
                stack.push(cur);
            }
        }

        // 스택에 남은 과제 마저 끝내기
        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }

        return result.toArray(new String[0]);
    }
}
