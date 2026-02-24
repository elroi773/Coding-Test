#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;

    // 장르별 총 재생 수
    unordered_map<string, int> genreTotal;

    // 장르별 노래 목록: (고유번호, 재생수)
    unordered_map<string, vector<pair<int, int>>> genreSongs;

    for (int i = 0; i < (int)genres.size(); i++) {
        genreTotal[genres[i]] += plays[i];
        genreSongs[genres[i]].push_back({i, plays[i]});
    }

    // 장르를 총 재생 수 기준으로 정렬하기 위해 벡터로 변환
    vector<pair<string, int>> sortedGenres;
    for (auto &g : genreTotal) {
        sortedGenres.push_back({g.first, g.second});
    }

    sort(sortedGenres.begin(), sortedGenres.end(),
         [](const pair<string, int>& a, const pair<string, int>& b) {
             return a.second > b.second; // 총 재생 수 내림차순
         });

    // 각 장르에서 상위 2곡 선택
    for (auto &g : sortedGenres) {
        string genre = g.first;
        auto &songs = genreSongs[genre];

        sort(songs.begin(), songs.end(),
             [](const pair<int, int>& a, const pair<int, int>& b) {
                 if (a.second == b.second) return a.first < b.first; // 재생 수 같으면 고유번호 오름차순
                 return a.second > b.second; // 재생 수 내림차순
             });

        int cnt = min(2, (int)songs.size());
        for (int i = 0; i < cnt; i++) {
            answer.push_back(songs[i].first);
        }
    }

    return answer;
}