package uk.ac.standrews.cs5031;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {

	public static void printOptions(){
		System.out.println("  1. Counties");
		System.out.println("  2. Countries");
		System.out.println("  3. Cities");
	}

	public static int choseTheCategory(){
		int inputCategoryNumber = 0;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Pick a category:");
			try {
				inputCategoryNumber = sc.nextInt();
			} catch (InputMismatchException e){
				inputCategoryNumber = 0;
				sc.next();
			}
		} while (inputCategoryNumber < 1 || inputCategoryNumber > Words.getNumberOfCategories());

		return inputCategoryNumber;
	}

	public static GamePlay initialiseGame(GameOptions gameOptions){

		String targetWord;
		GamePlay game;
		if (!gameOptions.wordSourceIsCustom()) {
			printOptions();
			int categoryNumber = choseTheCategory();
			targetWord = Words.randomWord(categoryNumber);
		} else {
			targetWord = Words.randomWord(gameOptions.getWordSource());
		}
		game = new GamePlay(targetWord, gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		return game;
	}

	public static void printTheResults(GamePlay game){
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.getNumberOfGuessesMade() + " guesses");
		} else {
			System.out.println("You lost! The word was " + game.getNumberOfWrongGuessesRemaining());
		}
	}

	public static void playTheGame(GameOptions gameOptions, GamePlay game) {
		boolean guessedLetterIscorrect;


		while (!game.won() && !game.lost()) {
			game.showWord(game.getWordToBeGuessed());

			System.out.println("Guesses remaining: " + game.getNumberOfWrongGuessesRemaining());

			String letter = game.guessLetter();
			while (letter.equals("?")) {
				game.hint();
				letter = game.guessLetter();
			}


			if (letter.length() > 1) {
				if (game.guessedWordIsRight(letter)){
					continue;
				}
			}
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
