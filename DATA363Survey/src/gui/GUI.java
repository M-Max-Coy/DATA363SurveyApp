package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import response.UserResponse;
import util.ArrayHelper;

public class GUI {

	public JFrame currentFrame;
	int width;
	int height;
	
	public GUI() {
		width = 1920;
		height = 1080;
		initMainFrame();
	}
	
	/*
	 * Initalizes the starting window upon opening the program.
	 * 
	 * Buttons:
	 * startSurvey - Moves to the questions page (calls initQuestionsFrame())
	 */
	private void initMainFrame() {
		JFrame mainFrame = new JFrame("DATA 363 Survey");
		mainFrame.setSize(width, height);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		JButton startSurvey = new JButton("Begin Survey");
        startSurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentFrame.dispose();
                initQuestionsFrame();
            }
        });
        
        mainFrame.add(startSurvey);
        
        currentFrame = mainFrame;
        
        showGUI();
	}
	
	/*
	 * Initalizes the questions window. In this window, the user enters responses to questions. When submitting answers, a UserResponse object is made,
	 * and the results are printed to a text file.
	 */
	private void initQuestionsFrame() {
		String[] flags = QuestionInput.fiveRandomFlags();
		String[] cities = QuestionInput.fiveRandomCities();
		String[] countries = QuestionInput.fiveRandomCountries();	
		
		JFrame questions = new JFrame("Questions");
		questions.setSize(width, height);
		questions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		questions.getContentPane().setLayout(new BoxLayout(questions.getContentPane(), BoxLayout.Y_AXIS));
		questions.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		Font labelFont = new Font("Arial", Font.BOLD, 24);
		
		JLabel one = new JLabel("How many countries have you been to?");
		one.setFont(labelFont);
		questions.add(one);
		
		JTextField oneResponse = new JTextField(3);
		questions.add(oneResponse);
		
		JLabel two = new JLabel("How many American states have you been to?");
		two.setFont(labelFont);
		questions.add(two);
		
		JTextField twoResponse = new JTextField(3);
		questions.add(twoResponse);
		
		JLabel three = new JLabel("Guess the name of the flags (type N/A if you have absolutely no idea).");
		three.setFont(labelFont);
		questions.add(three);
		
		JPanel flagPanel = new JPanel();
		flagPanel.setLayout(new GridLayout(2, 5));
		
		ImageIcon flag1 = QuestionInput.flagsMap.get(flags[0]);
		JLabel flag1label = new JLabel(flag1);
		flagPanel.add(flag1label);
		
		ImageIcon flag2 = QuestionInput.flagsMap.get(flags[1]);
		JLabel flag2label = new JLabel(flag2);
		flagPanel.add(flag2label);
		
		ImageIcon flag3 = QuestionInput.flagsMap.get(flags[2]);
		JLabel flag3label = new JLabel(flag3);
		flagPanel.add(flag3label);
		
		ImageIcon flag4 = QuestionInput.flagsMap.get(flags[3]);
		JLabel flag4label = new JLabel(flag4);
		flagPanel.add(flag4label);
		
		ImageIcon flag5 = QuestionInput.flagsMap.get(flags[4]);
		JLabel flag5label = new JLabel(flag5);
		flagPanel.add(flag5label);
		
		JTextField flag1Response = new JTextField(20);
		flagPanel.add(flag1Response);
		
		JTextField flag2Response = new JTextField(20);
		flagPanel.add(flag2Response);
		
		JTextField flag3Response = new JTextField(20);
		flagPanel.add(flag3Response);
		
		JTextField flag4Response = new JTextField(20);
		flagPanel.add(flag4Response);
		
		JTextField flag5Response = new JTextField(20);
		flagPanel.add(flag5Response);
		
		questions.add(flagPanel);
		
		JLabel four = new JLabel("Sort the following cities from most dangerous to least dangerous (by your perception of them).");
		four.setFont(labelFont);
		questions.add(four);
		
		JLabel fourp2 = new JLabel(ArrayHelper.arrayToString(cities));
		fourp2.setFont(labelFont);
		questions.add(fourp2);
		
		JTextField fourResponse = new JTextField(3);
		questions.add(fourResponse);
		
		JLabel five = new JLabel("Sort the following countries from most wealthy to least wealthy (by your perception of them).");
		five.setFont(labelFont);
		questions.add(five);
		
		JLabel fivep2 = new JLabel(ArrayHelper.arrayToString(countries));
		fivep2.setFont(labelFont);
		questions.add(fivep2);
		
		JTextField fiveResponse = new JTextField(3);
		questions.add(fiveResponse);
		
		JLabel six = new JLabel("How often do you seek out information about other states/countries/cultures (From 1 to 10 where 1 is lowest, 10 highest)?");
		six.setFont(labelFont);
		questions.add(six);
		
		JTextField sixResponse = new JTextField(3);
		questions.add(sixResponse);
		
		JButton submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String[] flagNamesResponse = new String[5];
            	flagNamesResponse[0] = flag1Response.getText();
            	flagNamesResponse[1] = flag2Response.getText();
            	flagNamesResponse[2] = flag3Response.getText();
            	flagNamesResponse[3] = flag4Response.getText();
            	flagNamesResponse[4] = flag5Response.getText();
            	
            	try {
            		UserResponse response = new UserResponse(
            				Integer.parseInt(oneResponse.getText()),
            				Integer.parseInt(twoResponse.getText()),
            				flags,
            				flagNamesResponse,
            				cities,
            				fourResponse.getText().split("\\s*,\\s*"),
            				countries,
            				fiveResponse.getText().split("\\s*,\\s*"),
            				Integer.parseInt(sixResponse.getText())
            		);
            		response.outputResponse();
            		
                	currentFrame.dispose();
                    initResultsFrame(response);
            	}
            	catch(Exception a) {
            		
            	}     
            }
        });
		
        questions.add(submitButton);
        
		currentFrame = questions;
		
		showGUI();
	}
	
	/*
	 * Initializes a window that gives the survee an overview of their results.
	 */
	public void initResultsFrame(UserResponse response) {
		JFrame results = new JFrame("Questions");
		results.setSize(width, height);
		results.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		results.getContentPane().setLayout(new BoxLayout(results.getContentPane(), BoxLayout.Y_AXIS));
		results.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		Font labelFont = new Font("Arial", Font.BOLD, 24);
		
		JLabel thanks = new JLabel("Thank you for participating");
		thanks.setFont(labelFont);
		results.add(thanks);
		
		JLabel flagsQuestion = new JLabel("Results for Flag Question:");
		thanks.setFont(labelFont);
		results.add(flagsQuestion);
		
		JPanel flagPanel = new JPanel();
		flagPanel.setLayout(new GridLayout(2, 5));
		
		String[] flags = response.flagGenerated;
		String[] flagsResponse = response.flagResponse;
		
		ImageIcon flag1 = QuestionInput.flagsMap.get(flags[0]);
		JLabel flag1label = new JLabel(flag1);
		flagPanel.add(flag1label);
		
		ImageIcon flag2 = QuestionInput.flagsMap.get(flags[1]);
		JLabel flag2label = new JLabel(flag2);
		flagPanel.add(flag2label);
		
		ImageIcon flag3 = QuestionInput.flagsMap.get(flags[2]);
		JLabel flag3label = new JLabel(flag3);
		flagPanel.add(flag3label);
		
		ImageIcon flag4 = QuestionInput.flagsMap.get(flags[3]);
		JLabel flag4label = new JLabel(flag4);
		flagPanel.add(flag4label);
		
		ImageIcon flag5 = QuestionInput.flagsMap.get(flags[4]);
		JLabel flag5label = new JLabel(flag5);
		flagPanel.add(flag5label);
		
		JLabel flag1answerAndResponse = new JLabel("Flag was: " + flags[0] + " You said: " + flagsResponse[0]);
		flagPanel.add(flag1answerAndResponse);
		JLabel flag2answerAndResponse = new JLabel("Flag was: " + flags[1] + " You said: " + flagsResponse[1]);
		flagPanel.add(flag2answerAndResponse);
		JLabel flag3answerAndResponse = new JLabel("Flag was: " + flags[2] + " You said: " + flagsResponse[2]);
		flagPanel.add(flag3answerAndResponse);
		JLabel flag4answerAndResponse = new JLabel("Flag was: " + flags[3] + " You said: " + flagsResponse[3]);
		flagPanel.add(flag4answerAndResponse);
		JLabel flag5answerAndResponse = new JLabel("Flag was: " + flags[4] + " You said: " + flagsResponse[4]);
		flagPanel.add(flag5answerAndResponse);
		
		results.add(flagPanel);
		
		JLabel explanation = new JLabel("If guessed right position you get 1 point. If 1 position off, .5 points. If 2+ off, 0 points");
		explanation.setFont(labelFont);
		results.add(explanation);
		
		JLabel citiesQuestion = new JLabel("Results for Cities Question:");
		citiesQuestion.setFont(labelFont);
		results.add(citiesQuestion);
		
		JLabel citiesAnswer = new JLabel("Actual Order: " + ArrayHelper.arrayToString(QuestionInput.fiveCitiesOrder(response.citiesGenerated)));
		citiesAnswer.setFont(labelFont);
		results.add(citiesAnswer);
		
		JLabel citiesResponse = new JLabel("You Answered: " + ArrayHelper.arrayToString(response.citiesResponse));
		citiesResponse.setFont(labelFont);
		results.add(citiesResponse);
		
		JLabel countriesQuestion = new JLabel("Results for Countries Question:");
		countriesQuestion.setFont(labelFont);
		results.add(countriesQuestion);
		
		JLabel countriesAnswer = new JLabel("Actual Order: " + ArrayHelper.arrayToString(QuestionInput.fiveCountriesOrder(response.countriesGenerated)));
		countriesAnswer.setFont(labelFont);
		results.add(countriesAnswer);
		
		JLabel countriesResponse = new JLabel("You Answered: " + ArrayHelper.arrayToString(response.countriesResponse));
		countriesResponse.setFont(labelFont);
		results.add(countriesResponse);
		
		JButton restartButton = new JButton("Begin Next Survey");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentFrame.dispose();
                initMainFrame();
            }
        });
		
        results.add(restartButton);
		
		currentFrame = results;
		
		showGUI();
	}
	
	private void showGUI() {
		currentFrame.setVisible(true);	
	}
}
