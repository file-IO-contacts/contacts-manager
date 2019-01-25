import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Actions implements ActionsInterface {
    static List<String> fileContent = null;
    static List<Person> contactsList = new ArrayList<>();
    static String fileName = "contacts.txt";
    static Path fileDirectory = Paths.get("src/", fileName);
    static Input input  = new Input();


    public void loadContacts() {
        try {
            if(Files.notExists(fileDirectory)) {
                System.out.println("Contacts file not found, creating file...");
                TimeUnit.SECONDS.sleep(6);
                Files.createFile(fileDirectory); // similar to "touch" on the cli
                System.out.println("File successfully created!");
            }
            fileContent = Files.readAllLines(fileDirectory);
        } catch (IOException e){
            System.out.println("file can't be read");
        } catch (InterruptedException e){
            System.out.println("file creation failed");
        }
        for(String line : fileContent){
            contactsList.add(createContact(line));
        }
    }

    public void showOptions(){
        System.out.print("" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n> ");
    }

    public void showContacts() {
        if (contactsList.size() != 0) {
            System.out.printf("%-12s | %s", "Name", "Phone Number\n");
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
            for (Person contact : contactsList) {
//            System.out.println(contact.first + " " + contact.last + " " + contact.phone);
                System.out.printf("%-12s | %s\n", contact.first + " " + contact.last, contact.phone);
            }
        }else {
            System.out.println("Contacts list is empty. Try adding a contact.");
        }

    }

    public void addContact(){
        contactsList.add(createContact(input.getString("Please enter a first name, last name, and phone number: ").trim()));
        System.out.println("Contact saved ^_^");
    }

    public void searchContacts() {
        if (contactsList.size() != 0) {
            int numOfFound = 0;
            String userInput = input.getString("Enter a name or phone number");
            for (Person contact : contactsList) {
                if (contact.contains(userInput)) {
                    System.out.printf("%s %s %s\n", contact.first, contact.last, contact.phone);
                    numOfFound++;
                }
            }
            if (numOfFound == 0) {
                System.out.println("No matches found.");
            }

        }else {
            System.out.println("Contacts list is empty. Try adding a contact.");
        }
    }

    public void deleteContact(){
        if (contactsList.size() != 0) {
            int numOfFound = 0;
            String userInput = input.getString("Enter a name or phone number to delete");
            ArrayList<Person> newContent = new ArrayList<>();
            for (Person contact : contactsList) {
                if (contact.contains(userInput)) {
                    System.out.printf("%s %s %s\n", contact.first, contact.last, contact.phone);
                    numOfFound++;
                    continue;
                }
                newContent.add(contact);
            }

            if (numOfFound != 0) {
                if (input.yesNo("Do you wish to delete the " + numOfFound + " contact(s) above?")) {
                    contactsList = newContent;
                    System.out.println("Contact(s) deleted");
                } else {
                    System.out.println("Delete aborted");
                }
            } else {
                System.out.println("No matches found.");
            }
        } else {
            System.out.println("Contacts list is empty. Try adding a contact.");
        }

        // accept input from user to which contact to delete
    }

    public void writeToFile(){
        fileContent.clear();
        for(Person contact: contactsList) {
            fileContent.add(contact.first + " " + contact.last + " " + contact.phone);
        }
        try {
            Files.write(fileDirectory, fileContent);
        } catch (IOException e){
            System.out.println("File can't be written to because: " + e);
        }
    }
    public Contact createContact(String x) {
        String[] split = x.split(" ");

        return new Contact(split[0],split[1],split[2]);
    }
}
