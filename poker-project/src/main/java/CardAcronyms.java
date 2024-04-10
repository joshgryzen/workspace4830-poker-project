import java.util.HashMap;
import java.util.Map;

public class CardAcronyms {
    static Map<Character, Integer> suitIndexes = new HashMap<>();
    static Map<Character, Integer> rankIndexes = new HashMap<>();

    static {
        suitIndexes.put('D', 0); // Diamonds
        suitIndexes.put('C', 1); // Clubs
        suitIndexes.put('H', 2); // Hearts
        suitIndexes.put('S', 3); // Spades

        rankIndexes.put('2', 2);
        rankIndexes.put('3', 3);
        rankIndexes.put('4', 4);
        rankIndexes.put('5', 5);
        rankIndexes.put('6', 6);
        rankIndexes.put('7', 7);
        rankIndexes.put('8', 8);
        rankIndexes.put('9', 9);
        rankIndexes.put('T', 10); // Ten
        rankIndexes.put('J', 11);
        rankIndexes.put('Q', 12);
        rankIndexes.put('K', 13);
        rankIndexes.put('A', 14);
    }

    public static String input2 = "TS+JS+QS"; // Example input with 5 cards separated by '+'
    public static String input1 = "KS+AS"; // Example input with 2 cards without any separator
    public static Card[] cardsInput1 = parseInput(input1);
    public static Card[] cardsInput2 = parseInput(input2);

    public static void main(String[] args) {

        // Print out the card information
        System.out.println("Cards from input2:");
        for (Card card : cardsInput2) {
            System.out.println("Rank: " + card.rank() + ", Suit: " + card.suit());
        }

        System.out.println("\nCards from input1:");
        for (Card card : cardsInput1) {
            System.out.println("Rank: " + card.rank() + ", Suit: " + card.suit());
        }
    }

    static Card[] parseInput(String input) {
        input = input.toUpperCase();
        String[] cardStrings = input.split("\\+");
        Card[] cards = new Card[cardStrings.length];

        for (int i = 0; i < cardStrings.length; i++) {
            cards[i] = createCard(cardStrings[i]);
        }

        return cards;
    }

    static Card createCard(String cardString) {
        char rankChar = cardString.charAt(0);
        char suitChar = cardString.charAt(1);

        int rank = rankIndexes.getOrDefault(rankChar, -1);
        int suit = suitIndexes.getOrDefault(suitChar, -1);

        if (rank != -1 && suit != -1) {
            return new Card(rank, suit);
        } else {
            // Invalid card string
            System.out.println("Invalid card: " + cardString);
            return null;
        }
    }
}
