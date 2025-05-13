function solution(arr) {
    let count = 0;
    let changed = true;

    while (changed) {
        changed = false;
        let newArr = arr.slice(); // 현재 상태 복사

        for (let i = 0; i < arr.length; i++) {
            let num = arr[i];

            if (num >= 50 && num % 2 === 0) {
                newArr[i] = num / 2;
            } else if (num < 50 && num % 2 === 1) {
                newArr[i] = num * 2 + 1;
            }
        }

        // 변화가 없으면 종료
        for (let i = 0; i < arr.length; i++) {
            if (arr[i] !== newArr[i]) {
                changed = true;
                break;
            }
        }

        if (changed) {
            arr = newArr;
            count++;
        }
    }

    return count;
}
