import java.util.*;

class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public List<FlashCard> organize(List<FlashCard> cards) {
        cards.sort(Comparator.comparingInt(FlashCard::getMistakeCount).reversed());
        return cards;
    }
}