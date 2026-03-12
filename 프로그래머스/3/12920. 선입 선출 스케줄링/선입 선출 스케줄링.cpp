#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, vector<int> cores) {
    int m = cores.size();
    
    // 처음에는 각 코어가 바로 하나씩 작업을 시작
    if (n <= m) return n;
    
    auto count_jobs = [&](long long t) {
        long long total = 0;
        for (int core : cores) {
            total += (t / core) + 1;
        }
        return total;
    };
    
    long long left = 0;
    long long right = (long long)*max_element(cores.begin(), cores.end()) * n;
    
    // n개 이상 처리 가능한 최소 시간 찾기
    while (left < right) {
        long long mid = (left + right) / 2;
        if (count_jobs(mid) >= n) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    long long time = left;
    long long done = count_jobs(time - 1);
    
    // time 시점에 비는 코어들 앞에서부터 확인
    for (int i = 0; i < m; i++) {
        if (time % cores[i] == 0) {
            done++;
            if (done == n) {
                return i + 1;
            }
        }
    }
    
    return -1;
}