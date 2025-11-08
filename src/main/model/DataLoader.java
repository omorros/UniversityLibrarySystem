package main.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads product data from CSV files.
 * Each CSV file corresponds to a specific product type.
 */
public class DataLoader {

    private static final String DATA_PATH = "src/main/data/";

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH + "books.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5) {
                    int id = Integer.parseInt(values[0].trim());
                    String title = values[1].trim();
                    String author = values[2].trim();
                    String isbn = values[3].trim();
                    String genre = values[4].trim();
                    books.add(new Book(id, title, author, isbn, genre));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books.csv: " + e.getMessage());
        }
        return books;
    }

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
                    cds.add(new CD(id, title, composer));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading cds.csv: " + e.getMessage());
        }
        return cds;
    }

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
                    dvds.add(new DVD(id, title, director));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dvds.csv: " + e.getMessage());
        }
        return dvds;
    }

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
                    audios.add(new Audiobook(id, title, narrator));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading audiobooks.csv: " + e.getMessage());
        }
        return audios;
    }
}
