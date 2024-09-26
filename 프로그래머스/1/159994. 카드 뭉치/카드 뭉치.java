class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // 각 카드 뭉치의 인덱스를 따로 관리
        int index1 = 0;
        int index2 = 0;
        
        // goal 배열의 단어를 하나씩 확인
        for (String word : goal) {
            // cards1의 현재 인덱스가 범위 내에 있고 해당 단어가 cards1에서 나올 경우
            if (index1 < cards1.length && cards1[index1].equals(word)) {
                index1++;  // cards1에서 사용했으므로 인덱스를 증가시킴
            }
            // cards2의 현재 인덱스가 범위 내에 있고 해당 단어가 cards2에서 나올 경우
            else if (index2 < cards2.length && cards2[index2].equals(word)) {
                index2++;  // cards2에서 사용했으므로 인덱스를 증가시킴
            }
            // 둘 다 아니라면 goal 배열을 만들 수 없음
            else {
                return "No";
            }
        }
        
        // 모든 단어를 순서대로 사용할 수 있었다면 "Yes"
        return "Yes";
    }
}
