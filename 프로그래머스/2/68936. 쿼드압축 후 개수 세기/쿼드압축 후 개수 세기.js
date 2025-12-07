function solution(arr) {
    let answer = [0, 0];  // answer[0] = 0의 개수, answer[1] = 1의 개수

    function isSame(x, y, size) {
        let value = arr[x][y];
        for (let i = x; i < x + size; i++) {
            for (let j = y; j < y + size; j++) {
                if (arr[i][j] !== value) return false;
            }
        }
        return true;
    }

    function compress(x, y, size) {
        if (isSame(x, y, size)) {
            answer[arr[x][y]]++;  // 해당 숫자 카운트 증가
            return;
        }

        let newSize = size / 2;

        // 4개로 쪼개서 재귀 호출
        compress(x, y, newSize);                   // 좌상
        compress(x, y + newSize, newSize);         // 우상
        compress(x + newSize, y, newSize);         // 좌하
        compress(x + newSize, y + newSize, newSize); // 우하
    }

    compress(0, 0, arr.length);
    return answer;
}
