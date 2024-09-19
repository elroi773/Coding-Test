class Solution {
    public int solution(int[] numbers, int k) {
        int n = numbers.length;
        
        // 2 * (k - 1)칸 이동한 위치를 배열의 길이로 나눈 나머지가 해당 사람의 인덱스
        int index = (2 * (k - 1)) % n;
        
        // 해당 인덱스의 사람 번호 반환
        return numbers[index];
    }
}
