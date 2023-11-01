package gui;

import javax.swing.ImageIcon;

import util.ArrayHelper;

import java.util.*;

public class QuestionInput {
	
	static HashMap<String, ImageIcon> flagsMap = new HashMap<>() {{
		put("The Bahamas", new ImageIcon((getClass().getClassLoader().getResource("resources/Bahamas.png"))));
		put("Canada", new ImageIcon((getClass().getClassLoader().getResource("resources/Canada.png"))));
		put("Cuba", new ImageIcon((getClass().getClassLoader().getResource("resources/Cuba.png"))));
		put("Haiti", new ImageIcon((getClass().getClassLoader().getResource("resources/Haiti.png"))));
		put("Honduras", new ImageIcon((getClass().getClassLoader().getResource("resources/Honduras.png"))));
		put("Argentina", new ImageIcon((getClass().getClassLoader().getResource("resources/Argentina.png"))));
		put("Brazil", new ImageIcon((getClass().getClassLoader().getResource("resources/Brazil.png"))));
		put("Ecuador", new ImageIcon((getClass().getClassLoader().getResource("resources/Ecuador.png"))));
		put("Colombia", new ImageIcon((getClass().getClassLoader().getResource("resources/Colombia.png"))));
		put("Uruguay", new ImageIcon((getClass().getClassLoader().getResource("resources/Uruguay.png"))));
		put("Belgium", new ImageIcon((getClass().getClassLoader().getResource("resources/Belgium.png"))));
		put("Croatia", new ImageIcon((getClass().getClassLoader().getResource("resources/Croatia.png"))));
		put("France", new ImageIcon((getClass().getClassLoader().getResource("resources/France.png"))));
		put("Greece", new ImageIcon((getClass().getClassLoader().getResource("resources/Greece.png"))));
		put("Slovakia", new ImageIcon((getClass().getClassLoader().getResource("resources/Slovakia.png"))));
		put("Egypt", new ImageIcon((getClass().getClassLoader().getResource("resources/Egypt.png"))));
		put("Kenya", new ImageIcon((getClass().getClassLoader().getResource("resources/Kenya.png"))));
		put("Liberia", new ImageIcon((getClass().getClassLoader().getResource("resources/Liberia.png"))));
		put("Somalia", new ImageIcon((getClass().getClassLoader().getResource("resources/Somalia.png"))));
		put("South Africa", new ImageIcon((getClass().getClassLoader().getResource("resources/South_Africa.png"))));
		put("India", new ImageIcon((getClass().getClassLoader().getResource("resources/India.png"))));
		put("Jordan", new ImageIcon((getClass().getClassLoader().getResource("resources/Jordan.png"))));
		put("Singapore", new ImageIcon((getClass().getClassLoader().getResource("resources/Singapore.png"))));
		put("Lebanon", new ImageIcon((getClass().getClassLoader().getResource("resources/Lebanon.png"))));
		put("Thailand", new ImageIcon((getClass().getClassLoader().getResource("resources/Thailand.png"))));
		put("Tonga", new ImageIcon((getClass().getClassLoader().getResource("resources/Tonga.png"))));
		put("Australia", new ImageIcon((getClass().getClassLoader().getResource("resources/Australia.png"))));
		put("New Zealand", new ImageIcon((getClass().getClassLoader().getResource("resources/New_Zealand.png"))));
		put("Samoa", new ImageIcon((getClass().getClassLoader().getResource("resources/Samoa.png"))));
		put("Papua New Guinea", new ImageIcon((getClass().getClassLoader().getResource("resources/Papua_New_Guinea.png"))));
	}};
	static String[] orderedCities = {"New Orleans", "Cleveland", "Philadelphia", "Chicago", "Las Vegas", "Dallas", 
	"Orlando", "Los Angeles", "Seattle", "New York City"}; // Ordered by crime rate in descending order
	static String[] orderedCountries = {"Switzerland", "Qatar", "United States", "United Kingdom", "Japan", "Spain", "Panama", 
	"Argentina", "China", "Egypt", "India", "Nigeria", "Chad"}; // Ordered by wealth in decreasing order
	
	/*
	 * Generates 5 random unique flags from list of ordered countries
	 * 
	 * Returns: Array of cities (as String)
	 */
	public static String[] fiveRandomFlags() {
		Random rand = new Random();
		
		String[] flagNames = flagsMap.keySet().toArray(new String[5]);
		HashSet<String> flags = new HashSet<>();
		
		int n = flagsMap.size();
		while (flags.size() < 5) {
			int cur = rand.nextInt(n);
			flags.add(flagNames[cur]);
		}
		
		return flags.toArray(new String[5]);	
	}
	
	/*
	 * Generates 5 random unique cities from list of ordered countries
	 * 
	 * Returns: Array of cities (as String)
	 */
	public static String[] fiveRandomCities() {
		Random rand = new Random();
		
		HashSet<String> cities = new HashSet<>();
		
		int n = orderedCities.length;
		while (cities.size() < 5) {
			int cur = rand.nextInt(n);
			cities.add(orderedCities[cur]);
		}
		
		return cities.toArray(new String[5]);
		
	}
	
	/*
	 * Generates 5 random unique countries from list of ordered countries
	 * 
	 * Returns: Array of countries (as String)
	 */
	public static String[] fiveRandomCountries() {		
		Random rand = new Random();
		
		HashSet<String> countries = new HashSet<>();
		
		countries.add("United States");
		
		int n = orderedCountries.length;
		while (countries.size() < 6) {
			int cur = rand.nextInt(n);
			countries.add(orderedCountries[cur]);
		}
		
		return countries.toArray(new String[6]);
	}
	
	public static String[] fiveCitiesOrder(String[] generatedCities) {
		HashSet<String> generatedCitiesSet = new HashSet<>();
		for (String city: generatedCities)
			generatedCitiesSet.add(city);
		
		String[] orderedCitiesCopy = ArrayHelper.copyArray(orderedCities);
		for (int i = 0; i < orderedCitiesCopy.length; i++) {
			if (!generatedCitiesSet.contains(orderedCitiesCopy[i]))
				orderedCitiesCopy[i] = null;
		}
		
		return ArrayHelper.removeNulls(orderedCitiesCopy);
	}
	
	public static String[] fiveCountriesOrder(String[] generatedCountries) {
		HashSet<String> generatedCountriesSet = new HashSet<>();
		for (String city: generatedCountries)
			generatedCountriesSet.add(city);
		
		String[] orderedCountriesCopy = ArrayHelper.copyArray(orderedCountries);
		for (int i = 0; i < orderedCountriesCopy.length; i++) {
			if (!generatedCountriesSet.contains(orderedCountriesCopy[i]))
				orderedCountriesCopy[i] = null;
		}
		
		return ArrayHelper.removeNulls(orderedCountriesCopy);
	}
}
