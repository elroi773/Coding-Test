import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너 객체를 생성하여 입력을 받음
        Scanner scanner = new Scanner(System.in);

        // A, B, C 값을 입력받음
        long A = scanner.nextLong();
        long B = scanner.nextLong();
        long C = scanner.nextLong();

        // 합계를 계산
        long sum = A + B + C;

        // 결과를 출력
        System.out.println(sum);
    }
}
