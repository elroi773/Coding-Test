class Solution {
    public int solution(int n, int k) {
        int pricePerSkewer = 12000;
        int pricePerDrink = 2000;

        // 양꼬치 비용 계산
        int skewerCost = n * pricePerSkewer;
        
        // 서비스 음료수 계산
        int freeDrinks = n / 10;
        
        // 실제 지불해야 할 음료수 계산
        int drinkCost = (k - freeDrinks) * pricePerDrink;

        // 총 비용 계산
        int totalCost = skewerCost + drinkCost;
        
        return totalCost;
    }
}
