function solution(survey, choices) {
    // 성격 유형 점수판
    let scores = {
        R: 0, T: 0,
        C: 0, F: 0,
        J: 0, M: 0,
        A: 0, N: 0
    };

    // 점수 매핑 (선택지 → 점수)
    const points = [3, 2, 1, 0, 1, 2, 3];

    for (let i = 0; i < survey.length; i++) {
        let [disagree, agree] = survey[i].split("");
        let choice = choices[i];

        if (choice < 4) { // 비동의 쪽
            scores[disagree] += points[choice - 1];
        } else if (choice > 4) { // 동의 쪽
            scores[agree] += points[choice - 1];
        }
    }

    // 최종 성격 유형 결정
    let result = "";
    result += (scores["R"] >= scores["T"]) ? "R" : "T";
    result += (scores["C"] >= scores["F"]) ? "C" : "F";
    result += (scores["J"] >= scores["M"]) ? "J" : "M";
    result += (scores["A"] >= scores["N"]) ? "A" : "N";

    return result;
}
