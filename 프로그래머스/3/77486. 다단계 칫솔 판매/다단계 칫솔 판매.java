import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;

        // 이름 -> 인덱스
        Map<String, Integer> idx = new HashMap<>(n * 2);
        for (int i = 0; i < n; i++) idx.put(enroll[i], i);

        // 부모(추천인) 인덱스: 없으면 -1(센터)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) parent[i] = -1;
            else parent[i] = idx.get(referral[i]);
        }

        int[] profit = new int[n];

        // 판매 기록 처리
        for (int i = 0; i < seller.length; i++) {
            int cur = idx.get(seller[i]);
            int money = amount[i] * 100;

            while (cur != -1 && money > 0) {
                int give = money / 10;     // 10% (원 단위 절사)
                int keep = money - give;
                profit[cur] += keep;

                cur = parent[cur];
                money = give;              // 위로 전달되는 금액
            }
        }

        return profit;
    }
}