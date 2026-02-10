#include <vector>
#include <set>
#include <tuple>
#include <algorithm>

using namespace std;

struct Node {
    int x, y, a; // a: 0 pillar, 1 beam
    bool operator<(const Node& other) const {
        if (x != other.x) return x < other.x;
        if (y != other.y) return y < other.y;
        return a < other.a;
    }
};

static bool exists(const set<Node>& S, int x, int y, int a) {
    return S.find(Node{x, y, a}) != S.end();
}

static bool validOne(const set<Node>& S, const Node& n) {
    int x = n.x, y = n.y, a = n.a;

    if (a == 0) { // pillar
        if (y == 0) return true; // on ground
        if (exists(S, x, y - 1, 0)) return true; // on another pillar
        if (exists(S, x - 1, y, 1)) return true; // on left beam end
        if (exists(S, x, y, 1)) return true;     // on right beam end
        return false;
    } else { // beam
        if (exists(S, x, y - 1, 0)) return true;     // left end on pillar
        if (exists(S, x + 1, y - 1, 0)) return true; // right end on pillar
        if (exists(S, x - 1, y, 1) && exists(S, x + 1, y, 1)) return true; // connected both sides
        return false;
    }
}

static bool validAll(const set<Node>& S) {
    for (const auto& n : S) {
        if (!validOne(S, n)) return false;
    }
    return true;
}

vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
    set<Node> S;

    for (const auto& cmd : build_frame) {
        int x = cmd[0], y = cmd[1], a = cmd[2], b = cmd[3];
        Node cur{x, y, a};

        if (b == 1) { // install
            S.insert(cur);
            if (!validAll(S)) S.erase(cur);
        } else { // delete
            S.erase(cur);
            if (!validAll(S)) S.insert(cur);
        }
    }

    vector<vector<int>> answer;
    answer.reserve(S.size());
    for (const auto& nnode : S) {
        answer.push_back({nnode.x, nnode.y, nnode.a});
    }
    // set 자체가 (x,y,a) 오름차순이라 이미 정렬되어 있지만, 명시적으로 해도 OK
    sort(answer.begin(), answer.end());
    return answer;
}