function solution(cards1, cards2, goal) {
    for (let word of goal) {
        if (cards1[0] === word) {
            cards1.shift();  // cards1에서 해당 단어 사용
        } else if (cards2[0] === word) {
            cards2.shift();  // cards2에서 해당 단어 사용
        } else {
            return "No";     // 둘 중 어디서도 사용 불가 → 불가능
        }
    }
    return "Yes";  // 모든 단어를 문제 없이 사용했다면 성공
}
