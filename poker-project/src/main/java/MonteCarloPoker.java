import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;

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
    static int simulate(List<Card> Hand, List<Card> table, int players) {
        // Create a copy of the deck
        List<Card> remainingDeck = new ArrayList<>(deck);
        // Remove cards that are already dealt
        for (Card card : Hand) {
            remainingDeck.remove(card);
        }

        for (Card card : table) {
            remainingDeck.remove(card);
        }
        //Complete table. 
        while (table.size() < 5) {
            table.add(remainingDeck.remove(0));
        }
        // Randomization would occur at this point, just before dealing cards to players.
        Collections.shuffle(remainingDeck); // Shuffle the deck
        // Combine player's hand and community cards
        List<Card> fullHand = new ArrayList<>(Hand);
        fullHand.addAll(table);
        // Deal cards to players
        System.out.println("Number of players: " + players);
        List<List<Card>> playerHands = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            List<Card> playerHand = new ArrayList<>(table);
            while (playerHand.size() < 7) {
                playerHand.add(remainingDeck.remove(0));
            }
            playerHands.add(playerHand);
            // Print the contents of the player's hand
            //System.out.println("Player " + (i + 1) + "'s hand:");
            List<Object> bestResult = evaluateBestHand(playerHand);
            List<Card> playerBestHand = (List<Card>) bestResult.get(0);
            int playerBestRank = (int) bestResult.get(1);
            for (Card card : playerBestHand) {
                System.out.println("Rank: " + card.rank + " Suit: " + card.suit);
            }
         // Print the rank of the player's hand
            System.out.println("Player " + (i + 1) + "'s hand rank: " + playerBestRank);
        }
        
        // Add community cards to the table
        //No idea what this is used for, but seems like we could use it for randomizing community cards
        //if we wanted to simulate the game
        //List<Card> fullBoard = new ArrayList<>(table);
        //while (fullBoard.size() < 5) {
           // fullBoard.add(remainingDeck.remove(0));
       // }
//	     // Add community cards to the table
//	     // Print out all the cards in the fullBoard for debugging purposes
//	     System.out.println("Community cards:");
//	     for (Card card : fullBoard) {
//	         System.out.println(card);
//	     }

        
        // Evaluate hands and determine winner
        //int myHandRank = Poker.valueHand(fullHand.toArray(new Card[0]));
        List<Object> myResult = evaluateBestHand(fullHand);
        List<Card> myBestHand = (List<Card>) myResult.get(0);
        int myBestRank = (int) myResult.get(1);
        
        List<Card> winningHand = fullHand; // Default to my hand
        int resultState = 0; // Assume win until proven otherwise
        for (List<Card> playerHand : playerHands) {
            int opponentHandRank = (int) evaluateBestHand(playerHand).get(1);
            if (opponentHandRank > myBestRank) {
                resultState = 1; // Lose
                winningHand = playerHand;
                break;
            } else if (opponentHandRank == myBestRank) {
                resultState = 2; // Split
            }
        }
        
        System.out.println("Winning hand:");
        for (Card card : winningHand) {
            System.out.println("Rank: " + card.rank + " Suit: " + card.suit);
        }
        return resultState;
    }

    // Method to perform Monte Carlo simulation and return win ratio for a single
    // Each trial would need to be randomized for the cards dealt to players
    // player
    static double monteCarloWinRatio(List<Card> Hand, List<Card> table, int players, int trials) {
        int wins = 0;
        for (int i = 0; i < trials; i++) {
            int outcome = simulate(Hand, table, players);
            if (outcome == 0) {
                wins++;
            }
        }
        
        return (double) wins / trials * 100;
    }
    
    static List<Object> evaluateBestHand(List<Card> fullHand) {
        List<Card> bestHand = new ArrayList<>(fullHand.subList(0, 5));
        int bestRank = Poker.valueHand(bestHand.toArray(new Card[0]));

        // Generate all combinations of 5 cards from 7-card hand
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 5; k++) {
                    for (int l = k + 1; l < 6; l++) {
                        for (int m = l + 1; m < 7; m++) {
                            List<Card> currentHand = new ArrayList<>(Arrays.asList(fullHand.get(i), fullHand.get(j),
                                    fullHand.get(k), fullHand.get(l), fullHand.get(m)));

                            int currentRank = Poker.valueHand(currentHand.toArray(new Card[0]));
                            if (currentRank > bestRank) {
                                bestHand = currentHand;
                                bestRank = currentRank;
                            }
                        }
                    }
                }
            }
        }

        List<Object> result = new ArrayList<>();
        result.add(bestHand);
        result.add(bestRank);
        return result;
    }
    public static void main(String[] args) {
    // // Example usage:
    // /* From HAND */
    List<Card> hand = new ArrayList<>();
    hand.add(new Card(10,0));
    hand.add(new Card(10,1));
    // // Using the cards from input2
    List<Card> table = new ArrayList<>();
    table.add(new Card(14,2));
    table.add(new Card(14,0));
    table.add(new Card(14,1));
    // /* From TURN */
    int trials = 10; // Example number of Monte Carlo trials
    
    // // Get the number of players from the servlet
    int intValuePlayers = 3;
    //
    double winRatio = monteCarloWinRatio(hand, table, intValuePlayers, trials);
    System.out.println("Win Ratio: " + winRatio + "%");
    
}}
