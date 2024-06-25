import java.util.Scanner;

public class Main {
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        
        int [][]num = new int [9][9];
        
        for(int i =0; i<9; i++){
            for(int j = 0; j<9; j++){
                num[i][j] = sc.nextInt();
            }
        }
        int temp_row = 1;
        int temp_coulmn = 1;
        int temp = 0;
        for(int i =0; i<9; i++){
            for(int j = 0; j<9; j++){
                if(temp < num[i][j]){
                    temp = num[i][j];
                    temp_row = i;
                    temp_coulmn=j;
                }
            }
        }
        System.out.println(temp);
         System.out.println(temp_row+1);
         System.out.println(temp_coulmn+1);
    }
}