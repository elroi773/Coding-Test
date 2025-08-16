#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;

    // 첫 번째 원소는 무조건 추가
    answer.push_back(arr[0]);

    for (size_t i = 1; i < arr.size(); i++) {
        if (arr[i] != arr[i - 1]) { // 직전 원소와 다르면 추가
            answer.push_back(arr[i]);
        }
    }

    return answer;
}
