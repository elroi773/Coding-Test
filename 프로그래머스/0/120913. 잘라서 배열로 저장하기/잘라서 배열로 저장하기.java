class Solution {
    public String[] solution(String my_str, int n) {
        // my_str의 길이를 n으로 나누었을 때 필요한 배열의 크기 계산
        int arraySize = (my_str.length() + n - 1) / n;
        String[] answer = new String[arraySize];
        
        // 문자열을 n의 길이만큼 잘라서 배열에 저장
        for (int i = 0; i < arraySize; i++) {
            int startIndex = i * n;
            int endIndex = Math.min(startIndex + n, my_str.length());
            answer[i] = my_str.substring(startIndex, endIndex);
        }
        
        return answer;
    }
}
