import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // 끝나는 지점(e) 기준 오름차순 정렬
        Arrays.sort(targets, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]); // e 같으면 s 기준 (선택사항)
            }
            return Integer.compare(a[1], b[1]);
        });

        int answer = 0;
        int last = -1; // 마지막 요격 미사일을 쏜 x 좌표를 의미하는 값 (논리적으로는 e 바로 앞)

        for (int[] t : targets) {
            int s = t[0];
            int e = t[1];

            // 현재 구간 (s, e)의 시작이 last 이상이면 기존 미사일로는 커버 불가 → 새로 발사
            if (s >= last) {
                answer++;
                last = e;   // 이 구간의 끝점 바로 앞에 쏜 것으로 간주
            }
        }

        return answer;
    }
}