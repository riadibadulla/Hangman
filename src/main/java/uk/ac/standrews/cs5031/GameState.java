package uk.ac.standrews.cs5031;

import java.util.ArrayList;
import java.util.Scanner;

// The game state
public class GameState {
	public String wordToBeGuessed;
	public int numberOfGuessesMade;
	public int numberOfWrongGuessesRemaining;
	public int numberOfHintsLeft;

	ArrayList<Character> listOfGuessedCharacters;
	ArrayList<Character> listOfNotGuessedCharacters;

	public Scanner sc = new Scanner(System.in).useDelimiter("\n");

	public GameState(String target, int numberOfMaximumWrongGuesses, int numberOfMaximumHints) {
		this.wordToBeGuessed = target ;
		listOfNotGuessedCharacters = new ArrayList<Character>();
		listOfGuessedCharacters = new ArrayList<Character>();
		
		for(int i = 0; i < target.length( ); ++i) {
			if (!listOfNotGuessedCharacters.contains(Character.toLowerCase(target.charAt(i))))
			listOfNotGuessedCharacters.add(Character.toLowerCase(target.charAt(i)));
		}
		
		this.numberOfGuessesMade = 0; // guesses made
		numberOfWrongGuessesRemaining = numberOfMaximumWrongGuesses; // guesses remaining
		this.numberOfHintsLeft = numberOfMaximumHints;
	}

	void showWord(String word) {
		for (int i = 0; i < word.length(); ++i) {
			if (listOfGuessedCharacters.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing);
	}
	
	boolean guessLetter() {
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String inputCharacter = sc.next().toLowerCase();
		
		if (inputCharacter.length() > 1) {
			if (inputCharacter == wordToBeGuessed) {
				listOfNotGuessedCharacters.clear();
				return true;
			} else return false;
		}

		letter = inputCharacter.charAt(0);
		
		if (letter == '?') {
			hint();
			return false;
		}
		
		for(int i = 0; i < listOfNotGuessedCharacters.size(); ++i) { // Loop over the not got
			if (listOfNotGuessedCharacters.get(i) == letter) {
				listOfNotGuessedCharacters.remove(i);
				listOfGuessedCharacters.add(letter);
				numberOfGuessesMade++;
				return true;
			}
		}

		numberOfGuessesMade++; // One more guess
		numberOfWrongGuessesRemaining--;
		return false;
	}
	
	boolean won() {
		if (listOfNotGuessedCharacters.size() == 0) return true; else return false;
	}

	boolean lost() {
		if (listOfNotGuessedCharacters.size() > 0 && numberOfWrongGuessesRemaining == 0) return true; else return false;
	}

	void hint() {
		if (numberOfHintsLeft == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(listOfNotGuessedCharacters.get((int)(Math.random()*listOfNotGuessedCharacters.size())));
	}
}
