package main.ui;

import main.model.*;
import java.util.Scanner;

/** Console menu interface for the library system. */
public class Menu {
    private static LibrarySystem system = new LibrarySystem();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        preloadData();
        runMenu();
    }

    private static void preloadData() {
        system.addProduct(new Book(1, "The Hobbit", "Tolkien", "978-0261", "Fantasy"));
        system.addProduct(new DVD(2, "Inception", "Nolan"));
        system.addProduct(new CD(3, "Classics", "Mozart"));
        system.addProduct(new Audiobook(4, "Atomic Habits", "James Clear"));

        system.setDemoUser(new AdultUser(1, "Alice", "alice@mail.com"));
    }

    private static void runMenu() {
        int choice;
        do {
            System.out.println("\n===== University Library System =====");
            System.out.println("1. View Products");
            System.out.println("2. Borrow Product");
            System.out.println("3. Return Product");
            System.out.println("4. View Loans");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1 -> system.displayAllProducts();
                case 2 -> borrow();
                case 3 -> returnProduct();
                case 4 -> system.displayAllLoans();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void borrow() {
        System.out.print("Enter Product ID: ");
        int id = readInt();
        system.handleBorrow(system.getDemoUser(), id);
    }

    private static void returnProduct() {
        System.out.print("Enter Product ID to return: ");
        int id = readInt();
        system.handleReturn(system.getDemoUser(), id);
    }

    private static int readInt() {
        try { return Integer.parseInt(sc.nextLine()); }
        catch (NumberFormatException e) { return -1; }
    }
}

