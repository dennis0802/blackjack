/**
 * Developer: Dennis Dao
 * Date: Jan 17, 2021
 * version 1.0
 */

// Imports
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Class to run a game of Blackjack
 */
public class Blackjack{
    /**
     * Function to sum up the cards
     * @param deck A player's deck
     * @return The minimum sum of the player's deck
     */
    static int sumCards(Set<Card> deck){
        // Two totals to account for the fact Aces can be 1 or 11
        int totalA = 0, totalB = 0;
        for(Card card : deck){
            if(card.getName().equals("Ace")){
                totalA += 1;
                totalB += 11;
            }
            else{
                totalA += card.getValue();
                totalB += card.getValue();
            }
        }
        // If a total is equal to 21, return it immediately
        if(totalA == 21){
            return totalA;
        }
        else if(totalB == 21){
            return totalB;
        }
        // Take the lower of the two totals otherwise
        else{
            return Math.min(totalA, totalB); 
        }
    }

    /**
     * Function to handle playing again
     * @param input The user's input
     * @param deckA The first deck
     * @param deckB The second deck (Computer Dealer)
     * @param cards The card generator
     * @return A truth value based on whether the player wants to play again
     */
    static boolean playAgain(String input, Set<Card> deckA, Set<Card> deckB, Random cards){
        // Helper variable
        int value;

        // Reset the decks if playing again
        if(input.toUpperCase().equals("Y")){
            deckA.clear();
            deckB.clear();
            for(int i = 0; i < 2; i++){
                value = cards.nextInt(11) + 1;
                Card card = new Card(value);
                deckA.add(card);
            }
            for(int i = 0; i < 2; i++){
                value = cards.nextInt(11) + 1;
                Card card = new Card(value);
                deckB.add(card);
            }
            return false;
        }
        // Leave the game
        else if(input.toUpperCase().equals("N")){
            return true;
        }
        return false;        
    }
    /**
     * Run the class
     * @param args not used
     */
    public static void main(String args[]){
        // Variable definitions
        Random cards = new Random();
        Boolean exit = false;
        Set<Card> yourCards = new HashSet<>(), rivalCards = new HashSet<>();;
        Scanner in = new Scanner(System.in);
        String input;
        int wins = 0;
        int losses = 0;

        // Set up the decks
        for(int i = 0; i < 2; i++){
            int value = cards.nextInt(11) + 1;
            Card card = new Card(value);
            yourCards.add(card);
        }
        for(int i = 0; i < 2; i++){
            int value = cards.nextInt(11) + 1;
            Card card = new Card(value);
            rivalCards.add(card);
        }

        // Welcome message
        System.out.println("-------------------------------------------------");
        System.out.println("Welcome to Command Line Blackjack!");
        System.out.println("Developer: Dennis Dao");
        System.out.println("Date: Jan 17, 2021");
        System.out.println("version 1.0");
        System.out.println("-------------------------------------------------");
        System.out.println("Try to get a total value of 21 without going over.");
        System.out.println("Assume multiple decks of cards have been mixed in.");
        System.out.println("The cards have no abilities, this is just a standard game of not going over 21.");

        while(exit == false){
            // Update the user with current status
            System.out.println("-------------------------------------------------");
            System.out.println("You have the following cards: ");
            for(Card card : yourCards){
                System.out.println(card.getName() + " of " + card.getSuit());
            }
            System.out.println("-------------------------------------------------");
            System.out.println("\nHit (H) or stay (S)?");
            input = in.next();

            // If user "hits"
            if(input.toUpperCase().equals("H")){
                int value = cards.nextInt(11) + 1;
                Card draw = new Card(value);
                yourCards.add(draw);
                System.out.println("You have drawn a(n) " + draw.getName() + " of " + draw.getSuit() + "\n");
                // Continue if cards equal 21
                if(sumCards(yourCards) == 21){
                    continue;
                }
                // Game over if over 21
                else if(sumCards(yourCards) > 21){
                    System.out.println("You have gone over 21! You have lost.");
                    losses++;
                    System.out.println("Wins: " + wins);
                    System.out.println("Losses: " + losses);
                    // Repeatedly ask the user for input until valid
                    while(input.toUpperCase().equals("Y") == false && input.toUpperCase().equals("N") == false){
                        System.out.println("Play again? (Y)/(N)");
                        input = in.next();
                    }
                    exit = playAgain(input, yourCards, rivalCards, cards);
                }
            }
            // If user "stays"
            else if(input.toUpperCase().equals("S")){
                while(sumCards(rivalCards) < 21){
                    int value = cards.nextInt(11) + 1;
                    Card draw = new Card(value);
                    rivalCards.add(draw);
                }
                System.out.println("-------------------------------------------------");
                System.out.println("The dealer has drawn the following: ");
                for(Card card : rivalCards){
                    System.out.println(card.getName() + " of " + card.getSuit());
                }
                System.out.println("-------------------------------------------------");

                // You and the dealer have 21
                if(sumCards(rivalCards) == 21 && sumCards(yourCards) == 21){
                    System.out.println("Draw!");
                    System.out.println("Wins: " + wins);
                    System.out.println("Losses: " + losses);
                    // Repeatedly ask the user for input until valid
                    while(input.toUpperCase().equals("Y") == false && input.toUpperCase().equals("N") == false){
                        System.out.println("Play again? (Y)/(N)");
                        input = in.next();
                    }
                    exit = playAgain(input, yourCards, rivalCards, cards);
                }
                // You have less than 21 but the dealer has 21
                else if(sumCards(rivalCards) == 21 && sumCards(yourCards) < 21){
                    System.out.println("You lose!");
                    losses++;
                    System.out.println("Wins: " + wins);
                    System.out.println("Losses: " + losses);
                    // Repeatedly ask the user for input until valid
                    while(input.toUpperCase().equals("Y") == false && input.toUpperCase().equals("N") == false){
                        System.out.println("Play again? (Y)/(N)");
                        input = in.next();
                    }
                    exit = playAgain(input, yourCards, rivalCards, cards);
                }
                // The dealer went over
                else{
                    System.out.println("You win!");
                    wins++;
                    System.out.println("Wins: " + wins);
                    System.out.println("Losses: " + losses);
                    // Repeatedly ask the user for input until valid
                    while(input.toUpperCase().equals("Y") == false && input.toUpperCase().equals("N") == false){
                        System.out.println("Play again? (Y)/(N)");
                        input = in.next();
                    }
                    exit = playAgain(input, yourCards, rivalCards, cards);
                }
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Hope to see you soon!");
        in.close();
    }
}