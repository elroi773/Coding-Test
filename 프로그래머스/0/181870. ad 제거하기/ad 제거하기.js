function solution(strArr) {
    var answer = strArr.filter(str => !str.includes("ad"));
    return answer;
}
