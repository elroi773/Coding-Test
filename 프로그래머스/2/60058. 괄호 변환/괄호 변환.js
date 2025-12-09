function solution(p) {
    return convert(p);
}

function convert(p) {
    if (p === "") return "";

    // 1. u, v 분리 (u는 균형잡힌 괄호 문자열)
    let balance = 0;
    let idx = 0;

    for (let i = 0; i < p.length; i++) {
        if (p[i] === "(") balance++;
        else balance--;

        if (balance === 0) {
            idx = i;
            break;
        }
    }

    const u = p.slice(0, idx + 1);
    const v = p.slice(idx + 1);

    // 2. u가 올바른지 확인
    if (isCorrect(u)) {
        return u + convert(v);
    }

    // 3. u가 올바르지 않은 경우 규칙대로 새 문자열 생성
    let result = "(";
    result += convert(v);
    result += ")";

    // 4. u의 첫/마지막을 제거하고 나머지 뒤집기
    let flipped = "";
    for (let i = 1; i < u.length - 1; i++) {
        flipped += u[i] === "(" ? ")" : "(";
    }

    return result + flipped;
}

// 올바른 괄호 문자열인지 검사
function isCorrect(s) {
    let count = 0;
    for (let c of s) {
        if (c === "(") count++;
        else count--;

        if (count < 0) return false;
    }
    return count === 0;
}
