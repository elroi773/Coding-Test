#include <string>
#include <vector>

using namespace std;

void dfs(int cur, const vector<vector<int>>& computers, vector<int>& visited) {
    visited[cur] = 1;
    int n = (int)computers.size();

    for (int nxt = 0; nxt < n; ++nxt) {
        if (computers[cur][nxt] == 1 && !visited[nxt]) {
            dfs(nxt, computers, visited);
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    vector<int> visited(n, 0);
    int answer = 0;

    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            answer++;
            dfs(i, computers, visited);
        }
    }
    return answer;
}