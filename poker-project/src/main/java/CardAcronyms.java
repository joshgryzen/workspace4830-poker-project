import java.util.HashMap;
import java.util.Map;

public class CardAcronyms {
    static Map<Character, Integer> suitIndexes = new HashMap<>();
    static Map<Character, Integer> rankIndexes = new HashMap<>();
    public static String input1;
    public static String input2;
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

    public static Card[] cardsInput1;
    public static Card[] cardsInput2;

    // Method to set the values of input1 and input2 and parse the inputs
    public static void setInputValues(String newInput1, String newInput2) {
        input1 = newInput1;
        input2 = newInput2;
        // Parse the inputs and assign to cardsInput1 and cardsInput2
        cardsInput1 = parseInput(input1);
        cardsInput2 = parseInput(input2);
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
        // Check if the cardString is null or empty
        if (cardString == null || cardString.isEmpty()) {
            System.out.println("Invalid card: " + cardString);
            return null;
        }

        // Check if the cardString has at least two characters
        if (cardString.length() < 2) {
            System.out.println("Invalid card: " + cardString);
            return null;
        }
        if (rank != -1 && suit != -1) {
            return new Card(rank, suit);
        } else {
            // Invalid card string
            System.out.println("Invalid card: " + cardString);
            return null;
        }
    }


}
