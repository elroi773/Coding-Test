import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);  // 오름차순 정렬
        int n = citations.length;

        for (int i = 0; i < n; i++) {
            int h = n - i;                // 남은 논문 수
            if (citations[i] >= h) {      // h-index 조건 만족 시
                return h;
            }
        }

        return 0;
    }
}
