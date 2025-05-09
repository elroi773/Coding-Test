function solution(arr, query) {
    for (let i = 0; i < query.length; i++) {
        if (i % 2 === 0) {
            // 짝수 인덱스: query[i] 이후는 잘라버림 (query[i] 포함)
            arr = arr.slice(0, query[i] + 1);
        } else {
            // 홀수 인덱스: query[i] 이전은 잘라버림 (query[i] 포함)
            arr = arr.slice(query[i]);
        }
    }
    return arr;
}
