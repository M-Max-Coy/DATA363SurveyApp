package response;

import java.io.*;

import util.ArrayHelper;

public class UserResponse {
	
	public int countries;
	public int states;
	public String[] flagGenerated;
	public String[] flagResponse;
	public String[] citiesGenerated;
	public String[] citiesResponse;
	public String[] countriesGenerated;
	public String[] countriesResponse;
	public int seek;
	
	public UserResponse(int countries, int states, String[] flagGenerated, String[] flagResponse, String[] citiesGenerated, String[] citiesResponse,
			String[] countriesGenerated, String[] countriesResponse, int seek) {
		this.countries = countries;
		this.states = states;
		this.flagGenerated = flagGenerated;
		this.flagResponse = flagResponse;
		this.citiesGenerated = citiesGenerated;
		this.citiesResponse = citiesResponse;
		this.countriesGenerated = countriesGenerated;
		this.countriesResponse = countriesResponse;
		this.seek = seek;
	}
	
	public void outputResponse() throws IOException {
		FileWriter writer = new FileWriter("output.txt", true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		
		bufferedWriter.write("Countries Visited: " + countries + "\n");
		bufferedWriter.write("States Visited: " + states + "\n");
		bufferedWriter.write("Randomly Generated Flags: " + ArrayHelper.arrayToString(flagGenerated) + "\n");
		bufferedWriter.write("User Response Flags: " + ArrayHelper.arrayToString(flagResponse) + "\n");
		bufferedWriter.write("Randomly Generated Cities: " + ArrayHelper.arrayToString(citiesGenerated) + "\n");
		bufferedWriter.write("User Response Cities: " + ArrayHelper.arrayToString(citiesResponse) + "\n");
		bufferedWriter.write("Randomly Generated Countries: " + ArrayHelper.arrayToString(countriesGenerated) + "\n");
		bufferedWriter.write("User Response Countries: " + ArrayHelper.arrayToString(countriesResponse) + "\n");
		bufferedWriter.write("How often seeks information: " + seek + "\n");
		bufferedWriter.write("\n");
		
		bufferedWriter.close();
		writer.close();
	}
}
