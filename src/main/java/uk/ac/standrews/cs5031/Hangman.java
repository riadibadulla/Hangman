package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

	public static void printOptions(){
		System.out.println("  1. Counties");
		System.out.println("  2. Countries");
		System.out.println("  3. Cities");

		System.out.print("Pick a category:");
	}


	public static void doStuff(Scanner sc, GameOptions gameOptions, GamePlay game) {

		boolean guessedLetterIscorrect;

		if (!gameOptions.wordSourceIsCustom()) {
			printOptions();
			game = new GamePlay(Words.randomWord(sc.nextInt()), gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		} else {
			game = new GamePlay(Words.randomWord(gameOptions.getWordSource()), gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		}

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
		Scanner sc = new Scanner(System.in);
		GamePlay gamePlay = null;
		GameOptions gameOptions;

		gameOptions = new GameOptions(args);

		doStuff(sc, gameOptions, gamePlay);

	}
}
