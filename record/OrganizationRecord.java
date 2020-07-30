package contacts.record;

import java.time.LocalDateTime;
import java.util.Scanner;

public class OrganizationRecord extends  AbstractRecord{
    String address;

    OrganizationRecord(Scanner scanner) {
        super(scanner);
        name = setName();
        address = setAddress();
        number = setNumber();
    }

    @Override
    public String getShortDescription() {
        return name;
    }

    @Override
    public String getFields() {
        return "name, address, number";
    }

    @Override
    public void editField(String field) {
        switch (field) {
            case "name":
                name = setName();
                break;
            case "address":
                address = setAddress();
                break;
            case "number":
                number = setNumber();
                break;
        }
        editTime = LocalDateTime.now();
    }

    @Override
    public String fullInfo() {
        return "Organization name: " + name + '\n' +
                "Number: " + (number == null ? "[no data]" : number) + '\n' +
                "Address: " + address + '\n' +
                "Time created: " + creatTime + '\n' +
                "Time last edit: " + editTime + '\n';
    }

    public String setAddress() {
        System.out.print("Enter the address: ");
        return scanner.nextLine();
    }
}
