class Solution {
    public int solution(int[] number) {
        int answer = 0;
        
        // 3중 for문으로 서로 다른 3명의 학생을 선택
        for (int i = 0; i < number.length - 2; i++) {
            for (int j = i + 1; j < number.length - 1; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    // 세 학생의 번호를 더해서 0이 되는지 확인
                    if (number[i] + number[j] + number[k] == 0) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
}
