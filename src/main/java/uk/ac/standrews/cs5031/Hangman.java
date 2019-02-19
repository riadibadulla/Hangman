package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {

	static void doStuff(Scanner sc, CommandOpts gameOptions, GameState g) {

		boolean correct;

		if (gameOptions.wordSource == "") {

			System.out.println("  1. Counties");
			System.out.println("  2. Countries");
			System.out.println("  3. Cities");

			System.out.print("Pick a category:");

			g = new GameState(Words.randomWord(sc.nextInt()), gameOptions.maxGuesses, gameOptions.maxHints);
		} else {
			g = new GameState(Words.randomWord(gameOptions.wordSource), gameOptions.maxGuesses, gameOptions.maxHints);
		}

		while (!g.won() && !g.lost()) {
			g.showWord(g.wordToBeGuessed);

			System.out.println("Guesses remaining: " + g.numberOfWrongGuessesRemaining);

			correct = g.guessLetter();

			if (correct) System.out.println("Good guess!");
			if (!correct) System.out.println("Wrong guess!");
		}

		if (g.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + g.numberOfGuessesMade + " guesses");
		} else {
			System.out.println("You lost! The word was " + g.numberOfWrongGuessesRemaining);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameState gamePlay = null;
		CommandOpts gameOptions;

		gameOptions = new CommandOpts(args);

		doStuff(sc, gameOptions, gamePlay);

	}
}
