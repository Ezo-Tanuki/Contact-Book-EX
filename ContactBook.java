package WEEK4;

import java.util.LinkedList;
import java.util.Scanner;

class Contact{
    protected String name;
    protected String phone_number;
    protected String email;

    public Contact(String name, String phone_number, String email){
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public boolean equals(Contact other){
        return this.name.equals(other.name) && this.phone_number.equals(other.phone_number) && this.email.equals(other.email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

public class ContactBook {
    private Scanner scanner = new Scanner(System.in);
    protected LinkedList<Contact> contacts = new LinkedList<>();
    void printMenu(){
        System.out.println("*".repeat(50));
        System.out.println("(A)dd");
        System.out.println("(D)elete");
        System.out.println("(E)mail search");
        System.out.println("(P)rint list");
        System.out.println("(S)earch");
        System.out.println("(Q)uit");
        System.out.println("*".repeat(50));
    }

    void printCredential(Contact contact){
        System.out.println("Name: " + contact.getName());
        System.out.println("Phone number: " + contact.getPhoneNumber());
        System.out.println("E-mail: " + contact.getEmail());
    }

    public void run(){
        //Initial run
        char state = 'M';
        String input;
        boolean running = true;

        //Running process
        while(running){
            System.out.println();
            switch (state) {
                //Menu
                case 'M' -> {
                    this.printMenu();
                    System.out.print("Please enter a command: ");
                    input = scanner.next().toUpperCase();

                    boolean valid = "ADEPSQ".contains(input);

                    while (input.length() != 1 || !valid) {
                        System.out.print("Please enter a valid command: ");
                        input = scanner.next().toUpperCase();
                        valid = "ADEPSQ".contains(input);
                    }

                    state = input.charAt(0);
                }
                //Add
                case 'A' -> {
                    System.out.println("Add");
                    String name, phone_number, email;
                    System.out.print("Enter the name: ");
                    name = scanner.next();
                    System.out.print("Enter the phone number: ");
                    phone_number = scanner.next();
                    System.out.print("Enter the email: ");
                    email = scanner.next();

                    contacts.add(new Contact(name, phone_number, email));
                    System.out.println("Added successfully!");
                    state = 'M';
                }
                //Delete
                case 'D' -> {
                    System.out.println("Delete");
                    String name, phone_number, email;
                    System.out.print("Enter the name: ");
                    name = scanner.next();
                    System.out.print("Enter the phone number: ");
                    phone_number = scanner.next();
                    System.out.print("Enter the email: ");
                    email = scanner.next();

                    int i = 0, idx = -1;
                    for (Contact contact : contacts) {
                        if (contact.equals(new Contact(name, phone_number, email))) {
                            idx = i;
                            break;
                        }
                        i++;
                    }

                    if (idx == -1) {
                        System.out.println("Contact not found!");
                        state = 'M';
                        break;
                    }

                    contacts.remove(idx);
                    System.out.println("Deleted successfully!");
                    state = 'M';
                }
                //Email search
                case 'E' -> {
                    System.out.println("Email search");
                    System.out.print("Enter the email: ");
                    String email = scanner.next();
                    Contact target = null;

                    for (Contact contact : contacts) {
                        if (contact.getEmail().equals(email)) {
                            target = contact;
                            break;
                        }
                    }

                    if (target == null) {
                        System.out.println("E-mail not found.");
                        state = 'M';
                        break;
                    }

                    this.printCredential(target);
                    state = 'M';
                }
                //Print list
                case 'P' -> {
                    System.out.println("Print list");
                    for(Contact c : contacts){
                        this.printCredential(c);
                        System.out.println();
                    }

                    if (contacts.size() == 0) System.out.print("Empty");

                    System.out.println();
                    state = 'M';
                }
                //Search
                case 'S' -> {
                    System.out.println("Search");
                    String name;
                    System.out.print("Enter the name: ");
                    name = scanner.next();
                    Contact target = null;

                    boolean found = false;

                    for (Contact contact : contacts) {
                        if (contact.getName().equals(name)) {
                            found = true;
                            target = contact;
                            break;
                        }
                    }

                    System.out.println("The credential is " + (found ? "registered " : "not registered ") + "in the contact book with the following credentials");
                    if (found) this.printCredential(target);

                    state = 'M';
                }
                //Quit
                case 'Q' -> running = false;

            }
        }
    }

    public ContactBook(){}

}
