class Solution {
    public String solution(String X, String Y) {
        // 각 숫자의 등장 횟수를 저장할 배열
        int[] countX = new int[10];
        int[] countY = new int[10];
        
        // X와 Y에서 각 숫자의 등장 횟수를 계산
        for (char ch : X.toCharArray()) {
            countX[ch - '0']++;
        }
        for (char ch : Y.toCharArray()) {
            countY[ch - '0']++;
        }
        
        StringBuilder result = new StringBuilder();
        
        // 9부터 0까지 공통으로 등장하는 숫자들을 큰 숫자부터 처리
        for (int i = 9; i >= 0; i--) {
            int commonCount = Math.min(countX[i], countY[i]);  // X와 Y에서 공통으로 등장하는 숫자의 수
            for (int j = 0; j < commonCount; j++) {
                result.append(i);  // 해당 숫자를 결과에 추가
            }
        }
        
        // 결과가 비어 있으면 공통 숫자가 없는 경우이므로 -1 반환
        if (result.length() == 0) {
            return "-1";
        }
        
        // 결과가 0으로만 이루어져 있으면 0 반환
        if (result.charAt(0) == '0') {
            return "0";
        }
        
        return result.toString();  // 최종 결과 반환
    }
}
