/*문제
예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.

입력
첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.

출력
첫째 줄부터 2×N-1번째 줄까지 차례대로 별을 출력한다.

예제 입력 1 
5
예제 출력 1 
    *
   ***
  *****
 *******
*********
 *******
  *****
   ***
    *          */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); 

        // 첫 번째 삼각형 (위쪽)
        for (int i = 1; i <= N; i++) {
            // 공백 출력
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println(); // 줄 바꿈
        }

        // 두 번째 삼각형 (아래쪽)
        for (int i = N - 1; i > 0; i--) {
            // 공백 출력
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println(); // 줄 바꿈
        }
        
        scanner.close(); // Scanner 닫기
    }
}
