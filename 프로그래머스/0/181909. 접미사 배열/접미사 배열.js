function solution(my_string) {
    // 모든 접미사를 answer 배열에 담기
    let answer = [];
    for (let i = 0; i < my_string.length; i++) {
        answer.push(my_string.slice(i));
    }

    // 사전순 정렬
    answer.sort();

    return answer;
}
