
import java.util.*;

class FlashCard {
    private final String question;
    private final String answer;
    private int mistakeCount = 0;
    private int correctCount = 0;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void incrementMistakes() {
        mistakeCount++;
    }

    public void incrementCorrect() {
        correctCount++;
    }

    public int getMistakeCount() {
        return mistakeCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }
}
interface CardOrganizer {
    List<FlashCard> organize(List<FlashCard> cards);
}