import java.util.Scanner;

public class Areacricle {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.print("\n Enter the radius of cricle: ");
        double radius = sc.nextDouble();
        double area = 3.14 * radius * radius;
        System.out.print("\n Area of given cricle is "+area);
    }
}
