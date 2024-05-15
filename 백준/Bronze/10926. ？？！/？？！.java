import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 사용자로부터 아이디를 입력 받기
        String userId = sc.nextLine();
        
        // 입력 받은 아이디 뒤에 "??!"를 붙여서 출력
        System.out.println(userId + "??!");
    }
}
