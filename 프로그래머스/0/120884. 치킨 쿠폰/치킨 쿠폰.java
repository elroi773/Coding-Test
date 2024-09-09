class Solution {
    public int solution(int chicken) {
        int answer = 0;  // 받을 수 있는 서비스 치킨 수
        int coupons = chicken;  // 처음에 받은 쿠폰 수

        while (coupons >= 10) {
            int service = coupons / 10;  // 받을 수 있는 서비스 치킨 수
            answer += service;  // 총 서비스 치킨 수에 더해줌
            coupons = coupons % 10 + service;  // 남은 쿠폰과 서비스 치킨으로 받은 쿠폰을 합산
        }

        return answer;
    }
}
