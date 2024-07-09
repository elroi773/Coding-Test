class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        // my_string의 길이와 overwrite_string의 길이를 구합니다.
        int my_len = my_string.length();
        int overwrite_len = overwrite_string.length();
        
        // s부터 overwrite_string의 길이만큼을 overwrite_string으로 바꿉니다.
        String result = my_string.substring(0, s) + overwrite_string + my_string.substring(s + overwrite_len);
        
        return result;
    }
}
