package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests GamePlay.
 * tests using different target words...
 */
public class GamePlayTest {

	String[] targetWords = { "Bang bang", "test test", "hello World", "hello 1", "test test test", "geeks2gits", "Australia" };
	Integer[] testingNumbers = {0, 5, 1000, -5, 10, 3};
	GamePlay gamePlay;

	@Before
	public void initialiseClass(){
		gamePlay = new GamePlay(targetWords[6], 10, 3);
	}

	@Test
	public void gameTestInitialise() {
		for (String word : targetWords){
			for (int number : testingNumbers) {
				GamePlay gamePlay = new GamePlay(word, number, number);
				assertEquals(gamePlay.getNumberOfGuessesMade(), 0);
				assertEquals(gamePlay.getNumberOfWrongGuessesRemaining(), number);
				assertEquals(gamePlay.getWordToBeGuessed(), word);
			}
		}
	}

	@Test
	public void testShowWord1(){
		gamePlay.setListOfGuessedCharacters(new ArrayList<Character>(Arrays.asList('a','s','r','l')));
		assertEquals(gamePlay.showWord(), "A_s_ral_a");
	}

	@Test
	public void testShowWord2(){
		gamePlay.setListOfGuessedCharacters(new ArrayList<Character>());
		assertEquals(gamePlay.showWord(), "_________");
	}


	@Test
	public void testGuessIsCorrect(){
		assertTrue(gamePlay.guessIsCorrect('a'));
		assertFalse(gamePlay.guessIsCorrect(' '));
	}

	@Test
	public void testGussedWordIsRight(){
		assertTrue(gamePlay.guessedWordIsRight("australia"));
		assertTrue(gamePlay.guessedWordIsRight("Australia"));
		assertTrue(gamePlay.guessedWordIsRight("austrAlia"));
		assertFalse(gamePlay.guessedWordIsRight("austra lia"));
		assertFalse(gamePlay.guessedWordIsRight("australia "));
		assertFalse(gamePlay.guessedWordIsRight(""));
		assertFalse(gamePlay.guessedWordIsRight(null));
	}

	@Test
	public void testWon(){
		gamePlay.setListOfNotGuessedCharacters(new ArrayList<Character>());
		assertTrue(gamePlay.won());

	}
}
