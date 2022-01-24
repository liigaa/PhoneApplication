import javax.swing.*;

public class Menu {
    PhoneBook phoneBook = new PhoneBook();
    ImageIcon icon = new ImageIcon("src/phone.png");
    public void start() {
    String userChoice;
        do {
            String[] options = {
                    "Select...", "Add contact", "Remove contact", "View all contacts", "Update contact","Exit"};
            userChoice = (String) JOptionPane.showInputDialog(null, """
                            Welcome to Phone Book!
                            Please select choice
                            Choose EXIT to quit""",
                    "Phone Book",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    options,
                    options[0]);
            switch (userChoice) {
                case "Add contact":
                    JOptionPane.showMessageDialog(null, phoneBook.addContact());
                    break;
                case "View all contacts":
                    phoneBook.viewAllContacts();
                    break;
                case "Remove contact":
                    JOptionPane.showMessageDialog(null, phoneBook.removeContact());
                    break;
                case "Update contact":
                    JOptionPane.showMessageDialog(null, phoneBook.updateContact());
                    break;
                default:
                    break;
            }
        } while (!userChoice.equals("Exit"));
    }
    }

