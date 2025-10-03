function solution(picks, minerals) {
    // 피로도 표 [곡괭이][광물]
    const fatigue = [
        [1, 1, 1],   // 다이아 곡괭이
        [5, 1, 1],   // 철 곡괭이
        [25, 5, 1]   // 돌 곡괭이
    ];

    // 광물 문자열 → 숫자 매핑
    const mineralToInt = (m) => {
        if (m === "diamond") return 0;
        if (m === "iron") return 1;
        return 2; // stone
    };

    const totalPicks = picks[0] + picks[1] + picks[2];
    let groupCount = Math.ceil(minerals.length / 5);
    groupCount = Math.min(groupCount, totalPicks);

    // 1) 그룹화
    const groups = [];
    for (let i = 0; i < groupCount; i++) {
        const slice = minerals.slice(i * 5, i * 5 + 5).map(mineralToInt);
        groups.push(slice);
    }

    // 2) 그룹 난이도 계산 (돌 곡괭이로 캤을 때 피로도)
    groups.sort((a, b) => {
        const ha = a.reduce((sum, m) => sum + fatigue[2][m], 0);
        const hb = b.reduce((sum, m) => sum + fatigue[2][m], 0);
        return hb - ha; // 내림차순
    });

    // 3) 곡괭이 리스트 풀기 (0=다이아,1=철,2=돌)
    const pickList = [];
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < picks[i]; j++) {
            pickList.push(i);
        }
    }
    pickList.sort((a, b) => a - b); // 좋은 곡괭이부터

    // 4) 피로도 계산
    let answer = 0;
    for (let i = 0; i < groups.length; i++) {
        const pick = pickList[i];
        for (const m of groups[i]) {
            answer += fatigue[pick][m];
        }
    }

    return answer;
}
