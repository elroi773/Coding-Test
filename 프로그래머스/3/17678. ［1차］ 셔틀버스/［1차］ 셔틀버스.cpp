#include <string>
#include <vector>
#include <algorithm>
#include <cstdio>

using namespace std;

string solution(int n, int t, int m, vector<string> timetable) {
    // "HH:MM" -> 분
    auto toMin = [](const string& s) -> int {
        int h = stoi(s.substr(0, 2));
        int mm = stoi(s.substr(3, 2));
        return h * 60 + mm;
    };
    
    // 분 -> "HH:MM"
    auto toStr = [](int minutes) -> string {
        int h = minutes / 60;
        int mm = minutes % 60;
        char buf[6];
        sprintf(buf, "%02d:%02d", h, mm);
        return string(buf);
    };
    
    // 크루 도착 시간 정렬
    vector<int> crew;
    crew.reserve(timetable.size());
    for (const auto& time : timetable) {
        crew.push_back(toMin(time));
    }
    sort(crew.begin(), crew.end());
    
    int idx = 0; // 아직 탑승하지 않은 크루 인덱스
    
    for (int i = 0; i < n; i++) {
        int busTime = 9 * 60 + i * t; // 09:00 + i*t
        int boarded = 0;
        
        // 현재 셔틀에 탑승 가능한 크루 태우기
        while (idx < (int)crew.size() && crew[idx] <= busTime && boarded < m) {
            idx++;
            boarded++;
        }
        
        // 마지막 셔틀이면 콘의 도착 시각 결정
        if (i == n - 1) {
            if (boarded < m) {
                // 자리 남음: 셔틀 도착 시각에 와도 됨
                return toStr(busTime);
            } else {
                // 자리 꽉 참: 마지막 탑승자보다 1분 먼저
                return toStr(crew[idx - 1] - 1);
            }
        }
    }
    
    return "";
}