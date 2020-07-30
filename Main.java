package contacts;

import contacts.application.Application;

public class Main {
    public static void main(String[] args) {
        String filename;
        try {
            filename = args[0];
        } catch (Exception ignored) {
            filename = null;
        }
        new Application(filename).run();
    }
}