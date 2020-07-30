package contacts.application;

import contacts.phonebook.PhoneBook;
import contacts.record.AbstractRecord;
import contacts.record.RecordFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class Application {
    Scanner scanner;
    PhoneBook phoneBook;
    RecordFactory recordFactory;
    String filename;

    public Application(String filename) {
        scanner = new Scanner(System.in);
        this.filename = filename;
        phoneBook = new PhoneBook();
        recordFactory = new RecordFactory(scanner);
    }
    public void run() {
            action();
    }

    public void action() {
        System.out.print("[menu] Enter action (add, list, search, count, exit): ");
        String action = scanner.nextLine();

        switch (action) {
            case "add":
                addRecord();
                break;
            case "list":
                listRecords(phoneBook.getRecords());
                System.out.println("[list] Enter action ([number], back):");
                action = scanner.nextLine();
                try {
                    int id = Integer.parseInt(action) - 1;
                    edit(id);
                } catch (Exception ignored) {
                }
                break;
            case "search":
                search();
                break;
            case "count":
                count();
                break;
            case "exit":
                try {
                    serialize(phoneBook.getRecords(), "phoneBook.db");
                } catch (Exception ignored) {

                }
                System.exit(0);
        }
        action();
    }

    public void addRecord() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        AbstractRecord newRecord = recordFactory.createNewRecord(type);
        phoneBook.addRecord(newRecord);
        System.out.println("The record added." + "\n");
    }

    public void listRecords(List<AbstractRecord> records) {
        int id = 1;
        for (AbstractRecord record : records) {
            System.out.println(id + ". " + record.getShortDescription());
        }
        System.out.println();
    }

    public void search() {
            System.out.print("Enter the search query: ");
            String query = scanner.nextLine();
            List<AbstractRecord> searchRecords = phoneBook.searchRecords(query);
            System.out.println("Found " + searchRecords.size() + " results:");
            listRecords(searchRecords);
            System.out.print("[search] Enter action ([number], back, again): ");
            String action = scanner.nextLine();
            try {
                int id = Integer.parseInt(action) - 1;
                id = phoneBook.getRecords().indexOf(searchRecords.get(id));
                edit(id);
            } catch (Exception ignored) {
                switch (action) {
                    case "back": return;
                    case "again": search();
                }
            }
    }

    public void edit(int id) {
        AbstractRecord record = phoneBook.getRecords().get(id);
        while (true) {
            System.out.println(record.fullInfo());
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit":
                    System.out.print("Select a field (" + record.getFields() + "): ");
                    record.editField(scanner.nextLine());
                    System.out.println("Saved");
                    break;
                case "delete":
                    phoneBook.removeRecord(id);
                    return;
                case "menu":
                    return;
            }
        }
    }

    public void count() {
        System.out.println("The Phone Book has " + phoneBook.getRecords().size() + " records" + "\n");
    }


    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }
}
