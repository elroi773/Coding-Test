import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    public int[] solution(int n) {
        // 소인수를 담을 Set (중복 방지)
        HashSet<Integer> primes = new HashSet<>();
        
        // 2부터 n의 제곱근까지 모든 수에 대해 나누기 시도
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) { // i로 나누어 떨어지면 소인수임
                primes.add(i);   // 소인수로 추가
                n /= i;          // n을 i로 나누어 갱신
            }
        }
        
        // 남은 n이 1보다 크다면 그것도 소인수
        if (n > 1) {
            primes.add(n);
        }
        
        // Set을 List로 변환 후 정렬
        List<Integer> primeList = new ArrayList<>(primes);
        primeList.sort(null);
        
        // List를 배열로 변환하여 반환
        return primeList.stream().mapToInt(Integer::intValue).toArray();
    }
}
