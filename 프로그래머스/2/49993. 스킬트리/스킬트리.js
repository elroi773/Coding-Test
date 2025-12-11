function solution(skill, skill_trees) {
    let answer = 0;

    for (let tree of skill_trees) {
        let idx = 0;           // skill에서 현재 배워야 할 스킬 위치
        let valid = true;

        for (let c of tree) {
            let pos = skill.indexOf(c);

            if (pos !== -1) {  // skill 안에 포함된 스킬이면
                if (pos === idx) {
                    idx++;     // 올바른 순서
                } else {
                    valid = false; // 순서 틀림
                    break;
                }
            }
        }

        if (valid) answer++;
    }

    return answer;
}
