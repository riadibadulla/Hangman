package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

	public static void doStuff(Scanner sc, GameOptions gameOptions, GamePlay g) {

		boolean guessedLetterIscorrect;

		if (gameOptions.getWordSource() == "") {

			System.out.println("  1. Counties");
			System.out.println("  2. Countries");
			System.out.println("  3. Cities");

			System.out.print("Pick a category:");

			g = new GamePlay(Words.randomWord(sc.nextInt()), gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		} else {
			g = new GamePlay(Words.randomWord(gameOptions.getWordSource()), gameOptions.getMaxGuesses(), gameOptions.getMaxHints());
		}

		while (!g.won() && !g.lost()) {
			g.showWord(g.getWordToBeGuessed());

			System.out.println("Guesses remaining: " + g.getNumberOfWrongGuessesRemaining());

			guessedLetterIscorrect = g.guessLetter();

			if (guessedLetterIscorrect) System.out.println("Good guess!");
			if (!guessedLetterIscorrect) System.out.println("Wrong guess!");
		}

		if (g.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + g.getNumberOfGuessesMade() + " guesses");
		} else {
			System.out.println("You lost! The word was " + g.getNumberOfWrongGuessesRemaining());
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
