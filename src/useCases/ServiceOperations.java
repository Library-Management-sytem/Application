package useCases;

import DAO.ClientImplementation;
import DAO.PrintImplementation;
import DAO.ServiceImplementation;
import exception.ClientException;
import exception.PrintException;
import models.Client;
import models.Print;
import models.Service;

import java.util.Scanner;

public class ServiceOperations {
    public static void loan() {
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        try {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("------------------------------Loan a book-------------------------------");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("--------------------Please provide your client email--------------------");
            System.out.print("-------> ");
            ClientImplementation clientImplementation = new ClientImplementation();
            PrintImplementation printImplementation = new PrintImplementation();
            Client client = new Client();
            Print print = new Print();
            client.setEmail(sc.nextLine());
            clientImplementation.get(client);
            if (!clientImplementation.get(client)) {
                System.out.println("--------------------Please provide your client name--------------------");
                System.out.print("-------> ");
                client.setName(sc.nextLine());
                if (!clientImplementation.Add(client))
                    System.out.println("------------------An error occurred while creating client----------------");
            }
            System.out.println("----------- Name ------------------- Email -----------------------------");
            System.out.println("-----------" + client.getName() + "-------------------" + client.getEmail() + " ---------------------");
            System.out.println("--------------------Please provide your print ID------------------------");
            System.out.print("-------> ");
            print.setId(scInt.nextInt());
            if (!printImplementation.get(print)) {
                System.out.println("---------------Incorrect ID, Please enter a valid ID-----------------");
                System.out.print("-------> ");
                print.setId(scInt.nextInt());
            }
            if (!print.getStatus().equalsIgnoreCase("Available") || print.getArchived()) {
                System.out.println("----This print is loaned or lost, please provide another print ID----");
                System.out.print("-------> ");
                print.setId(scInt.nextInt());
            }
            Service service = new Service(client, print);
            ServiceImplementation serviceImplementation = new ServiceImplementation();
            if (printImplementation.MakeLoaned(print) && serviceImplementation.loan(service))
                System.out.println("--------------------The print is out for loan successfully--------------------");
            else
                System.out.println("------------------An error occurred while registering your request----------------");
            ServiceOperations.loan();
        } catch (ClientException | PrintException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void returnPrint(){

    }
}
