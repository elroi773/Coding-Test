#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Job {
    int dur, req, id;
};

// 우선순위: dur ↑, req ↑, id ↑
struct Cmp {
    bool operator()(const Job& a, const Job& b) const {
        if (a.dur != b.dur) return a.dur > b.dur;
        if (a.req != b.req) return a.req > b.req;
        return a.id > b.id;
    }
};

int solution(vector<vector<int>> jobs) {
    int n = (int)jobs.size();
    if (n == 0) return 0;

    vector<Job> arr;
    arr.reserve(n);
    for (int i = 0; i < n; i++) {
        arr.push_back({jobs[i][1], jobs[i][0], i}); // dur, req, id
    }

    // 요청시각 기준 정렬
    sort(arr.begin(), arr.end(), [](const Job& a, const Job& b) {
        if (a.req != b.req) return a.req < b.req;
        if (a.dur != b.dur) return a.dur < b.dur;
        return a.id < b.id;
    });

    priority_queue<Job, vector<Job>, Cmp> pq;

    long long time = 0;
    long long total = 0;
    int idx = 0, done = 0;

    while (done < n) {
        // 현재 시각까지 들어온 작업들 큐에 삽입
        while (idx < n && arr[idx].req <= time) {
            pq.push(arr[idx]);
            idx++;
        }

        if (pq.empty()) {
            // 대기 큐가 비었으면 다음 요청 시각으로 점프
            time = max<long long>(time, arr[idx].req);
            continue;
        }

        // 우선순위 가장 높은 작업 수행 (선점 없음)
        Job cur = pq.top(); pq.pop();
        time += cur.dur;
        total += (time - cur.req);
        done++;
    }

    return (int)(total / n);
}