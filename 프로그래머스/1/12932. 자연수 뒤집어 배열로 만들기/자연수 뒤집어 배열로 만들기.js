function solution(n) {
    var answer = String(n)     // 숫자를 문자열로
                  .split("")   // ["1","2","3","4","5"]
                  .reverse()   // ["5","4","3","2","1"]
                  .map(Number);// [5,4,3,2,1]
    return answer;
}
