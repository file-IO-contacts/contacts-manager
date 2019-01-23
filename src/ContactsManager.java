import util.Input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ContactsManager {
    static String fileName = "contacts.txt";
    static Path fileDirectory = Paths.get("src/", fileName);
    static List<String> fileContent = null;
    static Input input  = new Input();

    public static void main(String[] args) {

        if (Files.exists(fileDirectory)) {
            System.out.println("the file can be found");
        }
        int userInput = 0;
        do {
            showOptions();
            userInput = input.getInt(1,5);
            if (userInput == 1) {
                showContacts();
            }
            if (userInput == 2) {
                addContact();
            }
            if (userInput == 3) {
                searchContacts();
            }
            if (userInput == 4) {
                deleteContact();
            }
            if (userInput == 5) {
                break;
            }
            input.getString("Press Enter to continue...");
            }while (true);
        System.exit(0);
    }

    public static void showOptions(){
        System.out.println("\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n> ");
    }

    public static void showContacts() {
        Path contactsPath = fileDirectory;
        try {
            fileContent = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String contact: fileContent) {
            System.out.println(contact);
        }

    }

    public static void addContact(){
        Path contactsPath = fileDirectory;
        try {
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        }
        System.out.println("Please enter a name and phone number: ");

        fileContent.add(input.getString());

        try {
            Files.write(fileDirectory, fileContent);
        } catch (IOException e){
            System.out.println("File can't be written to because: " + e);
        }
    }

    public static void searchContacts() {
        Path contactsPath = fileDirectory;
        try {
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        }

        String userInput = input.getString();
        for(String contact: fileContent) {
            if(contact.contains(userInput)) {
                System.out.println(contact);
            }
        }
    }

    public static void deleteContact(){
        Path contactsPath = fileDirectory;
        try {
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        }

        String userInput = input.getString();
        ArrayList<String> newContent = new ArrayList<String>();
        for(String contact: fileContent) {
            if(contact.contains(userInput)) {
                continue;
            }
            newContent.add(contact);
        }

        try {
            Files.write(fileDirectory, newContent);
        } catch (IOException e){
            System.out.println("File can't be written to because: " + e);
        }

        // accept input from user to which contact to delete
    }
}

