import java.util.ArrayList;

class Solution {
    public int[] solution(int l, int r) {
        ArrayList<Integer> resultList = new ArrayList<>();
        
        // l 이상 r 이하의 정수 중 조건을 만족하는 정수를 찾는다.
        for (int i = l; i <= r; i++) {
            if (isZeroOrFiveOnly(i)) {
                resultList.add(i);
            }
        }
        
        // 결과 리스트가 비어있는 경우 -1을 담은 배열을 반환
        if (resultList.isEmpty()) {
            return new int[] {-1};
        }
        
        // 결과 리스트를 배열로 변환하여 반환
        int[] answer = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
    
    // 숫자가 "0"과 "5"로만 이루어져 있는지 확인하는 메서드
    private boolean isZeroOrFiveOnly(int num) {
        String numStr = String.valueOf(num);
        for (char c : numStr.toCharArray()) {
            if (c != '0' && c != '5') {
                return false;
            }
        }
        return true;
    }
}
