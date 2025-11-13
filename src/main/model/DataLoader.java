package main.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class responsible for loading product data from external CSV files.
 * <p>
 * Each CSV file corresponds to a specific product type, and the loader
 * dynamically constructs appropriate {@link Product} objects (e.g.,
 * {@link Book}, {@link CD}, {@link DVD}, {@link Audiobook}) using
 * composition and file I/O.
 * </p>
 * <p>
 * This class demonstrates good separation of concerns: it focuses purely
 * on data retrieval and conversion, leaving business logic to
 * {@link LibrarySystem}. It also showcases basic exception handling and
 * the use of Java’s {@link BufferedReader} for efficient file reading.
 * </p>
 */
public class DataLoader {

    /** The directory path where CSV data files are stored. */
    private static final String DATA_PATH = "src/main/data/";

    // -------------------------------------------
    // LOAD BOOKS
    // -------------------------------------------

    /**
     * Loads all {@link Book} objects from the corresponding CSV file.
     * <p>
     * Expected CSV format:
     * <pre>
     * id, title, author, isbn, genre
     * </pre>
     * </p>
     *
     * @return a list of {@link Book} objects loaded from the file
     */
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH + "books.csv"))) {
            String line;

            // Read file line by line until EOF.
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Ensure line has sufficient data fields before parsing.
                if (values.length >= 5) {
                    int id = Integer.parseInt(values[0].trim());
                    String title = values[1].trim();
                    String author = values[2].trim();
                    String isbn = values[3].trim();
                    String genre = values[4].trim();

                    // Create a new Book object and add it to the list.
                    books.add(new Book(id, title, author, isbn, genre));
                }
            }
        } catch (IOException e) {
            // Handle cases such as missing or unreadable file.
            System.out.println("Error reading books.csv: " + e.getMessage());
        }
        return books;
    }

    // -------------------------------------------
    // LOAD CDS
    // -------------------------------------------

    /**
     * Loads all {@link CD} objects from the corresponding CSV file.
     * <p>
     * Expected CSV format:
     * <pre>
     * id, title, composer
     * </pre>
     * </p>
     *
     * @return a list of {@link CD} objects loaded from the file
     */
    public static List<CD> loadCDs() {
        List<CD> cds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH + "cds.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 3) {
                    int id = Integer.parseInt(values[0].trim());
                    String title = values[1].trim();
                    String composer = values[2].trim();

                    // Create and add a new CD object.
                    cds.add(new CD(id, title, composer));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading cds.csv: " + e.getMessage());
        }
        return cds;
    }

    // -------------------------------------------
    // LOAD DVDS
    // -------------------------------------------

    /**
     * Loads all {@link DVD} objects from the corresponding CSV file.
     * <p>
     * Expected CSV format:
     * <pre>
     * id, title, director
     * </pre>
     * </p>
     *
     * @return a list of {@link DVD} objects loaded from the file
     */
    public static List<DVD> loadDVDs() {
        List<DVD> dvds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH + "dvds.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 3) {
                    int id = Integer.parseInt(values[0].trim());
                    String title = values[1].trim();
                    String director = values[2].trim();

                    // Create and add a new DVD object.
                    dvds.add(new DVD(id, title, director));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dvds.csv: " + e.getMessage());
        }
        return dvds;
    }

    // -------------------------------------------
    // LOAD AUDIOBOOKS
    // -------------------------------------------

    /**
     * Loads all {@link Audiobook} objects from the corresponding CSV file.
     * <p>
     * Expected CSV format:
     * <pre>
     * id, title, narrator
     * </pre>
     * </p>
     *
     * @return a list of {@link Audiobook} objects loaded from the file
     */
    public static List<Audiobook> loadAudiobooks() {
        List<Audiobook> audios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH + "audiobooks.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 3) {
                    int id = Integer.parseInt(values[0].trim());
                    String title = values[1].trim();
                    String narrator = values[2].trim();

                    // Create and add a new Audiobook object.
                    audios.add(new Audiobook(id, title, narrator));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading audiobooks.csv: " + e.getMessage());
        }
        return audios;
    }
}
