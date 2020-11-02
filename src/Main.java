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
 * @version 7
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

        randomizeArray(customers, length, customerOrder);

        for (Customer customer : customers) {
            System.out.println(customer);
        }
        int standardMax = 30;
        int vipMax = 10;

        for (int i = 0; i < length; i++) {
            if (standardMax != 0 && vipMax != 0) {
                if (standardMax - customerOrder.peek().getStandardTicket() < 0) {
                    customers[i].setResponse("Sorry, but there is not enough tickets to fulfill you're request.");
                } else if (standardMax > 0) {
                    standardMax = standardMax - customerOrder.peek().getStandardTicket();
                    //Possibly make this message more formal
                    customers[i].setResponse("Hi, \n\n You're request for tickets was fulfilled and you must now go back to the website to complete payment");
                }
                if (vipMax - customerOrder.peek().getVIPTicket() < 0) {
                    customers[i].setResponse("Sorry, but there is not enough tickets to fulfill you're request.");
                } else if (vipMax > 0) {
                    vipMax = vipMax - customerOrder.peek().getVIPTicket();
                    customers[i].setResponse("You're request for tickets was fulfilled and you must now go back to the website to complete payment");
                }
                System.out.println(standardMax + " " + vipMax);
                customerOrder.dequeue();
            }
        }

        try {
            File newFile = new File("Ticket Response Emails.txt");
            if (newFile.createNewFile())
                System.out.println("File created: " + newFile.getName());
            else
                System.out.println("File already exists");

            FileWriter writer = new FileWriter("Ticket Response Emails.txt");
            for (int i = 0; i < length; i++) {
                writer.write("<<EMAIL>>");
                writer.write(customers[i].getEmail());
                writer.write("<<END EMAIL>>");
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An Error has occurred");
        }
    }

    public static void randomizeArray(Customer[] customers, int length, OrderQueue customerOrder) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            int index = i + rnd.nextInt(length - i);
            Customer temp = customers[index];
            customers[index] = customers[i];
            customers[i] = temp;
            customerOrder.enqueue(customers[i]);
        }
        //easy fix: just remove the previous print and add a for each loop here for printing the entire array
    }
}