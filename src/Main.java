import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        File text = new File("Ticket.txt");
        int length = 0,standard, vip;
        String name;
        Customer[] customers;//This error will disappear when this array is put into use
        Scanner sc = null;
        try {
            //Starting the scanner to scan the file and get object attribute values
            sc = new Scanner(text);
            length = sc.nextInt();
            sc.nextLine();
        }//Gives error message to user and ends the program if the file was not found on the computer
        catch (FileNotFoundException e) {
            System.out.println("The file was not found, please make sure it is outside the source folder. The program will now shutdown.");
            System.exit(0);
        }//Gives error message to user and ends the program if the value type did not match what is required
        catch (InputMismatchException e) {
            System.out.println("Please enter the content in the file correctly - Only enter numerical values into their respective fields; Enter the data in this order: \nType of game \nName \nPlatform \nRelease Year \nOther specific details on each line \nThe program will now shutdown.");
            System.exit(0);
        }//Gives error message to user and ends the program if the incorrect amount of emails was entered into the file
        catch (NoSuchElementException e) {
            System.out.println("Please enter the correct amount of customer emails into the list. The program will now shutdown.");
            System.exit(0);
        }
        customers = new Customer[length];
        try{
            for(int i = 0;i<length;i++){
                name = sc.nextLine();
                standard = sc.nextInt();
                vip = sc.nextInt();
                customers[i] = new Customer(name,standard,vip);
                sc.nextLine();
            }
        }//Gives error message to user and ends the program if the value type did not match what is required
        catch (InputMismatchException e) {
            System.out.println("Please enter the content in the file correctly - Only enter numerical values into their respective fields; Enter the data in this order: \nType of game \nName \nPlatform \nRelease Year \nOther specific details on each line \nThe program will now shutdown.");
            System.exit(0);
        }//Gives error message to user and ends the program if the incorrect amount of emails was entered into the file
        catch (NoSuchElementException e) {
            System.out.println("Please enter the correct amount of customer emails into the list. The program will now shutdown.");
            System.exit(0);
        }
        for (Customer customer:customers
             ) {
            System.out.println(customer);
        }
    }
}