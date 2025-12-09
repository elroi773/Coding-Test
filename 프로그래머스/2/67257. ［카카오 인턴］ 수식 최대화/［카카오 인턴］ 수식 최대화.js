function solution(expression) {
    const nums = expression.split(/[\+\-\*]/).map(Number); // 숫자 리스트
    const ops = expression.match(/[\+\-\*]/g); // 연산자 리스트

    const uniqueOps = [...new Set(ops)]; // 등장한 연산자만 추출
    const permutations = getPermutations(uniqueOps); // 우선순위 순열 생성
    
    let maxResult = 0;

    for (const priority of permutations) {
        const result = evaluate(nums, ops, priority);
        maxResult = Math.max(maxResult, Math.abs(result));
    }
    
    return maxResult;
}

// 연산자 순열 생성 함수
function getPermutations(arr) {
    const results = [];
    const dfs = (current, remain) => {
        if (remain.length === 0) {
            results.push(current);
            return;
        }
        for (let i = 0; i < remain.length; i++) {
            dfs([...current, remain[i]], [...remain.slice(0, i), ...remain.slice(i + 1)]);
        }
    };
    dfs([], arr);
    return results;
}

// 우선순위별 계산 함수
function evaluate(nums, ops, priority) {
    let tmpNums = [...nums];
    let tmpOps = [...ops];

    for (const op of priority) {
        const newNums = [tmpNums[0]];
        const newOps = [];

        for (let i = 0; i < tmpOps.length; i++) {
            if (tmpOps[i] === op) {
                const a = newNums.pop();
                const b = tmpNums[i + 1];
                newNums.push(calc(a, b, op));
            } else {
                newNums.push(tmpNums[i + 1]);
                newOps.push(tmpOps[i]);
            }
        }
        tmpNums = newNums;
        tmpOps = newOps;
    }

    return tmpNums[0];
}

// 실제 연산 함수
function calc(a, b, op) {
    if (op === '+') return a + b;
    if (op === '-') return a - b;
    return a * b;
}
