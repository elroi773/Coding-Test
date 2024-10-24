import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        // 부서별 요청 금액을 오름차순으로 정렬
        Arrays.sort(d);
        
        int answer = 0; // 지원 가능한 부서 수
        int sum = 0;    // 현재까지 사용한 금액의 합
        
        // 부서별 요청 금액을 순서대로 처리
        for (int i = 0; i < d.length; i++) {
            sum += d[i]; // 해당 부서의 금액을 더함
            if (sum > budget) { // 예산을 초과하는 경우
                break;
            }
            answer++; // 예산 안에서 지원 가능한 경우 부서 수를 증가
        }
        
        return answer; // 지원 가능한 부서 수 반환
    }
}
