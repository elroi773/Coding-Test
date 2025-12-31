#include <stdio.h>
#include <ctype.h>

int main(void) {
    int cnt[26] = {0};
    int ch;

    while ((ch = getchar()) != EOF && ch != '\n') {
        ch = toupper((unsigned char)ch);
        if (ch >= 'A' && ch <= 'Z') {
            cnt[ch - 'A']++;
        }
    }

    int max = -1, maxIdx = -1, maxCount = 0;

    for (int i = 0; i < 26; i++) {
        if (cnt[i] > max) {
            max = cnt[i];
            maxIdx = i;
            maxCount = 1;
        } else if (cnt[i] == max) {
            maxCount++;
        }
    }

    if (maxCount > 1) printf("?\n");
    else printf("%c\n", 'A' + maxIdx);

    return 0;
}