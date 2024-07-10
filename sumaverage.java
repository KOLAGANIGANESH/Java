import java.util.Scanner;

public class sumaverage {
public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter first number: ");
    int n1 = sc.nextInt();
    System.out.print("Enter second number: ");
    int n2 = sc.nextInt();
    System.out.print("Enter third number: ");
    int n3 = sc.nextInt();
    System.out.print("Enter fourth number: ");
    int n4 = sc.nextInt();
    System.out.print("Enter fiftieth number: ");
    int n5 = sc.nextInt();
    int sum = n1+n2+n3+n4+n5;
    int avg = (n1+n2+n3+n4+n5)/5;
    System.out.println("Sum of " +n1+ "," +n2+ "," +n3+ "," +n4+ "," +n5+ " = "  +sum);
    System.out.print("Average of " +n1+ "," +n2+ "," +n3+ "," +n4+ "," +n5+ " = "  +avg);

}
}
