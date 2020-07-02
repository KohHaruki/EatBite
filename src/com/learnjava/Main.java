package com.learnjava;

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
        System.out.println(computerWord); // delete this line later

        // while loop until either the player or the computer guessed other's word correctly




    }
}
