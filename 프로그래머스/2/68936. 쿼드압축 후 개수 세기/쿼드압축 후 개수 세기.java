class Solution {

    public int[] solution(int[][] arr) {
        int[] answer = new int[2]; // answer[0] = 0의 개수, answer[1] = 1의 개수
        compress(arr, 0, 0, arr.length, answer);
        return answer;
    }

    // 특정 영역이 모두 같은 값인지 확인
    private boolean isSame(int[][] arr, int x, int y, int size) {
        int value = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != value) return false;
            }
        }
        return true;
    }

    // 쿼드 트리 방식으로 압축
    private void compress(int[][] arr, int x, int y, int size, int[] answer) {
        if (isSame(arr, x, y, size)) {
            answer[arr[x][y]]++; // 0이면 answer[0]++, 1이면 answer[1]++
            return;
        }

        int newSize = size / 2;

        // 4개 영역 쪼개서 재귀 호출
        compress(arr, x, y, newSize, answer);                     // 좌상
        compress(arr, x, y + newSize, newSize, answer);           // 우상
        compress(arr, x + newSize, y, newSize, answer);           // 좌하
        compress(arr, x + newSize, y + newSize, newSize, answer); // 우하
    }
}
