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
	private Scanner scanner = new Scanner(System.in).useDelimiter("\n");

	/**
	 * Constructor.
	 * @param target the word to be guessed
	 * @param numberOfMaximumWrongGuesses
	 * @param numberOfMaximumHints
	 */
	public GamePlay(String target, int numberOfMaximumWrongGuesses, int numberOfMaximumHints) {
		this.wordToBeGuessed = target;
		listOfNotGuessedCharacters = new ArrayList<Character>();		//Not guessed yet
		listOfGuessedCharacters = new ArrayList<Character>();
		
		for (int i = 0; i < target.length( ); ++i) {
			if (!listOfNotGuessedCharacters.contains(Character.toLowerCase(target.charAt(i)))){
				listOfNotGuessedCharacters.add(Character.toLowerCase(target.charAt(i)));
			}
		}
		
		this.numberOfGuessesMade = 0; // guesses made
		numberOfWrongGuessesRemaining = numberOfMaximumWrongGuesses; // guesses remaining
		this.numberOfHintsLeft = numberOfMaximumHints;
	}

	//Getters
	public String getWordToBeGuessed(){
		return wordToBeGuessed;
	}

	public int getNumberOfGuessesMade(){
		return numberOfGuessesMade;
	}

	public int getNumberOfWrongGuessesRemaining(){
		return numberOfWrongGuessesRemaining;
	}

	//setters
	public void setListOfGuessedCharacters(ArrayList<Character> listOfGuessedCharacters){
		this.listOfGuessedCharacters = listOfGuessedCharacters;
	}

	public void setListOfNotGuessedCharacters(ArrayList<Character> listOfNotGuessedCharacters){
		this.listOfNotGuessedCharacters = listOfNotGuessedCharacters;
	}

	/**
	 * shows the word with guessed letters.
	 * @return
	 */
	public String showWord() {
		String outputWord = "";

		for (int i = 0; i < this.wordToBeGuessed.length(); ++i) {
			if (listOfGuessedCharacters.contains(this.wordToBeGuessed.toLowerCase().charAt(i))) {
				outputWord += this.wordToBeGuessed.charAt(i);
			} else {
				outputWord += "_";
			}
		}
		return outputWord;
	}

	/**
	 *
	 * @param letter
	 * @return true if the target word contains entered character
	 */
	public boolean guessIsCorrect(char letter){
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

	/**
	 *
	 * @param wholeWord
	 * @return true if the user guessed the whole word correctly
	 */
	public boolean guessedWordIsRight(String wholeWord){
		if (wholeWord != null && wholeWord.toLowerCase().equals(wordToBeGuessed.toLowerCase())) {
			listOfNotGuessedCharacters.clear();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Read's the users input.
	 * @return returns entered character or word in String
	 */
	public String guessLetter() {
		System.out.print("Guess a letter or word (? for a hint): ");
		return scanner.next().toLowerCase();
	}

	/**
	 * Won
	 * @return true when the user wins the game.
	 */
	public boolean won() {
		return listOfNotGuessedCharacters.size() == 0;
	}

	/**
	 *
	 * @return false when the user loses the game.
	 */
	public boolean lost() {
		return (listOfNotGuessedCharacters.size() > 0 && numberOfWrongGuessesRemaining == 0);
	}

	/**
	 * Gives a hint, random character from the target word which is not guessed yet.
	 */
	public void giveAHint() {
		if (numberOfHintsLeft == 0) {
			System.out.println("No more hints allowed");
		}
		System.out.print("Try: ");
		System.out.println(listOfNotGuessedCharacters.get((int) (Math.random() * listOfNotGuessedCharacters.size())));
	}
}
