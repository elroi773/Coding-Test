import java.util.*;

class Solution {
    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public String solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        List<Pos>[] pos = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                char ch = map[i][j];
                if ('A' <= ch && ch <= 'Z') {
                    pos[ch - 'A'].add(new Pos(i, j));
                }
            }
        }

        int total = 0;
        for (int i = 0; i < 26; i++) {
            if (!pos[i].isEmpty()) total++;
        }

        StringBuilder answer = new StringBuilder();

        for (int removed = 0; removed < total; removed++) {
            boolean found = false;

            for (int i = 0; i < 26; i++) {
                if (pos[i].isEmpty()) continue;

                Pos a = pos[i].get(0);
                Pos b = pos[i].get(1);

                if (canRemove(map, a, b)) {
                    char ch = (char) ('A' + i);
                    answer.append(ch);

                    map[a.r][a.c] = '.';
                    map[b.r][b.c] = '.';
                    pos[i].clear();

                    found = true;
                    break;
                }
            }

            if (!found) return "IMPOSSIBLE";
        }

        return answer.toString();
    }

    private boolean canRemove(char[][] map, Pos a, Pos b) {
        // 1) 직선 연결
        if (clearLine(map, a.r, a.c, b.r, b.c)) return true;

        // 2) 한 번 꺾기: (a.r, b.c)
        if (isCornerAvailable(map, a.r, b.c, a, b)) {
            if (clearLine(map, a.r, a.c, a.r, b.c) &&
                clearLine(map, a.r, b.c, b.r, b.c)) {
                return true;
            }
        }

        // 3) 한 번 꺾기: (b.r, a.c)
        if (isCornerAvailable(map, b.r, a.c, a, b)) {
            if (clearLine(map, a.r, a.c, b.r, a.c) &&
                clearLine(map, b.r, a.c, b.r, b.c)) {
                return true;
            }
        }

        return false;
    }

    private boolean isCornerAvailable(char[][] map, int r, int c, Pos a, Pos b) {
        // 꺾는 점은 빈칸이거나 두 끝점 중 하나여야 함
        if ((r == a.r && c == a.c) || (r == b.r && c == b.c)) return true;
        return map[r][c] == '.';
    }

    private boolean clearLine(char[][] map, int r1, int c1, int r2, int c2) {
        if (r1 == r2) {
            if (c1 > c2) {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            for (int c = c1 + 1; c < c2; c++) {
                if (map[r1][c] != '.') return false;
            }
            return true;
        }

        if (c1 == c2) {
            if (r1 > r2) {
                int temp = r1;
                r1 = r2;
                r2 = temp;
            }
            for (int r = r1 + 1; r < r2; r++) {
                if (map[r][c1] != '.') return false;
            }
            return true;
        }

        return false;
    }
}