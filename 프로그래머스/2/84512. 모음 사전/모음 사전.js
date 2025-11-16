function solution(word) {
    const vowels = ['A', 'E', 'I', 'O', 'U'];
    const weights = [781, 156, 31, 6, 1];
    let answer = 0;
    
    for (let i = 0; i < word.length; i++) {
        const index = vowels.indexOf(word[i]);
        answer += index * weights[i] + 1;
    }
    
    return answer;
}
