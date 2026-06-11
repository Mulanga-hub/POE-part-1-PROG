import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static int messageCounter = 0;

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

            System.out.println("\nChoose an option:");
            System.out.println("1. Send Message");
            System.out.println("2. Show Messages");
            System.out.println("3. Search Messages");
            System.out.println("4. Delete Message");
            System.out.println("5. Quit");

            choice = readInt(input);

            switch (choice) {

                case 1:

                    System.out.println("Enter recipient number:");
                    String recipient = input.nextLine();

                    System.out.println("Enter message (max 250 chars):");
                    String text = input.nextLine();

                    messageCounter++;

                    Message msg = new Message("", recipient, text, messageCounter);

                    if (!msg.checkMessageLength()) {
                        System.out.println("Message exceeds 250 characters");
                        break;
                    }

                    if (!msg.checkRecipientCell()) {
                        System.out.println("Invalid recipient number format");
                        break;
                    }

                    String id = msg.getMessageID();
                    String hash = msg.createMessageHash();

                    System.out.println("\nMessage Hash: " + hash);

                    System.out.println("\nChoose action:");
                    System.out.println("1. Send");
                    System.out.println("2. Discard");
                    System.out.println("3. Store");

                    int action = readInt(input);

                    if (action == 1) {
                        sentMessages.add(text);
                    } else if (action == 2) {
                        discardedMessages.add(text);
                    } else if (action == 3) {
                        storedMessages.add(text);
                        storeMessageToJSON(id, hash, recipient, text);
                    }

                    messageIDs.add(id);
                    messageHashes.add(hash);

                    break;

                case 2:
                    for (String m : sentMessages) {
                        System.out.println(m);
                    }
                    break;

                case 3:

                    System.out.println("Enter Message ID:");
                    String searchID = input.nextLine();

                    boolean found = false;

                    for (int i = 0; i < messageIDs.size(); i++) {
                        if (messageIDs.get(i).equals(searchID)) {
                            System.out.println(sentMessages.get(i));
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Not found");
                    }

                    break;

                case 4:

                    System.out.println("Enter Message Hash to delete:");
                    String delHash = input.nextLine();

                    boolean deleted = false;

                    for (int i = 0; i < messageHashes.size(); i++) {
                        if (messageHashes.get(i).equals(delHash)) {

                            sentMessages.remove(i);
                            messageIDs.remove(i);
                            messageHashes.remove(i);

                            System.out.println("Message deleted successfully");
                            deleted = true;
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Hash not found");
                    }

                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

            }

        } while (choice != 5);
    }

    public static int readInt(Scanner input) {
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Enter number:");
        }
        int val = input.nextInt();
        input.nextLine();
        return val;
    }

    public static void storeMessageToJSON(String id, String hash, String recipient, String text) {
        try {
            FileWriter writer = new FileWriter("messages.json", true);
            writer.write("{\"id\":\"" + id + "\",\"hash\":\"" + hash + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + text + "\"}\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}