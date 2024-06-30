import java.util.Scanner;

public class Main {
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int [] basket = new int [N];
        for(int i =0; i< N; i++){
            basket[i]=i+1;
        }
        for(int k =0; k<M; k++){
            int i = sc.nextInt()-1;
            int j = sc.nextInt()-1;
        
            int temp = basket[i];
            basket[i] = basket[j];
            basket[j] = temp;
        }
        for(int i =0; i<N; i++){
            System.out.print(basket[i]+" ");
        }
    }
}