function solution(str_list) {
    for (let i = 0; i < str_list.length; i++) {
        if (str_list[i] === "l") {
            return str_list.slice(0, i);  // "l"이 먼저 나오면 왼쪽 부분
        } else if (str_list[i] === "r") {
            return str_list.slice(i + 1);  // "r"이 먼저 나오면 오른쪽 부분
        }
    }
    return [];  // "l"이나 "r"이 없는 경우
}
