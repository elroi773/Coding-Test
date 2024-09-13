class Solution {
    public int solution(int[][] lines) {
        int[] lineMap = new int[201]; // -100 ~ 100을 표현하기 위해 201개의 배열 선언 (인덱스 0이 -100에 해당)
        int answer = 0;

        // 각 선분이 차지하는 구간을 기록
        for (int[] line : lines) {
            int start = line[0] + 100; // 음수를 피하기 위해 100을 더해줌
            int end = line[1] + 100;
            for (int i = start; i < end; i++) {
                lineMap[i]++;
            }
        }

        // 두 개 이상의 선분이 겹치는 구간의 길이를 계산
        for (int i = 0; i < 201; i++) {
            if (lineMap[i] > 1) {
                answer++;
            }
        }

        return answer;
    }
}
