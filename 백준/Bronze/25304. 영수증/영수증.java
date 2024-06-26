import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // 영수증에 적힌 총 금액 입력
        int totalReceiptAmount = sc.nextInt();
        // 구매한 물건의 종류 수 입력
        int numberOfItems = sc.nextInt();
        // 실제 계산한 총 금액 초기화
        int calculatedTotalAmount = 0;

        // 각 물건의 가격과 개수를 입력받아 총 금액 계산
        for (int i = 0; i < numberOfItems; i++) {
            int price = sc.nextInt();
            int quantity = sc.nextInt();
            calculatedTotalAmount += price * quantity;
        }
        
        // 계산한 총 금액과 영수증에 적힌 총 금액 비교
        if (calculatedTotalAmount == totalReceiptAmount) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }
}
