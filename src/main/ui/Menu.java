package main.ui;

import main.model.*;
import java.util.*;

public class Menu {
    private static LibrarySystem system = new LibrarySystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        preloadData();
        runMenu();
    }

    private static void runMenu() {
        int choice;
        do {
            System.out.println("\n==== University Library System ====");
            System.out.println("1. View Products");
            System.out.println("2. Borrow Product");
            System.out.println("3. Return Product");
            System.out.println("4. View Loans");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> system.displayAllProducts();
                case 2 -> borrowProduct();
                case 3 -> returnProduct();
                case 4 -> system.displayAllLoans();
                case 5 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 5);
    }

    private static void borrowProduct() {
        System.out.print("Enter product ID to borrow: ");
        int id = getIntInput();
        system.handleBorrow(system.getDemoUser(), id);
    }

    private static void returnProduct() {
        System.out.print("Enter product ID to return: ");
        int id = getIntInput();
        system.handleReturn(system.getDemoUser(), id);
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void preloadData() {
        system.addProduct(new Book(1, "The Hobbit", "J.R.R. Tolkien", "978-0-261", "Fantasy"));
        system.addProduct(new DVD(2, "Inception", "Christopher Nolan"));
        system.addProduct(new CD(3, "Mozart Classics", "Mozart"));
        system.addProduct(new Audiobook(4, "Atomic Habits", "James Clear"));

        system.setDemoUser(new AdultUser(100, "Alice", "alice@example.com"));
    }
}

