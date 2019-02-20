package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePlayTest {

	static String[] targetWords = { "Bang bang", "test test", "hello World", "hello 1", "test test test", "geeks2gits", "Australia" };
	Integer[] testingNumbers = {0, 5, 1000, -5, 10, 3};
	static GamePlay gamePlay;

	@BeforeClass
	public static void initialiseClass(){
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
		assertEquals(gamePlay.guessIsCorrect('a'), true);
		assertEquals(gamePlay.guessIsCorrect(' '), false);
	}

	@Test
	public void testGussedWordIsRight(){
		assertEquals(gamePlay.guessedWordIsRight("australia"), true);
		assertEquals(gamePlay.guessedWordIsRight("Australia"), true);
		assertEquals(gamePlay.guessedWordIsRight("austrAlia"), true);
		assertEquals(gamePlay.guessedWordIsRight("austra lia"), false);
		assertEquals(gamePlay.guessedWordIsRight("australia "), false);
		assertEquals(gamePlay.guessedWordIsRight(""), false);
		assertEquals(gamePlay.guessedWordIsRight(null), false);
	}

}
