class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            // arr1[i]와 arr2[i]를 OR 연산 후 이진수 문자열로 변환
            String binary = Integer.toBinaryString(arr1[i] | arr2[i]);
            
            // 길이가 n이 되도록 앞쪽에 0을 채워줍니다.
            binary = String.format("%" + n + "s", binary);
            
            // 1은 '#'으로, 0은 ' '으로 변환하여 전체 지도 한 줄로 만듭니다.
            answer[i] = binary.replace('1', '#').replace('0', ' ');
        }
        
        return answer;
    }
}
