package application;

import DAO.BookImplementation;
import DAO.ClientImplementation;
import DAO.PrintImplementation;
import custom.ConsoleColors;
import database.Datasource;
import exception.BookException;
import exception.ClientException;
import models.Book;
import models.Client;
import models.Print;
import models.Service;
import org.apache.commons.dbutils.QueryRunner;
import useCases.BookOperations;
import useCases.PrintOperations;
import useCases.ServiceOperations;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
//        DAO.User user = new DAO.User();
//        System.out.println(user.login("sidati@gmail.com", "sidati@123"));
        welcome();

    }

    public static void welcome(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|                         WELCOME TO BIBLIOSYTEM                         |");
        System.out.println("|              The standalone system to manage your library              |");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("------ " + ConsoleColors.BLUE_BOLD_BRIGHT + "Enter the operation you want to conduct" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.BLUE_BOLD_BRIGHT + "[1] - Books management" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.BLUE_BOLD_BRIGHT + "[2] - Loan and Return " + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.BLUE_BOLD_BRIGHT + "[3] - Prints management" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.BLUE_BOLD_BRIGHT + "[4] - Show statistics" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.BLUE_BOLD_BRIGHT + "[5] - Exit" + ConsoleColors.RESET);
        System.out.print("------" + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> BookOperations.BookInterface();
                case 2 -> ServiceOperations.ServiceInterface();
                case 3 -> PrintOperations.PrintInterface();
                case 4 -> ServiceOperations.showStats();
                case 5 -> System.out.println("------------------ GOOD BYE ------------------");
                default -> {
                    System.out.println("------" + ConsoleColors.BLUE + "Please enter a valid choice" + ConsoleColors.RESET);
                    welcome();
                }
            }
        }catch (InputMismatchException i){
            System.out.println("Please enter a valid choice");
            welcome();
        }
    }

}