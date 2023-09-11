package useCases;

import DAO.ClientImplementation;
import DAO.PrintImplementation;
import DAO.ServiceImplementation;
import exception.ClientException;
import exception.PrintException;
import exception.ServiceException;
import models.Client;
import models.Print;
import models.Service;

import java.util.Scanner;

public class ServiceOperations {
    public static void ServiceInterface(){

    }
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
            System.out.println("----------------------------Return a print------------------------------");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("----------------------Please provide your print ID----------------------");
            System.out.print("-------> ");
            PrintImplementation printImplementation = new PrintImplementation();
            Print print = new Print();
            print.setId(scInt.nextInt());
            if (printImplementation.get(print)){
                System.out.println("------ ID --------- ISBN ----------- Status -----------Archived---------");
                System.out.println("------" + print.getId() + "---------" + print.getBook().getISBN() + "-----------" + print.getStatus() + "------------" + print.getArchived() + "---------");
                Service service = new Service(null, print);
                ServiceImplementation serviceImplementation = new ServiceImplementation();
                if(serviceImplementation.returnPrint(service)){
                    printImplementation.MakeAvailable(print);
                    System.out.println("--------------------Your print has been returned------------------------");
                }
            }else
                System.out.println("--------------------Your print has not been found------------------------");

        }catch (PrintException | ServiceException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public static void showStats(){
        PrintImplementation print = new PrintImplementation();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|                              BiblioSystem                              |");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|  Loaned prints   |  Available prints  |  Lost prints  |  Total prints  |");
        System.out.println("--------------------------------------------------------------------------");
        try {
        System.out.println("|         " + print.LoanedStats() + "        |         " + print.AvailableStats() + "          |       " + print.LostStats() + "       |       " +  print.Total() + "        |");
        System.out.println("--------------------------------------------------------------------------");

        }catch (PrintException p){
            System.out.println(p.getMessage());
        }
    }
}
