#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

static bool diffOne(const string& a, const string& b) {
    int diff = 0;
    for (int i = 0; i < (int)a.size(); i++) {
        if (a[i] != b[i]) {
            diff++;
            if (diff > 1) return false;
        }
    }
    return diff == 1;
}

int solution(string begin, string target, vector<string> words) {
    // target이 words에 없으면 불가능
    if (find(words.begin(), words.end(), target) == words.end()) return 0;

    int n = (int)words.size();
    vector<int> dist(n, -1);
    queue<int> q;

    // begin에서 시작: begin과 1글자 다른 단어들을 큐에 넣고 dist=1
    for (int i = 0; i < n; i++) {
        if (diffOne(begin, words[i])) {
            dist[i] = 1;
            q.push(i);
        }
    }

    while (!q.empty()) {
        int cur = q.front(); q.pop();

        if (words[cur] == target) return dist[cur];

        for (int nxt = 0; nxt < n; nxt++) {
            if (dist[nxt] == -1 && diffOne(words[cur], words[nxt])) {
                dist[nxt] = dist[cur] + 1;
                q.push(nxt);
            }
        }
    }

    return 0;
}