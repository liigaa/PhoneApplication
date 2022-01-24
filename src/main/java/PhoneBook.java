import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class PhoneBook {
    FileRepository fileRepository = new FileRepository();
    public ArrayList<Contact> contacts;
    public PhoneBook(){
        this.contacts = new ArrayList<>();
    }

    public ArrayList<Contact> getContacts() {
        return fileRepository.readFile();
    }
    public String addContact(){
        JTextField name = new JTextField(15);
        JTextField phoneNumber = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name:"));
        myPanel.add(name);
        myPanel.add(new JLabel("Phone Number"));
        myPanel.add(phoneNumber);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Add Contact", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return "No new Contact!";
        }
        if (result == JOptionPane.OK_OPTION) {
            String filepath = "phone_book.txt";
            fileRepository.writeFile(name.getText(),phoneNumber.getText(), filepath);

        }
        return "Contact: " + name.getText() + " added successfully";
    }

    public String updateContact(){
       ArrayList<Contact> contacts = getContacts();
        try {
            Contact contactToUpdate = (Contact) JOptionPane.showInputDialog(null,
                    "Choose contact to update", "Update contact",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    contacts.toArray(new Contact[0]), contacts);
            JTextField nameField = new JTextField(15);
            JTextField phoneField = new JTextField(15);
            nameField.setText(contactToUpdate.getName());
            phoneField.setText(contactToUpdate.getPhoneNumber());
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Name:"));
            myPanel.add(nameField);
            myPanel.add(new JLabel("Phone Number"));
            myPanel.add(phoneField);

            String name = contactToUpdate.getName();
            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Update Contact: " + name, JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) {
                return "Update canceled!";
            }

            if (result == JOptionPane.OK_OPTION) {
                fileRepository.removeRecord(name);
                String newName = nameField.getText();
                String newPhoneNumber = phoneField.getText();
                contactToUpdate.setName(newName);
                contactToUpdate.setPhoneNumber(newPhoneNumber);
                String filepath = "phone_book.txt";
                fileRepository.writeFile(newName, newPhoneNumber, filepath);
            }
            return "Contact: " + name + " updated successfully!";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No Contact to update",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        return "Choose Contact to update";
    }

    public String removeContact(){
        ArrayList<Contact> contacts = getContacts();
       try {
           Contact contactToRemove = (Contact) JOptionPane.showInputDialog(null,
                   "Choose contact to remove", "Delete contact",
                   JOptionPane.INFORMATION_MESSAGE, null,
                   contacts.toArray(new Contact[0]), contacts);
           String name = contactToRemove.getName();
           fileRepository.removeRecord(name);

           return "Contact: " + contactToRemove.getName() + " deleted successfully";
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "No contact to delete",
                   "Warning!", JOptionPane.WARNING_MESSAGE);
       }return "Choose contact to delete";
    }

    public void viewAllContacts(){

        List<String> contactString = getContacts().stream().map(Contact::toString).toList();
        JOptionPane.showMessageDialog(null, String.join(",\n", contactString), "All Contacts",
                JOptionPane.PLAIN_MESSAGE);

    }
}
