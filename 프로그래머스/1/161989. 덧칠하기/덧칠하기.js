function solution(n, m, section) {
    let answer = 0;
    let painted = -1
    
    for (let i =0; i< section.length; i++){
        if (section[i] > painted){
            painted = section[i]+m-1;
            answer += 1;
        }
    }
    return answer;
}