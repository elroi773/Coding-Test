function solution(answers) {
    const one = [1, 2, 3, 4, 5];
    const two = [2, 1, 2, 3, 2, 4, 2, 5];
    const three = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];

    const scores = [0, 0, 0];

    for (let i = 0; i < answers.length; i++) {
        if (answers[i] === one[i % one.length]) scores[0]++;
        if (answers[i] === two[i % two.length]) scores[1]++;
        if (answers[i] === three[i % three.length]) scores[2]++;
    }

    const maxScore = Math.max(...scores);
    const result = [];

    for (let i = 0; i < scores.length; i++) {
        if (scores[i] === maxScore) result.push(i + 1); // 수포자는 1번부터 시작
    }

    return result;
}
