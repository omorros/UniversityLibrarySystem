package main.ui;

import main.model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console menu interface for the library system.
 * Provides a clear, role-based user experience for viewing, borrowing, and returning products.
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
                system.setDemoUser(new Student(3, "Oriol", "omorros@aru.ac.uk",
                        "Software Engineering", 3));
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
// VIEW PRODUCTS MENU
// -------------------------------------------
    private static void viewProductsMenu() {
        User currentUser = system.getDemoUser();
        boolean isAdult = currentUser instanceof AdultUser;
        boolean isStudent = currentUser instanceof Student;
        boolean isChild = currentUser instanceof ChildUser;

        System.out.println("\nSelect category to view:");
        if (isAdult) {
            System.out.println("1. Books");
            System.out.println("2. CDs");
            System.out.println("3. DVDs");
            System.out.println("4. Audiobooks");
        } else if (isStudent) {
            System.out.println("1. Books");
            System.out.println("2. Audiobooks");
        } else if (isChild) {
            System.out.println("1. Books");
        }

        System.out.print("Enter option: ");
        int opt = readInt();

        String category = switch (opt) {
            case 1 -> "Book";
            case 2 -> (isAdult ? "CD" : "Audiobook");
            case 3 -> "DVD";
            case 4 -> "Audiobook";
            default -> null;
        };

        if (category == null) {
            System.out.println("Invalid option.");
            return;
        }

        List<Product> list = system.getProductsByCategory(category);
        if (list.isEmpty()) {
            System.out.println("No products available in this category.");
            return;
        }

        System.out.println("\n" + category + "s in Library:");
        for (Product p : list) {
            System.out.println(p.getInfo());
        }
    }


    // -------------------------------------------
// BORROW PRODUCT MENU (ROLE-BASED)
// -------------------------------------------
    private static void borrowMenu() {
        User currentUser = system.getDemoUser();

        System.out.println("\n===== Borrow Product =====");
        boolean isAdult = currentUser instanceof AdultUser;
        boolean isStudent = currentUser instanceof Student;
        boolean isChild = currentUser instanceof ChildUser;

        System.out.println("Select a category to borrow from:");
        if (isAdult) {
            System.out.println("1. Book");
            System.out.println("2. CD");
            System.out.println("3. DVD");
            System.out.println("4. Audiobook");
        } else if (isStudent) {
            System.out.println("1. Book");
            System.out.println("2. Audiobook");
        } else if (isChild) {
            System.out.println("1. Book");
        }

        System.out.print("Enter option: ");
        int opt = readInt();

        String category = switch (opt) {
            case 1 -> "Book";
            case 2 -> (isAdult ? "CD" : "Audiobook");
            case 3 -> "DVD";
            case 4 -> "Audiobook";
            default -> null;
        };

        if (category == null) {
            System.out.println("Invalid option.");
            return;
        }

        List<Product> list = system.getProductsByCategory(category);
        List<Product> available = new ArrayList<>();
        for (Product p : list) {
            if (p.isAvailable()) available.add(p);
        }

        if (available.isEmpty()) {
            System.out.println("No available items in this category.");
            return;
        }

        System.out.println("\nAvailable " + category + "s:");
        available.forEach(p -> System.out.println(p.getInfo()));

        System.out.print("\nEnter Product ID to borrow: ");
        int id = readInt();
        system.handleBorrow(currentUser, id);
    }


    // -------------------------------------------
    // RETURN PRODUCT MENU
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
            // Remove from the system’s master list of loans
            system.removeLoanRecord(product, user);
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

