function solution(arr) {
    var answer = [];
    
    // 첫 번째 원소는 무조건 추가
    answer.push(arr[0]);
    
    for (let i = 1; i < arr.length; i++) {
        if (arr[i] !== arr[i - 1]) { // 직전 원소와 다르면 추가
            answer.push(arr[i]);
        }
    }
    
    return answer;
}
