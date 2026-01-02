import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        // 1) 약수 개수 cnt[i]
        int[] cnt = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                cnt[j]++;
            }
        }

        // 2) best[i] = [i..e]에서 약수개수 최대(동점이면 더 작은 수)
        int[] best = new int[e + 1];
        int bestNum = e;
        int bestCnt = cnt[e];
        best[e] = e;

        for (int i = e - 1; i >= 1; i--) {
            // 동점이면 더 작은 i가 정답이므로 >=
            if (cnt[i] >= bestCnt) {
                bestCnt = cnt[i];
                bestNum = i;
            }
            best[i] = bestNum;
        }

        // 3) 질의 처리
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = best[starts[i]];
        }
        return answer;
    }
}