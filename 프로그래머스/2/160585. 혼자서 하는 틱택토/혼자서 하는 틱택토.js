function solution(board) {
    let countO = 0, countX = 0;

    // O, X 개수 세기
    for (let row of board) {
        for (let ch of row) {
            if (ch === 'O') countO++;
            else if (ch === 'X') countX++;
        }
    }

    // 승리 여부 판단 함수
    function checkWin(player) {
        for (let i = 0; i < 3; i++) {
            // 가로, 세로 체크
            if (board[i][0] === player && board[i][1] === player && board[i][2] === player) return true;
            if (board[0][i] === player && board[1][i] === player && board[2][i] === player) return true;
        }
        // 대각선 체크
        if (board[0][0] === player && board[1][1] === player && board[2][2] === player) return true;
        if (board[0][2] === player && board[1][1] === player && board[2][0] === player) return true;

        return false;
    }

    const oWin = checkWin('O');
    const xWin = checkWin('X');

    // 규칙 위반 조건들
    if (countX > countO) return 0;            // X가 더 많을 수 없음
    if (countO > countX + 1) return 0;        // O가 2개 이상 많을 수 없음
    if (oWin && xWin) return 0;               // 둘 다 승리 불가
    if (oWin && countO !== countX + 1) return 0; // O 승리인데 개수 안 맞음
    if (xWin && countO !== countX) return 0;     // X 승리인데 개수 안 맞음

    return 1; // 정상적인 상황
}
