package contacts.phonebook;

import contacts.record.AbstractRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 1L;
    List<AbstractRecord> records;

    public PhoneBook() {
            System.out.println("open phoneBook.db" + "\n");
            records = new ArrayList<>();
    }

    public void addRecord(AbstractRecord record) {
        records.add(record);
    }

    public void removeRecord(int index) {
        records.remove(index);
    }

    public List<AbstractRecord> getRecords() {
        return records;
    }

    public List<AbstractRecord> searchRecords(String query) {
        List<AbstractRecord> searchRecords = new ArrayList<>();
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        for (AbstractRecord record : records) {
            if (pattern.matcher(record.getShortDescription()).find() || record.getNumber().contains(query)) {
                searchRecords.add(record);
            }
        }
        return searchRecords;
    }
}
