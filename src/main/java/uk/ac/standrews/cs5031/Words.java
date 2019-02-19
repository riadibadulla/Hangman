package uk.ac.standrews.cs5031;

import java.io.*;
import java.util.ArrayList;

public class Words {

	static String[] counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
	static String[] countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
	static String[] cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	static ArrayList<String> customWords;

	public static String randomWord(int category) {
		if (category == 1)
			return counties[(int)(Math.random()*9)];
		if (category == 2)
			return countries[(int)(Math.random()*15)];
		return cities[(int)(Math.random()*10)];
	}
	
	public static String randomWord(String wordsource) {
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
