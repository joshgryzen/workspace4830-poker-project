import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MonteCarloPoker {

    // Assuming suits are ordered as Diamonds (0), Clubs (1), Hearts (2), Spades (3)
    static int[] suits = { 0, 1, 2, 3 };
    // Ranks from 2 to 14 (where Ace is high and represented as 14)
    static int[] ranks = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

    // Create a deck of cards as Card objects
    static List<Card> deck = new ArrayList<>();
    static {
        for (int suit : suits) {
            for (int rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    // Method to simulate a single poker game
    static int simulate(List<Card> hand, List<Card> table, int players) {
        // Create a copy of the deck
        List<Card> remainingDeck = new ArrayList<>(deck);
        // Remove cards that are already dealt
        remainingDeck.removeAll(hand);
        remainingDeck.removeAll(table);

        // Combine player's hand and community cards
        List<Card> fullHand = new ArrayList<>(hand);
        fullHand.addAll(table);

        // Deal cards to players
        List<List<Card>> playerHands = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            List<Card> playerHand = new ArrayList<>(table); // Include community cards
            for (int j = 0; j < 2; j++) {
                playerHand.add(remainingDeck.remove(0)); // Deal two cards to each player
            }
            playerHands.add(playerHand);
        }

        // Add community cards to the table
        List<Card> fullBoard = new ArrayList<>(table);
        while (fullBoard.size() < 5) {
            fullBoard.add(remainingDeck.remove(0));
        }

        // Evaluate hands and determine winner
        int myHandRank = Poker.valueHand(fullHand.toArray(new Card[0]));

        int resultState = 0; // Assume win until proven otherwise
        for (List<Card> playerHand : playerHands) {
            int opponentHandRank = Poker.valueHand(playerHand.toArray(new Card[0]));

            if (opponentHandRank > myHandRank) {
                resultState = 1; // Lose
                break;
            } else if (opponentHandRank == myHandRank) {
                resultState = 2; // Split
            }
        }

        return resultState;
    }

    // Method to perform Monte Carlo simulation and return win ratio for a single
    // player
    static double monteCarloWinRatio(List<Card> hand, List<Card> table, int players, int trials) {
        int wins = 0;
        for (int i = 0; i < trials; i++) {
            int outcome = simulate(hand, table, players);
            if (outcome == 0) {
                wins++;
            }
        }
        return (double) wins / trials * 100;
    }

    public static void main(String[] args) {
        // Example usage:
        /* From HAND */
        List<Card> hand = Arrays.asList(CardAcronyms.cardsInput1);

        // Using the cards from input2
        List<Card> table = Arrays.asList(CardAcronyms.cardsInput2);
        /* From TURN */
        int players = 4; // Example number of players
        int trials = 100000; // Example number of Monte Carlo trials

        double winRatio = monteCarloWinRatio(hand, table, players, trials);
        System.out.println("Win Ratio: " + winRatio + "%");
    }
}
