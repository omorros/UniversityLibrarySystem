package main.ui;

import main.model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console-based user interface for the University Library System.
 * <p>
 * The {@code Menu} class provides a simple text-based interface for
 * interacting with the {@link LibrarySystem}. It allows different
 * types of users ({@link AdultUser}, {@link ChildUser}, {@link Student})
 * to log in, view products, borrow and return items, and check active loans.
 * </p>
 * <p>
 * This class represents the <b>View and Controller</b> elements in an
 * MVC-style architecture: it interacts with the model layer
 * ({@link main.model}) to execute logic and display results.
 * </p>
 */
public class Menu {

    /** Core library system instance that manages users, products, and loans. */
    private static LibrarySystem system = new LibrarySystem();

    /** Scanner used for reading console input from the user. */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Application entry point.
     * <p>
     * Loads product data, prompts user login, and starts the main menu loop.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        system.loadAllData(); // Load product data from CSV files
        loginMenu();          // Handle user type selection
        runMenu();            // Run main interactive menu
    }

    // -------------------------------------------
    // LOGIN MENU
    // -------------------------------------------

    /**
     * Displays the login menu and assigns a user type
     * ({@link AdultUser}, {@link ChildUser}, or {@link Student})
     * to the active session.
     * <p>
     * Demonstrates polymorphism by assigning different user subclasses
     * to a single {@link User} reference based on user selection.
     * </p>
     */
    private static void loginMenu() {
        System.out.println("===== Library Login =====");
        System.out.println("Select user type:");
        System.out.println("1. Adult User");
        System.out.println("2. Child User");
        System.out.println("3. Student");
        System.out.print("Enter option: ");

        int opt = readInt();

        // Instantiate appropriate user subclass depending on user choice.
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
                // Fallback user in case of invalid input
                system.setDemoUser(new AdultUser(99, "Default", "default@library.com"));
                System.out.println("Invalid choice, logged in as default AdultUser.");
            }
        }
    }

    // -------------------------------------------
    // MAIN MENU
    // -------------------------------------------

    /**
     * Displays the main operational menu.
     * <p>
     * Users can view, borrow, or return products, and view all active loans.
     * The menu continues looping until the user chooses to exit.
     * </p>
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

    /**
     * Displays available products filtered by user type and category.
     * <p>
     * Implements role-based access by restricting available categories
     * for each type of user (e.g., {@link ChildUser} can only view books).
     * </p>
     */
    private static void viewProductsMenu() {
        User currentUser = system.getDemoUser();

        // Role checks
        boolean isAdult = currentUser instanceof AdultUser;
        boolean isStudent = currentUser instanceof Student;
        boolean isChild = currentUser instanceof ChildUser;

        System.out.println("\nSelect category to view:");

        // Display available categories depending on user type.
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

        // Determine category based on user choice and role.
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

        // Retrieve and display available items for the selected category.
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

    /**
     * Allows a logged-in user to borrow an item, restricted by their role type.
     * <p>
     * Demonstrates polymorphism by calling {@link User#borrowProduct(Product, Policy)}
     * which behaves differently depending on the subclass (e.g., {@link ChildUser}
     * or {@link Student}).
     * </p>
     */
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

        // Determine borrowing category based on user type and input.
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

        // Filter available items.
        List<Product> list = system.getProductsByCategory(category);
        List<Product> available = new ArrayList<>();
        for (Product p : list) {
            if (p.isAvailable()) available.add(p);
        }

        if (available.isEmpty()) {
            System.out.println("No available items in this category.");
            return;
        }

        // Display all available items to the user.
        System.out.println("\nAvailable " + category + "s:");
        available.forEach(p -> System.out.println(p.getInfo()));

        System.out.print("\nEnter Product ID to borrow: ");
        int id = readInt();
        system.handleBorrow(currentUser, id);
    }

    // -------------------------------------------
    // RETURN PRODUCT MENU
    // -------------------------------------------

    /**
     * Allows a user to return a previously borrowed product.
     * <p>
     * Lists all active loans for the current user and prompts for a product ID
     * to return. If the item exists and is in the user's loan list, it will be
     * marked as available again and removed from the system’s records.
     * </p>
     */
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

        // Process the return and update the system’s records.
        boolean success = user.returnProduct(product);
        if (success) {
            product.setAvailable(true);
            system.removeLoanRecord(product, user); // Sync with main system
            System.out.println("Return successful: " + product.getTitle());
        } else {
            System.out.println("Return failed. Ensure you borrowed this item.");
        }
    }

    // -------------------------------------------
    // INPUT VALIDATION
    // -------------------------------------------

    /**
     * Reads an integer from user input, returning -1 if invalid.
     *
     * @return the entered integer, or -1 if parsing fails
     */
    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

