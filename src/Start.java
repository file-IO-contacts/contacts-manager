public class Start {
    public static void start(){
        Actions actions = new Actions();
        actions.loadContacts();
            do {
                actions.showOptions();
                switch(Actions.input.getInt(1,5)) {
                    case 1:
                        actions.showContacts();
                        break;
                    case 2:
                        actions.addContact();
                        break;
                    case 3:
                        actions.searchContacts();
                        break;
                    case 4:
                        actions.deleteContact();
                        break;
                    default:
                        actions.writeToFile();
                        System.exit(0);
                }
            Actions.input.getString("Press Enter to continue...");
        }while (true);
    }
}
