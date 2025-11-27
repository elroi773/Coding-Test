function solution(numbers) {
    var answer = [];
    
    for (let x of numbers) {
        if (x % 2 === 0) {
            // 짝수면 +1
            answer.push(x + 1);
        } else {
            // 홀수면 오른쪽 첫 0 비트 찾기
            let bit = 1n; // BigInt 사용 (x가 10^15까지 가능)
            let bigX = BigInt(x);
            
            while ((bigX & bit) !== 0n) {
                bit <<= 1n;
            }
            
            // 찾은 0 위치 바로 오른쪽 1비트만 더함
            answer.push(Number(bigX + (bit >> 1n)));
        }
    }
    
    return answer;
}
