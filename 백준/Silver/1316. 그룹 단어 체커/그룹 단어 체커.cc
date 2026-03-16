#include <iostream>
#include <string>
#include <vector>
using namespace std;

bool isGroupWord(const string& word) {
    bool visited[26] = { false };
    
    char prev = 0;
    for (char c : word) {
        if (c != prev) {
            if (visited[c - 'a']) {
                return false;
            }
            visited[c - 'a'] = true;
        }
        prev = c;
    }
    
    return true;
}

int main() {
    int N;
    cin >> N;
    
    int count = 0;
    while (N--) {
        string word;
        cin >> word;
        
        if (isGroupWord(word)) {
            count++;
        }
    }
    
    cout << count << '\n';
    return 0;
}