import java.util.*;

class Solution {

    // 구조물 존재 여부: [x][y][a]  (a: 0 기둥, 1 보)
    private boolean[][][] exist;

    private boolean canPlace(int x, int y, int a) {
        if (a == 0) { // 기둥
            // 1) 바닥
            if (y == 0) return true;
            // 2) 아래 기둥
            if (exist[x][y - 1][0]) return true;
            // 3) 왼쪽 보 끝 위
            if (x > 0 && exist[x - 1][y][1]) return true;
            // 4) 오른쪽 보 끝 위
            if (exist[x][y][1]) return true;
            return false;
        } else { // 보
            // 1) 왼쪽 끝이 기둥 위
            if (y > 0 && exist[x][y - 1][0]) return true;
            // 2) 오른쪽 끝이 기둥 위
            if (y > 0 && exist[x + 1][y - 1][0]) return true;
            // 3) 양쪽 끝이 다른 보와 연결
            if (x > 0 && exist[x - 1][y][1] && exist[x + 1][y][1]) return true;
            return false;
        }
    }

    private boolean isValidAll(int n) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int a = 0; a <= 1; a++) {
                    if (exist[x][y][a] && !canPlace(x, y, a)) return false;
                }
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        // 좌표가 0..n 이므로 n+1, 보에서 x+1 접근하니 n+2로 안전하게
        exist = new boolean[n + 2][n + 2][2];

        for (int[] cmd : build_frame) {
            int x = cmd[0], y = cmd[1], a = cmd[2], b = cmd[3];

            if (b == 1) { // 설치
                exist[x][y][a] = true;
                if (!isValidAll(n)) exist[x][y][a] = false; // 롤백
            } else { // 삭제
                exist[x][y][a] = false;
                if (!isValidAll(n)) exist[x][y][a] = true; // 롤백
            }
        }

        // 결과 수집
        List<int[]> list = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int a = 0; a <= 1; a++) {
                    if (exist[x][y][a]) list.add(new int[]{x, y, a});
                }
            }
        }

        // 정렬: x -> y -> a (기둥(0)이 보(1)보다 먼저)
        list.sort((p1, p2) -> {
            if (p1[0] != p2[0]) return p1[0] - p2[0];
            if (p1[1] != p2[1]) return p1[1] - p2[1];
            return p1[2] - p2[2];
        });

        // int[][]로 변환
        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        return answer;
    }
}