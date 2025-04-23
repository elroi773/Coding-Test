#include <stdio.h>
#include <string.h>

int solution(const char* myString, const char* pat) {
    int answer = 0;
    int lenMy = strlen(myString);
    int lenPat = strlen(pat);
    
    for (int i = 0; i <= lenMy - lenPat; i++) {
        if (strncmp(&myString[i], pat, lenPat) == 0) {
            answer++;
        }
    }
    
    return answer;
}
