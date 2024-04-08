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

        // Deal cards to players
        List<List<Card>> playerHands = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            List<Card> playerHand = new ArrayList<>(hand);
            playerHand.add(remainingDeck.remove(0));
            playerHand.add(remainingDeck.remove(0));
            playerHands.add(playerHand);
        }

        // Add community cards to the table
        List<Card> fullBoard = new ArrayList<>(table);
        while (fullBoard.size() < 5) {
            fullBoard.add(remainingDeck.remove(0));
        }

        // Evaluate hands and determine winner
        String myHand = String.join("", hand);
        String myBoard = String.join("", fullBoard);
        int myHandRank = Poker.valueHand(myHand + myBoard);

        int resultState = 0; // Assume win until proven otherwise
        for (List<String> playerHand : playerHands) {
            String opponentHand = String.join("", playerHand);
            String opponentBoard = String.join("", fullBoard);
            int opponentHandRank = Poker.valueHand(opponentHand + opponentBoard);

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
        // Example usage
        List<Card> myHand = Arrays.asList("As", "Ks");
        List<Card> communityCards = Arrays.asList("Qs", "Js", "Ts");
        int numberOfPlayers = 4;
        int numberOfTrials = 10000;

        double winRatio = monteCarloWinRatio(myHand, communityCards, numberOfPlayers, numberOfTrials);
        System.out.println("Win ratio: " + winRatio + "%");
    }
}
