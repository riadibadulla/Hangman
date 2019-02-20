package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOptionsTest {

	String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
	String[] args2 = { "guesses", "a", "--hints", "4", "words.txt" };
	String[] args3 = { "--guesses", "2", "--hints", "as", "words.txt" };
	String[] args4 = { "--guesses", "2", "--hints", "4", "words.txkkjt" };

	@Test
	public void optionsTest1() {
		GameOptions opts = new GameOptions(args);
		assertEquals(opts.getMaxGuesses(), 2);
		assertEquals(opts.getMaxHints(), 4);
		assertEquals(opts.getWordSource(), "words.txt");
	}

	@Test
	public void optionsTestGuessesIsLetter() {
		GameOptions opts = new GameOptions(args2);
		assertEquals(opts.getMaxGuesses(), 10);
		assertEquals(opts.getMaxHints(), 4);
		assertEquals(opts.getWordSource(), "words.txt");
	}

	@Test
	public void optionsTestHintsIsLetter() {
		GameOptions opts = new GameOptions(args3);
		assertEquals(opts.getMaxGuesses(), 2);
		assertEquals(opts.getMaxHints(), 2);
		assertEquals(opts.getWordSource(), "words.txt");
	}

	@Test
	public void optionsTestWithWrongFileExtension() {
		GameOptions opts = new GameOptions(args4);
		assertEquals(opts.getMaxGuesses(), 2);
		assertEquals(opts.getMaxHints(), 4);
		assertEquals(opts.getWordSource(), "");
	}



}
