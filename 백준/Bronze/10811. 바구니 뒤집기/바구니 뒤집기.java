import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] baskets = new int[N];
        
        for (int i = 0; i < N; i++) {
            baskets[i] = i + 1;
        }
        
   
        for (int k = 0; k < M; k++) {
            int i = scanner.nextInt() - 1;
            int j = scanner.nextInt() - 1;
            reverse(baskets, i, j);
        }
        
           for (int basket : baskets) {
            System.out.print(basket + " ");
        }
        
        scanner.close();
    }

   
    private static void reverse(int[] array, int i, int j) {
        while (i < j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }
}
