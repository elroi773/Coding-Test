#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;

    int side = (1 << n) + 1;
    cout << side * side << '\n';

    return 0;
}