function solution(h1, m1, s1, h2, m2, s2) {
    // 초 단위로 변환
    const start = h1 * 3600 + m1 * 60 + s1;
    const end   = h2 * 3600 + m2 * 60 + s2;

    // [start, end] 구간에서
    // "초침 - 다른 바늘" 한 쌍이 겹치는 횟수를 세는 함수
    // 상대각속도 Δω/360 = num/den 일 때,
    // 0초부터 t초까지 겹친 횟수 F(t) = floor(num * t / den)
    // [start, end] 겹침 횟수 = F(end) - F(start) + I(start에서 겹치면 1)
    const pairCountClosed = (start, end, num, den) => {
        const F_end = Math.floor((num * end) / den);
        const F_start = Math.floor((num * start) / den);
        let cnt = F_end - F_start;

        // start 시각이 겹치는 순간이면 포함
        if ((num * start) % den === 0) {
            cnt += 1;
        }
        return cnt;
    };

    // 초침 - 분침
    // Δω = 6 - 0.1 = 5.9 deg/s, Δω/360 = 59/3600
    const cnt_sm = pairCountClosed(start, end, 59, 3600);

    // 초침 - 시침
    // Δω = 6 - 1/120 = 719/120 deg/s, Δω/360 = 719/43200
    const cnt_sh = pairCountClosed(start, end, 719, 43200);

    let total = cnt_sm + cnt_sh;

    // 세 바늘이 동시에 겹치는 시각: 0초(0:0:0), 43200초(12:0:0)
    // 이 시각들은 초-분, 초-시 둘 다 겹치므로 2번 세었음 → 실제 알람은 1번
    let triple = 0;
    if (start <= 0 && 0 <= end) triple++;
    if (start <= 43200 && 43200 <= end) triple++;

    // triple 개수만큼 1씩 빼서 중복 제거
    return total - triple;
}