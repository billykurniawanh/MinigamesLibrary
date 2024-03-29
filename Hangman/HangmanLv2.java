import java.util.*;
import java.io.*;

public class HangmanLv2 {
    public static final String DICTIONARY_FILE = "DictionaryLv2.txt";
    public static final boolean SHOW_COUNT = false;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the hangman game.");
        System.out.println();

        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNext())
            dictionary.add(input.next().toLowerCase());

        Scanner console = new Scanner(System.in);
        System.out.print("What length word do you want to use? ");
        int length = console.nextInt();
        System.out.print("How many wrong answers allowed? ");
        int max = console.nextInt();
        System.out.println();

        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        HangmanLv2Class hangman = new HangmanLv2Class(dictionary2, length, max);
        if (hangman.words().isEmpty()) {
            System.out.println("No words of that length in the dictionary.");
        } else {
            playGame(console, hangman);
            showResults(hangman);
        }
    }

    public static void playGame(Scanner console, HangmanLv2Class hangman) {
        while (hangman.guessesLeft() > 0 && hangman.pattern().contains("-")) {
            System.out.println("guesses : " + hangman.guessesLeft());
            if (SHOW_COUNT) {
                System.out.println("words   : " + hangman.words().size());
            }
            System.out.println("guessed : " + hangman.guesses());
            System.out.println("current : " + hangman.pattern());
            System.out.print("Your guess? ");
            char ch = console.next().toLowerCase().charAt(0);
            if (hangman.guesses().contains(ch)) {
                System.out.println("You already guessed that");
            } else {
                int count = hangman.record(ch);
                if (count == 0) {
                    System.out.println("Sorry, there are no " + ch + "'s");
                } else if (count == 1) {
                    System.out.println("Yes, there is one " + ch);
                } else {
                    System.out.println("Yes, there are " + count + " " + ch +
                                       "'s");
                }
            }
            System.out.println();
        }
    }

    public static void showResults(HangmanLv2Class hangman) {
        String answer = hangman.words().iterator().next();
        System.out.println("answer = " + answer);
        if (hangman.guessesLeft() > 0) {
            System.out.println("You beat me");
        } else {
            System.out.println("Sorry, you lose");
        }
    }
}