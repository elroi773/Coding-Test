#include <iostream>
#include <string>
using namespace std;

int main() {
    string n;
    int b;
    cin >> n >> b;

    int result = 0;

    for (char ch : n) {
        int value;
        if (ch >= '0' && ch <= '9') {
            value = ch - '0';
        } else {
            value = ch - 'A' + 10;
        }

        result = result * b + value;
    }

    cout << result << '\n';
    return 0;
}