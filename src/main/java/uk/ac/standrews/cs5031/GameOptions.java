package uk.ac.standrews.cs5031;

public class GameOptions {
	private int maxGuesses;
	private int maxHints;
	private String wordSource;

	//getters
	public int getMaxGuesses(){
		return maxGuesses;
	}

	public int getMaxHints(){
		return maxHints;
	}

	public String getWordSource(){
		return wordSource;
	}

	public void setWordSource(String wordSource){
		this.wordSource = wordSource;
	}
	/**
	 * If word source is custom or not
	 * @return true if the word source is specified by the user
	 */
	public boolean wordSourceIsCustom(){
		return !getWordSource().equals("");
	}

	/**
	 * Sets options based on the application parameters
	 * @param args argument(options) set by the user
	 */
	public GameOptions(String[] args) {
		maxGuesses = 10;
		maxHints = 2;

		wordSource = "";
		
		for(int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses")) {
				try {

					//Number of maximum gusses always after the --guesses keyword
					maxGuesses = Integer.parseInt(args[i + 1]);
				} catch(NumberFormatException e){
					System.out.println("Number of Maximum Guesses should be an integer");
				}
				i++;
			}
			else if (args[i].equals("--hints")) {
				try {

					//Number of maximum hints always after the --hint keyword
					maxHints = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e){
					System.out.println("Number of Maximum Hints should be an integer");
				}
				i++;
			}

			//file can have only extension .txt
			else if (args[i].endsWith(".txt")){
				wordSource = args[i];
			}
		}
	}
}
