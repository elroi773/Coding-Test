#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

bool isPrime(int x) {
    if (x == 1) return false;
    for (int i = 2; i <= sqrt(x); i++) {
        if (x % i == 0) return false;
    }
    return true;
}

int main() {
    int n, num, count = 0;
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> num;
        if (isPrime(num)) count++;
    }

    cout << count;
    return 0;
}