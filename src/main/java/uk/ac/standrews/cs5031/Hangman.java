package uk.ac.standrews.cs5031;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author 180029410
 * Main class which plays the game(Player class)
 */
public class Hangman {

	public static void printMenu(){
		System.out.println("  1. Counties");
		System.out.println("  2. Countries");
		System.out.println("  3. Cities");
	}

	/**
	 * Method let's the user enter the integer
	 * @return entered integer
	 */
	public static int letTheUserEnter(){
		Scanner sc = new Scanner(System.in);
		int inputInteger = 0;

		/* To prevent the user from entering letters instead of the digits */
		try {
			inputInteger = sc.nextInt();
		} catch (InputMismatchException e){
			inputInteger = 0;
			sc.next();
		}
		return inputInteger;
	}

	/**
	 * Let's the user chose the category
	 * @return category number
	 */
	public static int chooseTheCategory(){
		int inputCategoryNumber = 0;
		do {
			System.out.print("Pick a category:");
			inputCategoryNumber = letTheUserEnter();

		/* Hence, the user can chose only the categories which exist from 1 to number of categories*/
		} while (inputCategoryNumber < 1 || inputCategoryNumber > Words.getNumberOfCategories());

		return inputCategoryNumber;
	}

	/**
	 * Creates a new game with specific word which it gets from the Words class
	 * @param gameOptions Options set by user
	 * @return game
	 */
	public static GamePlay initialiseGame(GameOptions gameOptions){

		String targetWord;
		GamePlay game;
		if (!gameOptions.wordSourceIsCustom()) {
			printMenu();
			int categoryNumber = chooseTheCategory();

			/* If the user the word source is not custom, the word is picked randomly from the category */
			targetWord = Words.getRandomWord(categoryNumber);
		} else {

			/* If the word source is custom, the word is picked from the file*/
			targetWord = Words.getRandomWord(gameOptions.getWordSource());
		}

		/* If the problem occurred with the word */
		if (targetWord.equals("")){
			System.exit(0);
		}
		game = new GamePlay(targetWord, gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		return game;
	}

	/**
	 * Print the results of the game, after it finishes
	 * @param game
	 */
	public static void printTheResults(GamePlay game){
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.getNumberOfGuessesMade() + " guesses");
		} else {
			System.out.println("You lost! The word was " + game.getNumberOfWrongGuessesRemaining());
		}
	}

	/**
	 * The playing method. Consists of the while loop
	 * @param gameOptions
	 * @param game
	 */
	public static void playTheGame(GameOptions gameOptions, GamePlay game) {
		boolean guessedLetterIscorrect;

		/* Play until you lose or win*/
		while (!game.won() && !game.lost()) {
			System.out.println(game.showWord());
			System.out.println("Allowed wrong guesses remaining: " + game.getNumberOfWrongGuessesRemaining());
			String letter = game.guessLetter();

			/* The user asks for a hint */
			while (letter.equals("?")) {
				game.giveAHint();
				letter = game.guessLetter();
			}

			/* The user tried to guess the whole word*/
			if (letter.length() > 1) {
				if (game.guessedWordIsRight(letter)){

					/* if the word is right, it will go back to the beginning of the loop and will fail the condition */
					continue;
				}
			}

			/* if the user entered a character. Letter of space */
			guessedLetterIscorrect = game.guessIsCorrect(letter.toCharArray()[0]);

			if (guessedLetterIscorrect) System.out.println("Good guess!");
			if (!guessedLetterIscorrect) System.out.println("Wrong guess!");
		}
		printTheResults(game);
	}

	public static void main(String[] args) {
		GameOptions gameOptions;

		gameOptions = new GameOptions(args);

		GamePlay game = initialiseGame(gameOptions);
		playTheGame(gameOptions, game);

	}
}
