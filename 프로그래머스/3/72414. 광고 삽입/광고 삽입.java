import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = toSec(play_time);
        int advSec  = toSec(adv_time);

        if (advSec >= playSec) return "00:00:00";

        long[] diff = new long[playSec + 2]; // +2 for safe indexing

        // [start, end) 로 처리
        for (String log : logs) {
            int dash = log.indexOf('-');
            int s = toSec(log.substring(0, dash));
            int e = toSec(log.substring(dash + 1));
            diff[s] += 1;
            diff[e] -= 1;
        }

        // prefixWatch[t] = 0초 ~ (t-1)초까지 누적 재생시간(초 합)
        long[] prefixWatch = new long[playSec + 2];
        long viewers = 0;

        for (int t = 0; t < playSec; t++) {
            viewers += diff[t];
            prefixWatch[t + 1] = prefixWatch[t] + viewers;
        }

        int bestStart = 0;
        long bestValue = -1;

        for (int start = 0; start <= playSec - advSec; start++) {
            int end = start + advSec;
            long value = prefixWatch[end] - prefixWatch[start];
            if (value > bestValue) {
                bestValue = value;
                bestStart = start;
            }
            // 동일하면 더 빠른 start 유지(갱신 안 함)
        }

        return toTime(bestStart);
    }

    private int toSec(String time) {
        int h = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        int m = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        int s = (time.charAt(6) - '0') * 10 + (time.charAt(7) - '0');
        return h * 3600 + m * 60 + s;
    }

    private String toTime(int sec) {
        int h = sec / 3600;
        int m = (sec % 3600) / 60;
        int s = sec % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}