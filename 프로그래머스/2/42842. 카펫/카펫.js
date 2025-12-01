function solution(brown, yellow) {
    for (let h = 1; h <= yellow; h++) {
        if (yellow % h !== 0) continue;   // 노란색 내부 세로
        
        let w = yellow / h;               // 노란색 내부 가로
        
        const totalW = w + 2;             // 전체 가로
        const totalH = h + 2;             // 전체 세로
        
        // 갈색 격자 수 공식
        if (2 * totalW + 2 * totalH - 4 === brown) {
            return [totalW, totalH];
        }
    }
    
    return [];
}
