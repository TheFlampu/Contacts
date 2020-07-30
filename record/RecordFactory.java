package contacts.record;

import java.util.Scanner;

public class RecordFactory {
    Scanner scanner;

    public RecordFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    public AbstractRecord createNewRecord (String type) {
        switch (type) {
            case "person": return new PersonRecord(scanner);
            case "organization": return new OrganizationRecord(scanner);
            default: return null;
        }
    }
}
