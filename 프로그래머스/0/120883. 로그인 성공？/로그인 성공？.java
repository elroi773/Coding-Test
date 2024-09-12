class Solution {
    public String solution(String[] id_pw, String[][] db) {
        String id = id_pw[0];
        String pw = id_pw[1];
        
        for (String[] user : db) {
            String dbId = user[0];
            String dbPw = user[1];
            
            if (dbId.equals(id)) {
                // 아이디가 일치하면 비밀번호 확인
                if (dbPw.equals(pw)) {
                    return "login"; // 아이디와 비밀번호가 모두 일치
                } else {
                    return "wrong pw"; // 아이디는 맞지만 비밀번호가 틀림
                }
            }
        }
        
        return "fail"; // 아이디가 존재하지 않음
    }
}
