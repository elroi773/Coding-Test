class Solution {
    public int solution(String myString, String pat) {
        // 변환된 문자열을 저장할 StringBuilder를 생성합니다.
        StringBuilder transformed = new StringBuilder();

        // myString을 순회하며 각 문자를 변환하여 StringBuilder에 추가합니다.
        for (int i = 0; i < myString.length(); i++) {
            char c = myString.charAt(i);
            // "A"는 "B"로, "B"는 "A"로 변환합니다.
            if (c == 'A') {
                transformed.append('B');
            } else if (c == 'B') {
                transformed.append('A');
            }
        }

        // 변환된 문자열에서 pat을 찾습니다.
        String transformedString = transformed.toString();
        if (transformedString.contains(pat)) {
            return 1;
        } else {
            return 0;
        }
    }
}
