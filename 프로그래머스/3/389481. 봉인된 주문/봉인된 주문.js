function solution(n, bans) {

    // 26의 거듭제곱 (BigInt)
    const pow26 = Array(12).fill(0n);
    pow26[0] = 1n;
    for (let i = 1; i <= 11; i++) {
        pow26[i] = pow26[i - 1] * 26n;
    }

    // 문자열 → 순서 번호 (1-based)
    function getRank(s) {
        const L = s.length;
        let r = 0n;

        // 더 짧은 문자열 개수
        for (let l = 1; l < L; l++) {
            r += pow26[l];
        }

        // 같은 길이에서 사전순
        for (let i = 0; i < L; i++) {
            r += BigInt(s.charCodeAt(i) - 97) * pow26[L - i - 1];
        }

        return r + 1n;
    }

    // 순서 번호 → 문자열
    function getString(k) {
        let L = 1;
        while (k > pow26[L]) {
            k -= pow26[L];
            L++;
        }

        k -= 1n; // 0-based
        let res = '';

        for (let i = 0; i < L; i++) {
            const div = pow26[L - i - 1];
            res += String.fromCharCode(
                Number(k / div) + 97
            );
            k %= div;
        }

        return res;
    }

    // bans → rank 배열
    const banned = bans.map(getRank).sort((a, b) => (a < b ? -1 : 1));

    // n 보정
    let target = BigInt(n);
    for (const r of banned) {
        if (r <= target) {
            target++;
        } else {
            break;
        }
    }

    // 결과
    return getString(target);
}
