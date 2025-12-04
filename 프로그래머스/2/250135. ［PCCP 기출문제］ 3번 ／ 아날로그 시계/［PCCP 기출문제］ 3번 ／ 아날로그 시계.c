#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

typedef long long ll;

// [start, end] 구간에서
// "초침 - 다른 바늘" 한 쌍이 겹치는 횟수를 세는 함수
// 상대각속도 Δω/360 = num/den 일 때,
// 0초부터 t초까지 겹친 횟수 F(t) = floor(num * t / den)
// [start, end] 동안의 겹침 횟수 = F(end) - F(start) + I(start에서 겹치면 1)
static ll pair_count_closed(int start, int end, int num, int den) {
    ll n = num;
    ll s = start;
    ll e = end;
    ll d = den;

    ll F_end = (n * e) / d;
    ll F_start = (n * s) / d;
    ll cnt = F_end - F_start;

    // start 시각이 정확히 겹치는 순간이면 포함
    if ((n * s) % d == 0) {
        cnt += 1;
    }
    return cnt;
}

int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
    // 초 단위로 변환
    int start = h1 * 3600 + m1 * 60 + s1;
    int end   = h2 * 3600 + m2 * 60 + s2;

    // 초침 - 분침
    // Δω = 6 - 0.1 = 5.9 deg/s, Δω/360 = 59/3600
    ll cnt_sm = pair_count_closed(start, end, 59, 3600);

    // 초침 - 시침
    // Δω = 6 - 1/120 = 719/120 deg/s, Δω/360 = 719/43200
    ll cnt_sh = pair_count_closed(start, end, 719, 43200);

    ll total = cnt_sm + cnt_sh;

    // 세 바늘이 동시에 겹치는 시각: 0초(0시 0분 0초), 43200초(12시 0분 0초)
    // 이 시각들은 초-분, 초-시 둘 다 겹치므로 2번 세었음 → 실제 알람은 1번
    int triple = 0;
    if (start <= 0 && 0 <= end) {
        triple++;
    }
    if (start <= 43200 && 43200 <= end) {
        triple++;
    }

    // triple 개수만큼 1씩 빼서 중복 제거
    int answer = (int)(total - triple);
    return answer;
}