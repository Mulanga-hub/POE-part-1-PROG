import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> discardedMessages = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to QuickChat");

        do {

            System.out.println("\n1. Send");
            System.out.println("2. Show Sent");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Longest Message");
            System.out.println("6. Report");
            System.out.println("7. Quit");

            choice = readInt(input);

            switch (choice) {

                case 1:

                    System.out.println("Recipient:");
                    String recipient = input.nextLine();

                    System.out.println("Message:");
                    String text = input.nextLine();

                    String id = String.valueOf(System.currentTimeMillis());
                    String hash = id.substring(0,2) + ":" + text.split(" ")[0].toUpperCase();

                    sentMessages.add(text);
                    messageIDs.add(id);
                    messageHashes.add(hash);

                    System.out.println("Sent");
                    break;

                case 2:
                    for (String m : sentMessages) {
                        System.out.println(m);
                    }
                    break;

                case 3:

                    System.out.println("Enter ID:");
                    String search = input.nextLine();

                    boolean found = false;

                    for (int i = 0; i < messageIDs.size(); i++) {
                        if (messageIDs.get(i).equals(search)) {
                            System.out.println(sentMessages.get(i));
                            found = true;
                        }
                    }

                    if (!found) System.out.println("Not found");

                    break;

                case 4:

                    System.out.println("Enter Hash:");
                    String del = input.nextLine();

                    for (int i = 0; i < messageHashes.size(); i++) {
                        if (messageHashes.get(i).equals(del)) {

                            sentMessages.remove(i);
                            messageIDs.remove(i);
                            messageHashes.remove(i);

                            System.out.println("Deleted");
                            break;
                        }
                    }
                    break;

                case 5:

                    String longest = "";

                    for (String m : sentMessages) {
                        if (m.length() > longest.length()) {
                            longest = m;
                        }
                    }

                    System.out.println("Longest message: " + longest);
                    break;

                case 6:

                    System.out.println("REPORT:");

                    for (int i = 0; i < sentMessages.size(); i++) {
                        System.out.println("------------------");
                        System.out.println("ID: " + messageIDs.get(i));
                        System.out.println("HASH: " + messageHashes.get(i));
                        System.out.println("MESSAGE: " + sentMessages.get(i));
                    }

                    break;

                case 7:
                    System.out.println("Bye");
                    break;
            }

        } while (choice != 7);
    }

    public static int readInt(Scanner input) {
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Enter number:");
        }
        int v = input.nextInt();
        input.nextLine();
        return v;
    }
}