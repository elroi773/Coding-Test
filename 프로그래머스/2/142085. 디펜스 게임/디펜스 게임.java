import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // Java의 PriorityQueue는 기본적으로 '최소 힙'이므로,
        // Collections.reverseOrder()를 써서 '최대 힙'으로 만듭니다.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];               // 병사로 일단 막음
            maxHeap.add(enemy[i]);       // 막은 라운드 저장
            
            if (n < 0) {                 // 병사가 부족할 때
                if (k > 0) {             // 무적권이 있으면
                    int largest = maxHeap.poll(); // 가장 큰 적 수 제거 (무적권 사용)
                    n += largest;        // 병사 복원
                    k--;                 // 무적권 사용
                } else {                 // 무적권 없으면 종료
                    return i;
                }
            }
        }
        
        return enemy.length; // 모든 라운드 막았을 때
    }
}
