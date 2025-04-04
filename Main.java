// Main.java
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "cards.txt"; // Default file name
        String order = "random";
        int repetitions = 1;
        boolean invert = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--order":
                    if (i + 1 < args.length) {
                        order = args[++i];
                    } else {
                        System.out.println("Error: Missing value for --order");
                        return;
                    }
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) {
                        try {
                            repetitions = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid number for --repetitions");
                            return;
                        }
                    } else {
                        System.out.println("Error: Missing value for --repetitions");
                        return;
                    }
                    break;
                case "--invertCards":
                    invert = true;
                    break;
                default:
                    System.out.println("Error: Unknown option " + args[i]);
                    return;
            }
        }

        FlashCardSystem system = new FlashCardSystem();
        try {
            system.loadCards(filePath);
            system.start(order, repetitions, invert);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
