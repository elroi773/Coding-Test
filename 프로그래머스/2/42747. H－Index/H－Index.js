function solution(citations) {
    citations.sort((a, b) => a - b);  // 오름차순 정렬
    const n = citations.length;

    for (let i = 0; i < n; i++) {
        const h = n - i;               // 남아있는 논문 수 (후보 h값)
        if (citations[i] >= h) {
            return h;
        }
    }

    return 0;
}
