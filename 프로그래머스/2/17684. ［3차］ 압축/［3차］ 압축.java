import java.util.*;

class Solution {
    public int[] solution(String msg) {
        // 1) 사전 초기화: A~Z => 1~26
        Map<String, Integer> dic = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            dic.put(String.valueOf((char)('A' + i)), i + 1);
        }
        int nextIdx = 27;

        List<Integer> out = new ArrayList<>();
        int n = msg.length();
        int i = 0;

        while (i < n) {
            // 2) 현재 위치에서 사전에 있는 가장 긴 문자열 w 찾기
            int j = i + 1;
            while (j <= n && dic.containsKey(msg.substring(i, j))) {
                j++;
            }

            // j는 1칸 더 나간 상태 -> 실제 w = [i, j-1)
            String w = msg.substring(i, j - 1);
            out.add(dic.get(w));

            // 4) 다음 글자가 남아있다면 w+c 등록 (j <= n 이면 [i, j) 존재)
            if (j <= n) {
                String wc = msg.substring(i, j); // w + c
                dic.put(wc, nextIdx++);
            }

            // 3) w만큼 전진
            i += w.length();
        }

        // List -> int[]
        int[] answer = new int[out.size()];
        for (int k = 0; k < out.size(); k++) answer[k] = out.get(k);
        return answer;
    }
}