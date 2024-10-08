class Solution {
    public String solution(int a, int b) {
        // 2016년 각 월의 일수
        int[] daysOfMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // 요일 배열 (일요일부터 토요일까지)
        String[] daysOfWeek = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        
        // 1월 1일을 기준으로 a월 b일까지의 총 일수 계산
        int totalDays = 0;
        
        // a-1월까지의 일수 합산
        for (int i = 0; i < a - 1; i++) {
            totalDays += daysOfMonth[i];
        }
        
        // b일 더하기
        totalDays += b - 1;  // 1월 1일이 첫 번째 날이므로 b에서 1을 뺌
        
        // 총 일수를 7로 나눈 나머지를 사용해 요일 계산
        return daysOfWeek[totalDays % 7];
    }
}
