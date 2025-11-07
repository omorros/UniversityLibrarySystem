package main.ui;

import main.model.*;
import java.util.Scanner;

/**
 * Console menu interface for the library system.
 * Includes a login menu to choose between Adult, Child, or Student users.
 */
public class Menu {
    private static LibrarySystem system = new LibrarySystem();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        preloadData();
        loginMenu();    // NEW login menu
        runMenu();      // Main library menu
    }

    /**
     * Preloads some demo products into the library system.
     */
    private static void preloadData() {
        system.addProduct(new Book(1, "The Hobbit", "J.R.R. Tolkien", "978-0261", "Fantasy"));
        system.addProduct(new DVD(2, "Inception", "Christopher Nolan"));
        system.addProduct(new CD(3, "Mozart Classics", "Mozart"));
        system.addProduct(new Audiobook(4, "Atomic Habits", "James Clear"));
    }

    /**
     * Allows user to select a user category (Adult, Child, or Student).
     */
    private static void loginMenu() {
        System.out.println("===== Library Login =====");
        System.out.println("Select user type:");
        System.out.println("1. Adult User");
        System.out.println("2. Child User");
        System.out.println("3. Student");
        System.out.print("Enter option: ");
        int opt = readInt();

        switch (opt) {
            case 1 -> {
                system.setDemoUser(new AdultUser(1, "Dhrew", "dhrew@mail.com"));
                System.out.println("Logged in as AdultUser: Dhrew");
            }
            case 2 -> {
                ChildUser child = new ChildUser(2, "Sara", "Sara@mail.com");
                AdultUser guardian = new AdultUser(10, "Parent", "parent@mail.com");
                guardian.addChild(child);
                system.setDemoUser(child);
                System.out.println("Logged in as ChildUser: Sara (Guardian: Parent)");
            }
            case 3 -> {
                system.setDemoUser(new Student(3, "Oriol", "omorros@aru.ac.uk",
                        "Software Engineering", 3));
                System.out.println("Logged in as Student: Oriol");
            }
            default -> {
                system.setDemoUser(new AdultUser(99, "Default", "default@library.com"));
                System.out.println("⚠ Invalid choice, logged in as default AdultUser.");
            }
        }
    }

    /**
     * Main operational menu.
     */
    private static void runMenu() {
        int choice;
        do {
            System.out.println("\n===== University Library System =====");
            System.out.println("Logged in as: " +
                    system.getDemoUser().getClass().getSimpleName() +
                    " (" + system.getDemoUser().getName() + ")");
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
                case 5 -> System.out.println("Exiting system... Goodbye!");
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 5);
    }

    private static void borrow() {
        System.out.print("Enter Product ID to borrow: ");
        int id = readInt();
        system.handleBorrow(system.getDemoUser(), id);
    }

    private static void returnProduct() {
        System.out.print("Enter Product ID to return: ");
        int id = readInt();
        system.handleReturn(system.getDemoUser(), id);
    }

    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}