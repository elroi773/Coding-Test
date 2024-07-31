class Solution {
    public String[] solution(String[] strArr) {
        // 결과를 저장할 배열을 생성합니다.
        String[] answer = new String[strArr.length];

        // 배열을 순회하면서 문자열을 변환합니다.
        for (int i = 0; i < strArr.length; i++) {
            if (i % 2 == 0) {
                // 짝수 인덱스: 소문자로 변환
                answer[i] = strArr[i].toLowerCase();
            } else {
                // 홀수 인덱스: 대문자로 변환
                answer[i] = strArr[i].toUpperCase();
            }
        }

        // 변환된 배열을 반환합니다.
        return answer;
    }
}
