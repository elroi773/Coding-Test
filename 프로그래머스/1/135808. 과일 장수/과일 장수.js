function solution(k, m, score) {
    // 1. 내림차순 정렬 (최고 점수부터 시작)
    score.sort((a, b) => b - a);
    
    let total = 0;

    // 2. m개씩 묶어서 상자 만들기
    for (let i = 0; i + m <= score.length; i += m) {
        let box = score.slice(i, i + m); // m개짜리 상자
        let minScore = box[box.length - 1]; // 가장 낮은 점수
        total += minScore * m;
    }

    return total;
}
