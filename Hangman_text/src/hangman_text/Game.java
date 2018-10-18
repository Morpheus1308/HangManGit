package hangman_text;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bende
 */
public class Game {

    private ArrayList<String> words;

    private String word;
    private int wordLength;
    private String secretWord;
    
    Guess guess;
    

    Scanner input = new Scanner(System.in);
    Scanner text;
    

    public Game() {

    }

    public void play() {
        //creation of list with common english words (1000)
        words = new ArrayList<>();
        java.io.File file = new java.io.File("wordsHangman.txt");
        try {
            this.text = new Scanner(file);
            while (text.hasNext()) {
                words.add(text.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }

        //introduction
        System.out.printf("%s", introduction());
        //getting userinput about the length of the word
        System.out.println("");
        wordLength = input.nextInt();
        while (!rightAmount(wordLength)) {
            System.out.println("Unfortunately there is no word with this amount of characters, please enter another number");
            wordLength = input.nextInt();
        }
        secretWord = findWord(wordLength);

        guess = new Guess(secretWord);
        guess.guess();

    }

    public String introduction() {
        String intro = "The man is hanging, please enter the length of the word you are trying to guess?";
        return intro;
    }

    //method to find a random word with the specified length
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

    //checking if there is word with the specified length
    private boolean rightAmount(int length) {
        boolean rightAmount = false;
        for (String word1 : words) {
            if (word1.length() == length) {
                rightAmount = true;
            }
        }
        return rightAmount;
    }

    //Management of the users guesses
}
