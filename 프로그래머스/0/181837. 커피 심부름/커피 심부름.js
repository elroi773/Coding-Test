function solution(order) {
    let answer = 0;
    for (let menu of order) {
        if (menu.includes("cafelatte")) {
            answer += 5000;
        } else {
            // 아메리카노 또는 "anything"
            answer += 4500;
        }
    }
    return answer;
}
