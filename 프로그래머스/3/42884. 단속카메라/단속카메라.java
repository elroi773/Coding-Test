import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        // 1) 진출 지점(끝점) 기준 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int cameras = 0;
        int camPos = -30001; // 범위(-30000~30000) 밖에서 시작

        // 2) 그리디: 아직 안 걸린 구간을 만나면 그 구간의 끝점에 카메라 설치
        for (int[] r : routes) {
            int start = r[0];
            int end = r[1];

            if (camPos < start) {
                cameras++;
                camPos = end;
            }
        }

        return cameras;
    }
}