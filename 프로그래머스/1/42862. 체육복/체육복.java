import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 정렬하여 순서대로 처리하기 위해 미리 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 도난당했지만 여벌이 있는 학생을 제외한 리스트를 저장하기 위한 변수
        int[] lostFiltered = Arrays.stream(lost)
                                   .filter(l -> Arrays.stream(reserve).noneMatch(r -> r == l))
                                   .toArray();
        int[] reserveFiltered = Arrays.stream(reserve)
                                      .filter(r -> Arrays.stream(lost).noneMatch(l -> l == r))
                                      .toArray();

        // 수업을 들을 수 있는 학생 수를 n에서 lostFiltered 길이만큼 제외한 상태로 시작
        int answer = n - lostFiltered.length;
        
        for (int l : lostFiltered) {
            for (int i = 0; i < reserveFiltered.length; i++) {
                // 빌려줄 수 있는 학생(앞이나 뒤 번호에 여벌이 있는 경우) 탐색
                if (reserveFiltered[i] == l - 1 || reserveFiltered[i] == l + 1) {
                    answer++;  // 체육복을 빌려줬으므로 수업을 들을 수 있는 학생 추가
                    reserveFiltered[i] = -1;  // 체육복을 빌려준 학생은 다시 빌려줄 수 없으므로 제거
                    break;
                }
            }
        }
        
        return answer;
    }
}
