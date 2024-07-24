class Solution {
    public int solution(String my_string, String is_prefix) {
        // String의 startsWith 메서드를 사용하여 접두사인지 확인합니다.
        if (my_string.startsWith(is_prefix)) {
            return 1; // 접두사라면 1을 반환합니다.
        } else {
            return 0; // 접두사가 아니라면 0을 반환합니다.
        }
    }
}
