import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRepository {
    private Scanner scanner;

    public void writeFile(String name, String phoneNumber, String filePath){
        try {
            FileWriter filewriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(name + ", " + phoneNumber);
            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }
    public void removeRecord(String name){
        String tempFile = "temp.txt";
        String filePath = "phone_book.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);


        try {
            FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            scanner = new Scanner(oldFile);

            while (scanner.hasNextLine()){
               String text = scanner.nextLine();
               String[] splitText = text.split(",");
                if(!(splitText[0].equals(name))){
                    printWriter.println(text);
                }
            }scanner.close();
            printWriter.flush();
            printWriter.close();
            fileWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    public ArrayList<Contact> readFile(){
       ArrayList<Contact>contacts = new ArrayList<>();
        String filePath = "phone_book.txt";
        String name1;
        String phoneNumber;
        try {
            scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("[,\n]");

            while (scanner.hasNext()){
                name1 = scanner.next();
                phoneNumber = scanner.next();
                Contact contact = new Contact(name1, phoneNumber);
                contacts.add(contact);
            }scanner.close();
            return contacts;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }return null;
    }
}
