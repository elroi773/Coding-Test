function solution(s) {
    const len = s.length;
    const mid = Math.floor(len / 2);
    
    return len % 2 === 0 
        ? s.slice(mid - 1, mid + 1)  // 짝수: 가운데 두 글자
        : s.charAt(mid);            // 홀수: 가운데 한 글자
}
