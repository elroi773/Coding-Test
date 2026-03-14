#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Pos {
    int r, c;
};

bool clearLine(const vector<string>& board, int r1, int c1, int r2, int c2) {
    if (r1 == r2) {
        if (c1 > c2) swap(c1, c2);
        for (int c = c1 + 1; c < c2; c++) {
            if (board[r1][c] != '.') return false;
        }
        return true;
    }

    if (c1 == c2) {
        if (r1 > r2) swap(r1, r2);
        for (int r = r1 + 1; r < r2; r++) {
            if (board[r][c1] != '.') return false;
        }
        return true;
    }

    return false;
}

bool canRemove(const vector<string>& board, Pos a, Pos b) {
    // 직선 연결
    if (clearLine(board, a.r, a.c, b.r, b.c)) return true;

    // 꺾는 점 1: (a.r, b.c)
    if (board[a.r][b.c] == '.' || (a.r == b.r && b.c == b.c) || (a.r == b.r && b.c == a.c)) {
        // 위 조건은 사실상 불필요하게 복잡하니 아래에서 끝점 여부 직접 처리
    }

    // (a.r, b.c)를 경유
    if ((board[a.r][b.c] == '.' || (a.r == b.r && b.c == b.c) || (a.r == b.r && b.c == a.c))) {
        // no-op
    }

    bool corner1ok = false;
    if ((a.r == b.r && a.c == b.c) == false) {
        char corner = board[a.r][b.c];
        if ((a.r == b.r && b.c == a.c) || corner == '.') {
            if (clearLine(board, a.r, a.c, a.r, b.c) &&
                clearLine(board, a.r, b.c, b.r, b.c)) {
                corner1ok = true;
            }
        }
    }
    if (corner1ok) return true;

    // (b.r, a.c)를 경유
    bool corner2ok = false;
    {
        char corner = board[b.r][a.c];
        if ((b.r == a.r && a.c == b.c) || corner == '.') {
            if (clearLine(board, a.r, a.c, b.r, a.c) &&
                clearLine(board, b.r, a.c, b.r, b.c)) {
                corner2ok = true;
            }
        }
    }
    if (corner2ok) return true;

    return false;
}

string solution(int m, int n, vector<string> board) {
    vector<vector<Pos>> pos(26);

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            char ch = board[i][j];
            if ('A' <= ch && ch <= 'Z') {
                pos[ch - 'A'].push_back({i, j});
            }
        }
    }

    int total = 0;
    for (int i = 0; i < 26; i++) {
        if (!pos[i].empty()) total++;
    }

    string answer = "";

    for (int removed = 0; removed < total; removed++) {
        bool found = false;

        for (int i = 0; i < 26; i++) {
            if (pos[i].empty()) continue;

            Pos p1 = pos[i][0];
            Pos p2 = pos[i][1];

            if (canRemove(board, p1, p2)) {
                char ch = 'A' + i;
                answer.push_back(ch);
                board[p1.r][p1.c] = '.';
                board[p2.r][p2.c] = '.';
                pos[i].clear();
                found = true;
                break;
            }
        }

        if (!found) return "IMPOSSIBLE";
    }

    return answer;
}