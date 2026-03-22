#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int C;
        cin >> C;

        int q = C / 25;
        C %= 25;

        int d = C / 10;
        C %= 10;

        int n = C / 5;
        C %= 5;

        int p = C;

        cout << q << ' ' << d << ' ' << n << ' ' << p << '\n';
    }

    return 0;
}