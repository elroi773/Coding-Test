#include <string>
#include <vector>
#include <queue>
#include <utility>

using namespace std;

vector<int> solution(vector<string> operations) {
    // (value, id)
    priority_queue<pair<int,int>> maxH;
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> minH;

    vector<char> alive;
    alive.reserve(operations.size());

    int nextId = 0;
    int validCount = 0;

    auto cleanMax = [&]() {
        while (!maxH.empty() && alive[maxH.top().second] == 0) maxH.pop();
    };
    auto cleanMin = [&]() {
        while (!minH.empty() && alive[minH.top().second] == 0) minH.pop();
    };

    for (const string& op : operations) {
        if (op[0] == 'I') {
            int x = stoi(op.substr(2));
            int id = nextId++;
            alive.push_back(1);
            maxH.push({x, id});
            minH.push({x, id});
            validCount++;
        } else { // 'D'
            if (validCount == 0) continue;

            if (op[2] == '1') { // D 1 : delete max
                cleanMax();
                if (!maxH.empty()) {
                    int id = maxH.top().second;
                    maxH.pop();
                    alive[id] = 0;
                    validCount--;
                }
            } else { // D -1 : delete min
                cleanMin();
                if (!minH.empty()) {
                    int id = minH.top().second;
                    minH.pop();
                    alive[id] = 0;
                    validCount--;
                }
            }
        }
    }

    if (validCount == 0) return {0, 0};

    cleanMax();
    cleanMin();

    return {maxH.top().first, minH.top().first};
}