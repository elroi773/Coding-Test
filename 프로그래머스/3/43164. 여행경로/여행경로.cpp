#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

vector<string> solution(vector<vector<string>> tickets) {
    unordered_map<string, vector<string>> g;
    g.reserve(tickets.size() * 2);

    // 1) 그래프 구성
    for (const auto& t : tickets) {
        g[t[0]].push_back(t[1]);
    }

    // 2) 사전순 가장 앞 경로를 만들기 위해
    //    목적지 리스트를 내림차순 정렬해두고 back()에서 pop
    for (auto& kv : g) {
        auto& v = kv.second;
        sort(v.begin(), v.end(), greater<string>());
    }

    // 3) Hierholzer (스택 기반)
    vector<string> st;
    st.reserve(tickets.size() + 1);
    st.push_back("ICN");

    vector<string> route;
    route.reserve(tickets.size() + 1);

    while (!st.empty()) {
        string cur = st.back();
        auto it = g.find(cur);

        if (it != g.end() && !it->second.empty()) {
            // 다음 목적지 하나 사용
            string nxt = it->second.back();
            it->second.pop_back();
            st.push_back(nxt);
        } else {
            // 더 갈 곳 없으면 경로 확정
            route.push_back(cur);
            st.pop_back();
        }
    }

    reverse(route.begin(), route.end());
    return route;
}