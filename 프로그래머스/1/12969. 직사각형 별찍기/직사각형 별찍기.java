import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 가로 길이
        int m = sc.nextInt();  // 세로 길이
        
        // m번 반복해서 각 줄에 n개의 별을 출력
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();  // 줄바꿈
        }
    }
}
