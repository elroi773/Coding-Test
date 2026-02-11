#include <string>
#include <vector>

using namespace std;

static vector<vector<int>> rotate90(const vector<vector<int>>& a) {
    int n = (int)a.size();
    vector<vector<int>> r(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            r[j][n - 1 - i] = a[i][j];
        }
    }
    return r;
}

static bool isUnlocked(const vector<vector<int>>& board, int pad, int lockSize) {
    for (int i = 0; i < lockSize; i++) {
        for (int j = 0; j < lockSize; j++) {
            if (board[i + pad][j + pad] != 1) return false;
        }
    }
    return true;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    int m = (int)key.size();
    int n = (int)lock.size();

    int pad = m - 1;
    int size = n + pad * 2;

    vector<vector<int>> board(size, vector<int>(size, 0));
    // 중앙에 lock 배치
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            board[i + pad][j + pad] = lock[i][j];
        }
    }

    vector<vector<int>> curKey = key;

    for (int rot = 0; rot < 4; rot++) {
        for (int x = 0; x <= size - m; x++) {
            for (int y = 0; y <= size - m; y++) {
                // key를 board에 더하기
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        board[x + i][y + j] += curKey[i][j];
                    }
                }

                if (isUnlocked(board, pad, n)) return true;

                // 원복
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        board[x + i][y + j] -= curKey[i][j];
                    }
                }
            }
        }
        curKey = rotate90(curKey);
    }

    return false;
}