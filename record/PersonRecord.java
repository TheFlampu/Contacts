package contacts.record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PersonRecord extends AbstractRecord {
    String surname;
    LocalDate birthDate;
    String gender;

    PersonRecord(Scanner scanner) {
        super(scanner);
        name = setName();
        surname = setSurname();
        birthDate = setBirthDay();
        gender = setGender();
        number = setNumber();
    }

    @Override
    public String getShortDescription() {
        return name + " " + surname;
    }

    @Override
    public String getFields() {
        return "name, surname, birth, gender, number";
    }

    @Override
    public void editField(String field) {
        switch (field) {
            case "name":
                name = setName();
                break;
            case "surname":
                surname = setSurname();
                break;
            case "birth":
                birthDate = setBirthDay();
                break;
            case "gender":
                gender = setGender();
                break;
            case "number":
                number = setNumber();
                break;
        }
        editTime = LocalDateTime.now();
    }

    @Override
    public String fullInfo() {
        return "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "Birth date: " + (birthDate == null ? "[no data]" : birthDate) + '\n' +
                "Gender: " + (gender == null ? "[no data]" : gender) + '\n' +
                "Number: " + (number == null ? "[no data]" : number) + '\n' +
                "Time created: " + creatTime.withSecond(0).withNano(0) + '\n' +
                "Time last edit: " + editTime.withSecond(0).withNano(0) + '\n';
    }

    public String setSurname() {
        System.out.print("Enter the surname: ");
        return scanner.nextLine();
    }

    public LocalDate setBirthDay() {
        System.out.print("Enter the birth day: ");
        try {
            return LocalDate.parse(scanner.nextLine());
        } catch (Exception ignored) {
            System.out.println("Bad birth day!");
            return null;
        }
    }

    public String setGender() {
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if ("M".equals(gender) || "F".equals(gender)) {
            return gender;
        }
        System.out.println("Bad gender!");
        return null;
    }
}
