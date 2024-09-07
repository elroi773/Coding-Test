import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 삼각형의 높이와 너비

        // 삼각형 출력
        for (int i = 1; i <= n; i++) {
            // i번째 줄에 i개의 '*' 출력
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();  // 줄 바꿈
        }
    }
}
