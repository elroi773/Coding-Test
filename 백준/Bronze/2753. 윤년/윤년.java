import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫째 줄에 연도를 입력 받습니다.
        int year = scanner.nextInt();

        // 윤년 판별 로직
        int isLeapYear;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            isLeapYear = 1;
        } else {
            isLeapYear = 0;
        }

        // 결과 출력
        System.out.println(isLeapYear);

        scanner.close();
    }
}
