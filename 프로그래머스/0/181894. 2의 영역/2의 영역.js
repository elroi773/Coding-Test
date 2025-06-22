function solution(arr) {
    // 배열에서 2의 인덱스를 찾음
    const firstIndex = arr.indexOf(2);
    const lastIndex = arr.lastIndexOf(2);

    // 2가 없으면 [-1] 반환
    if (firstIndex === -1) return [-1];

    // 처음 2부터 마지막 2까지의 부분 배열 반환
    return arr.slice(firstIndex, lastIndex + 1);
}
