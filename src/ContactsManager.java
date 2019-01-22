import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    static String fileName = "contacts.txt";
    static Path fileDirectory = Paths.get("src/", fileName);
    static List<String> fileContent = null;
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

        if (Files.exists(fileDirectory)) {
            System.out.println("the file can be found");
        }

        try {
            Files.write(fileDirectory, fileContent);
        } catch (IOException e){
            System.out.println("File can't be written to because: " + e);
        }
    }

    public  void showOptions(){
        System.out.println("" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n> ");
    }

    public void showContacts(){
        try {
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        }

        System.out.println(fileContent);
    }

    public void addContact(){
        try {
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        }
        System.out.println("");
        fileContent.add(scanner.next());
    }

    public void searchContacts(){

    }
    public void deleteContact(){
        // accept input from user to which contact to delete
    }
}

