import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static int messageCounter = 0;

    // ===== PART 3 REQUIRED ARRAYS =====
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
            System.out.println("3. Quit");

            choice = readInt(input);

            switch (choice) {

                case 1:

                    System.out.println("Enter recipient number:");
                    String recipient = input.nextLine();

                    System.out.println("Enter message (max 250 chars):");
                    String text = input.nextLine();

                    messageCounter++;
                    int messageNumber = messageCounter;

                    Message msg = new Message("", recipient, text, messageNumber);

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

                    switch (action) {

                        case 1:
                            System.out.println("Message successfully sent.");

                            sentMessages.add(text);
                            messageIDs.add(id);
                            messageHashes.add(hash);
                            break;

                        case 2:
                            System.out.println("Message discarded.");

                            discardedMessages.add(text);
                            messageIDs.add(id);
                            messageHashes.add(hash);
                            break;

                        case 3:
                            System.out.println("Message successfully stored.");

                            storedMessages.add(text);
                            messageIDs.add(id);
                            messageHashes.add(hash);

                            storeMessageToJSON(id, hash, recipient, text);
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }

                    break;

                case 2:

                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages sent yet.");
                    } else {
                        System.out.println("Recently sent messages:");
                        for (String m : sentMessages) {
                            System.out.println(m);
                        }
                    }

                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option");
            }

        } while (choice != 3);

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

            System.out.println("Message stored in JSON file.");

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}