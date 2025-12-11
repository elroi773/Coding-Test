class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String tree : skill_trees) {
            int idx = 0;        // skill에서 현재 배워야 할 스킬 위치
            boolean valid = true;

            for (char c : tree.toCharArray()) {
                int skillPos = skill.indexOf(c);

                // skill에 포함된 문자라면 순서 체크
                if (skillPos != -1) {
                    if (skillPos == idx) {
                        idx++;  // 올바른 순서
                    } else {
                        valid = false; // 순서가 어긋남
                        break;
                    }
                }
            }

            if (valid) answer++;
        }

        return answer;
    }
}
