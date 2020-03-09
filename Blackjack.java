
import java.util.Scanner;

public class Blackjack{
    public static void main(String[] args){

        P1Random rng = new P1Random(); // using the P1 class provided
        Scanner scnr = new Scanner(System.in);

        int hand = 0, numOfGames = 1, myNumber, dealerNum, numUserWins = 0;
        int quitGame = 0, chooseAgain = 1, menuChoice, numTies = 0, numDealWins = 0;
        boolean variable = true;
        double percentage; // establishing all variables

        while (quitGame == 0){ // outer while loop corresponds to new game
            System.out.print("START GAME #" + numOfGames + "\n" + "\n"); //
            chooseAgain = 1;

            while(chooseAgain == 1){ // inner while loop corresponds to user menu choice
               if (variable) { // this is used for switch case 3 to skip to printing the menu(It is turned off). It is turned on for all other cases
                   myNumber = rng.nextInt(13) + 1;

                   if ((myNumber >= 2) && (myNumber <= 10)) { // determines what to print based on random # generated
                       System.out.print("Your card is a " + myNumber + "!\n");
                       hand = hand + myNumber;
                   } else if (myNumber == 1) {
                       System.out.print("Your card is a ACE!\n");
                       hand = hand + myNumber;
                   } else if (myNumber == 11) {
                       System.out.print("Your card is a JACK!\n");
                       hand = hand + 10;
                   } else if (myNumber == 12) {
                       System.out.print("Your card is a QUEEN!\n");
                       hand = hand + 10;
                   } else if (myNumber == 13) {
                       System.out.print("Your card is a KING!\n");
                       hand = hand + 10;
                   }

                   System.out.print("Your hand is: " + hand + "\n" + "\n");
               }

                if (hand < 21){ // switch cases only should run when the user's hand is still below 21

                    System.out.print("1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n" + "\n");
                    System.out.print("Choose an option: ");

                    menuChoice = scnr.nextInt();

                    System.out.print("\n");

                    switch(menuChoice){ //switch cases perform different tasks based off of the user's menu choice
                        case 1:
                            variable = true; // case one should run whole inner while loop again
                        break;
                        case 2: // case 2 has 3 different outcomes based on dealer's random card
                           dealerNum = rng.nextInt(11) + 16;
                            System.out.print("Dealer's hand: " + dealerNum + "\n");
                            System.out.print("Your hand is: " + hand + "\n"+ "\n");
                           if (dealerNum > 21){
                               System.out.print("You win!\n" + "\n");
                               numUserWins = numUserWins + 1;
                               numOfGames = numOfGames + 1;
                               chooseAgain = 0;
                               hand = 0;
                               variable = true;
                               break;
                           }
                           else if (dealerNum == hand){
                               System.out.print("It's a tie! No one wins!\n" + "\n");
                               numOfGames = numOfGames + 1;
                               chooseAgain = 0;
                               numTies = numTies + 1;
                               hand = 0;
                               variable = true;
                               break;
                           }
                           else if ((dealerNum > hand)||(dealerNum == 21)){
                               System.out.print("Dealer wins!\n" + "\n");
                               numOfGames = numOfGames + 1;
                               chooseAgain = 0;
                               numDealWins = numDealWins + 1;
                               hand = 0;
                               variable = true;
                               break;
                           }
                           break;
                        case 3:
                            percentage = ((double)numUserWins / (numOfGames - 1))*100; //used numUserWins as a double here just to get % to one decimal
                            System.out.print("Number of Player wins: " + numUserWins + "\n");
                            System.out.print("Number of Dealer wins: " + numDealWins + "\n");
                            System.out.print("Number of tie games: " + numTies + "\n");
                            System.out.print("Total # of games played is: " + (numOfGames - 1) + "\n");
                            System.out.print("Percentage of Player wins: " + percentage + "%"+ "\n" + "\n");
                            variable = false; // turns off first if statement in inner while loop to skip to displaying menu
                            break;
                        case 4:
                            chooseAgain = 0;
                            quitGame = 1;
                            break;
                        default: // this is a case for when the user enters an "invalid" menu choice like 6
                            System.out.print("Invalid input!\nPlease enter an integer value between 1 and 4.\n" + "\n");
                            variable = false;

                    }
            }
                else if (hand > 21){ // starts new game and increases dealer wins
                    System.out.print("You exceeded 21! You lose.\n" + "\n");
                    numDealWins = numDealWins + 1;
                    numOfGames = numOfGames + 1;
                    hand = 0;
                    break;
                }
                else if (hand == 21){ // starts new game and increases user wins
                    System.out.print("BLACKJACK! You win!\n" + "\n");
                    numUserWins = numUserWins + 1;
                    numOfGames = numOfGames + 1;
                    hand = 0;
                    break;
                }
            }
        }
    }
}

