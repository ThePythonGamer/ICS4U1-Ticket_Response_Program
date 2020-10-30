import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @version 7
 */
public class Main {
    public static void main(String[] args){
        File text = new File("Ticket.txt");
        int length = 0 ,standard, vip;
        String name;
        Customer[] customers = new Customer[0];
        OrderQueue customerOrder = new OrderQueue();

        try {
            //Starting the scanner to scan the file and get object attribute values
            Scanner sc = new Scanner(text);
            length = sc.nextInt();
            sc.nextLine();

            customers = new Customer[length];

            for(int i = 0;i<length;i++){
                name = sc.nextLine();
                standard = sc.nextInt();
                vip = sc.nextInt();
                sc.nextLine();
                customers[i] = new Customer(name,standard,vip);
            }
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

        randomizeArray(customers, length, customerOrder);

        for (Customer customer:customers) {
            System.out.println(customer);
        }
        int standardMax = 30;
        int vipMax = 10;

        for(int i = 0; i < length; i++){
            if (standardMax != 0 && vipMax != 0) {
                if (standardMax - customerOrder.peek().getStandardTicket() < 0){
                    System.out.println("not enough");
                }else if (standardMax > 0) {
                    standardMax = standardMax - customerOrder.peek().getStandardTicket();
                }
                if (vipMax - customerOrder.peek().getVIPTicket() < 0){
                    System.out.println("not enough");
                }else if (vipMax > 0) {
                    vipMax = vipMax - customerOrder.peek().getVIPTicket();
                }

                System.out.println(standardMax + " " + vipMax);
                customerOrder.dequeue();


            }
        }
    }

    public static void randomizeArray(Customer[] customers, int length, OrderQueue customerOrder){
        Random rnd = ThreadLocalRandom.current();
        for (int i = length - 1; i > -1; i--)
        {
            int index = rnd.nextInt(i + 1);
            Customer temp = customers[index];
            customers[index] = customers[i];
            customers[i] = temp;
            customerOrder.enqueue(customers[i]);
        }
    }
}