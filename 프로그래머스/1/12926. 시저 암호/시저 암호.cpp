#include <string>
#include <vector>
using namespace std;

string solution(string s, int n) {
    string answer = "";
    for (char c : s) {
        if (c == ' ') {  
            // 공백은 그대로
            answer += ' ';
        } else if (c >= 'A' && c <= 'Z') {  
            // 대문자 처리
            answer += (c - 'A' + n) % 26 + 'A';
        } else if (c >= 'a' && c <= 'z') {  
            // 소문자 처리
            answer += (c - 'a' + n) % 26 + 'a';
        }
    }
    return answer;
}
