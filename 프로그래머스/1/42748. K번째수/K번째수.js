function solution(array, commands) {
    var answer = [];
    
    for (let [i, j, k] of commands) {
        let sliced = array.slice(i - 1, j).sort((a, b) => a - b);
        answer.push(sliced[k - 1]);
    }
    
    return answer;
}
