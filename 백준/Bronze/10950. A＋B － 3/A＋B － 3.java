//첫째 줄에 테스트 케이스의 개수 T가 주어진다.

//각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. (0 < A, B < 10)

//두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 번째 줄에 테스트 케이스의 개수 T를 입력받음
        int T = scanner.nextInt();

        // 각 테스트 케이스마다 A와 B를 입력받고 A + B를 출력
        for (int i = 0; i < T; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            System.out.println(A + B);
        }

        // Scanner 닫기
        scanner.close();
    }
}
