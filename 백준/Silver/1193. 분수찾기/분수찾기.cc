#include <iostream>
using namespace std;

int main() {
    int x;
    cin >> x;

    int line = 1;

    while (x > line) {
        x -= line;
        line++;
    }

    if (line % 2 == 0) {
        cout << x << "/" << line - x + 1;
    } else {
        cout << line - x + 1 << "/" << x;
    }

    return 0;
}