package hangman_text;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bende
 */
public class Game {

    private ArrayList<String> words;
    private String word;
    private int wordLength;
    private String secretWord;
    
    Scanner input = new Scanner(System.in);
    Scanner text;
    private int numGuess;

    public Game() {
        

    }

    public void play() {
        words = new ArrayList<>();
        java.io.File file = new java.io.File("wordsHangman.txt");
        try {
            this.text = new Scanner(file);
            while(text.hasNext()){
                words.add(text.nextLine());
        }
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }
        
        System.out.printf("%s", introduction());
        wordLength = input.nextInt();
        while (!rightAmount(wordLength)) {
            System.out.println("Unfortunately there is no word with this amount of characters, please enter another number");
            wordLength = input.nextInt();
        }
        secretWord = findWord(wordLength);
        guessWord();

    }

    public String introduction() {
        String intro = "The man is hanging, please enter the length of the word you are trying to guess?";
        return intro;
    }

    private String findWord(int length) {
        String word;
        Random randomGenerator = new Random();
        ArrayList<String> rightWords = new ArrayList<>();
        for (String word1 : words) {
            if (word1.length() == length) {
                rightWords.add(word1);
            } else {
                continue;
            }
        }
        int index = randomGenerator.nextInt(rightWords.size());
        word = rightWords.get(index);
        System.out.println("the word is " + word);

        return word;
    }

    private boolean rightAmount(int length) {
        boolean rightAmount = false;
        for (String word1 : words) {
            if (word1.length() == length) {
                rightAmount = true;
            }
        }
        return rightAmount;
    }

    private void guessWord() {
        numGuess = 0;
        String guess;
        char[] wordInChars;
        System.out.println("we've found a word, now its your turn to guess it:");
        while (numGuess <= secretWord.length() * 3) {

            System.out.println("please enter a character, you think might be in the word: \n");
            guess = input.next();
            while (!checkInput(guess)) { //checking if the input has only one charakter and hasnt been tryed before
                System.out.println("please enter only one character");
                guess = input.next();
            }
            wordInChars = secretWord.toCharArray();
            if (secretWord.contains(guess)) {
                System.out.println("your guess was correct\n");

                for (char character : wordInChars) {
                    if (character == guess.charAt(0)) {
                        System.out.printf("%s", character);
                    } else {
                        System.out.printf(" _ ");
                    }
                }

                System.out.println("");
            } else {
                System.out.println("sorry, there is no " + guess + " in the word");
            }

            numGuess++;
        }

    }

    private boolean checkInput(String guess) {
        ArrayList<String> alphabet = new ArrayList<>();
        boolean checkInput = false;
        if (guess.length() > 1) {
            System.out.println("please enter only a single character");
            checkInput = false;
        } else if (!alphabet.contains(guess)) {
            alphabet.add(guess);
            checkInput = true;

        } else {
            System.out.println("you already entered this char, try again");
            checkInput = false;
        }
        return checkInput;
    }

}
