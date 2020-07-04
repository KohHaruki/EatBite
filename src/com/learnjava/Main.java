package com.learnjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // String ComputerGenerateWord() : generates a random string that is letterCount long
    // @param int letterCount : this is used to specify the length of the random word
    public static String ComputerGenerateWord(int letterCount) {
        // Whole alphabet in capital letters
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Create a StringBuilder object
        StringBuilder randomChar = new StringBuilder();

        // Initialization of index, which will be used to access Alphabet.charAt(index)
        int index;

        for (int i = 0; i < letterCount; i++) {
            // Random index within the range of Alphabet.length();
            index = (int)(Alphabet.length() * Math.random());

            // Appends the randomly chosen letter to randomChar
            randomChar.append(Alphabet.charAt(index));
        }

        return randomChar.toString();

    }

    // int[] EatBiteIdentifier() : identifies if there is any EAT or BITE between two strings given (correctWord and guess)
    // @param String correctWord : the opponents' word
    // @param String guess : current player (player/computer)'s guess
    public static int[] EatBiteIdentifier(String correctWord, String guess) {
        // This array will contain 2 numbers; [#num of Eats, #num of Bites]
        int[] eatBite = new int[2];

        // Number of occurrence of eat and bite
        int eat = 0;
        int bite = 0;

        // Checks if there is any same char between guess and correctWord
        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < correctWord.length(); j++) {
                if (guess.charAt(i) == correctWord.charAt(j)) {
                    // It's EAT when the common char is at the exact same place--
                    if (i == j)
                        eat++;
                    // -- otherwise it's a bite
                    else
                        bite++;
                }
            }
        }

        eatBite[0] = eat;
        eatBite[1] = bite;

        return eatBite;
    }


//    public static String ComputerGuessTheWord(char[] illegalCharArr, int charLength) {
//        String allowedChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        char[] allowedCharArr = allowedChar.toCharArray();
//
//        for (int i = 0; i < illegalCharArr.length; i++) {
//            for (int j = 0; j < allowedCharArr.length; j++) {
//                if (illegalCharArr[i] == allowedCharArr[j]) {
//                    allowedCharArr[j] = ' ';
//                }
//            }
//        }
//
//        char[] randomChar = new char[charLength];
//
//        for (int i = 0; i < charLength; i++) {
//            randomChar[i] = allowedCharArr[(int)(charLength * Math.random())];
//        }
//
//        return randomChar.toString();
//
//    }

    public static void main(String[] args) {
        // String constant for EAT and BITE
	    String EAT = "EAT";
	    String BITE = "BITE";

	    // ====== Game Introduction ======
        System.out.println("===== Eat Bite, Guess The Word =====");
        System.out.println("\tThis is a game where you guess a word that a computer generated, and vice versa.");
        System.out.println("=== Game Rules ===");
        System.out.println("\tThe player (you) and the computer each take a turn to guess each other's choice of word. \nWhen either player guesses a word, \nthe system will say \"Eat\" when the guessed word contains correct letter(s) at correct place(s), \nit will say \"Bite\" when the guessed word contains correct letter(s) but is/are not at the correct place(s). \nThe first one who guessed the word correctly is the winner. Here is an example:");
        System.out.println("\tYour word: APPLE");
        System.out.println("\tComputer's guess: AEIOU");
        System.out.println("\tSystem: 1 " + EAT + " and 1 " + BITE + ".\n");

        // ====== Setting up for the game mechanics =====
        // The Scanner object for taking console inputs
        Scanner scanner = new Scanner(System.in);

        // Player and computer's choices of words
        String playerWord;
        String computerWord;

        // regular expression for digits or white space
        String exceptionPattern = "(\\d)|(\\s)";

        // Create a Pattern object
        Pattern illegalPattern = Pattern.compile(exceptionPattern);

        // Create a Matcher object
        Matcher illegalMatch;

        // letter count; both the player and the computer have to have words with same number of letter count
        int wordLength;


        // ====== Game itself ======

        // Asks the user to put in legal words (e.g. only characters, no digits allowed)
        while(true) {
            // Gets user's choice of word
            System.out.print("Type your word: ");
            playerWord = scanner.next().toUpperCase();
            illegalMatch = illegalPattern.matcher(playerWord);

            if (illegalMatch.find()) {
                System.out.println("Illegal letter found: " + illegalMatch.group(0));
                System.out.println("Please type alphabets.");
                continue;
            }

            System.out.println("\nYour word is: " + playerWord);
            break;

        }

        wordLength = playerWord.length();

        // Randomly generates a string with the size of wordLength
        computerWord = ComputerGenerateWord(wordLength);
        System.out.println("Computer generated " + wordLength + " characters-long word.");
        System.out.println(computerWord + " // This is printed for debug purposes"); // delete the line as needed


        // Number of turns passed
        int numTurns = 1;


        // Player's Eat Bite count && computer's eat bite count
        int[] playerEatBite = new int[2];
        int[] computerEatBite = new int[2];

        // Player's guess && Computer's guess
        String playerGuess;
        String computerGuess;

        // while loop until either the player or the computer guessed other's word correctly
        while(true) {
            // Player's turn (every odd turn)
            if (numTurns%2 == 1) {
                // Prohibits player from guessing a word longer than wordLength
                while(true) {
                    System.out.print("GUESS COMPUTER'S WORD: ");
                    playerGuess = scanner.next().toUpperCase();
                    if (playerGuess.length() > wordLength) {
                        System.out.println("YOU CANNOT GUESS A WORD LONGER THAN " + wordLength + " CHARACTERS.");
                        continue;
                    }
                    break;
                }

                // Gets how many EATs and BITEs are between computerWord and playerGuess
                playerEatBite = EatBiteIdentifier(computerWord, playerGuess);

                // Prints out the EAT & BITE result
                System.out.println("EAT: " + playerEatBite[0] +", BITE: " + playerEatBite[1]);

            }
            // Computer's turn (every even turn)
            else {
                computerGuess = ComputerGenerateWord(wordLength);
                System.out.println("THE COMPUTER GUSSED: " + computerGuess);
                computerEatBite = EatBiteIdentifier(playerWord, computerGuess);
                System.out.println("EAT: " + computerEatBite[0] + ", BITE: " + computerEatBite[1]);
            }

            // When the number of Eat == wordLength, it means all char are at the correct place ==> the player guessed the word correctly
            if (playerEatBite[0] == wordLength) {
                System.out.println("CONGRATULATIONS! YOU HAVE GUESSED THE WORD CORRECTLY!");
                break;
            } else if (computerEatBite[0] == wordLength) { // When the computer guessed player's word correctly
                System.out.println("THE COMPUTER HAS GUESSED YOUR WORD CORRECTLY! YOU LOSE.");
                break;
            }

            numTurns++;
        }

    }
}
