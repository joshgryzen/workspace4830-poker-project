import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
    private int playerId;
    private List<Card> cards;

    public PlayerHand(int playerId) {
        this.playerId = playerId;
        this.cards = new ArrayList<>();
    }

    public int getPlayerId() {
        return playerId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
