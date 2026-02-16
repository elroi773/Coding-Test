#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int solution(int N, int number) {
    if (N == number) return 1;

    vector<unordered_set<int>> dp(9); // dp[i] = N을 i번 써서 만들 수 있는 값들

    for (int i = 1; i <= 8; i++) {
        // 이어붙인 수: N, NN, NNN...
        int concat = 0;
        for (int k = 0; k < i; k++) concat = concat * 10 + N;
        dp[i].insert(concat);

        // j + (i-j) 분할 조합
        for (int j = 1; j < i; j++) {
            for (int a : dp[j]) {
                for (int b : dp[i - j]) {
                    dp[i].insert(a + b);
                    dp[i].insert(a - b);
                    dp[i].insert(a * b);
                    if (b != 0) dp[i].insert(a / b); // 정수 나눗셈(나머지 버림)
                }
            }
        }

        if (dp[i].find(number) != dp[i].end()) return i;
    }

    return -1;
}