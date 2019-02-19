package uk.ac.standrews.cs5031;

public class GameOptions {

	private int maxGuesses;
	private int maxHints;

	private String wordSource;

	public int getMaxGuesses(){
		return maxGuesses;
	}

	public int getMaxHints(){
		return maxHints;
	}

	public String getWordSource(){
		return wordSource;
	}

	public GameOptions(String[] args) {
		maxGuesses = 10;
		maxHints = 2;

		wordSource = "";
		
		for(int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses")) {
				maxGuesses = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equals("--hints")) {
				maxHints = Integer.parseInt(args[i+1]);
				i++;
			}
			else wordSource = args[i];
		}
	}
}
