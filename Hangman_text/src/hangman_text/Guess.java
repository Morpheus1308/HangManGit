package hangman_text;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bende
 */
public class Guess {

    private String secretWord;
    private int guessAllowed;
    private int numGuess;
    private int rightGuess;
    private int charInWord; //different characters in the secretWord
    private ArrayList<String> alphabet = new ArrayList<>();
    private ArrayList<Character> alphabet2 = new ArrayList<>(); //used to count different characters in the secretWord
    private ArrayList<String> correctGuesses = new ArrayList<>();
    private ArrayList<String> characters = new ArrayList<>();
    char[] wordInChars;

    public Guess(String secretWord) {
        this.secretWord = secretWord;
        wordInChars = secretWord.toCharArray();
        for (char wordInChar : wordInChars) {
            if (!alphabet2.contains(wordInChar)) {
                alphabet2.add(wordInChar);
                charInWord++;
            } else {
                continue;
            }
        }
        
        guessAllowed = (int) (secretWord.length() * 1.5);
    }

    public void guess() {
        Scanner input = new Scanner(System.in);
        numGuess = 0;
        rightGuess = 0;
        String guess;

        System.out.println("we've found a word, now its your turn to guess it:");
        while (numGuess < guessAllowed &&  rightGuess < charInWord)  {

            System.out.println("So far you have guessed:");

            for (char character : wordInChars) {
                if (correctGuesses.contains(String.valueOf(String.valueOf(character)))) {
                    System.out.print(" " + character + " ");
                } else {
                    System.out.print(" _ ");
                }
            }
            System.out.println("");
            System.out.println("you have " + (guessAllowed - numGuess) + " guesses left");
            System.out.println("please enter a character, you think might be in the word: \n");
            guess = input.next();
            if (guess == "quit") {
                System.out.println("system exit");
                java.lang.System.exit(0);
            }
            while (!checkInput(guess)) { //checking if the input has only one charakter and hasnt been tryed before
                guess = input.next();
            }
            wordInChars = secretWord.toCharArray();
            if (secretWord.contains(guess)) {
                System.out.println("your guess was correct\n");
                correctGuesses.add(guess);
                rightGuess++;
                for (char character : wordInChars) {
                    if (correctGuesses.contains(String.valueOf(String.valueOf(character)))) {
                        System.out.print(" " + character + " ");

                    } else {
                        System.out.print(" _ ");
                    }
                }
                System.out.println("");
            } else {
                System.out.println("sorry, there is no " + guess + " in the word");
                numGuess++;
            }
            //creating a string of the correct guesses to check if the entire word is guessed
            if (rightGuess == charInWord) {
            System.out.println("Youve won the game");
        }
        }
        
        if (numGuess == guessAllowed) {
            System.out.println("Too many tries, the man is dead");
        }
    }
    //method to check if the user inputs more than one character

    private boolean checkInput(String guess) {

        boolean checkInput = true;

        if (guess.length() > 1) {
            System.out.println("please enter only a single character");
            checkInput = false;
        } else if (this.alphabet.contains(guess)) {
            System.out.println("you already entered this charakter, try again");
            checkInput = false;
        } else {
            alphabet.add(guess);
        }
        return checkInput;
    }
}
