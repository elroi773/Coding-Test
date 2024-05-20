import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);

        // 첫 번째 세 자리 수 입력
        int num1 = scanner.nextInt();
        // 두 번째 세 자리 수 입력
        int num2 = scanner.nextInt();

        // 두 번째 수의 각 자리수를 분리
        int unit = num2 % 10;            // 일의 자리
        int ten = (num2 / 10) % 10;      // 십의 자리
        int hundred = num2 / 100;        // 백의 자리

        // 곱셈의 각 단계 계산
        int result1 = num1 * unit;       // 첫 번째 수와 일의 자리 곱셈
        int result2 = num1 * ten;        // 첫 번째 수와 십의 자리 곱셈
        int result3 = num1 * hundred;    // 첫 번째 수와 백의 자리 곱셈
        int result4 = num1 * num2;       // 첫 번째 수와 두 번째 수 전체 곱셈

        // 결과 출력
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
