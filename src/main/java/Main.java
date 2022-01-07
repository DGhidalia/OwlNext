import internals.Rate;

import java.util.Scanner;

public class Main {

    /**
     * Enter both currencies in command line then you get the corresponding rate
     */
    public static void main (String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter first currency: ");
        String currency1= sc.nextLine();
        System.out.println("Enter second currency: ");
        String currency2= sc.nextLine();
        Rate test = new Rate(currency1, currency2);
        test.start();
    }
}
