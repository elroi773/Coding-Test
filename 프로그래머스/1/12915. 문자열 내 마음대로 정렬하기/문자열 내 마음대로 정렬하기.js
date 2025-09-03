function solution(strings,n){
    let result = [];
    for (let i = 0; i < strings.length; i++){
        result[i] = strings[i][n] + strings[i];
    }
    result.sort();
    
    for(let j = 0; j < result.length; j++){
        result[j] = result[j].slice(1);
    }
    return result;
}