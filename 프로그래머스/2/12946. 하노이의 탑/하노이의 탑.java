class Solution {
    int index = 0; // 이동 기록 인덱스

    public int[][] solution(int n) {
        int moveCount = (int) Math.pow(2, n) - 1;
        int[][] answer = new int[moveCount][2];

        hanoi(n, 1, 2, 3, answer);
        return answer;
    }

    private void hanoi(int n, int start, int via, int end, int[][] answer) {
        if (n == 1) {
            answer[index][0] = start;
            answer[index][1] = end;
            index++;
            return;
        }

        // 1. n-1개를 보조 기둥으로
        hanoi(n - 1, start, end, via, answer);

        // 2. 가장 큰 원판 이동
        answer[index][0] = start;
        answer[index][1] = end;
        index++;

        // 3. n-1개를 목표 기둥으로
        hanoi(n - 1, via, start, end, answer);
    }
}
