#include <stdio.h>
#include <string.h>

int main() {
    char s[105];
    scanf("%s", s);

    int len = (int)strlen(s);
    int count = 0;

    for (int i = 0; i < len; ) {
        if (i + 2 < len && s[i] == 'd' && s[i+1] == 'z' && s[i+2] == '=') {
            count++;
            i += 3;
        } else if (i + 1 < len) {
            if ((s[i] == 'c' && (s[i+1] == '=' || s[i+1] == '-')) ||
                (s[i] == 'd' && s[i+1] == '-') ||
                (s[i] == 'l' && s[i+1] == 'j') ||
                (s[i] == 'n' && s[i+1] == 'j') ||
                (s[i] == 's' && s[i+1] == '=') ||
                (s[i] == 'z' && s[i+1] == '=')) {
                count++;
                i += 2;
            } else {
                count++;
                i += 1;
            }
        } else {
            count++;
            i += 1;
        }
    }

    printf("%d\n", count);
    return 0;
}