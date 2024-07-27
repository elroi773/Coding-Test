class Solution {
    public String solution(String my_string, int s, int e) {
        // StringBuilder를 사용하여 부분 문자열을 뒤집기
        StringBuilder result = new StringBuilder();

        // s 이전의 부분을 그대로 추가
        result.append(my_string.substring(0, s));

        // s부터 e까지의 부분을 뒤집어서 추가
        StringBuilder reversePart = new StringBuilder(my_string.substring(s, e + 1));
        result.append(reversePart.reverse());

        // e 이후의 부분을 그대로 추가
        result.append(my_string.substring(e + 1));

        // 결과를 문자열로 변환하여 반환
        return result.toString();
    }
}
