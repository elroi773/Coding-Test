function solution(k, score) {
    const hallOfFame = [];
    const answer = [];

    for (let i = 0; i < score.length; i++) {
        hallOfFame.push(score[i]);                    // 오늘 점수 추가
        hallOfFame.sort((a, b) => b - a);             // 내림차순 정렬 (높은 점수 먼저)

        if (hallOfFame.length > k) {
            hallOfFame.pop();                         // k명 초과 시 가장 낮은 점수 제거
        }

        answer.push(hallOfFame[hallOfFame.length - 1]); // 현재 명예의 전당 최하위 점수 추가
    }

    return answer;
}
