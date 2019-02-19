package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

	public static void printOptions(){
		System.out.println("  1. Counties");
		System.out.println("  2. Countries");
		System.out.println("  3. Cities");

		System.out.print("Pick a category:");
	}


	public static GamePlay initialGame(GameOptions gameOptions){

		String targetWord;
		GamePlay game;
		Scanner sc = new Scanner(System.in);
		if (!gameOptions.wordSourceIsCustom()) {
			printOptions();
			targetWord = Words.randomWord(sc.nextInt());
		} else {
			targetWord = Words.randomWord(gameOptions.getWordSource());
		}
		game = new GamePlay(targetWord, gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		return game;
	}

	public static void playTheGame(GameOptions gameOptions, GamePlay game) {
		boolean guessedLetterIscorrect;


		while (!game.won() && !game.lost()) {
			game.showWord(game.getWordToBeGuessed());

			System.out.println("Guesses remaining: " + game.getNumberOfWrongGuessesRemaining());

			String letter = game.guessLetter();
			while (letter == "?") {
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

		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.getNumberOfGuessesMade() + " guesses");
		} else {
			System.out.println("You lost! The word was " + game.getNumberOfWrongGuessesRemaining());
		}
	}

	public static void main(String[] args) {
		GameOptions gameOptions;

		gameOptions = new GameOptions(args);

		GamePlay game = initialGame(gameOptions);
		playTheGame(gameOptions, game);

	}
}
