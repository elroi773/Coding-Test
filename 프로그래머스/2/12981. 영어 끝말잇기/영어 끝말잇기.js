function solution(n, words) {
    const used = new Set();

    for (let i = 0; i < words.length; i++) {
        const word = words[i];

        // 1️⃣ 한 글자인 단어
        if (word.length < 2) {
            return [(i % n) + 1, Math.floor(i / n) + 1];
        }

        // 2️⃣ 이미 나온 단어
        if (used.has(word)) {
            return [(i % n) + 1, Math.floor(i / n) + 1];
        }

        // 3️⃣ 끝말잇기 규칙 위반 (첫 단어 제외)
        if (i > 0) {
            const prev = words[i - 1];
            if (prev[prev.length - 1] !== word[0]) {
                return [(i % n) + 1, Math.floor(i / n) + 1];
            }
        }

        used.add(word);
    }

    // 탈락자가 없는 경우
    return [0, 0];
}
