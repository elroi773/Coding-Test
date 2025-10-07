class Solution {
    fun solution(board: Array<String>): Int {
        var countO = 0
        var countX = 0

        // O, X 개수 세기
        for (row in board) {
            for (c in row) {
                if (c == 'O') countO++
                else if (c == 'X') countX++
            }
        }

        // 승리 여부 확인 함수
        fun checkWin(player: Char): Boolean {
            for (i in 0..2) {
                // 가로
                if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true
                // 세로
                if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true
            }
            // 대각선
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true
            if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true
            return false
        }

        val oWin = checkWin('O')
        val xWin = checkWin('X')

        // 규칙 검사
        if (countX > countO) return 0           // X가 더 많으면 불가능
        if (countO > countX + 1) return 0       // O가 2개 이상 많으면 불가능
        if (oWin && xWin) return 0              // 둘 다 이길 수 없음
        if (oWin && countO != countX + 1) return 0  // O가 이겼는데 개수 불일치
        if (xWin && countO != countX) return 0      // X가 이겼는데 개수 불일치

        return 1 // 유효한 보드
    }
}
