function solution(names) {
    let result = [];
    
    for (let i = 0; i < names.length; i += 5) {
        result.push(names[i]); // 각 그룹의 첫 번째 사람 추가
    }
    
    return result;
}
