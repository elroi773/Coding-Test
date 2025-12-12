import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people); // 정렬
        int left = 0; // 가장 가벼운 사람
        int right = people.length - 1; // 가장 무거운 사람
        
        int boats = 0;
        
        while (left <= right) {
            // 둘이 같이 탈 수 있는 경우
            if (people[left] + people[right] <= limit) {
                left++; // 가벼운 사람 태움
            }
            right--; // 무거운 사람 태움 (혼자 or 같이)
            boats++; // 보트 1개 사용
        }
        
        return boats;
    }
}
