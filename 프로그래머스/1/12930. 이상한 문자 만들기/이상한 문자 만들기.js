function solution(s) {
    // 공백으로 단어 분리
    const words = s.split(' ');

    // 각 단어별로 문자 변환
    const transformed = words.map(word => {
        let newWord = '';
        for (let i = 0; i < word.length; i++) {
            if (i % 2 === 0) {
                newWord += word[i].toUpperCase();
            } else {
                newWord += word[i].toLowerCase();
            }
        }
        return newWord;
    });

    // 다시 공백으로 합쳐서 반환
    return transformed.join(' ');
}
