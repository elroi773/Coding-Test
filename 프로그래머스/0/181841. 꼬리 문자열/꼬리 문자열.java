class Solution {
    public String solution(String[] str_list, String ex) {
        StringBuilder result = new StringBuilder();
        
        for (String str : str_list) {
            if (!str.contains(ex)) {
                result.append(str);
            }
        }
        
        return result.toString();
    }
}
