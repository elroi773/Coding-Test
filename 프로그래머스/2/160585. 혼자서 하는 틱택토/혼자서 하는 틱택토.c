#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

bool checkWin(const char* board[], char player) {
    // 가로/세로/대각선 8가지 경우 검사
    for (int i = 0; i < 3; i++) {
        // 가로 검사
        if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
            return true;
        // 세로 검사
        if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            return true;
    }
    // 대각선 검사
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
        return true;
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        return true;

    return false;
}

int solution(const char* board[], size_t board_len) {
    int countO = 0, countX = 0;

    // O, X 개수 세기
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == 'O') countO++;
            else if (board[i][j] == 'X') countX++;
        }
    }

    bool oWin = checkWin(board, 'O');
    bool xWin = checkWin(board, 'X');

    // 조건 위반 체크
    if (countX > countO) return 0;               // X가 먼저 두는 경우
    if (countO > countX + 1) return 0;           // O가 너무 많이 둔 경우
    if (oWin && xWin) return 0;                  // 동시에 승리 불가능
    if (oWin && countO != countX + 1) return 0;  // O가 이겼으면 O가 하나 더 많아야 함
    if (xWin && countO != countX) return 0;      // X가 이겼으면 개수가 같아야 함

    return 1; // 정상 가능한 게임 상황
}