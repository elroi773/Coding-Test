#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times) {
    sort(times.begin(), times.end());

    long long left = 1;
    long long right = (long long)times.back() * (long long)n; // 최악의 경우
    long long answer = right;

    while (left <= right) {
        long long mid = left + (right - left) / 2;

        long long processed = 0;
        for (int t : times) {
            processed += mid / (long long)t;
            if (processed >= n) break; // 충분하면 더 안 더해도 됨
        }

        if (processed >= n) {
            answer = mid;
            right = mid - 1; // 더 작은 시간 가능?
        } else {
            left = mid + 1;  // 시간이 더 필요
        }
    }

    return answer;
}