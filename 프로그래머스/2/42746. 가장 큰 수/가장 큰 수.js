function solution(numbers) {
    // 문자열 배열로 변환 후 정렬
    let arr = numbers.map(String);

    arr.sort((a, b) => (b + a) - (a + b));

    // "0"으로만 구성된 경우
    if (arr[0] === "0") return "0";

    // 합치기
    return arr.join('');
}
