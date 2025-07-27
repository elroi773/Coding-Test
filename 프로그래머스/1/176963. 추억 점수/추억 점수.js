function solution(name, yearning, photo) {
    const nameScore = {};
    
    // 이름 → 그리움 점수 매핑
    for (let i = 0; i < name.length; i++) {
        nameScore[name[i]] = yearning[i];
    }

    const answer = [];

    for (const pic of photo) {
        let total = 0;
        for (const person of pic) {
            total += nameScore[person] || 0; // 없으면 0점
        }
        answer.push(total);
    }

    return answer;
}
