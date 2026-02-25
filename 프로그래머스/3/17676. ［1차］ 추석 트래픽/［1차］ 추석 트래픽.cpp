#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

int parseTimeToMs(const string& timeStr) {
    // "hh:mm:ss.sss"
    int h = stoi(timeStr.substr(0, 2));
    int m = stoi(timeStr.substr(3, 2));
    int s = stoi(timeStr.substr(6, 2));
    int ms = stoi(timeStr.substr(9, 3));
    return ((h * 3600 + m * 60 + s) * 1000) + ms;
}

int parseDurationToMs(const string& durStr) {
    // "2s", "2.0s", "0.351s"
    string t = durStr.substr(0, durStr.size() - 1); // remove 's'
    size_t dot = t.find('.');
    
    if (dot == string::npos) {
        return stoi(t) * 1000;
    } else {
        int sec = stoi(t.substr(0, dot));
        string msStr = t.substr(dot + 1);
        while (msStr.size() < 3) msStr += '0';
        if (msStr.size() > 3) msStr = msStr.substr(0, 3);
        int ms = stoi(msStr);
        return sec * 1000 + ms;
    }
}

int solution(vector<string> lines) {
    vector<pair<int, int>> intervals; // {start, end}
    
    for (const string& line : lines) {
        // format: "2016-09-15 hh:mm:ss.sss x.xxxs"
        stringstream ss(line);
        string date, timeStr, durStr;
        ss >> date >> timeStr >> durStr;
        
        int endMs = parseTimeToMs(timeStr);
        int durMs = parseDurationToMs(durStr);
        int startMs = endMs - durMs + 1; // inclusive
        
        intervals.push_back({startMs, endMs});
    }
    
    int answer = 0;
    
    // 후보 시점: 각 로그의 시작/끝 시점
    for (const auto& itv : intervals) {
        int candidates[2] = {itv.first, itv.second};
        
        for (int i = 0; i < 2; i++) {
            int windowStart = candidates[i];
            int windowEnd = windowStart + 999; // 1초 구간 [start, start+999]
            
            int count = 0;
            for (const auto& target : intervals) {
                // 구간 겹침 판정
                if (target.second >= windowStart && target.first <= windowEnd) {
                    count++;
                }
            }
            
            answer = max(answer, count);
        }
    }
    
    return answer;
}