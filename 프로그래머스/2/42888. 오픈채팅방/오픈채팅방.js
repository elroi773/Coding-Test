function solution(record) {
    const user = {}; // uid → nickname 저장

    // 1) 마지막 닉네임 업데이트
    for (let r of record) {
        const [action, uid, nickname] = r.split(" ");

        if (action === "Enter" || action === "Change") {
            user[uid] = nickname;
        }
    }

    // 2) 실제 출력 메시지 생성
    const result = [];

    for (let r of record) {
        const [action, uid] = r.split(" ");

        if (action === "Enter") {
            result.push(`${user[uid]}님이 들어왔습니다.`);
        } else if (action === "Leave") {
            result.push(`${user[uid]}님이 나갔습니다.`);
        }
    }

    return result;
}
