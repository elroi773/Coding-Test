#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

long long solution(int price, int money, int count) {
    long long total = (long long)price * count * (count + 1) / 2;
    long long needed = total - money;
    if (needed > 0) return needed;
    else return 0;
}
