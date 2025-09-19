function solution(numbers, hand) {
    let answer = '';

    // 숫자 → 좌표 매핑
    const pos = {
        1: [0,0], 2: [0,1], 3: [0,2],
        4: [1,0], 5: [1,1], 6: [1,2],
        7: [2,0], 8: [2,1], 9: [2,2],
        '*': [3,0], 0: [3,1], '#': [3,2]
    };

    // 초기 손가락 위치
    let left = pos['*'];
    let right = pos['#'];

    for (let num of numbers) {
        if ([1,4,7].includes(num)) {
            answer += 'L';
            left = pos[num];
        } else if ([3,6,9].includes(num)) {
            answer += 'R';
            right = pos[num];
        } else {
            // 가운데 줄 (2,5,8,0)
            const target = pos[num];
            const leftDist = Math.abs(left[0]-target[0]) + Math.abs(left[1]-target[1]);
            const rightDist = Math.abs(right[0]-target[0]) + Math.abs(right[1]-target[1]);

            if (leftDist < rightDist) {
                answer += 'L';
                left = target;
            } else if (rightDist < leftDist) {
                answer += 'R';
                right = target;
            } else {
                if (hand === 'left') {
                    answer += 'L';
                    left = target;
                } else {
                    answer += 'R';
                    right = target;
                }
            }
        }
    }

    return answer;
}
