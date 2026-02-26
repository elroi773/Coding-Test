#include <iostream>
#include <vector>
using namespace std;

int solution(int n, vector<int> stations, int w)
{
    long long answer = 0;                 // 안전하게 long long
    long long cover = 2LL * w + 1;        // 기지국 1개가 커버하는 길이
    long long position = 1;               // 아직 커버 안 된 시작 아파트 번호 (1-based)

    for (int s : stations) {
        long long left = (long long)s - w;   // 이 기지국이 커버 시작
        long long right = (long long)s + w;  // 이 기지국이 커버 끝

        // position ~ (left-1) 가 비어있다면 채우기
        if (position < left) {
            long long gap = left - position;
            // ceil(gap / cover) = (gap + cover - 1) / cover
            answer += (gap + cover - 1) / cover;
        }

        // 이 기지국이 커버하는 구간 다음으로 position 이동
        position = max(position, right + 1);

        if (position > n) break;
    }

    // 마지막 기지국 이후 끝까지 남은 구간
    if (position <= n) {
        long long gap = (long long)n - position + 1;
        answer += (gap + cover - 1) / cover;
    }

    return (int)answer;
}