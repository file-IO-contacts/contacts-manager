public class Contact extends Person{
    public Contact(String first, String last, String phone) {
        super(first, last, phone);
    }

    public boolean contains(String input){
        return first.contains(input) || last.contains(input) || phone.contains(input);
    }
}