import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        int[][] intervals = new int[n][2]; // [start, end]
        
        for (int i = 0; i < n; i++) {
            String[] parts = lines[i].split(" ");
            String timeStr = parts[1];
            String durStr = parts[2];
            
            int endMs = parseTimeToMs(timeStr);
            int durMs = parseDurationToMs(durStr);
            int startMs = endMs - durMs + 1; // inclusive
            
            intervals[i][0] = startMs;
            intervals[i][1] = endMs;
        }
        
        int answer = 0;
        
        // 각 요청의 시작/끝 시점을 기준으로 1초 구간 검사
        for (int i = 0; i < n; i++) {
            int[] candidates = {intervals[i][0], intervals[i][1]};
            
            for (int windowStart : candidates) {
                int windowEnd = windowStart + 999; // [windowStart, windowStart+999]
                int count = 0;
                
                for (int j = 0; j < n; j++) {
                    int start = intervals[j][0];
                    int end = intervals[j][1];
                    
                    // 겹치면 카운트
                    if (end >= windowStart && start <= windowEnd) {
                        count++;
                    }
                }
                
                answer = Math.max(answer, count);
            }
        }
        
        return answer;
    }
    
    private int parseTimeToMs(String timeStr) {
        // "hh:mm:ss.sss"
        int h = Integer.parseInt(timeStr.substring(0, 2));
        int m = Integer.parseInt(timeStr.substring(3, 5));
        int s = Integer.parseInt(timeStr.substring(6, 8));
        int ms = Integer.parseInt(timeStr.substring(9, 12));
        
        return ((h * 3600 + m * 60 + s) * 1000) + ms;
    }
    
    private int parseDurationToMs(String durStr) {
        // "2s", "2.0s", "0.351s"
        String t = durStr.substring(0, durStr.length() - 1); // remove 's'
        
        if (!t.contains(".")) {
            return Integer.parseInt(t) * 1000;
        }
        
        String[] parts = t.split("\\.");
        int sec = Integer.parseInt(parts[0]);
        String msStr = parts[1];
        
        while (msStr.length() < 3) msStr += "0";
        if (msStr.length() > 3) msStr = msStr.substring(0, 3);
        
        int ms = Integer.parseInt(msStr);
        return sec * 1000 + ms;
    }
}