/*The "ScrabbleP2V2" class.
*Purpose: Scrabble game. Making words using the letters given. 2 Player
*@Author Yusra Irfan and Areeba Rizvi
*@Version 50: January 15th 2016
*Citations:
    MouseListner and MouseMotionListener:
	http://www.realapplets.com/tutorial/chapter2.html
    KeyListner: http://www.dgp.toronto.edu/~mjmcguff/learn/java/05-keyboardInput/
*Maheen Adhami helped with the Timer Code (ActionListener), she got it from:
    http://stackoverflow.com/questions/16308376/beginner-java-applet-cannot-get-swing-timer-to-function
	
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class ScrabbleP2SV2 extends Applet implements MouseMotionListener, MouseListener, KeyListener, ActionListener //, Runnable
{
    AudioClip background;
    AudioClip click; //sound after each click
    AudioClip countDown;
    Timer timer = new Timer (1000, this); //Declaring the speed of the timer
    Random generator = new Random ();

    //font styles
    Font fontMenu = new Font ("Lucida Console", 1, 150);
    Font fontLetters = new Font ("Fixedsys", 1, 60);
    Font fontScore = new Font ("Lucida Console", 1, 100);
    Font fontLetters1 = new Font ("Fixedsys", 1, 30);
    Font fontInfo = new Font ("Lucida Console", 0, 20);
    Font fontInfo1 = new Font ("Lucida Console", 0, 20);
    Font font = new Font ("TheDeadAreComing", 0, 12);

    //booleans in general use
    boolean player1 = true;
    boolean player2 = false;
    boolean gameOver = false;
    boolean Screen1 = true; //menu
    boolean Next = false; //next turn
    boolean overRide;  //checks if the user is overriding any letter

    //these will be true when the mouse is clicked(rectangle)
    boolean isPlayPressed = false;
    boolean isInstructionPressed = false;
    boolean isAboutPressed = false;
    boolean isNextPressed = false;
    boolean isPauseBPressed = false;
    boolean isPlayBPressed = false;
    boolean isYesPressed = false;
    boolean isNoPressed = false;
    boolean isRestartPressed = false;
    boolean isResetPressed = false;
    boolean isExitPressed = false;
    boolean isInstruction = false;

    //these make the options appear different if the mouse is in their range (rectangle)
    boolean PlayActive = false;
    boolean InstructionActive = false;
    boolean AboutActive = false;
    boolean NextActive = false;
    boolean PauseBActive = false;
    boolean PlayBActive = false;
    boolean YesActive = false;
    boolean NoActive = false;
    boolean RestartActive = false;
    boolean ResetLActive = false;
    boolean MenuActive = false;
    boolean NextBActive = false;
    boolean BackBActive = false;
    boolean ExitActive = false;

    int randomInt; //random number for assigned letter player1
    int randomInt2; //random number for assigned letter player2
    int count1 = 0; //Number of times the timer turns 0 for player1
    int count2 = 0; //Number of times the timer turns 0 for player2
    int oldScore = 0;
    int oldScore2 = 0;
    int newScore = 0;
    int newScore2 = 0;


    int x; //for overWrite to replace letters
    int amount; //Amount of time the timer runs for


    int x_pos = 474;        // x - Position of the box
    int y_pos = 297;       // y - Position of the box
    int x_posLetter; // x - position of the letter (after box is moved)
    int y_posLetter; // y - position of the letter (after box is moved)
    int square_position; //position of frame of assinged letters
    int height = 30;       // height
    int width = 43;        //width
    int boardwidth = 600;  //scrabble board width
    int leftBorder = 190;  //left border for scrabble board
    int rightBorder = 780; //right border for scrabble board
    int reWrite = 0;  //helps with score in overRide method
    int j = 0; //for position in array after or before next
    int score = 0;
    int xpos; // The X-coordinate of the Mouse Position
    int ypos; // The  Y-coordinate of the Mouse Position

    int[] fixedScore = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10}; //score according to the letters in array alphabet
    int[] countLetter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //The number of times each letter is assigned
    int[] x_posLetters = new int [255]; //arrays to keep track of their positions
    int[] y_posLetters = new int [255]; //arrays to keep track of their positions
    int[] scoreLetters = new int [255]; //arrays to keep track of their score

    char input = ' ';
    char[] alphabet = {'A', 'B', 'C', 'D', 'E',
	'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
	'V', 'W', 'X', 'Y', 'Z'};
    char[] assignedLetters = {' ', ' ', ' ', ' ', ' ', ' ', ' '};  //array to keep track of letters
    char[] assignedLetters2 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};  //array to keep track of letters
    char[] enteredLetters = new char [255]; //arrays to keep track of the letters entered


    // Place instance variables here
    private Image dbImage;
    private Graphics dbg;
    // create meadia tracker and image variables for image
    Image img;
    MediaTracker tr;
    private final String StartScreen = "StartScreen.jpg";
    private final String StartSPlay = "StartScreenPlay.jpg";
    private final String StartSIns = "StartScreenIns.jpg";
    private final String StartSAbout = "StartScreenAbout.jpg";
    private final String Scrabble = "board.jpg";
    private final String Background = "BackgroundMain.jpg";
    private final String NextPlayer = "NextPlayer.png";
    private final String Score = "Score.png";
    private final String NextPlayer2 = "NextPlayer2.png";
    private final String frame = "frame.png";
    private final String GameOver = "GameOver.png";
    private final String PlayAgain = "PlayAgain.png";
    private final String YesOrNo = "YesOrNo.png";
    private final String PlayText = "Play3.png";
    private final String PauseText = "Pause1.png";
    private final String PauseButton = "PauseB.png";
    private final String PauseText1 = "Pause2.png";
    private final String PlayButton = "PlayB.png";
    private final String PlayText1 = "Play4.png";
    private final String YesText = "Yes.png";
    private final String NoText = "No.png";
    private final String Restart = "Restart.png";
    private final String ResetLetters = "reset.png";
    private final String ResetLetters1 = "reset1.png";
    private final String MainMenu = "menu.png";
    private final String MainMenu1 = "menu1.png";
    private final String AboutText = "aboutText.png";
    private final String InstructionText = "instructionsText.png";
    private final String NextB = "nextB.png";
    private final String NextB1 = "nextB1.png";
    private final String BackB = "BackB.png";
    private final String BackB1 = "BackB1.png";
    private final String Restart1 = "Restart1.png";
    private final String Instructions = "instructionslist.png";
    private final String Purple = "purple.png";
    private final String Clock = "clock.png";
    private final String Lives = "lives.png";
    private final String AboutText1 = "aboutText1.png";
   
    Image StartScreenPic;
    Image StartSPlayPic;
    Image StartSInsPic;
    Image StartSAboutPic;
    Image ScrabblePic;
    Image BackgroundPic;
    Image NextPlayerPic;
    Image ScorePic;
    Image NextPlayer2Pic;
    Image framePic;
    Image GameOverPic;
    Image PlayAgainPic;
    Image YesOrNoPic;
    Image PlayTextPic;
    Image PauseTextPic;
    Image PauseButtonPic;
    Image PauseText1Pic;
    Image PlayButtonPic;
    Image PlayText1Pic;
    Image YesPic;
    Image NoPic;
    Image RestartPic;
    Image ResetPic;
    Image ResetPic1;
    Image MenuPic;
    Image MenuPic1;
    Image AboutTextPic;
    Image InstructionTextPic;
    Image NextBPic;
    Image NextBPic1;
    Image BackBPic;
    Image BackBPic1;
    Image RestartPic1;
    Image ListInstructions;
    Image PurplePic;
    Image ClockPic;
    Image LivesPic;
    Image AboutTextPic1;

    public void init ()
    {
	// Place the body of the initialization method here
	resize (1300, 700);
	//Toolkiting the Images
	StartScreenPic = getToolkit ().getImage (StartScreen);
	prepareImage (StartScreenPic, this);
	StartSPlayPic = getToolkit ().getImage (StartSPlay);
	prepareImage (StartSPlayPic, this);
	StartSInsPic = getToolkit ().getImage (StartSIns);
	prepareImage (StartSInsPic, this);
	StartSAboutPic = getToolkit ().getImage (StartSAbout);
	prepareImage (StartSAboutPic, this);
	ScrabblePic = getToolkit ().getImage (Scrabble);
	prepareImage (ScrabblePic, this);
	BackgroundPic = getToolkit ().getImage (Background);
	prepareImage (BackgroundPic, this);
	NextPlayerPic = getToolkit ().getImage (NextPlayer);
	prepareImage (NextPlayerPic, this);
	ScorePic = getToolkit ().getImage (Score);
	prepareImage (ScorePic, this);
	NextPlayer2Pic = getToolkit ().getImage (NextPlayer2);
	prepareImage (NextPlayer2Pic, this);
	framePic = getToolkit ().getImage (frame);
	prepareImage (framePic, this);
	GameOverPic = getToolkit ().getImage (GameOver);
	prepareImage (GameOverPic, this);
	PlayAgainPic = getToolkit ().getImage (PlayAgain);
	prepareImage (PlayAgainPic, this);
	YesOrNoPic = getToolkit ().getImage (YesOrNo);
	prepareImage (YesOrNoPic, this);
	PlayTextPic = getToolkit ().getImage (PlayText);
	prepareImage (PlayTextPic, this);
	PauseTextPic = getToolkit ().getImage (PauseText);
	prepareImage (PauseTextPic, this);
	PauseButtonPic = getToolkit ().getImage (PauseButton);
	prepareImage (PauseButtonPic, this);
	PauseText1Pic = getToolkit ().getImage (PauseText1);
	prepareImage (PauseText1Pic, this);
	PlayButtonPic = getToolkit ().getImage (PlayButton);
	prepareImage (PlayButtonPic, this);
	PlayText1Pic = getToolkit ().getImage (PlayText1);
	prepareImage (PlayText1Pic, this);
	YesPic = getToolkit ().getImage (YesText);
	prepareImage (YesPic, this);
	NoPic = getToolkit ().getImage (NoText);
	prepareImage (NoPic, this);
	RestartPic = getToolkit ().getImage (Restart);
	prepareImage (RestartPic, this);
	ResetPic = getToolkit ().getImage (ResetLetters);
	prepareImage (ResetPic, this);
	ResetPic1 = getToolkit ().getImage (ResetLetters1);
	prepareImage (ResetPic1, this);
	MenuPic = getToolkit ().getImage (MainMenu);
	prepareImage (MenuPic, this);
	MenuPic1 = getToolkit ().getImage (MainMenu1);
	prepareImage (MenuPic1, this);
	AboutTextPic = getToolkit ().getImage (AboutText);
	prepareImage (AboutTextPic, this);
	InstructionTextPic = getToolkit ().getImage (InstructionText);
	prepareImage (InstructionTextPic, this);
	NextBPic = getToolkit ().getImage (NextB);
	prepareImage (NextBPic, this);
	NextBPic1 = getToolkit ().getImage (NextB1);
	prepareImage (NextBPic1, this);
	BackBPic = getToolkit ().getImage (BackB);
	prepareImage (BackBPic, this);
	BackBPic1 = getToolkit ().getImage (BackB1);
	prepareImage (BackBPic1, this);
	RestartPic1 = getToolkit ().getImage (Restart1);
	prepareImage (RestartPic1, this);
	ListInstructions = getToolkit ().getImage (Instructions);
	prepareImage (ListInstructions, this);
	PurplePic = getToolkit ().getImage (Purple);
	prepareImage (PurplePic, this);
	ClockPic = getToolkit ().getImage (Clock);
	prepareImage (ClockPic, this);
	LivesPic = getToolkit ().getImage (Lives);
	prepareImage (LivesPic, this);
	AboutTextPic1 = getToolkit ().getImage (AboutText1);
	prepareImage (AboutTextPic1, this);
  
	//Audio
	background = getAudioClip (getCodeBase (), "scrabbleMusic.wav");
	click = getAudioClip (getCodeBase (), "clicksound.wav");
	countDown = getAudioClip (getCodeBase (), "timer.wav");
	background.loop ();

	// Add the picture to the list of images to be tracked
	MediaTracker tracker = new MediaTracker (this);
	tracker.addImage (StartScreenPic, 0);
	tracker.addImage (StartSPlayPic, 0);
	tracker.addImage (StartSInsPic, 0);
	tracker.addImage (StartSAboutPic, 0);
	tracker.addImage (ScrabblePic, 0);
	tracker.addImage (BackgroundPic, 0);
	tracker.addImage (NextPlayerPic, 0);
	tracker.addImage (ScorePic, 0);
	tracker.addImage (NextPlayer2Pic, 0);
	tracker.addImage (framePic, 0);
	tracker.addImage (GameOverPic, 0);
	tracker.addImage (PlayAgainPic, 0);
	tracker.addImage (YesOrNoPic, 0);
	tracker.addImage (PlayTextPic, 0);
	tracker.addImage (PauseTextPic, 0);
	tracker.addImage (PauseButtonPic, 0);
	tracker.addImage (PauseText1Pic, 0);
	tracker.addImage (PlayButtonPic, 0);
	tracker.addImage (PlayText1Pic, 0);
	tracker.addImage (YesPic, 0);
	tracker.addImage (NoPic, 0);
	tracker.addImage (ResetPic, 0);
	tracker.addImage (ResetPic1, 0);
	tracker.addImage (MenuPic, 0);
	tracker.addImage (MenuPic1, 0);
	tracker.addImage (AboutTextPic, 0);
	tracker.addImage (InstructionTextPic, 0);
	tracker.addImage (NextBPic, 0);
	tracker.addImage (NextBPic1, 0);
	tracker.addImage (BackBPic, 0);
	tracker.addImage (BackBPic1, 0);
	tracker.addImage (ListInstructions, 0);
	tracker.addImage (PurplePic, 0);
	tracker.addImage (ClockPic, 0);
	tracker.addImage (LivesPic, 0);
	tracker.addImage (AboutTextPic1, 0);

	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	// If there were any errors loading the image, then abort the program with a message.
	if (tracker.isErrorAny ())
	{
	    showStatus ("Couldn't load ");
	    return;
	}
	addMouseListener (this);
	addMouseMotionListener (this);
	addKeyListener (this);
    }


    public void actionPerformed (ActionEvent ae)
    {
	if (isPauseBPressed == false)
	{
	    if (amount == 0)
	    {
		if (player1 == true)
		    count1++; //counts the number of times the player1 timer turns 0
		if (player2 == true)
		    count2++; //counts the number of times the player2 timer turns 0

		Next = true;
	    }
	    else
		amount--; //decreasing the timer
	}
	repaint ();
    }


    public void paint (Graphics g)
    {
	if (Screen1) //main screen
	{
	    g.drawImage (StartScreenPic, 0, 0, null); //startpage
	    if (PlayActive)//colored play button
		g.drawImage (StartSPlayPic, 0, 0, null);
	    if (InstructionActive)//colored instruction button
		g.drawImage (StartSInsPic, 0, 0, null);
	    if (AboutActive)//colored about button
		g.drawImage (StartSAboutPic, 0, 0, null);
	  
	}

	if (isInstruction) //Instruction pressed
	{
	    g.drawImage (BackgroundPic, 0, 0, null); //background
	    g.drawImage (BackBPic, 100, 545, null); //BackBPic
	    g.drawImage (NextBPic, 900, 550, null); //NextP
	    g.drawImage (InstructionTextPic, 200, 100, null); //InstructionsText
	    g.drawImage (ListInstructions, 190, 180, null); //InstructionsText

	    if (NextBActive)
		g.drawImage (NextBPic1, 909, 545, null); //NextBPic colored
	    if (BackBActive)
		g.drawImage (BackBPic1, 100, 548, null); //BackBPic colored
	}

	if (isPlayPressed || isPlayBPressed) //play pressed
	{
	    if (!gameOver)
	    {
		g.drawImage (BackgroundPic, 0, 0, null); //background
		g.drawImage (ScrabblePic, 150, 60, null); //scrabble board
		g.drawImage (PurplePic, 474, 297, null); //square in the middle
		g.drawImage (NextPlayerPic, 950, 500, null); //nextltters
		g.drawImage (ScorePic, 850, 100, null); //score
		g.drawImage (PauseTextPic, 1120, 10, null); //pause
		g.drawImage (PauseButtonPic, 1056, 14, null); //pause button
		g.drawImage (ResetPic, 950, 427, null); //reset button
		g.setFont (fontLetters1);
		g.drawString ("Player1", 160, 40);
		g.drawString ("Player2", 500, 40);
		g.setFont (font);
		g.drawString ("OR Back Space", 1050, 500);
		g.drawString ("OR Enter", 1050, 570);

		g.setFont (fontLetters);
		if (amount < 10) //timer turns red when less than 10 seconds left
		{
		    g.setColor (Color.RED);
		    g.drawString (amount + " Seconds", 900, 380);
		    g.drawImage (ClockPic, 890, 226, null);
		    countDown.play ();
		}
		else
		{
		    g.setColor (Color.BLACK);
		    g.drawString (amount + " Seconds", 900, 380);
		    countDown.stop ();
		}

		if (player1)
		{
		    if (count1 == 3 || oldScore >= 100) //timer turns 0 thrice or the score of 100 is reached
			gameOver = true;

		    if (Next)
		    {
			//for clearing the letters
			g.drawImage (BackgroundPic, 0, 590, null);
			letters (); //getting letters
			amount = 60; //resets the timer
			timer.setRepeats (true); //allows timer to restart
			timer.start (); //timer starts after next is pressed
			player1 = false;
			player2 = true;
			Next = false;
		    }
		    g.setFont (fontLetters);
		    g.setColor (Color.BLACK);
		    g.drawString ("Player1", 982, 635);

		    x_posLetter = 170;
		    square_position = 145;
		    g.setFont (fontLetters);
		    g.setColor (Color.BLACK);
		    //displying the assigned letters and their positions in frames
		    for (int index = 0 ; index < assignedLetters.length ; index++)
		    {
			g.drawImage (framePic, square_position, 585, null); //frame
			g.drawString (Character.toString (assignedLetters [index]), x_posLetter, 650);
			x_posLetter = x_posLetter + 100; //moves the letter
			square_position = square_position + 100; //moves the frame
		    }
		}
	       // g.setFont (font);
	       // g.drawString ("(" + xpos + "," + ypos + ")", xpos, ypos); //LISTS COORDINATES

		if (player2)
		{
		    if (count2 == 3 || oldScore2 >= 100) //timer turns 0 thrice or the score of 100 is reached
			gameOver = true;

		    if (Next)
		    {
			//for clearing everything
			g.drawImage (BackgroundPic, 0, 590, null);
			letters (); //getting letters
			amount = 60; //restarts the countdown
			timer.setRepeats (true); //allows timer to restart
			timer.start (); //timer starts after next is pressed

			player2 = false;
			player1 = true;
			Next = false;
		    }

		    g.setFont (fontLetters);
		    g.setColor (Color.BLACK);
		    g.drawString ("Player2", 982, 635);

		    //setting the assigned letters and their positions in the frames
		    x_posLetter = 170;
		    square_position = 145;
		    g.setFont (fontLetters);
		    for (int index = 0 ; index < assignedLetters2.length ; index++)
		    {
			g.drawImage (framePic, square_position, 585, null); //frame
			g.drawString (Character.toString (assignedLetters2 [index]), x_posLetter, 650);
			x_posLetter = x_posLetter + 100; //moves the letter
			square_position = square_position + 100; //moves the frame
		    }
		}

		if (NextActive)
		    g.drawImage (NextPlayer2Pic, 950, 497, null); //nextletters colored

		if (PauseBActive)
		    g.drawImage (PauseText1Pic, 1109, 2, null); //Pause colored

		if (ResetLActive)
		    g.drawImage (ResetPic1, 950, 427, null); //reset colored

		g.setColor (Color.BLACK);
		g.setFont (fontInfo1);
		y_posLetter = 80;
		for (int index = 0 ; index < alphabet.length ; index++) //printing the alphabet and their corresponding score
		{
		    g.drawString (Character.toString (alphabet [index]) + " - " + Integer.toString (fixedScore [index]), 40, y_posLetter);
		    y_posLetter = y_posLetter + 20;
		}

		g.setFont (fontLetters1);
		g.setColor (Color.BLUE);
		g.drawString ("Player 1: " + Integer.toString (oldScore), 997, 250); //prints score

		g.setFont (fontLetters1);
		g.setColor (Color.RED);
		g.drawString ("Player 2: " + Integer.toString (oldScore2), 997, 300); //prints score

		if (player1)
		    g.setColor (Color.BLUE);
		if (player2)
		    g.setColor (Color.RED);
		g.fillRect (x_pos, y_pos, width, height); //box for user to move around the board

		g.setFont (fontLetters1);
		g.setColor (Color.BLACK);
		for (int i = 0 ; i < j + 1 ; i++) //prints the letters on the board
		{
		    g.drawString (Character.toString (enteredLetters [i]), x_posLetters [i], y_posLetters [i]);
		}

		if (count1 == 0)
		{
		    g.drawImage (LivesPic, 300, 18, null);    
		    g.drawImage (LivesPic, 340, 18, null); 
		    g.drawImage (LivesPic, 380, 18, null); 
		}
		else if (count1 == 1)
		{
		    g.drawImage (LivesPic, 300, 18, null); 
		    g.drawImage (LivesPic, 340, 18, null); 
		}
		else if (count1 == 2)
		    g.drawImage (LivesPic, 300, 18, null);

		if (count2 == 0)
		{
		    g.drawImage (LivesPic, 640, 18, null);  
		    g.drawImage (LivesPic, 680, 18, null); 
		    g.drawImage (LivesPic, 720, 18, null); 
		}
		else if (count2 == 1)
		{
		    g.drawImage (LivesPic, 640, 18, null); 
		    g.drawImage (LivesPic, 680, 18, null);
		    }
		else if (count2 == 2)
		    g.drawImage (LivesPic, 640, 18, null); 
	    }

	    else if (gameOver)
	    {
		timer.stop ();
		g.drawImage (BackgroundPic, 0, 0, null); //background
		g.drawImage (ScorePic, 427, 208, null); //score
		g.drawImage (GameOverPic, 343, 75, null); //gameover
		g.drawImage (PlayAgainPic, 535, 431, null); //play again
		g.drawImage (YesOrNoPic, 566, 512, null); //yes or no

		if (YesActive)
		    g.drawImage (YesPic, 556, 512, null); //yes pic

		if (NoActive)
		    g.drawImage (NoPic, 770, 512, null); //no pic

		if (isYesPressed)
		{
		    resetGame (); //reset method
		    gameOver = false;
		    isYesPressed = false;
		}

		g.setFont (fontLetters);
		g.setColor (Color.BLUE);
		g.drawString ("Player 1: " + Integer.toString (oldScore), 500, 350); //prints player 1 score

		g.setFont (fontLetters);
		g.setColor (Color.RED);
		g.drawString ("Player 2: " + Integer.toString (oldScore2), 500, 410); //prints player 2 score

		if (count1 == 3 || oldScore2 >= 100)
		{
		    g.setColor (Color.ORANGE);
		    g.drawString ("Player 2 WINS", 500, 650); 
		}
		if (count2 == 3 || oldScore >= 100)
		{
		    g.setColor (Color.ORANGE);
		    g.drawString ("Player 1 WINS", 500, 650); 
		}
	    }
	}

	if (isPauseBPressed)
	{
	    g.drawImage (BackgroundPic, 0, 0, null); //background
	    g.drawImage (PlayTextPic, 588, 244, null); //play text
	    g.drawImage (PlayButtonPic, 448, 240, null); //play button
	    g.drawImage (RestartPic1, 515, 454, null); //restart button
	    g.drawImage (MenuPic, 470, 385, null); //menu button

	    if (PlayBActive)
		g.drawImage (PlayText1Pic, 588, 244, null); //play text colored

	    if (MenuActive)
		g.drawImage (MenuPic1, 470, 385, null);  //menu button

	    if (RestartActive)
		g.drawImage (RestartPic, 515, 454, null); //restart button
	}

	if (isInstructionPressed) //Instruction pressed
	{
	    g.drawImage (BackgroundPic, 0, 0, null); //background
	    g.drawImage (BackBPic, 100, 545, null); //back
	    g.drawImage (InstructionTextPic, 200, 100, null); //background

	    g.setColor (Color.BLACK);
	    g.setFont (fontInfo);
	    g.drawImage (ListInstructions, 240, 180, null); //InstructionsText

	    if (BackBActive)
		g.drawImage (BackBPic1, 100, 548, null); //NextP
	}

	if (isAboutPressed) //About pressed
	{
	    g.drawImage (BackgroundPic, 0, 0, null); //background
	    g.drawImage (BackBPic, 100, 545, null); //back button
	    g.drawImage (AboutTextPic, 200, 100, null); //about

	    g.drawImage (AboutTextPic1, 400, 150, null); //about text




	    if (BackBActive)
		g.drawImage (BackBPic1, 100, 548, null); //BackB
	}
     //   g.setFont (font);
    //    g.drawString ("(" + xpos + "," + ypos + ")", xpos, ypos); //LISTS COORDINATES
    }


    public void mouseMoved (MouseEvent me)
    {
	xpos = me.getX (); //checks x - position of the mouse
	ypos = me.getY (); //checks y - position of the mouse

	if (Screen1)
	{
	    // Checking if the mouse is in the rectangle
	    if (xpos >= 530 && xpos <= 790 && ypos >= 305 && ypos <= 424) //play
		PlayActive = true;
	    else
		PlayActive = false;

	    if (xpos >= 315 && xpos <= 1025 && ypos >= 435 && ypos <= 530)
		InstructionActive = true;
	    else
		InstructionActive = false;

	    if (xpos >= 485 && xpos <= 848 && ypos >= 570 && ypos <= 827)
		AboutActive = true;
	    else
		AboutActive = false;
	}

	if (isPlayPressed)
	{
	    if (xpos >= 960 && xpos <= 1060 && ypos >= 480 && ypos <= 545) //next
		NextActive = true;
	    else
		NextActive = false;

	    if (xpos >= 1058 && xpos <= 1270 && ypos >= 13 && ypos <= 67) //pause
		PauseBActive = true;
	    else
		PauseBActive = false;

	    if (xpos >= 953 && xpos <= 1195 && ypos >= 433 && ypos <= 477) //reset
		ResetLActive = true;
	    else
		ResetLActive = false;

	    if (xpos >= 433 && xpos <= 882 && ypos >= 235 && ypos <= 352) //playB
		PlayBActive = true;
	    else
		PlayBActive = false;
	}

	if (gameOver)
	{
	    if (xpos >= 570 && xpos <= 636 && ypos >= 520 && ypos <= 555) //yes
		YesActive = true;
	    else
		YesActive = false;

	    if (xpos >= 780 && xpos <= 825 && ypos >= 521 && ypos <= 544) //no
		NoActive = true;
	    else
		NoActive = false;
	}

	if (isPauseBPressed)
	{
	    if (xpos >= 474 && xpos <= 833 && ypos >= 390 && ypos <= 440) //menu
		MenuActive = true;
	    else
		MenuActive = false;

	    if (xpos >= 513 && xpos <= 806 && ypos >= 462 && ypos <= 507) //restart
		RestartActive = true;
	    else
		RestartActive = false;

	    if (xpos >= 433 && xpos <= 882 && ypos >= 235 && ypos <= 352) //playB
		PlayBActive = true;
	    else
		PlayBActive = false;

	    //////

	}

	if (isInstruction)
	{
	    if (xpos >= 915 && xpos <= 1093 && ypos >= 556 && ypos <= 615) //nextB
	    {
		amount = 60;
		NextBActive = true;
	    }
	    else
		NextBActive = false;
	}

	if (isInstruction || isInstructionPressed || isAboutPressed)
	{
	    if (xpos >= 105 && xpos <= 293 && ypos >= 555 && ypos <= 611) //back
		BackBActive = true;
	    else
		BackBActive = false;
	}

	repaint (); //show the results of the motion
    }


    public void mouseClicked (MouseEvent me)
    {
	//Saves the coordinates of the clicks
	xpos = me.getX ();
	ypos = me.getY ();

	if (Screen1)
	{
	    if (!isPlayPressed)
	    {
		// Check if the click was inside the rectangle area.
		if (xpos >= 530 && xpos <= 790 && ypos >= 305 && ypos <= 424) //play
		{
		    click.play ();
		    isPlayPressed = false;
		    isAboutPressed = false;
		    isInstruction = true;
		    Screen1 = false;
		    resetGame ();
		    gameOver = false;
		    isInstructionPressed = false;

		    player1 = true;
		    player2 = false;
		}
		else
		    isPlayPressed = false;
	    }

	    if (!isInstructionPressed || !isInstruction)
	    {
		if (xpos >= 315 && xpos <= 1025 && ypos >= 435 && ypos <= 530) //instructions
		{
		    click.play ();
		    isInstructionPressed = true;
		    isPlayPressed = false;
		    isAboutPressed = false;
		    Screen1 = false;
		}
		else
		    isInstructionPressed = false;
	    }

	    if (!isAboutPressed || !isInstruction)   //about
	    {
		if (xpos >= 485 && xpos <= 848 && ypos >= 570 && ypos <= 827) //about
		{
		    click.play ();
		    isAboutPressed = true;
		    isPlayPressed = false;
		    isInstructionPressed = false;
		    Screen1 = false;
		}
		else
		    isAboutPressed = false;
	    }
	}

	if (isPlayPressed || !isInstruction)  //play
	{
	    if (xpos >= 960 && xpos <= 1060 && ypos >= 480 && ypos <= 545) //next
	    {
		click.play ();
		if (newScore != 0)
		{
		    letters ();
		    Next = true;
		    if (player1)
			oldScore = oldScore;
		    if (player2)
			oldScore2 = oldScore2;
		    reWrite = j + 1;
		}
	    }
	    else
		isNextPressed = false;
	}

	if (isPlayPressed)
	{
	    if (xpos >= 1058 && xpos <= 1270 && ypos >= 13 && ypos <= 67) //Pause Button
	    {
		click.play ();
		isPauseBPressed = true;
		isPlayPressed = false;
	    }
	    else
		isPauseBPressed = false;
	}


	if (isPlayPressed)
	{
	    if (xpos >= 953 && xpos <= 1195 && ypos >= 433 && ypos <= 477) //reset letters
	    {
		click.play ();
		isResetPressed = true;
		isNextPressed = false;
		letters ();
	    }
	    else
		isResetPressed = false;
	}

	if (gameOver)
	{
	    if (xpos >= 570 && xpos <= 636 && ypos >= 520 && ypos <= 555) //play
	    {
		click.play ();
		isYesPressed = true;
		isPlayPressed = true;
	    }
	    else
		isYesPressed = false;
	}

	if (gameOver)
	{
	    if (xpos >= 780 && xpos <= 825 && ypos >= 521 && ypos <= 544) //no
	    {
		click.play ();
		Screen1 = true;
		isPlayPressed = false;
		isNoPressed = false;
	    }
	}

	if (isPauseBPressed)
	{
	    if (xpos >= 433 && xpos <= 882 && ypos >= 235 && ypos <= 352) //play
	    {
		click.play ();
		isPauseBPressed = false;
		isPlayPressed = true;
	    }
	}

	if (isPauseBPressed)
	{
	    if (xpos >= 474 && xpos <= 833 && ypos >= 390 && ypos <= 440) //menu
	    {
		click.play ();
		isNextPressed = false;
		isAboutPressed = false;
		isPlayPressed = false;
		isPauseBPressed = false;
		isInstructionPressed = false;
		Screen1 = true;
	    }
	}

	if (isPauseBPressed)
	{
	    if (xpos >= 513 && xpos <= 806 && ypos >= 462 && ypos <= 507) //restart
	    {
		click.play ();
		isPlayPressed = true;
		isPauseBPressed = false;
		resetGame ();
		player1 = true;
		player2 = false;
	    }
	}

	if (isInstruction || isInstructionPressed || isAboutPressed)
	{
	    if (xpos >= 105 && xpos <= 293 && ypos >= 555 && ypos <= 611) //back
	    {
		click.play ();
		isInstruction = false;
		Screen1 = true;
		isNextPressed = false;
		isAboutPressed = false;
		isPlayPressed = false;
		isInstructionPressed = false;
	    }
	}

	if (isInstruction)
	{
	    if (xpos >= 915 && xpos <= 1093 && ypos >= 556 && ypos <= 615) //nextB
	    {
		click.play ();
		isInstruction = false;
		isPlayPressed = true;
	    }
	}

	repaint ();
    }


    public void update (Graphics g)
    {
	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}

	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);
    }


    public void keyPressed (KeyEvent ke)
    {
	if (ke.getKeyCode () == ke.VK_RIGHT)
	{
	    // box is bounced if its x - position reaches the right border of the board
	    if (x_pos > rightBorder)
		x_pos -= 44; // Change direction of box
	    else
		x_pos += 44;
	}

	if (ke.getKeyCode () == ke.VK_LEFT)
	{
	    // box is bounced if its x - position reaches the left border of the board
	    if (x_pos < leftBorder)
		x_pos += 44; // Change direction of box movement
	    else
		x_pos -= 44;
	}

	if (ke.getKeyCode () == ke.VK_UP)
	{
	    // box is bounced if its y - position reaches the top border of the board
	    if (y_pos < 100)
		y_pos += 32; // Change direction of box
	    else
		y_pos -= 32;
	}

	if (ke.getKeyCode () == ke.VK_DOWN)
	{
	    // box is bounced if its y - position reaches the bottom border of the board
	    if (y_pos > 500)
		y_pos -= 32; // Change direction of box
	    else
		y_pos += 32;
	}

	if (ke.getKeyCode () == ke.VK_ENTER)
	{
	    click.play ();
	    if (isInstruction)
	    {
		isInstruction = false;
		Screen1 = false;
		isNextPressed = false;
		isAboutPressed = false;
		isPlayPressed = true;
		isInstructionPressed = false;
		Next = false;
		player1 = true;
		player2 = false;
		amount = 60;
	    }
	    if (isPlayPressed)
	    {
		if (newScore != 0)
		{
		    letters ();
		    Next = true;
		    if (player1)
			oldScore = newScore;
		    if (player2)
			oldScore2 = newScore2;
		    reWrite = j + 1;
		}
	    }
	}

	if (ke.getKeyCode () == ke.VK_BACK_SPACE)
	{
	    click.play ();
	    isResetPressed = true;
	    isNextPressed = false;
	    letters ();
	}

	if (ke.getKeyCode () == ke.VK_M)
	{
	    if (isPauseBPressed)
	    {
		click.play ();
		isNextPressed = false;
		isAboutPressed = false;
		isPlayPressed = false;
		isPauseBPressed = false;
		isInstructionPressed = false;
		Screen1 = true;
	    }
	}

	if (ke.getKeyCode () == ke.VK_R)
	{
	    if (isPauseBPressed)
	    {
		click.play ();
		isPlayPressed = true;
		isPauseBPressed = false;
		resetGame ();
		player1 = true;
		player2 = false;
	    }
	}

	if (ke.getKeyCode () == ke.VK_P)
	{
	    if (isPauseBPressed)
	    {
		click.play ();
		isPauseBPressed = false;
		isPlayPressed = true;
	    }
	}

	if (ke.getKeyCode () == ke.VK_SPACE)
	{
	    if (isPauseBPressed)
	    {
		click.play ();
		isPauseBPressed = false;
		isPlayPressed = true;
	    }
	}
	this.repaint ();
    }


    public void keyTyped (KeyEvent e)
    {
	input = e.getKeyChar (); //gets user input
	input = Character.toUpperCase (input);

	if (input != KeyEvent.CHAR_UNDEFINED) //checks if the character entered is a letter
	{
	    if (player1)
	    {
		for (int i = 0 ; i < assignedLetters.length ; i++)    //reads entered character and checks if it's part of the assigned letters
		{
		    if (input == assignedLetters [i]) //checks if the letter used is part of the assigned letters
		    {
			assignedLetters [i] = ' ';

			x = i; //saving position of i
			letterScore ();
			overRide (x);
			if (!overRide)
			{
			    enteredLetters [j] = input;
			    x_posLetters [j] = x_pos + 12;
			    y_posLetters [j] = y_pos + 26;
			    scoreLetters [j] = score;
			    j++;
			}
			addingScore ();
		    }
		}
	    }

	    if (player2)
	    {
		for (int i = 0 ; i < assignedLetters2.length ; i++)  //reads entered character and checks if it's part of the assigned letters
		{
		    if (input == assignedLetters2 [i]) //checks if the letter used is part of the assigned letters
		    {
			assignedLetters2 [i] = ' ';
			x = i; //saving position of i
			letterScore ();
			overRide (x);
			if (!overRide)
			{
			    enteredLetters [j] = input;
			    x_posLetters [j] = x_pos + 12;
			    y_posLetters [j] = y_pos + 26;
			    scoreLetters [j] = score;
			    j++;
			}
			addingScore ();
		    }
		}
	    }
	}
	this.repaint ();
    }


    public void addingScore ()
    {
	if (player1)
	    newScore = newScore + score;

	if (player2)
	    newScore2 = newScore2 + score;
    }


    public void letterScore ()  //assigns scores for each letter
    {
	if (input == 'A' || input == 'E' || input == 'I' || input == 'O' || input == 'U' || input == 'N' || input == 'S' || input == 'T' || input == 'R' || input == 'L')
	    score = 1;
	else if (input == 'D' || input == 'G')
	    score = 2;
	else if (input == 'B' || input == 'C' || input == 'M' || input == 'P')
	    score = 3;
	else if (input == 'F' || input == 'H' || input == 'V' || input == 'W' || input == 'Y')
	    score = 4;
	else if (input == 'K')
	    score = 5;
	else if (input == 'J' || input == 'X')
	    score = 8;
	else if (input == 'Q' || input == 'Z')
	    score = 10;
	else
	    score = 0;
    }


    public void letters ()
    {
	if (isResetPressed)
	{
	    if (player1)
	    {
		for (int i = 0 ; i < 7 ; i++) //generates new letters when back is pressed
		{
		    randomInt = generator.nextInt (26);
		    assignedLetters [i] = alphabet [randomInt];
		}
	    }
	    if (player2)
	    {
		for (int i = 0 ; i < 7 ; i++) //generates new letters when back is pressed
		{
		    randomInt2 = generator.nextInt (26);
		    assignedLetters2 [i] = alphabet [randomInt2];
		}
	    }
	}

	else
	{
	    if (player1)
	    {
		for (int i = 0 ; i < 7 ; i++) //generates letters when next is pressed
		{
		    if (assignedLetters [i] == ' ')
		    {
			randomInt = generator.nextInt (26);
			assignedLetters [i] = alphabet [randomInt];
		    }
		}
	    }
	    if (player2)
	    {
		for (int i = 0 ; i < 7 ; i++) //generates letters when next is pressed
		{
		    if (assignedLetters2 [i] == ' ')
		    {
			randomInt2 = generator.nextInt (26);
			assignedLetters2 [i] = alphabet [randomInt2];
		    }
		}
	    }
	}
    }


    public void overRide (int x)  //writes over the previous letter
    {
	for (int i = 0 ; i <= j ; i++)
	{
	    if (x_posLetters [i] == x_pos + 12 && y_posLetters [i] == y_pos + 26) //checks x and y position on the board
	    {
		if (i <= reWrite) //before next
		{
		    overRide = true;
		    if (player1)
			assignedLetters [x] = enteredLetters [i]; //turns old letter to new letter

		    if (player2)
			assignedLetters2 [x] = enteredLetters [i]; //turns old letter to new letter

		    enteredLetters [i] = input;
		    x_posLetters [i] = x_pos + 12;
		    y_posLetters [i] = y_pos + 26;
		    if (player1)
			newScore = newScore - scoreLetters [i]; //subtracts the score of the previously entered letter for player 1
		    if (player2)
			newScore2 = newScore2 - scoreLetters [i]; //subtracts the score of the previously entered letter for player 2
		    scoreLetters [i] = score;
		}
		else if (i > reWrite)
		    overRide = true;
	    }
	    else
		overRide = false;
	}
    }


    public void resetGame ()//method called when whole game is restarted
    {
	x_pos = 474;          // x - Position of the box
	y_pos = 297;       // y - Position of the box
	enteredLetters = new char [255];
	x_posLetters = new int [255];
	y_posLetters = new int [255];
	scoreLetters = new int [255];
	newScore = 0;
	oldScore = 0;
	newScore2 = 0;
	oldScore2 = 0;
	count1 = 0;
	count2 = 0;
	j = 0;
	int[] fixedScore = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10}; //score according to the letters in array alphabet
	int[] countLetter = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //The number of times each letter is
	char[] assignedLetters = {' ', ' ', ' ', ' ', ' ', ' ', ' '};  //array to keep track of letters
	char[] assignedLetters2 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};  //array to keep track of letters
	player1 = true;
	player2 = true;
	letters ();
	amount = 60;
	timer.setRepeats (true); //allows the counter to restart
	timer.start (); //starts the timer
	player2 = false;
    }


    // None these are needed for the actual code, so they're left alone for now
    public void keyReleased (KeyEvent e)
    {
    }


    public void mouseDragged (MouseEvent me)
    {
    }


    public void mousePressed (MouseEvent me)
    {
    }


    public void mouseReleased (MouseEvent me)
    {
    }


    public void mouseEntered (MouseEvent me)
    {
    }


    public void mouseExited (MouseEvent me)
    {
    }
} // StartPage class



