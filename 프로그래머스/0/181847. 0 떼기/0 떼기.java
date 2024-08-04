class Solution {
    public String solution(String n_str) {
        
        String answer = n_str.replaceFirst("^0+", "");
        
       
        if (answer.isEmpty()) {
            return "0";
        }
        
        return answer;
    }
}
