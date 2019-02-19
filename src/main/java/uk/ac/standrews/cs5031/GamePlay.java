package uk.ac.standrews.cs5031;

import java.util.ArrayList;
import java.util.Scanner;

// The game state
public class GamePlay {
	private String wordToBeGuessed;
	private int numberOfGuessesMade;
	private int numberOfWrongGuessesRemaining;
	private int numberOfHintsLeft;

	private ArrayList<Character> listOfGuessedCharacters;
	private ArrayList<Character> listOfNotGuessedCharacters;

	public Scanner scanner = new Scanner(System.in).useDelimiter("\n");

	public GamePlay(String target, int numberOfMaximumWrongGuesses, int numberOfMaximumHints) {
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

	public String getWordToBeGuessed(){
		return wordToBeGuessed;
	}

	public int getNumberOfGuessesMade(){
		return numberOfGuessesMade;
	}

	public int getNumberOfWrongGuessesRemaining(){
		return numberOfWrongGuessesRemaining;
	}

	public void showWord(String word) {
		for (int i = 0; i < word.length(); ++i) {
			if (listOfGuessedCharacters.contains(word.toLowerCase().charAt(i))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing);
	}
	
	public boolean guessLetter() {
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String inputCharacter = scanner.next().toLowerCase();
		
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
	
	public boolean won() {
		if (listOfNotGuessedCharacters.size() == 0) return true; else return false;
	}

	public boolean lost() {
		if (listOfNotGuessedCharacters.size() > 0 && numberOfWrongGuessesRemaining == 0) return true; else return false;
	}

	public void hint() {
		if (numberOfHintsLeft == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(listOfNotGuessedCharacters.get((int)(Math.random()*listOfNotGuessedCharacters.size())));
	}
}
