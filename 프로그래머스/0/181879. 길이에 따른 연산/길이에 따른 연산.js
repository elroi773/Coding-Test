function solution(num_list) {
    if (num_list.length >= 11) {
        return num_list.reduce((acc, curr) => acc + curr, 0);  // 합 구하기
    } else {
        return num_list.reduce((acc, curr) => acc * curr, 1);  // 곱 구하기
    }
}
