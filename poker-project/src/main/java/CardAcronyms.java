import java.util.HashMap;
import java.util.Map;

public class CardAcronyms {
    // Map to store the string acronyms of suits
    static Map<Character, Integer> suitIndexes = new HashMap<>();
    // Map to store the string acronyms of ranks
    static Map<Character, Integer> rankIndexes = new HashMap<>();

    static {
        // Populate suit indexes
        suitIndexes.put('D', 0); // Diamonds
        suitIndexes.put('C', 1); // Clubs
        suitIndexes.put('H', 2); // Hearts
        suitIndexes.put('S', 3); // Spades

        // Populate rank indexes
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
    public static String input1 = "KS+JH+2D+3C+5S"; // Example input with 5 cards separated by '+'
    public static String input2 = "KSJH"; // Example input with 2 cards without any separator

    public static void main(String[] args) {

        parseInput(input1);
        parseInput(input2);
    }

    // Method to parse input and process cards
    static void parseInput(String input) {
        input = input.toUpperCase();

        String[] cards;
        if (input.contains("+")) {
            cards = input.split("\\+");
        } else {
            cards = new String[] { input.substring(0, 2), input.substring(2) };
        }

        for (String card : cards) {
            int[] cardIndexes = getCardIndexes(card);
            System.out.println("Indexes for " + card + ": Suit - " + cardIndexes[0] + ", Rank - " + cardIndexes[1]);
        }
    }

    // Method to get the suit and rank indexes of a card
    static int[] getCardIndexes(String card) {
        int[] indexes = new int[2];
        indexes[0] = suitIndexes.getOrDefault(card.charAt(1), -1);
        indexes[1] = rankIndexes.getOrDefault(card.charAt(0), -1);
        return indexes;
    }
}
