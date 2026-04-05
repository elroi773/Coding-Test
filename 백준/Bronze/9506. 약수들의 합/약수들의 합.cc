#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    while (cin >> n) {
        if (n == -1) break;

        vector<int> divisors;
        divisors.push_back(1);

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.push_back(i);
                if (i != n / i) divisors.push_back(n / i);
            }
        }

        sort(divisors.begin(), divisors.end());

        int sum = 0;
        for (int d : divisors) sum += d;

        if (sum == n) {
            cout << n << " = ";
            for (int i = 0; i < divisors.size(); i++) {
                cout << divisors[i];
                if (i != divisors.size() - 1) cout << " + ";
            }
            cout << '\n';
        } else {
            cout << n << " is NOT perfect.\n";
        }
    }

    return 0;
}