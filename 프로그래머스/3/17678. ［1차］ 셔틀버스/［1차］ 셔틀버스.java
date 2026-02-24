import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // "HH:MM" -> 분
        List<Integer> crew = new ArrayList<>();
        for (String time : timetable) {
            crew.add(toMin(time));
        }
        Collections.sort(crew);

        int idx = 0; // 아직 탑승하지 않은 크루 인덱스

        for (int i = 0; i < n; i++) {
            int busTime = 9 * 60 + i * t; // 09:00 + i*t
            int boarded = 0;

            // 현재 셔틀에 탈 수 있는 크루 태우기
            while (idx < crew.size() && crew.get(idx) <= busTime && boarded < m) {
                idx++;
                boarded++;
            }

            // 마지막 셔틀이면 콘의 도착 시간 결정
            if (i == n - 1) {
                if (boarded < m) {
                    return toStr(busTime); // 자리 남음
                } else {
                    return toStr(crew.get(idx - 1) - 1); // 꽉 참
                }
            }
        }

        return "";
    }

    // "HH:MM" -> 분
    private int toMin(String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        return h * 60 + m;
    }

    // 분 -> "HH:MM"
    private String toStr(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }
}