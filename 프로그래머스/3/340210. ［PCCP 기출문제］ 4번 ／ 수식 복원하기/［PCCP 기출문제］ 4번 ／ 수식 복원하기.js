function solution(expressions) {
    const parsed = expressions.map(exp => {
        const [A, op, B, , C] = exp.split(" ");
        return { A, B, C, op, raw: exp };
    });

    const toDecimal = (str, base) => {
        for (let ch of str) {
            if (Number(ch) >= base) return null;
        }
        return parseInt(str, base);
    };

    let possibleBases = [];

    for (let base = 2; base <= 9; base++) {
        let ok = true;

        for (let { A, B, C, op } of parsed) {
            const a = toDecimal(A, base);
            const b = toDecimal(B, base);
            if (a === null || b === null) {
                ok = false;
                break;
            }

            if (C === "X") continue;

            const c = toDecimal(C, base);
            if (c === null) {
                ok = false;
                break;
            }

            const result = op === "+" ? a + b : a - b;
            if (result !== c) {
                ok = false;
                break;
            }
        }

        if (ok) possibleBases.push(base);
    }

    const answer = [];

    for (let { A, B, C, op, raw } of parsed) {
        if (C !== "X") continue;

        const results = new Set();

        for (let base of possibleBases) {
            const a = toDecimal(A, base);
            const b = toDecimal(B, base);
            if (a === null || b === null) continue;

            const val = op === "+" ? a + b : a - b;
            results.add(val.toString(base));
        }

        const result = results.size === 1 ? [...results][0] : "?";
        answer.push(raw.replace("X", result));
    }

    return answer;
}
