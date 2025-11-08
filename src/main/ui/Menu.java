package main.ui;

import main.model.*;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu interface for the library system.
 * Provides a clear user experience for viewing and borrowing items by category.
 */
public class Menu {
    private static LibrarySystem system = new LibrarySystem();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        system.loadAllData(); // Load from CSV files
        loginMenu();
        runMenu();
    }

    // -------------------------------------------
    // LOGIN MENU
    // -------------------------------------------
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
                ChildUser child = new ChildUser(2, "Sara", "sara@mail.com");
                AdultUser guardian = new AdultUser(10, "Parent", "parent@mail.com");
                guardian.addChild(child);
                system.setDemoUser(child);
                System.out.println("Logged in as ChildUser: Sara (Guardian: Parent)");
            }
            case 3 -> {
                system.setDemoUser(new Student(3, "Oriol", "omorros@aru.ac.uk", "Software Engineering", 3));
                System.out.println("Logged in as Student: Oriol");
            }
            default -> {
                system.setDemoUser(new AdultUser(99, "Default", "default@library.com"));
                System.out.println("Invalid choice, logged in as default AdultUser.");
            }
        }
    }

    // -------------------------------------------
    // MAIN MENU
    // -------------------------------------------
    private static void runMenu() {
        int choice;
        do {
            System.out.println("\n===== University Library System =====");
            System.out.println("Logged in as: " + system.getDemoUser().getClass().getSimpleName() +
                    " (" + system.getDemoUser().getName() + ")");
            System.out.println("1. View Products");
            System.out.println("2. Borrow Product");
            System.out.println("3. Return Product");
            System.out.println("4. View Loans");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1 -> viewProductsMenu();
                case 2 -> borrowMenu();
                case 3 -> returnProduct();
                case 4 -> system.displayAllLoans();
                case 5 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 5);
    }

    // -------------------------------------------
    // PRODUCT VIEW MENU
    // -------------------------------------------
    private static void viewProductsMenu() {
        System.out.println("\nSelect category to view:");
        System.out.println("1. Books");
        System.out.println("2. CDs");
        System.out.println("3. DVDs");
        System.out.println("4. Audiobooks");
        System.out.print("Enter option: ");
        int opt = readInt();

        switch (opt) {
            case 1 -> DataLoader.loadBooks().forEach(b -> System.out.println(b.getInfo()));
            case 2 -> DataLoader.loadCDs().forEach(c -> System.out.println(c.getInfo()));
            case 3 -> DataLoader.loadDVDs().forEach(d -> System.out.println(d.getInfo()));
            case 4 -> DataLoader.loadAudiobooks().forEach(a -> System.out.println(a.getInfo()));
            default -> System.out.println("Invalid option.");
        }
    }

    // -------------------------------------------
    // BORROW MENU
    // -------------------------------------------
    private static void borrowMenu() {
        System.out.println("\nSelect category to borrow from:");
        System.out.println("1. Book");
        System.out.println("2. CD");
        System.out.println("3. DVD");
        System.out.println("4. Audiobook");
        System.out.print("Enter option: ");
        int opt = readInt();

        List<? extends Product> categoryList = switch (opt) {
            case 1 -> DataLoader.loadBooks();
            case 2 -> DataLoader.loadCDs();
            case 3 -> DataLoader.loadDVDs();
            case 4 -> DataLoader.loadAudiobooks();
            default -> null;
        };

        if (categoryList == null) {
            System.out.println("Invalid option.");
            return;
        }

        if (categoryList.isEmpty()) {
            System.out.println("No products available in this category.");
            return;
        }

        System.out.println("\nAvailable items:");
        categoryList.forEach(p -> System.out.println(p.getInfo()));

        System.out.print("Enter Product ID to borrow: ");
        int id = readInt();

        // Now find and borrow directly from the system’s master list (not just this category)
        system.handleBorrow(system.getDemoUser(), id);
    }

    // -------------------------------------------
    // RETURN MENU
    // -------------------------------------------
    private static void returnProduct() {
        User user = system.getDemoUser();
        List<Loan> userLoans = user.viewLoans();

        System.out.println("\n===== Return Product =====");
        if (userLoans.isEmpty()) {
            System.out.println("You have no active loans to return.");
            return;
        }

        System.out.println("Your current loans:");
        for (Loan loan : userLoans) {
            System.out.println(loan.getInfo());
        }

        System.out.print("\nEnter the Product ID to return: ");
        int id = readInt();

        Product product = system.findProductById(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        boolean success = user.returnProduct(product);
        if (success) {
            product.setAvailable(true);
            System.out.println("Return successful: " + product.getTitle());
        } else {
            System.out.println("Return failed. Ensure you borrowed this item.");
        }
    }


    // -------------------------------------------
    // INPUT VALIDATION
    // -------------------------------------------
    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
