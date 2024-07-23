class Solution {
    public String solution(String my_string, int[] index_list) {
        StringBuilder answer = new StringBuilder();
        
        // index_list의 각 인덱스를 순회
        for (int index : index_list) {
            // index에 해당하는 my_string의 문자 추출
            char character = my_string.charAt(index);
            // StringBuilder에 문자를 추가
            answer.append(character);
        }
        
        // 최종 문자열 반환
        return answer.toString();
    }
}
