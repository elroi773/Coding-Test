function solution(weights) {
    let answer = 0;
    const count = new Map();

    // 1️⃣ 몸무게별 등장 횟수 카운트
    for (let w of weights) {
        count.set(w, (count.get(w) || 0) + 1);
    }

    // 2️⃣ 가능한 비율 (분수 형태)
    const ratios = [
        [1, 1], // same
        [3, 2], // 1.5
        [2, 1], // 2.0
        [4, 3], // 1.333...
    ];

    // 3️⃣ 각 몸무게에 대해 짝 찾기
    for (let [w, cnt] of count.entries()) {
        // 같은 몸무게끼리 짝꿍 (조합 nC2)
        if (cnt > 1) {
            answer += (cnt * (cnt - 1)) / 2;
        }

        // 다른 비율로 짝꿍 찾기
        for (let i = 1; i < ratios.length; i++) { // ratio[0]은 1:1이므로 제외
            const [num, den] = ratios[i];
            if ((w * num) % den !== 0) continue; // 정수만 허용

            const target = (w * num) / den;
            if (count.has(target)) {
                answer += cnt * count.get(target);
            }
        }
    }

    return answer;
}