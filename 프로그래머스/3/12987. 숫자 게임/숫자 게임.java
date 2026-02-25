import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int[] a = A.clone();
        int[] b = B.clone();
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        int i = 0; // A 포인터
        int j = 0; // B 포인터
        int answer = 0;
        
        // B에서 현재 A를 이길 수 있는 가장 작은 수를 매칭
        while (i < a.length && j < b.length) {
            if (b[j] > a[i]) {
                answer++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        
        return answer;
    }
}