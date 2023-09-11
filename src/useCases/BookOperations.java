package useCases;

import DAO.BookImplementation;
import application.Main;
import custom.ConsoleColors;
import exception.BookException;
import models.Book;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookOperations {
    public static void BookInterface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the operation you want to conduct" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[1] - Show books" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[2] - Search book" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[3] - Insert book" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[4] - Update book" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[5] - Delete book" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "[6] - Exit" + ConsoleColors.RESET);
        System.out.print("------" + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        try {
        int choice = sc.nextInt();
            switch (choice) {
                case 1 -> showBooks();
                case 2 -> searchBook();
                case 3 -> addBook();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 6 -> Exit();
                default -> {
                    System.out.println("------" + ConsoleColors.BLUE + "Please enter a valid choice" + ConsoleColors.RESET);
                    BookInterface();
                }
            }
        }catch (InputMismatchException i){
            System.out.println("Please enter a valid choice");
            BookInterface();
        }
    }

    public static void showBooks() {
        BookImplementation book = new BookImplementation();
        try {
            List<Book> books = book.get();
            System.out.println("| ISBN | Title | Prints available | Author | Year |");
            System.out.println("--------------------------------");
            for (Book b : books) {
                System.out.println(b.getISBN() + " | " + b.getName() + " | " + b.getPrints_Available() + " | " + b.getAuthor() + " | " + b.getYear());
            }
            BookInterface();
        } catch (BookException e) {
            throw new RuntimeException(e);
        }
    } // Done

    public static void searchBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the Name or Author of the book you want to find: " + ConsoleColors.RESET);
        BookImplementation check = new BookImplementation();
        List<Book> books = check.search(sc.nextLine());
        if(books == null){
            System.out.println("------ " + ConsoleColors.RED_BOLD +"The ISBN entered does not exist" + ConsoleColors.RESET);
            searchBook();
        }
        System.out.println("------ " + ConsoleColors.PURPLE + "| ISBN | Title | Prints available | Author | Year |" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
        assert books != null;
        for (Book book: books) {
        System.out.println("------ " + book.getISBN() + " | " + book.getName() + " | " + book.getPrints_Available() + " | " + book.getAuthor() + " | " + book.getYear());
        System.out.println("------ " + ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
        }
        BookInterface();
    }

    public static void addBook() {
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Book book = new Book();
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the book ISBN" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        book.setISBN(scInt.nextInt());
        System.out.println("------" + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the book name" + ConsoleColors.RESET);
        System.out.print("------" + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        book.setName(sc.nextLine());
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the book author" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        book.setAuthor(sc.nextLine());
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the book published year" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        book.setYear(scInt.nextInt());
        BookImplementation insert = new BookImplementation();
        try {
            insert.Add(book);
            System.out.println("------ " + ConsoleColors.WHITE + "Your book has been added successfully" + ConsoleColors.RESET);
        } catch (BookException e) {
            String result = e.getMessage();
            System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + result + ConsoleColors.RESET);
            try {
                throw new BookException();
            } catch (BookException ex) {
                throw new RuntimeException(ex);
            }
        }
        BookInterface();
    } // Done

    public static void updateBook() {
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Book book = new Book();
        System.out.println("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the ISBN of the book you want to modify" + ConsoleColors.RESET);
        book.setISBN(scInt.nextInt());
        BookImplementation check = new BookImplementation();
        try {
            check.get(book);
            if(book.getISBN() == null){
                System.out.println("------ " + ConsoleColors.RED_BOLD +"The ISBN entered does not exist" + ConsoleColors.RESET);
                updateBook();
            }
        } catch (BookException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------ " + ConsoleColors.PURPLE + "Enter the new book ISBN (Enter 0 to skip)" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        int isbn = scInt.nextInt();
        if (isbn != 0){
            book.setTempISBN(book.getISBN());
            book.setISBN(isbn);
        }
        System.out.println("------" + ConsoleColors.PURPLE + "Enter the book name (Type skip to skip)" + ConsoleColors.RESET);
        System.out.print("------" + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        String name = sc.nextLine();
        if (!name.equalsIgnoreCase("SKIP"))
            book.setName(name);
        System.out.println("------ " + ConsoleColors.PURPLE + "Enter the book author (Type skip to skip)" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        String author = sc.nextLine();
        if (!author.equalsIgnoreCase("SKIP"))
            book.setAuthor(author);
        System.out.println("------ " + ConsoleColors.PURPLE + "Enter the book published year (Enter 0 to skip)" + ConsoleColors.RESET);
        System.out.print("------ " + ConsoleColors.BLUE + "Your choice: " + ConsoleColors.RESET);
        int year = scInt.nextInt();
        if (year != 0) book.setYear(year);
        BookImplementation update = new BookImplementation();
        try {
            update.Update(book);
            System.out.println("------ " + ConsoleColors.WHITE + "Your book has been updated successfully" + ConsoleColors.RESET);
        } catch (BookException e) {
            String result = e.getMessage();
            System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + result + ConsoleColors.RESET);
        }
        BookInterface();

    } // Done

    public static void deleteBook() {
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Book book = new Book();
        System.out.print("------ " + ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the ISBN of the book you want to delete: " + ConsoleColors.RESET);
        book.setISBN(scInt.nextInt());
        BookImplementation check = new BookImplementation();
        try {
            check.get(book);
            if(book.getISBN() == null){
                System.out.println("------ " + ConsoleColors.RED_BOLD +"The ISBN entered does not exist" + ConsoleColors.RESET);
                deleteBook();
            }
        } catch (BookException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------ " + ConsoleColors.PURPLE + "| ISBN | Title | Author | Year |" + ConsoleColors.RESET);
        System.out.println("------ " + ConsoleColors.PURPLE + "--------------------------------" + ConsoleColors.RESET);
        System.out.println(book.getISBN() + " | " + book.getName() + " | " + book.getAuthor() + " | " + book.getYear());
        System.out.println("------ " + ConsoleColors.PURPLE + "Are you sure you want to delete this book? (Yes/no)" + ConsoleColors.RESET);
        if(sc.nextLine().equalsIgnoreCase("YES")) {
            try {
                if (check.Delete(book))
                    System.out.println("------ " + ConsoleColors.WHITE + "Your book has been deleted successfully" + ConsoleColors.RESET);
            } catch (BookException e) {
                System.out.println(e.getMessage());
            }
        }
        BookInterface();


    } // Done

    public static void Exit() {
        Main.welcome();
    } // Done
}
