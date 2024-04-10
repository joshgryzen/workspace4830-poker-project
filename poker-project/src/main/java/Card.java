public class Card {
    private int rank;
    private int suit;

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int rank() {
        return rank;
    }

    public int suit() {
        return suit;
    }
}