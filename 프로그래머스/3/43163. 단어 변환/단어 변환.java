import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int n = words.length;

        // target이 words에 없으면 불가능
        boolean hasTarget = false;
        for (String w : words) {
            if (w.equals(target)) { hasTarget = true; break; }
        }
        if (!hasTarget) return 0;

        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();

        // begin에서 시작: begin과 1글자 다른 단어들을 dist=1로 큐에 넣기
        for (int i = 0; i < n; i++) {
            if (diffOne(begin, words[i])) {
                dist[i] = 1;
                q.addLast(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.removeFirst();

            if (words[cur].equals(target)) return dist[cur];

            for (int nxt = 0; nxt < n; nxt++) {
                if (dist[nxt] == -1 && diffOne(words[cur], words[nxt])) {
                    dist[nxt] = dist[cur] + 1;
                    q.addLast(nxt);
                }
            }
        }

        return 0;
    }

    private boolean diffOne(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return diff == 1;
    }
}