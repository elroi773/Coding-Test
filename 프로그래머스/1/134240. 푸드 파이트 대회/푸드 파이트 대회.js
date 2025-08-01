function solution(food) {
    let left = "";

    for (let i = 1; i < food.length; i++) {
        let count = Math.floor(food[i] / 2); // 공정성을 위해 양쪽으로 나눌 수 있는 수
        left += String(i).repeat(count);     // 왼쪽에 배치
    }

    const right = [...left].reverse().join(""); // 오른쪽은 좌측 반대 순서
    return left + "0" + right; // 중앙에 물 배치
}
