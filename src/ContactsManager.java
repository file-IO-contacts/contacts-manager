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
    static ArrayList<Contact> contactsList = new ArrayList<>();


    public static void main(String[] args) {

//        if (Files.exists(fileDirectory)) {
//            System.out.println("the file can be found");
//        }
        int userInput = 0;
        do {
            loadContacts();
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

    public static void loadContacts() {
        try {
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        }
        for(String line : fileContent){
            contactsList.add(createContact(line));
        }

    }

    public static void showContacts() {
        System.out.printf("%-18s | %s", "Name", "Phone Number\n");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _\n");
        for (Contact contact: contactsList) {
//            System.out.println(contact.first + " " + contact.last + " " + contact.phone);
            System.out.printf("%-9s %-9s %s%n", contact.first, contact.last, contact.phone);
        }

    }

    public static void addContact(){
        System.out.println("Please enter a name and phone number: ");
        for(String line : fileContent){
            contactsList.add(createContact(line));
        }
        fileContent.add(input.getString());

        try {
            Files.write(fileDirectory, fileContent);
        } catch (IOException e){
            System.out.println("File can't be written to because: " + e);
        }
    }

    public static void searchContacts() {

        String userInput = input.getString();
        for(String contact: fileContent) {
            if(contact.contains(userInput)) {
                System.out.println(contact);
            }
        }
    }

    public static void deleteContact(){

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

    public static void writeToFile(){

    }
    public static Contact createContact(String x) {
        String[] split = x.split(" ");

        Contact contact = new Contact(split[0],split[1],split[2]);
        return contact;
    }

}//end contactsmanager class

class Contact {
        String first;
        String last;
        String phone;


        public Contact(String first, String last, String phone) {
            this.first = first;
            this.last = last;
            this.phone = phone;
        }

}
