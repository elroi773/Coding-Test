function solution(numbers) {
    const answer = [];

    for (const num of numbers) {
        // 1) 이진수 변환
        let binary = num.toString(2);

        // 2) 포화 이진트리 길이(2^k - 1)로 맞추기
        let len = 1;
        while (len < binary.length) {
            len = len * 2 + 1;
        }

        // 3) 앞에 0 패딩
        binary = binary.padStart(len, '0');

        // 4) 유효성 검사
        answer.push(isValid(binary) ? 1 : 0);
    }

    return answer;
}

function isValid(tree) {
    const mid = Math.floor(tree.length / 2);

    // 루트가 0인데 하위에 1이 있으면 불가능
    if (tree[mid] === '0' && tree.includes('1')) {
        return false;
    }

    // 리프 노드
    if (tree.length === 1) return true;

    const left = tree.slice(0, mid);
    const right = tree.slice(mid + 1);

    return isValid(left) && isValid(right);
}
