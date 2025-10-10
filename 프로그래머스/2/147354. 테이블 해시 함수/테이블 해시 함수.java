import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        // 1. 정렬: col 기준 오름차순, 첫 번째 컬럼 기준 내림차순
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return b[0] - a[0]; // 첫 번째 컬럼 기준 내림차순
            } else {
                return a[col - 1] - b[col - 1]; // col 기준 오름차순
            }
        });

        // 2. S_i 계산 및 XOR 누적
        int answer = 0;
        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;
            for (int value : data[i - 1]) {
                sum += value % i; // 각 열의 나머지 합
            }
            answer ^= sum; // XOR 누적
        }

        return answer;
    }
}
