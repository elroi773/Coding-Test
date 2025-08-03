function solution(X, Y) {
    const xCount = Array(10).fill(0);
    const yCount = Array(10).fill(0);

    // 각 숫자(0~9)의 개수 세기
    for (let ch of X) {
        xCount[parseInt(ch)]++;
    }
    for (let ch of Y) {
        yCount[parseInt(ch)]++;
    }

    let answer = "";

    // 9부터 0까지 반복하며 공통된 개수만큼 추가
    for (let i = 9; i >= 0; i--) {
        const minCount = Math.min(xCount[i], yCount[i]);
        answer += String(i).repeat(minCount);
    }

    if (answer === "") return "-1";
    if (answer[0] === "0") return "0";  // 000...0 인 경우 처리
    return answer;
}
