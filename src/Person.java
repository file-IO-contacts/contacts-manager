public abstract class Person {
    final String first;
    final String last;
    final String phone;

    public Person(String first, String last, String phone){
        this.first = first;
        this.last = last;
        this.phone = phone;
    }

    abstract boolean contains(String input);
}
