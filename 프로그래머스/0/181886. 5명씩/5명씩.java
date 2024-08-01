class Solution {
    public String[] solution(String[] names) {
        // 그룹의 수를 계산합니다.
        int groupCount = (names.length + 4) / 5; // 5명씩 묶었을 때의 그룹 수
        String[] answer = new String[groupCount]; // 결과를 저장할 배열
        
        // 각 그룹의 첫 번째 이름을 결과 배열에 추가합니다.
        for (int i = 0; i < groupCount; i++) {
            answer[i] = names[i * 5];
        }
        
        return answer; // 결과 배열을 반환합니다.
    }
}
