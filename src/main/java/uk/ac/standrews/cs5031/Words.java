package uk.ac.standrews.cs5031;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Words {

	private final static String[] COUNTIES = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
	private final static String[] COUNTRIES = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
	private final static String[] CITIES = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	private static ArrayList<String> customWords;
	private static ArrayList<String[]> categories = new ArrayList<String[]>(Arrays.asList(COUNTIES,COUNTRIES,CITIES));

	public static int getNumberOfCategories(){
		return categories.size();
	}

	public static String getRandomWord(int categoryNumber) {
		int lengthOfCategory = categories.get(categoryNumber-1).length;
		return categories.get(categoryNumber-1)[(int)(Math.random()*lengthOfCategory)];
	}
	
	public static String getRandomWord(String wordsource) {
		String line;
		customWords = new ArrayList<String>();
		
		try {
			FileReader file = new FileReader(wordsource);
			BufferedReader reader = new BufferedReader(file);
			while((line = reader.readLine()) != null) {
                customWords.add(line);
            }
			return customWords.get((int)(Math.random()*customWords.size()));
		} catch(FileNotFoundException e) {
			System.out.println("File error");
			return "";
		} catch(IOException e) {
		System.out.println("IO error");
		return "";
	}
	}
}
