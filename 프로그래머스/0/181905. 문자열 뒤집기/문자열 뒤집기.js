function solution(my_string, s, e) {
    // 부분 문자열을 뒤집어서 적용
    return my_string.slice(0, s) + 
           my_string.slice(s, e + 1).split('').reverse().join('') + 
           my_string.slice(e + 1);
}
