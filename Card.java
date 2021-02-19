/**
 * Developer: Dennis Dao
 * Date: Jan 17, 2021
 * version 1.0
 */

// Imports
import java.util.Random;

/**
 * Make a card class
 */
public class Card{
    // Instance variables
    private int value;
    private int temp;
    private String name;
    private String suit;
    private Random random = new Random();

    /**
     * Default constructor
     * @param value Value of the card
     */
    public Card(int value){
        this.value = value;

        // Based on a random value, assign a suit
        temp = random.nextInt(4);
        if(temp == 0){
            this.suit = "Hearts";
        }
        else if(temp == 1){
            this.suit = "Spades";
        }
        else if(temp == 2){
            this.suit = "Clubs";
        }
        else if(temp == 3){
            this.suit = "Diamonds";
        }

        // Based on the value, assign the card a name
        if(value == 1 || value == 11){
            this.name = "Ace";
        }
        else if(value == 2){
            this.name = "2";
        }
        else if(value == 3){
            this.name = "3";
        }
        else if(value == 4){
            this.name = "4";
        }
        else if(value == 5){
            this.name = "5";
        }
        else if(value == 6){
            this.name = "6";
        }
        else if(value == 7){
            this.name = "7";
        }
        else if(value == 8){
            this.name = "8";
        }
        else if(value == 9){
            this.name = "9";
        }
        else if(value == 10){
            // Assign the card a specific name, still a value of 10
            temp = random.nextInt(3);
            if(temp == 0){
                this.name = "King";
            }
            else if(temp == 1){
                this.name = "Queen";
            }
            else if(temp == 2){
                this.name = "Jack";
            }
        } 
    }

    // Getters
    /**
     * Get the value of the card
     * @return Value of the card
     */
    public int getValue(){
       return this.value; 
    }

    /**
     * Get the name of the card
     * @return Name of the card
     */
    public String getName(){
        return this.name; 
    }

    /**
     * Get the suit of the card
     * @return Suit of the card
     */
    public String getSuit(){
        return this.suit;
    }
    
}
