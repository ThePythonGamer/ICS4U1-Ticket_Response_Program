import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @version 9
 */
public class Main {
    public static void main(String[] args) {
        File text = new File("Ticket.txt");
        int length = 0, standard, vip;
        String name;
        Customer[] customers = new Customer[0];
        OrderQueue customerOrder = new OrderQueue();

        try {
            //Starting the scanner to scan the file and get object attribute values
            Scanner sc = new Scanner(text);
            length = sc.nextInt();
            sc.nextLine();

            customers = new Customer[length];

            for (int i = 0; i < length; i++) {
                name = sc.nextLine();
                standard = sc.nextInt();
                vip = sc.nextInt();
                if (i != length - 1) {
                    sc.nextLine();
                }//change this so the file could have the last number without a new line symbol
                customers[i] = new Customer(name, standard, vip, "");
            }
            sc.close();
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

        //This is the lottery which randomizes the order of customers
        randomizeArray(customers, length, customerOrder);

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        int standardMax = 30;
        int vipMax = 10;

        for (int i = 0; i < length; i++) {
            //Checks if there are any tickets available first
            if (standardMax != 0 && vipMax != 0) {
                //Checks if both their standard and vip ticket order cannot be fulfilled
                if (standardMax - customerOrder.peek().getStandardTicket() < 0 || vipMax - customerOrder.peek().getVIPTicket() < 0) {
                    customers[i].setResponse("Thank you for your interest in our intimate virtual concert series! \nUnfortunately, your request for " + customers[i].getStandardTicket() + " standard ticket(s) and " + customers[i].getVIPTicket() + " VIP ticket(s) could not be fulfilled. \nBut don't fret—we have other great concerts coming up too, visit our website to check them out! \n(Please note this is an automated message from an unmonitored account, kindly do not reply to this email) \nThanks & have a great day, \nAna LaForest's Virtual Concert Series");
                } //Checks if more than 0 tickets available
                else if (standardMax > 0 && vipMax > 0) {
                    standardMax = standardMax - customerOrder.peek().getStandardTicket();
                    vipMax = vipMax - customerOrder.peek().getVIPTicket();
                    customers[i].setResponse("Thank you for your interest in our intimate virtual concert series! \nCongratulations, your request for " + customers[i].getStandardTicket() + " standard ticket(s) and " + customers[i].getVIPTicket() + " VIP ticket(s) has been successful! \nTo complete your purchase, please return to our website, login with your email and password, and you will be directed to our secure payment page to process the payment. \n(Please note this is an automated message from an unmonitored account, kindly do not reply to this email) \nThanks & have a great day, \nAna LaForest's Virtual Concert Series");
                }
                System.out.println(standardMax + " " + vipMax);
                customerOrder.dequeue();
            }
        }

        //Creates a file if needed to print the output text for the customers
        try {
            File newFile = new File("Ticket Response Emails.txt");
            if (newFile.createNewFile())
                System.out.println("File created: " + newFile.getName());
            else {
                System.out.println("File already exists");
                FileWriter emptyFile = new FileWriter("Ticket Response Emails.txt");
                //Emptying the content in the file
                emptyFile.close();
            }
        } catch (IOException e) {
            System.out.println("An Error has occurred");
        }

        //Attempts to write to the file with all the customer's email and email text
        try {
            FileWriter writer = new FileWriter("Ticket Response Emails.txt");
            for (int i = 0; i < length; i++) {
                writer.write("<<EMAIL>>\n");
                writer.write(customers[i].getEmail());
                writer.write("\n" + customers[i].getResponse());
                writer.write("\n<<END EMAIL>>\n");
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An Error has occurred");
        }
    }

    /**
     * Randomizes the order of customers for the lottery
     * @param customers The array with all of the customers information
     * @param length The number of customers there are in the array
     * @param customerOrder The queue with the new order of customers
     */
    public static void randomizeArray(Customer[] customers, int length, OrderQueue customerOrder) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            int index = i + rnd.nextInt(length - i);
            Customer temp = customers[index];
            customers[index] = customers[i];
            customers[i] = temp;
            customerOrder.enqueue(customers[i]);
        }
    }
}