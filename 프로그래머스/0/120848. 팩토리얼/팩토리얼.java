class Solution {
    public int solution(int n) {
        int factorial = 1; // 팩토리얼 값을 저장할 변수
        int i = 1;         // i는 팩토리얼의 인자
        
        // i!가 n을 넘지 않을 때까지 i를 증가시키며 팩토리얼 값을 구함
        while (factorial <= n) {
            i++;
            factorial *= i; // i! 값을 계산
        }
        
        // 마지막에 n을 넘었으므로, 그 이전의 i가 정답
        return i - 1;
    }
}
