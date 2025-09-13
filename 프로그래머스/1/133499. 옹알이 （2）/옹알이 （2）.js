function solution(babbling) {
    const words = ["aya", "ye", "woo", "ma"];
    let answer = 0;

    for (let s of babbling) {
        let pos = 0;
        let last = ""; // 직전에 사용한 단어
        let valid = true;

        while (pos < s.length) {
            let matched = false;

            for (let w of words) {
                if (s.startsWith(w, pos) && w !== last) {
                    pos += w.length;
                    last = w;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                valid = false;
                break;
            }
        }

        if (valid) answer++;
    }

    return answer;
}
