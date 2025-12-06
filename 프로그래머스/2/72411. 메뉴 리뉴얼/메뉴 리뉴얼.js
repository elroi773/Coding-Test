function solution(orders, course) {
    const answer = [];

    // 주문들을 미리 알파벳 순으로 정렬해 둠
    const sortedOrders = orders.map(order =>
        order.split("").sort().join("")
    );

    // 각 코스 길이에 대해 처리
    for (const c of course) {
        const counter = {};  // 조합 문자열 -> 횟수
        let maxCount = 0;

        // 각 주문에서 길이 c짜리 조합 생성
        for (const order of sortedOrders) {
            if (order.length < c) continue;
            makeCombinations(order, c, 0, [], counter);
        }

        // 최소 2번 이상 등장한 조합 중 최대 빈도 찾기
        for (const comb in counter) {
            const cnt = counter[comb];
            if (cnt >= 2 && cnt > maxCount) {
                maxCount = cnt;
            }
        }

        if (maxCount < 2) continue; // 이 코스 길이에 해당하는 후보 없음

        // 최대 빈도를 가진 조합들만 정답에 추가
        for (const comb in counter) {
            if (counter[comb] === maxCount) {
                answer.push(comb);
            }
        }
    }

    // 사전 순 정렬 후 반환
    answer.sort();
    return answer;
}

// 조합 생성: 문자열 order에서 길이 c인 조합을 만들고 counter에 카운트
function makeCombinations(order, c, start, path, counter) {
    if (path.length === c) {
        const key = path.join("");
        counter[key] = (counter[key] || 0) + 1;
        return;
    }

    for (let i = start; i < order.length; i++) {
        path.push(order[i]);
        makeCombinations(order, c, i + 1, path, counter);
        path.pop();
    }
}