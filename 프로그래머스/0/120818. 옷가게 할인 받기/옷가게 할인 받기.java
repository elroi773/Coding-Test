class Solution {
    public int solution(int price) {
        // 할인율을 계산하여 price에 적용합니다.
        if (price >= 500000) {
            price = price * 80 / 100; // 20% 할인
        } else if (price >= 300000) {
            price = price * 90 / 100; // 10% 할인
        } else if (price >= 100000) {
            price = price * 95 / 100; // 5% 할인
        }
        // 최종 금액을 반환합니다.
        return price;
    }
}
