class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int lastPainted = 0;  // 마지막으로 페인트칠을 완료한 구역의 끝

        for (int i = 0; i < section.length; i++) {
            if (section[i] > lastPainted) {
                answer++;  // 롤러로 한 번 칠함
                lastPainted = section[i] + m - 1;  // 롤러로 칠할 수 있는 범위만큼 업데이트
            }
        }
        return answer;
    }
}
