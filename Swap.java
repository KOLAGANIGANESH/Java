import java.util.*;
public class Swap {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("\n Enter firstNnumber: ");
        int a = sc.nextInt();
        System.out.print("\n Enter Secong Number: ");
        int b = sc.nextInt();
        sc.close();
        int temp = a;
        a = b;
        b = temp;
        System.out.print("Swapped number are frist number: "+a+", 10Second number: "+b);
    }
}
