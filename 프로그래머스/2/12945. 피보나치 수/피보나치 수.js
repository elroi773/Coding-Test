function solution(n) {
    const MOD = 1234567;

    let a = 0; // F(0)
    let b = 1; // F(1)

    for (let i = 2; i <= n; i++) {
        const next = (a + b) % MOD;
        a = b;
        b = next;
    }

    return b;
}
