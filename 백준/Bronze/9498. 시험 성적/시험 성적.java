//90 ~ 100점은 A, 80 ~ 89점은 B, 70 ~ 79점은 C, 60 ~ 69점은 D, 나머지 점수는 F를 출력

import java.util.Scanner;
public class Main {
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        
        if (a <= 100 && a >= 90){
            System.out.print("A");
        }else if (a >= 80 && a <= 89){
            System.out.print("B");
        }else if (a >= 70 && a<= 79){
            System.out.print("C");
        }else if (a >= 60 &&  a<= 69){
            System.out.print("D");
        }else
            System.out.print("F");        
    }
}