#include <string>
#include <vector>
#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    long long total = 0;
    
    for (int w : works) {
        total += w;
    }
    
    // 일을 다 처리할 수 있으면 피로도는 0
    if (total <= n) return 0;
    
    priority_queue<int> pq;
    
    for (int w : works) {
        pq.push(w);
    }
    
    while (n--) {
        int top = pq.top();
        pq.pop();
        pq.push(top - 1);
    }
    
    long long answer = 0;
    
    while (!pq.empty()) {
        long long x = pq.top();
        pq.pop();
        answer += x * x;
    }
    
    return answer;
}