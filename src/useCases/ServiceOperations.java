package useCases;

import DAO.ClientImplementation;
import DAO.PrintImplementation;
import DAO.ServiceImplementation;
import application.Main;
import custom.ConsoleColors;
import exception.ClientException;
import exception.PrintException;
import exception.ServiceException;
import models.Client;
import models.Print;
import models.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ServiceOperations {
    public static void ServiceInterface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the operation you want to conduct" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[1] - Loan print" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[2] - Return print" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[3] - Exit" + ConsoleColors.RESET);
        System.out.print("------" + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> loan();
                case 2 -> returnPrint();
                case 3 -> Main.welcome();
                default -> {
                    System.out.println("------" + ConsoleColors.BLUE + "Please enter a valid choice" + ConsoleColors.RESET);
                    ServiceInterface();
                }
            }
        }catch (InputMismatchException i){
            System.out.println("Please enter a valid choice");
            ServiceInterface();
        }

    }
    public static void showServices(){

    }
    public static void loan() {
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        try {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("|                             Loan a book                              |");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("-------> Please provide your client email");
            System.out.print("-------> ");
            ClientImplementation clientImplementation = new ClientImplementation();
            PrintImplementation printImplementation = new PrintImplementation();
            Client client = new Client();
            Print print = new Print();
            client.setEmail(sc.nextLine());
            clientImplementation.get(client);
            if (!clientImplementation.get(client)) {
                System.out.println("-------> Please provide your client name");
                System.out.print("-------> ");
                client.setName(sc.nextLine());
                if (!clientImplementation.Add(client))
                    System.out.println("-------> An error occurred while creating client");
            }
            System.out.printf(ConsoleColors.BLUE + "---------------------------------------------------------%n");
            System.out.printf(ConsoleColors.BLUE_BOLD + "# %-20s | %-30s #%n", "Client Name", "Email");
            System.out.printf(ConsoleColors.BLUE + "---------------------------------------------------------%n" + ConsoleColors.RESET);
            System.out.printf("# %-20s | %-30s #%n", client.getName(), client.getEmail());
            System.out.printf("---------------------------------------------------------%n"+ ConsoleColors.RESET);
            System.out.println("-------> Please provide your print ID");
            do {
                System.out.print("-------> ");
                print.setId(scInt.nextInt());

                if (!printImplementation.get(print)) {
                    System.out.println(ConsoleColors.RED +"-------> Incorrect ID, Please enter a valid ID");
                } else if (print.getStatus() != Print.Status.Available || print.getArchived()) {
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT +"-------> This print is loaned or lost, please provide another print ID");
                }
            } while (!printImplementation.get(print) || (print.getStatus() != Print.Status.Available || print.getArchived()));

            Service service = new Service(client, print);
            ServiceImplementation serviceImplementation = new ServiceImplementation();
            if (printImplementation.MakeLoaned(print) && serviceImplementation.loan(service))
                System.out.println(ConsoleColors.GREEN_BOLD + "-------> The print is out for loan successfully");
            else
                System.out.println(ConsoleColors.RED_BOLD + "-------> An error occurred while registering your request");
            ServiceOperations.ServiceInterface();
        } catch (ClientException | PrintException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void returnPrint(){
        Scanner scInt = new Scanner(System.in);
        try {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("|                           Return a print                             |");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("-------> Please provide your print ID");
            System.out.print("-------> ");
            PrintImplementation printImplementation = new PrintImplementation();
            Print print = new Print();
            print.setId(scInt.nextInt());
            if (printImplementation.get(print)){
                Service service = new Service(null, print);
                ServiceImplementation serviceImplementation = new ServiceImplementation();
                if(serviceImplementation.returnPrint(service) && printImplementation.MakeAvailable(print)){
                    System.out.printf(ConsoleColors.BLUE + "-------------------------------------------------------------------------------------------------------%n");
                    System.out.printf(ConsoleColors.BLUE_BOLD + "# %-20s | %-30s | %-20s | %-20s #%n", "Print ID", "Book ISBN", "Status", "Archived");
                    System.out.printf(ConsoleColors.BLUE + "-------------------------------------------------------------------------------------------------------%n" + ConsoleColors.RESET);
                    System.out.printf("# %-20d | %-30d | %-20s | %-20s #%n", print.getId(), print.getBook().getISBN(), print.getStatus(), print.getArchived());
                    System.out.printf("-------------------------------------------------------------------------------------------------------%n"+ ConsoleColors.RESET);
                    System.out.println(ConsoleColors.GREEN_BOLD + "-------> Your print has been returned");
                    ServiceInterface();
                }
            }else
                System.out.println(ConsoleColors.RED + "-------> Your print has not been found");

        }catch (PrintException | ServiceException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public static void showStats(){
        PrintImplementation print = new PrintImplementation();
        try {
        System.out.printf("---------------------------------------------------------------------------------------------%n");
        System.out.printf(ConsoleColors.RED_BOLD +"|                                       BiblioSystem                                        |%n"+ ConsoleColors.RESET);
        System.out.printf(ConsoleColors.BLUE + "---------------------------------------------------------------------------------------------%n");
        System.out.printf("# "+ ConsoleColors.YELLOW_BOLD_BRIGHT +"%-20s | "+ ConsoleColors.GREEN_BRIGHT +"%-20s | "+ ConsoleColors.RED_BRIGHT +"%-20s | "+ ConsoleColors.BLUE +"%-20s |%n", "Borrowed", "Available", "Lost", "Total");
        System.out.printf(ConsoleColors.BLUE + "---------------------------------------------------------------------------------------------%n" + ConsoleColors.RESET);
        System.out.printf("# "+ ConsoleColors.YELLOW_BOLD_BRIGHT +"%-20d | "+ ConsoleColors.GREEN_BRIGHT +"%-20d | "+ ConsoleColors.RED_BRIGHT +"%-20d | "+ ConsoleColors.BLUE +"%-20d |%n", print.LoanedStats(), print.AvailableStats(), print.LostStats(), print.Total());
        System.out.printf("---------------------------------------------------------------------------------------------%n"+ ConsoleColors.RESET);
        ServiceInterface();

        }catch (PrintException p){
            System.out.println(p.getMessage());
        }
    }
}
