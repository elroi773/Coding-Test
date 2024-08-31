class Solution {
    public int[] solution(int money) {
        int price = 5500; // 아메리카노 한 잔의 가격
        int count = money / price; // 최대로 살 수 있는 아메리카노의 잔 수
        int remainingMoney = money % price; // 남는 돈
        
        return new int[] {count, remainingMoney};
    }
}
