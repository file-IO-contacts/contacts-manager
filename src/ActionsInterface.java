public interface ActionsInterface {
    void loadContacts();
    void showOptions();
    void showContacts();
    void addContact();
    void searchContacts();
    void deleteContact();
    void writeToFile();
    Contact createContact(String input);
}
