#include <iostream>
using namespace std;

int main() {
    int chess[6] = {1, 1, 2, 2, 2, 8};
    int input[6];

    for (int i = 0; i < 6; i++) {
        cin >> input[i];
        cout << chess[i] - input[i] << " ";
    }

    return 0;
}