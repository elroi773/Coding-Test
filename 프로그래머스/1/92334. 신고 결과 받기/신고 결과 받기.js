function solution(id_list, report, k) {
    // 1. 중복 제거
    const uniqueReports = [...new Set(report)];

    // 2. 신고 관계 저장
    const reportedBy = {};  // {신고당한 사람: [신고한 사람들]}
    const reportedCount = {}; // {유저: 신고당한 횟수}

    id_list.forEach(id => {
        reportedBy[id] = [];
        reportedCount[id] = 0;
    });

    uniqueReports.forEach(r => {
        const [user, target] = r.split(" ");
        reportedBy[target].push(user);
        reportedCount[target]++;
    });

    // 3. 정지된 유저 찾기
    const banned = id_list.filter(user => reportedCount[user] >= k);

    // 4. 메일 횟수 계산
    const idIndex = {};
    id_list.forEach((id, i) => idIndex[id] = i);
    const answer = new Array(id_list.length).fill(0);

    banned.forEach(b => {
        reportedBy[b].forEach(reporter => {
            answer[idIndex[reporter]]++;
        });
    });

    return answer;
}
