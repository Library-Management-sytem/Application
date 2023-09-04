package main;

import custom.ConsoleColors;
import model.Book;
import model.Service;
import model.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
//        model.User user = new model.User();
//        System.out.println(user.login("sidati@gmail.com", "sidati@123"));
        welcome();

    }

    public static void welcome() throws SQLException {
        Service service = new Service();
        System.out.println(service.returnBook(111100, "salah@gmail.com"));
//        Book book = new Book();
//        System.out.println(book.add(234955, "The social contract", "JJ Rousseau", 1784));
//        User user = new User();
//        System.out.println(user.login("sidati@gmail.com", "sidati@123"));
//        Service service = new Service();
//        System.out.println(service.checkClient("salaah@gmail.com"));
//        Scanner sc = new Scanner(System.in);
//        System.out.println(ConsoleColors.CYAN_BACKGROUND+ConsoleColors.RED+"##############################################################################################################################################################################");
//        System.out.println("                                      " +ConsoleColors.CYAN_BACKGROUND+ConsoleColors.RED+"WELCOME TO BIBLIOSYTEM"+ ConsoleColors.RESET+"                                                                                       #");
//        System.out.println("                                      " +ConsoleColors.BLUE_BOLD_BRIGHT+"The standalone system to manage your library" + ConsoleColors.RESET+"                                                                                 #");
//        System.out.println(ConsoleColors.CYAN_BACKGROUND+ConsoleColors.RED+"##############################################################################################################################################################################");
//        System.out.println("                                      " +ConsoleColors.PURPLE_BOLD_BRIGHT + "[1] LOG IN"+ ConsoleColors.RESET);
////        System.out.println("Press 2 to" +ConsoleColors.PURPLE_BOLD_BRIGHT + " Register as Buyer." + ConsoleColors.RESET);
////        System.out.println("Press 3 to" +ConsoleColors.PURPLE_BOLD_BRIGHT + " logIn as Buyer."+ ConsoleColors.RESET);
////        System.out.println("Press 4 to" +ConsoleColors.PURPLE_BOLD_BRIGHT + " Register as Seller." + ConsoleColors.RESET);
////        System.out.println("Press 5 to" +ConsoleColors.PURPLE_BOLD_BRIGHT + " logIn as Seller."+ ConsoleColors.RESET);
//        System.out.println("                                      " +ConsoleColors.PURPLE_BOLD_BRIGHT +"[2] EXIT"+ ConsoleColors.RESET);
//        System.out.print("                                      " +ConsoleColors.BLUE +"CHOOSE AN OPTION: "+ ConsoleColors.RESET);
//        String choice = sc.nextLine();
    }

}