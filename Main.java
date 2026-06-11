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
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Search Messages");
            System.out.println("4. Quit");

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

                    System.out.println("\nChoose an action:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Discard Message");
                    System.out.println("3. Store Message");

                    int action = readInt(input);

                    if (action == 1) {

                        sentMessages.add(text);
                        messageIDs.add(id);
                        messageHashes.add(hash);
                        System.out.println("Message successfully sent.");

                    } else if (action == 2) {

                        discardedMessages.add(text);
                        messageIDs.add(id);
                        messageHashes.add(hash);
                        System.out.println("Message discarded.");

                    } else if (action == 3) {

                        storedMessages.add(text);
                        messageIDs.add(id);
                        messageHashes.add(hash);

                        storeMessageToJSON(id, hash, recipient, text);
                        System.out.println("Message successfully stored.");
                    }

                    break;

                case 2:

                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages sent yet.");
                    } else {
                        for (String m : sentMessages) {
                            System.out.println(m);
                        }
                    }

                    break;

                case 3:

                    System.out.println("Enter Message ID:");
                    String searchID = input.nextLine();

                    boolean found = false;

                    for (int i = 0; i < messageIDs.size(); i++) {
                        if (messageIDs.get(i).equals(searchID)) {
                            System.out.println("Message found: " + sentMessages.get(i));
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Message ID not found.");
                    }

                    break;

                case 4:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option");
            }

        } while (choice != 4);
    }

    public static int readInt(Scanner input) {
        while (true) {
            if (input.hasNextInt()) {
                int value = input.nextInt();
                input.nextLine();
                return value;
            }
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
        }
    }

    public static void storeMessageToJSON(String id, String hash, String recipient, String text) {

        try {

            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("\"id\": \"" + id + "\",\n");
            writer.write("\"hash\": \"" + hash + "\",\n");
            writer.write("\"recipient\": \"" + recipient + "\",\n");
            writer.write("\"message\": \"" + text + "\"\n");
            writer.write("}\n\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}