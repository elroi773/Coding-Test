class Solution {
    public int solution(String[] board) {
        int countO = 0, countX = 0;

        // O, X 개수 세기
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') countO++;
                else if (c == 'X') countX++;
            }
        }

        boolean oWin = checkWin(board, 'O');
        boolean xWin = checkWin(board, 'X');

        // 1️⃣ X가 더 많으면 불가능
        if (countX > countO) return 0;
        // 2️⃣ O가 X보다 2개 이상 많으면 불가능
        if (countO > countX + 1) return 0;
        // 3️⃣ 둘 다 승리하면 불가능
        if (oWin && xWin) return 0;
        // 4️⃣ O가 이겼는데 개수가 안 맞으면 불가능
        if (oWin && countO != countX + 1) return 0;
        // 5️⃣ X가 이겼는데 개수가 안 맞으면 불가능
        if (xWin && countO != countX) return 0;

        // 모두 통과 시 가능한 상태
        return 1;
    }

    private boolean checkWin(String[] board, char player) {
        // 가로, 세로 검사
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player &&
                board[i].charAt(1) == player &&
                board[i].charAt(2) == player) return true;

            if (board[0].charAt(i) == player &&
                board[1].charAt(i) == player &&
                board[2].charAt(i) == player) return true;
        }

        // 대각선 검사
        if (board[0].charAt(0) == player &&
            board[1].charAt(1) == player &&
            board[2].charAt(2) == player) return true;

        if (board[0].charAt(2) == player &&
            board[1].charAt(1) == player &&
            board[2].charAt(0) == player) return true;

        return false;
    }
}
