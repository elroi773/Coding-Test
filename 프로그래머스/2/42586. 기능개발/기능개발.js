function solution(progresses, speeds) {
    var answer = [];
    
    // 1. 각 작업 완료까지 걸리는 날짜 계산
    let days = progresses.map((p, i) => {
        return Math.ceil((100 - p) / speeds[i]);
    });
    
    // 2. 배포 묶기
    let currentDay = days[0];
    let count = 1;
    
    for (let i = 1; i < days.length; i++) {
        if (days[i] <= currentDay) {
            count++;
        } else {
            answer.push(count);
            currentDay = days[i];
            count = 1;
        }
    }
    
    // 마지막 배포
    answer.push(count);
    
    return answer;
}
