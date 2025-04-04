import java.io.*;
import java.util.*;

class FlashCardSystem {
    private final List<FlashCard> cards = new ArrayList<>();
    private final Map<String, Integer> stats = new HashMap<>();

    public void loadCards(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    cards.add(new FlashCard(parts[0], parts[1]));
                }
            }
        }
    }

    public void start(String order, int repetitions, boolean invert) {
        if (order.equals("recent-mistakes-first")) {
            new RecentMistakesFirstSorter().organize(cards);
        } else if (order.equals("random")) {
            Collections.shuffle(cards);
        }

        Scanner scanner = new Scanner(System.in);
        for (FlashCard card : cards) {
            int correctCount = 0;
            while (correctCount < repetitions) {
                System.out.println((invert ? card.getAnswer() : card.getQuestion()) + "?");
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase(invert ? card.getQuestion() : card.getAnswer())) {
                    correctCount++;
                    card.incrementCorrect();
                    stats.put("CORRECT", stats.getOrDefault("CORRECT", 0) + 1);
                } else {
                    card.incrementMistakes();
                    stats.put("REPEAT", stats.getOrDefault("REPEAT", 0) + 1);
                }
            }
        }
        scanner.close();
    }
}
