
function solution(str1, str2) {
    var answer = '';
    
    // 두 문자열의 길이가 같으므로 하나의 길이로 반복
    for (let i = 0; i < str1.length; i++) {
        answer += str1[i] + str2[i];
    }
    
    return answer;
}

// 테스트
console.log(solution("aaaaa", "bbbbb")); // "ababababab"
console.log(solution("abc", "def"));     // "adbecf"
console.log(solution("hi", "yo"));       // "hyio"