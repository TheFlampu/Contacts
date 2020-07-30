package contacts.record;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class AbstractRecord {
    Scanner scanner;
    String name;
    String number;
    LocalDateTime creatTime;
    LocalDateTime editTime;

    AbstractRecord(Scanner scanner) {
        this.scanner = scanner;
        creatTime = editTime = LocalDateTime.now();
    }

    public abstract String getShortDescription();
    public abstract String getFields();
    public abstract void editField(String field);
    public abstract String fullInfo();

    public String setName() {
        System.out.print("Enter the name: ");
        return scanner.nextLine();
    }

    public String setNumber() {
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (Pattern.compile("([+]?(\\(\\w+\\)[-\\s]?)(\\w{2,}[-\\s]?)*)|([+]?(\\w+[-\\s])\\(\\w{2,}\\)[-\\s]?(\\w{2,}[-\\s]?)*)|([+]?(\\w)?[-\\s]?(\\w{2,}[-\\s]?)*)").matcher(number).matches()) {
            return number;
        }
        return null;
    }

    public String getNumber() {
        return number;
    }
}
