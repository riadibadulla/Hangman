package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Hangman Tests.
 * printl test
 */
public class HangmanTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;


	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));

	}


	@Test
	public void testPrintTheResult(){
		GamePlay game = new GamePlay("Test", 10, 10);
		game.setListOfNotGuessedCharacters(new ArrayList<Character>());
		Hangman.printTheResults(game);
		assertEquals("Well done!\nYou took 0 guesses\n", outContent.toString());

	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);

	}
}
