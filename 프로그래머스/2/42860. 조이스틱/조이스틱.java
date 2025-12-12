class Solution {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();

        // 1. 위아래 이동(각 문자 변경)
        for (int i = 0; i < n; i++) {
            char ch = name.charAt(i);
            answer += Math.min(ch - 'A', 'Z' - ch + 1);
        }

        // 2. 좌우 이동 최소화
        int move = n - 1;  // 기본: 오른쪽으로 쭉 이동

        for (int i = 0; i < n; i++) {
            int nextIdx = i + 1;

            // 연속된 A 구간 찾기
            while (nextIdx < n && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }

            // 3가지 경우 중 최소값 선택
            // ① i까지 갔다가 뒤로 돌아가기
            // ② 처음부터 끝으로 갔다가 i로 돌아오기
            move = Math.min(
                    move,
                    2 * i + (n - nextIdx)
            );
            move = Math.min(
                    move,
                    i + 2 * (n - nextIdx)
            );
        }

        return answer + move;
    }
}
