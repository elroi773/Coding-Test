function solution(n, s) {
    if (s < n) return [-1];
    
    const answer = new Array(n).fill(Math.floor(s / n));
    let remain = s % n;
    
    for (let i = n - remain; i < n; i++) {
        answer[i] += 1;
    }
    
    return answer;
}