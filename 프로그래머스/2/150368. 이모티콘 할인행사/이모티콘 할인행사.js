function solution(users, emoticons) {
    const discounts = [10, 20, 30, 40];
    const m = emoticons.length;

    let bestPlus = 0;
    let bestSales = 0;

    // 할인율 조합 저장용
    function dfs(idx, arr) {
        if (idx === m) {
            simulate(arr);
            return;
        }

        for (let d of discounts) {
            arr[idx] = d;
            dfs(idx + 1, arr);
        }
    }

    // 조합에 대한 구매 시뮬레이션
    function simulate(rateArr) {
        let plusCnt = 0;
        let sales = 0;

        for (let [needRate, needPrice] of users) {
            let sum = 0;

            // 사용자가 구매하는 금액 계산
            for (let i = 0; i < m; i++) {
                if (rateArr[i] >= needRate) {
                    let discounted = emoticons[i] * (100 - rateArr[i]) / 100;
                    sum += discounted;
                }
            }

            // 플러스 가입 or 매출 추가
            if (sum >= needPrice) {
                plusCnt++;
            } else {
                sales += sum;
            }
        }

        // 최적 비교
        if (plusCnt > bestPlus || (plusCnt === bestPlus && sales > bestSales)) {
            bestPlus = plusCnt;
            bestSales = sales;
        }
    }

    dfs(0, Array(m).fill(0));

    return [bestPlus, bestSales];
}
