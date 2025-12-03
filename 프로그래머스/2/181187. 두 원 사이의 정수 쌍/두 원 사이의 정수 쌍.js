function solution(r1, r2) {
    let ans = 0;

    for (let x = 0; x <= r2; x++) {
        const maxY = Math.floor(Math.sqrt(r2 * r2 - x * x));
        const minSq = r1 * r1 - x * x;
        const minY = minSq > 0 ? Math.ceil(Math.sqrt(minSq)) : 0;

        if (maxY < minY) continue;

        if (x === 0) {
            // x = 0 (y축 위): (0, y), (0, -y) → 2개씩
            ans += 2 * (maxY - minY + 1);
        } else {
            if (minY === 0) {
                // y = 0인 점들: (±x, 0) → 2개
                ans += 2;
                // y >= 1인 점들: (±x, ±y) → 4개씩
                ans += 4 * (maxY - 0);
            } else {
                // y >= 1인 점들만 존재: (±x, ±y) → 4개씩
                ans += 4 * (maxY - minY + 1);
            }
        }
    }

    return ans;
}