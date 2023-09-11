package useCases;

import DAO.BookImplementation;
import DAO.PrintImplementation;
import application.Main;
import custom.ConsoleColors;
import exception.BookException;
import exception.PrintException;
import models.Book;
import models.Print;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintOperations {
    public static Scanner sc = new Scanner(System.in);
    public static Scanner scInt = new Scanner(System.in);
    public static void PrintInterface() {
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the operation you want to conduct" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[1] - Insert print" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[2] - Delete print" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[3] - Exit" + ConsoleColors.RESET);
        System.out.print("------" + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addPrint();
                case 2 -> deletePrint();
                case 3 -> Exit();
                default -> {
                    System.out.println("------" + ConsoleColors.BLUE + "Please enter a valid choice" + ConsoleColors.RESET);
                    PrintInterface();
                }
            }
        } catch (InputMismatchException i) {
            System.out.println("Please enter a valid choice");
            PrintInterface();
        }
    }

//    public static void searchPrint() {
//        Book book = new Book();
//        System.out.print("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the ISBN of the book you want to find: " + ConsoleColors.RESET);
//        book.setISBN(scInt.nextInt());
//        BookImplementation check = new BookImplementation();
//        try {
//            check.get(book);
//            if(book.getISBN() == null){
//                System.out.println("------ " + ConsoleColors.RED_BOLD +"The ISBN entered does not exist" + ConsoleColors.RESET);
//                searchPrint();
//            }
//        } catch (BookException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("------ " + ConsoleColors.PURPLE + "| ISBN | Title | Author | Year |" + ConsoleColors.RESET);
//        System.out.println("------ " + ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
//        System.out.println("------ " + book.getISBN() + " | " + book.getName() + " | " + book.getAuthor() + " | " + book.getYear());
//        System.out.println("------ " + ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
//        PrintInterface();
//    }

    public static void addPrint() {
        Book book = new Book();
        Print print = new Print();
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the print ISBN" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        book.setISBN(sc.nextInt());
        PrintImplementation insert = new PrintImplementation();
        BookImplementation check = new BookImplementation();
        try {
            check.get(book);
            if (book.getISBN() == null) {
                System.out.println("------ " + ConsoleColors.RED_BOLD + "The ISBN entered does not exist" + ConsoleColors.RESET);
                addPrint();
            }
            System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the print ID" + ConsoleColors.RESET);
            System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
            print.setId(sc.nextInt());
            print.setBook(book);
            insert.Add(print);
            System.out.println("------ " + ConsoleColors.WHITE + "Your print has been added successfully" + ConsoleColors.RESET);
        } catch (BookException | PrintException e) {
            String result = e.getMessage();
            System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + result + ConsoleColors.RESET);
            try {
                throw new BookException();
            } catch (BookException ex) {
                throw new RuntimeException(ex);
            }
        }
        PrintInterface();
    } // Done

    public static void deletePrint() {
        Scanner sc1 = new Scanner(System.in);
        Print print = new Print();
        System.out.print("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the ID of the print you want to delete: " + ConsoleColors.RESET);
        print.setId(scInt.nextInt());
        PrintImplementation check = new PrintImplementation();
        System.out.println("------ " + ConsoleColors.PURPLE + "Are you sure you want to delete this print? (Yes/no)" + ConsoleColors.RESET);
        if (sc1.nextLine().equalsIgnoreCase("YES")) {
            try {
                if (check.Delete(print))
                    System.out.println("------ " + ConsoleColors.WHITE + "Your print has been deleted successfully" + ConsoleColors.RESET);
            } catch (PrintException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        PrintInterface();


    } // Done

    public static void Exit() {
        Main.welcome();
    } // Done
}
