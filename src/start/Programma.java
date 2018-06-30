package start;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import saving.GameSave;
import saving.Saver;
import sound.SoundMixer;
import components.*;
import keyActions.KeyAction3;
import layout.Theme;
import timers.GameSaveLoadTimer;


public class Programma extends JPanel implements SwingConstants{
	
	final static int LAYOUT_FULL = 2;
	final static int LAYOUT_THEME_SCREEN = 1;
	
	static String[] gameModes = { "normal", "hardcore"};
	public static String gameMode = "normal";
	static String[] musicProfiles = { "random","semi-random","select" };
	static String[] difficulties = { "easy","medium","hard", "too hard"};
	static String[] randomnesses = { "low", "mean", "high", "random mess"};
	public static String difficulty = "medium";
	public static String randomness = "low";
	public static int difficultyLevel = 5; // 3, 5, 7, 10
	public static int randomnessLevel = 0; // 0, 1, 2, 3
	static Disco disco = new Disco();

	static SoundRing soundRing;
	
	public static Credits intro;
	
	public static Dimension fullscreen;
	
	static boolean isFullScreen = false;
	public static boolean isGameRunning = false;
	public static boolean isFromeEscape = false;
	//public static boolean scopeLock = false;
	
	public static int paneWidth; 
	public static int paneHeight;
	public static double dpiScale;
    static boolean firstSettingsTime = true;
    public static boolean isNavScreen = true;
    
    static KeyAction3 navKeys = new KeyAction3(KeyAction3.NAV);
	
    public static Font helvetica;
    public static Font helvetica100;
    public static Font helvetica32;
    public static Font helvetica20;
    public static Font helvetica16;
    public static Font helvetica64;
    public static Font helvetica48;
    public static Font helvetica4;
    public static Font helvetica3;
    public static Font helvetica8;
	private static Font helveticaPlain64;
    public static Font fixedsys;
    public static Content gameContainer;
    public static String musicProfile = "random";
    public static String currentScreen;
	static JTextField typingArea;
	
	public static VolumeSelector masterVolumeSelector;
	public static VolumeSelector musicVolumeSelector;
	public static VolumeSelector soundVolumeSelector;
	public static VolumeSelector backgroundVolumeSelector;

	public static JFrame frame;
	//static JLabel label = new JLabel("lol", CENTER);
	//static JLabel gameModeLabel = new JLabel("  ", CENTER);
	//static JLabel gameModeLabel2 = new JLabel("Gamemode:", CENTER);
	//static JLabel musicProfileLabel = new JLabel("  ", CENTER);
	//static JLabel musicProfileLabel2 = new JLabel("Music Profile:", CENTER);
	//static JLabel difficultyLabel = new JLabel("  ", CENTER);
	//static JLabel difficultyLabel2 = new JLabel("Difficulty:", CENTER);
	//static JLabel soundLabel1 = new JLabel("Disable", CENTER);
	//static JLabel soundLabel2  = new JLabel("Sound Effects", CENTER);
	static JLabel settingsFrame = new JLabel("");
	static JLabel dieLabel = new JLabel("<html><CENTER> You didn't survive!! <CENTER><html>",CENTER);
	static JLabel dieLabel1 = new JLabel("Some reason",CENTER);
	static JLabel completedLabel = new JLabel("<html><CENTER> Congratulations!! <CENTER><html>",CENTER);
	static JLabel completedLabel1 = new JLabel("<html><CENTER>You completed the game.<CENTER><html>",CENTER);
	public static JLabel header = new JLabel("Settings",CENTER);

	static ColorText title;
	static JLabel title1 = new JLabel("<html><center> For Some <br> Reason <center><html>",CENTER);
	static JLabel title2 = new JLabel("<html><center> For Some <br> Reason <center><html>",CENTER);
	static JLabel devStatus = new JLabel("<html><center>alpha<center><html>",RIGHT);

	static JLabel escapeTitle = new JLabel("<html><center> For Some Reason <center><html>",CENTER);
	static JLabel escapeTitle2 = new JLabel("<html><center> For Some Reason <center><html>",CENTER);

	static Border settingsBorder;
	//static Border border = BorderFactory.createLineBorder(darkOrange,8);
	//static Border musicBorder1 = BorderFactory.createLineBorder(darkerGreen,1);
	//static Border musicBorder2 = BorderFactory.createTitledBorder(musicBorder1, "Select Background Music");

	public static JEditorPane name;
	public static JLabel definition;
	
	static EasyImage picLabel;
	static EasyButton aboutButton = new EasyButton("About", "about");
	static EasyButton settingsButton = new EasyButton("Settings", "settings");
	static EasyButton mainMenuButton = new EasyButton("Main Menu", "main");
	static EasyButton resumeButton = new EasyButton("Resume Game", "resume");
	static EasyButton resumeAfterDeathButton = new EasyButton("Resume Game", "resumeAfterDeath");
	static EasyButton playAgainButton = new EasyButton("Play again", "play");
	static EasyButton playButton = new EasyButton("Play", "play");
	static EasyButton quitButton = new EasyButton("Quit", "quit");
	static EasyButton enableMusicButton = new EasyButton("Disable Music", "enableMusic");
	static EasyButton enableSoundEffectsButton = new EasyButton("Disable Sound Effects", "enableSound");
	static EasyButton continueButton = new EasyButton("Continue", "continue");
	static EasyButton musicButton = new EasyButton("Choose Music", "chooseMusic");
	static EasyButton themeButton = new EasyButton("Choose Theme", "chooseTheme");
	static EasyButton backButton = new EasyButton("Back", "escape");
	static EasyButton loadButton = new EasyButton("Load", "load");
	static EasyButton playGameSaveButton = new EasyButton("Play game", "playGameSave");
	static EasyButton deleteGameSaveButton = new EasyButton("Delete", "deleteGameSave");
	static EasyButton refreshGameSaveButton = new EasyButton("Refresh", "refreshGameSave");
	static EasyButton createGameSaveButton = new EasyButton("Create", "createGameSave");

	static EasyImage loadGear;

	public static Theme currentTheme;
	
	static ArrayList<EasyButton> musicButtons = new ArrayList<EasyButton>();
	
	/*static EasyButton FSR_theme = new EasyButton("For Some Reason theme song", "FSR_theme");
	static EasyButton Pirates = new EasyButton("Pirates Of The Caribbean", "Pirates Of The Caribbean Theme Song");
	static EasyButton Poep = new EasyButton("Poep in je hoofd", "poep in je hoofd");
	static EasyButton Ranger = new EasyButton("Ranger's Apprentice Main Map", "Ranger");
	static EasyButton Clocks = new EasyButton("Clocks", "Clocks");
	static EasyButton Rondo = new EasyButton("Rondo Capriccioso", "introduction et rondo capriccioso");
	static EasyButton Mountain_King = new EasyButton("In the Hall of the Mountain King", "In the Hall of the Mountain King");
	static EasyButton Kinessa_Song = new EasyButton("Kinessa Song", "Kinessa Song");
	static EasyButton imBlue = new EasyButton("I'm Blue", "I'm Blue");*/
	
	static EasyCombo gameModeChooser = new EasyCombo (gameModes, 250,60);
	static EasyCombo musicProfileChooser = new EasyCombo (musicProfiles, 320,60);
	static EasyCombo difficultyChooser = new EasyCombo (difficulties, 320,60);
	static EasyCombo randomnessChooser = new EasyCombo (randomnesses, 320,60);
	public static boolean isDieScreen;

	public static long playTime;
	public static int deathCount;
	public static long beginGameTime;

	public static boolean isQuit = false;

	public static GameSave selectedGameSave;
	public static GameSaveLoadTimer gslt = new GameSaveLoadTimer();

	//static EasyCombo difficultyChooser = new EasyCombo (difficulties, 320,60);
	
	
	//static KeyAction escapeAction = new KeyAction(KeyEvent.VK_ESCAPE,"escape" );
	//static PictureMaker pic1 = new PictureMaker("Images/Formule lol.PNG");
	//static PictureMaker picture = new PictureMaker("images/Formule%20lol.png");
	
	//private static boolean drawImage;
	
	public static void resetLayout(int part){
		
		switch(part){
		case 2:
			//title.setForeground(currentTheme.title1Color);
			//title2.setForeground(currentTheme.title2Color);
			title.setColors(currentTheme.title1Color, currentTheme.title2Color);
			escapeTitle.setForeground(currentTheme.title1Color);
			escapeTitle2.setForeground(currentTheme.title2Color);
			devStatus.setForeground(currentTheme.devStatusColor);
			
			gameModeChooser.recolor();
			musicProfileChooser.recolor();
			difficultyChooser.recolor();
			randomnessChooser.recolor();
			
			masterVolumeSelector.recolor();
			musicVolumeSelector.recolor();
			soundVolumeSelector.recolor();
			backgroundVolumeSelector.recolor();
			
			aboutButton.setRightLayout();
			mainMenuButton.setRightLayout();
			resumeButton.setRightLayout();
			resumeAfterDeathButton.setRightLayout();
			playAgainButton.setRightLayout();
			playButton.setRightLayout();
			quitButton.setRightLayout();
			enableMusicButton.setRightLayout();
			enableSoundEffectsButton.setRightLayout();
			continueButton.setRightLayout();
			musicButton.setRightLayout();
			backButton.setRightLayout();

			name.setBackground(currentTheme.backgroundColor);
			name.setForeground(currentTheme.foregroundColor);
			definition.setForeground(currentTheme.buttonBorderColor.darker());
			
		case 1:
			header.setBackground(currentTheme.buttonBgColor);
			header.setForeground(currentTheme.foregroundColor);
			header.setBorder(EasyButton.border);
			
			settingsBorder = BorderFactory.createLineBorder(currentTheme.buttonBorderColor,12*paneWidth/1920);
			settingsFrame.setBorder(settingsBorder);
			settingsFrame.setBackground(currentTheme.backgroundColor);
			settingsFrame.setForeground(currentTheme.foregroundColor);
			
			settingsButton.setRightLayout();
		}
		
		
		
		for(EasyButton musicButton : musicButtons){
			musicButton.setRightLayout();
		}
	}
	
	public static void settingsScreen(Container pane, String string){
		pane.setLayout(null);
		pane.removeAll();
		
		gameModeChooser.setSelected(false);
				
		randomnessChooser.setSelected(false);
		
		//if(string != "random" && string != "semi-random" && string != "select"){
		
		if(firstSettingsTime){
			firstSettingsTime = false;
			difficultyChooser.setSelectedIndex(1);
		}
		difficultyChooser.setSelected(false);
		
		
/*		
		gameModeLabel.setOpaque(true);
		gameModeLabel.setSize(4*paneWidth/20, 7*paneHeight/40);
		gameModeLabel.setLocation(13*paneWidth/20, 7*paneHeight/40);
		gameModeLabel.setBackground(Color.orange);
		gameModeLabel.setLabelFor(gameModeChooser);
		gameModeLabel.setBorder(border);
		
		gameModeChooser.setComboFont(helvetica32);
		gameModeChooser.setBoxSize(400,400);
		gameModeChooser.setText("Gamemode");
		gameModeChooser.setLocation(13*paneWidth/20 + gameModeLabel.getWidth()/2 - 125, 7*paneHeight/40 + gameModeLabel.getHeight()/2 );
		
		gameModeLabel2.setOpaque(true);
		gameModeLabel2.setFont(helvetica32);
		gameModeLabel2.setSize(paneWidth/6, paneHeight/16);
		gameModeLabel2.setLocation(13*paneWidth/20 + gameModeLabel.getWidth()/2 -paneWidth/12, 7*paneHeight/40  + gameModeLabel.getHeight()/10);
		gameModeLabel2.setBackground(Color.orange); 
		
		musicProfileLabel.setOpaque(true);
		musicProfileLabel.setSize(4*paneWidth/20, 7*paneHeight/40);
		musicProfileLabel.setLocation(13*paneWidth/20, 16*paneHeight/40 );
		musicProfileLabel.setBackground(Color.orange);
		musicProfileLabel.setLabelFor(gameModeChooser);
		musicProfileLabel.setBorder(border);
		
		musicProfileChooser.setComboFont(helvetica32);
		musicProfileChooser.setOpaque(true);
		musicProfileChooser.setLayout(null);
		musicProfileChooser.setLocation(13*paneWidth/20 + musicProfileLabel.getWidth()/2 - 160, 16*paneHeight/40 + musicProfileLabel.getHeight()/2);
		
		musicProfileLabel2.setOpaque(true);
		musicProfileLabel2.setFont(helvetica32);
		musicProfileLabel2.setSize(paneWidth/6, paneHeight/16);
		musicProfileLabel2.setLocation(13*paneWidth/20 + musicProfileLabel.getWidth()/2 - paneWidth/12 , 16*paneHeight/40 + musicProfileLabel.getHeight()/10);
		musicProfileLabel2.setBackground(Color.orange);
		
		difficultyLabel.setOpaque(true);
		difficultyLabel.setSize(4*paneWidth/20, 7*paneHeight/40);
		difficultyLabel.setLocation(8*paneWidth/20, 7*paneHeight/40 );
		difficultyLabel.setBackground(Color.orange);
		difficultyLabel.setLabelFor(gameModeChooser);
		difficultyLabel.setBorder(border);
		
		difficultyChooser.setComboFont(helvetica32);
		difficultyChooser.setOpaque(true);
		difficultyChooser.setLayout(null);
		difficultyChooser.setLocation(8*paneWidth/20 + difficultyLabel.getWidth()/2 - 160, 7*paneHeight/40 + difficultyLabel.getHeight()/2);
		
		difficultyLabel2.setOpaque(true);
		difficultyLabel2.setFont(helvetica32);
		difficultyLabel2.setSize(paneWidth/6, paneHeight/16);
		difficultyLabel2.setLocation(8*paneWidth/20 + difficultyLabel.getWidth()/2 - paneWidth/12 , 7*paneHeight/40 + difficultyLabel.getHeight()/10);
		difficultyLabel2.setBackground(Color.orange);
		*/
        //musicProfileChooser.setSelectedIndex(0);
		
        
		
		
		//pane.add(difficultyLabel2);
		//pane.add(difficultyLabel);
		
		//pane.add(musicProfileLabel2);
		
		//pane.add(gameModeLabel2);
		//pane.add(gameModeLabel);
		if(isFromeEscape){
			setAsEscapeButton(backButton);
	        pane.add(backButton);
		}
		else{
	        setAsEscapeButton(mainMenuButton);
	        pane.add(mainMenuButton);
		}
		
		
        
        enableMusicButton.setButtonSize(4*paneWidth/20, 2*paneHeight/40);
        enableMusicButton.setLocation(3*paneWidth/20, 30*paneHeight/80 - paneHeight/150);
        enableMusicButton.setButtonFont(helvetica48);
        if(SoundMixer.isMusicEnabled){
        	enableMusicButton.setText("Disable Music");
        }
        else{
        	enableMusicButton.setText("Enable Music");
        }
        
        enableSoundEffectsButton.setButtonSize(4*paneWidth/20, 2*paneHeight/40);
        enableSoundEffectsButton.setLocation(3*paneWidth/20, 17*paneHeight/40 + paneHeight/150);
        enableSoundEffectsButton.setButtonFont(helvetica48);
        if(SoundMixer.isMusicEnabled){
        	enableSoundEffectsButton.setText("Disable Sound Effects");
        }
        else{
        	enableSoundEffectsButton.setText("Enable Sound Effects");
        }
        
        musicButton.setButtonSize(4*paneWidth/20, 4*paneHeight/40 + 2*paneHeight/150);
        musicButton.setLocation(8*paneWidth/20, 15*paneHeight/40 - paneHeight/150);
        musicButton.setButtonFont(helvetica48);
        
        themeButton.setButtonSize(4*paneWidth/20, 4*paneHeight/40 + 2*paneHeight/150);
        themeButton.setLocation(13*paneWidth/20, 15*paneHeight/40 - paneHeight/150);
        themeButton.setButtonFont(helvetica48);
        
        if(musicButtons.size() == 0){
        	
        	musicButtons.add(new MusicButton("For Some Reason theme song", "FSR_theme"));
			musicButtons.add(new MusicButton("Pirates Of The Caribbean", "Pirates Of The Caribbean Theme Song"));
			musicButtons.add(new MusicButton("Poep in je hoofd", "poep in je hoofd"));
			musicButtons.add(new MusicButton("Ranger's Apprentice Main Map", "Ranger"));
			musicButtons.add(new MusicButton("Clocks", "Clocks"));
			musicButtons.add(new MusicButton("Rondo Capriccioso", "introduction et rondo capriccioso"));
			musicButtons.add(new MusicButton("In the Hall of the Mountain King", "In the Hall of the Mountain King"));
			musicButtons.add(new MusicButton("Kinessa Song", "Kinessa Song"));
			musicButtons.add(new MusicButton("I'm Blue", "I'm Blue"));
			musicButtons.add(new MusicButton("Viva La Vida", "Viva_La_Vida"));
			musicButtons.add(new MusicButton("Summertime","K_391_Summertime"));
			
			//musicButtons.add(new EasyButton());
			
			musicButtons.get(0).setMouseCommand("FSR_theme");
			musicButtons.get(1).setMouseCommand("Pirates Of The Caribbean Theme Song");
			musicButtons.get(2).setMouseCommand("poep in je hoofd");
			musicButtons.get(3).setMouseCommand("Ranger");
			musicButtons.get(4).setMouseCommand("Clocks");
			musicButtons.get(5).setMouseCommand("introduction et rondo capriccioso");
			musicButtons.get(6).setMouseCommand("In the Hall of the Mountain King");
			musicButtons.get(7).setMouseCommand("Kinessa Song");
			musicButtons.get(8).setMouseCommand("I'm Blue");
			musicButtons.get(9).setMouseCommand("Viva_La_Vida");
			musicButtons.get(10).setMouseCommand("K_391_Summertime");


			
			int x = 3;
			int y = 18;
			
			for(int i = 0; i < musicButtons.size(); i += 3){
				
				for(int j = 0; j < musicButtons.size() - i; j++){
					musicButtons.get(i+j).setButtonFont(helvetica64);
					musicButtons.get(i+j).setButtonSize(4*paneWidth/20, 2*paneHeight/25);
					musicButtons.get(i+j).setLocation(x*paneWidth/20, y*paneHeight/40);
					x += 5;
				}
				x = 3;
				y += 4;
			}
			
	        
	        
			
			musicProfileChooser.setSelected(false);
	        
        }
        
        
        /*JLabel musicLabel2 = new JLabel("Select Your Background Music", CENTER);
        JLabel musicLabel = new JLabel("");
        musicLabel2.setFont(helvetica64);
        musicLabel.setOpaque(true);
        musicLabel2.setOpaque(true);
        musicLabel.setBackground(darkerGreen);
        musicLabel2.setBackground(darkerGreen);
        musicLabel2.setBounds(paneWidth/2 -200, 24*paneHeight/40 + 10, 400, 30);
        musicLabel.setBounds(5*paneWidth/40, 24*paneHeight/40, 30*paneWidth/40, 11*paneHeight/40);
        
        /*FSR_theme.setRightBorder();
		Pirates.setRightBorder();
		Poep.setRightBorder();
		Ranger.setRightBorder();
		Rondo.setRightBorder();
		Clocks.setRightBorder();
		Mountain_King.setRightBorder();
		imBlue.setRightBorder();
		Kinessa_Song.setRightBorder();
		
		//KeyAction escapeKey1 = new KeyAction();
       // frame.add(escapeKey1);
        pane.add(Pirates);
        pane.add(Poep);
        pane.add(FSR_theme);
        pane.add(Ranger);
        pane.add(Clocks);
        pane.add(Rondo);
        pane.add(Mountain_King);
        pane.add(Kinessa_Song);
        pane.add(imBlue); */
        
        header.setText("Settings");
        musicButton.setRightLayout();
        themeButton.setRightLayout();
        
        if(SoundMixer.isSoundEnabled){
			enableSoundEffectsButton.setText("Disable Sound Effects");
		} 
		else{
			enableSoundEffectsButton.setText("Enable Sound Effects");
		}
        

		pane.add(masterVolumeSelector);
		pane.add(musicVolumeSelector);
		pane.add(soundVolumeSelector);
		pane.add(backgroundVolumeSelector);
        
		pane.add(navKeys);
		
		pane.add(musicButton);
		pane.add(themeButton);

        pane.add(header);
        //pane.add(mainMenuButton);
        pane.add(enableMusicButton);
        pane.add(difficultyChooser);
        pane.add(randomnessChooser);
        //pane.add(musicProfileChooser);
		pane.add(gameModeChooser);
        //pane.add(soundLabel2);
        pane.add(enableSoundEffectsButton); 
        //pane.add(musicLabel2);
        //pane.add(musicLabel);
        pane.add(settingsFrame);
        pane.add(picLabel);
       // KeyAction escapeKey = new KeyAction();
       // pane.add(escapeKey);
        pane.repaint();
	}
	
	public static void startScreen(Container pane, String string) {
		//boolean picture = false;
		pane.setLayout(null);
		pane.removeAll();
		pane.setBackground(null);
		
		if(string == "start"){
			        
			settingsBorder = BorderFactory.createLineBorder(currentTheme.buttonBgColor,12*paneWidth/1920);
	        picLabel = new EasyImage("Images/een tropisch eiland .png", paneWidth, paneHeight);
			settingsFrame.setOpaque(true);
			//settingsFrame.setBackground(darkGreen);
			settingsFrame.setBounds(paneWidth/10, paneHeight/10, 8*paneWidth/10, 8*paneHeight/10);
			settingsFrame.setBorder(settingsBorder);

			
			continueButton.setBounds(19*paneWidth/24, 21*paneHeight/24, paneWidth/6, 2*paneHeight/24);
			continueButton.setFont(helvetica32);
			continueButton.setCursor(null);
			
			aboutButton.setOpaque(true); //content panes must be opaque
	        aboutButton.setLayout(null);
	        aboutButton.setButtonSize(3*paneWidth/17, 3*paneHeight/34 - 10);
	        aboutButton.setLocation(3*paneWidth/8 - aboutButton.getWidth()/2, 7*paneHeight/8 - aboutButton.getHeight()/2 + 3*paneHeight/68 + 5 );
	        aboutButton.setButtonFont(helvetica32);
	        
	        
	        header.setOpaque(true);
	        header.setBackground(currentTheme.buttonBgColor);
	        header.setBorder(EasyButton.border);
	        header.setFont(helvetica20);
	        header.setSize(8*paneWidth/20, 4*paneHeight/40);
	        header.setLocation(6*paneWidth/20, 2*paneHeight/40);

	        title = new ColorText("<html><center> For Some <br> Reason <center><html>", currentTheme.title1Color, currentTheme.title2Color, helvetica4, 7*paneWidth/1920);
			title.setSize(paneWidth, 3*paneHeight/4);

	        /*title.setOpaque(false);
	        //title.setForeground(Color.RED);
	        title.setFont(helvetica4);
	        title.setSize(paneWidth, 3*paneHeight/4);
	        title.setLocation(0, 0);
	        
	        title2.setOpaque(false);
	        //title2.setForeground(currentTheme.title2Color);
	        title2.setFont(helvetica4);
	        title2.setSize(paneWidth, 3*paneHeight/4);
	        title2.setLocation(7*paneWidth/1920, 7*paneWidth/1920);*/
	        
	        devStatus.setOpaque(false);
	        devStatus.setFont(helvetica20);
	        devStatus.setSize(paneWidth/5, paneHeight/10);
	        devStatus.setLocation(2*paneWidth/3, 15*paneHeight/22);
	        
	        escapeTitle.setOpaque(false);
	        //escapeTitle.setForeground(Color.RED);
	        escapeTitle.setFont(helvetica8);
	        escapeTitle.setSize(paneWidth, paneHeight/5);
	        escapeTitle.setLocation(0, 0);
	        
	        escapeTitle2.setOpaque(false);
	        //escapeTitle2.setForeground(EasyButton.darkOrange);
	        escapeTitle2.setFont(helvetica8);
	        escapeTitle2.setSize(paneWidth, paneHeight/5);
	        escapeTitle2.setLocation(3*paneWidth/1920, 3*paneWidth/1920);
	        
	        masterVolumeSelector = new VolumeSelector("Master Volume", 13*paneWidth/40, 7*paneHeight/40, VolumeSelector.MASTER_VOLUME);
	        masterVolumeSelector.setLocation(3*paneWidth/20, 20*paneHeight/40);
			
			musicVolumeSelector = new VolumeSelector("Music Volume", 13*paneWidth/40, 7*paneHeight/40, VolumeSelector.MUSIC_VOLUME);
	        musicVolumeSelector.setLocation(21*paneWidth/40, 20*paneHeight/40);
			
			soundVolumeSelector = new VolumeSelector("Sound Effect Volume", 13*paneWidth/40, 7*paneHeight/40, VolumeSelector.SOUND_VOLUME);
	        soundVolumeSelector.setLocation(3*paneWidth/20, 28*paneHeight/40);
			
			backgroundVolumeSelector = new VolumeSelector("Background Sound Volume", 13*paneWidth/40, 7*paneHeight/40, VolumeSelector.BACKGROUND_VOLUME);
	        backgroundVolumeSelector.setLocation(21*paneWidth/40, 28*paneHeight/40);
	        
	        
	        musicProfileChooser.setComboFont(helvetica32);
			musicProfileChooser.setBoxSize(4*paneWidth/20, 7*paneHeight/40);
			musicProfileChooser.setText("Music Profile:");
			musicProfileChooser.setLocation(8*paneWidth/20, 8*paneHeight/40);
			
			difficultyChooser.setComboFont(helvetica32);
			difficultyChooser.setBoxSize(4*paneWidth/20, 7*paneHeight/40);
			difficultyChooser.setText("Difficulty:");
			difficultyChooser.setLocation(8*paneWidth/20, 7*paneHeight/40);
	        
			randomnessChooser.setComboFont(helvetica32);
			randomnessChooser.setBoxSize(4*paneWidth/20, 7*paneHeight/40);
			randomnessChooser.setText("Randomness:");
			randomnessChooser.setLocation(13*paneWidth/20, 7*paneHeight/40);
			
			gameModeChooser.setComboFont(helvetica32);
			gameModeChooser.setBoxSize(4*paneWidth/20, 7*paneHeight/40);
			gameModeChooser.setText("Gamemode:");
			gameModeChooser.setLocation(3*paneWidth/20, 7*paneHeight/40);

			soundRing = new SoundRing();

			name = new JEditorPane();
			name.setContentType("text/html");
			name.setFont(helveticaPlain64);
			name.setBackground(currentTheme.backgroundColor);
			name.setForeground(currentTheme.foregroundColor);
			name.setLocation(254*paneWidth/1920, 60*paneWidth/1920 + 3*paneHeight/10);
			name.setSize(1412*paneWidth/1920, 6*paneHeight/10 - 3*24*paneWidth/1920);
			name.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
			name.setText("<HTML> <b>Description</b> <br>" +
					"You barely survived a plane crash, but now you are stuck on a lonely island. " +
					"Try to get off this island full of dangers by making the <br>right decisions and fighting animals and monsters. " +
					"Can you reach the civilised world?<br>" +
					"<br>" +
					"<b>Hotkeys</b> <br>" +
					"Press \"P\" to play (again).<br>" +
					"Press \"R\" to resume the game.<br>" +
					"Press \"Esc\" to pause the game.<br>" +
					"Press \"Enter\" to continue in the game, this is equivalent to pressing the on-screen \"Continue\" button. <br>" +
					"Use the number keys \"1\", \"2\", \"3\" and \"4\" to make decisions, this is equivalent to pressing one of the buttons on your screen, from left <br>to right respectively.<br>" +
					"Other hotkeys will be explained within the game.<br>" +
					"<br>" +
					"<b>About the name</b> <br>" +
					"The name of this game is why the name is what it is, but what's in the game?" +
					"<HTML>" );
			name.setEditable(false);
			name.setAutoscrolls(true);
			name.repaint();
			name.setIgnoreRepaint(true);
			name.setFocusable(false);
			name.setOpaque(true);

			definition = new JLabel("<HTML><i> \"Used to convey that one does not know the reason for a particular situation, often with the implication that one finds it strange or surprising.\"</i><HTML>", JLabel.CENTER);
			definition.setFont(helvetica32);
			definition.setOpaque(false);
			definition.setForeground(currentTheme.buttonBorderColor.darker());
			definition.setLocation(254*paneWidth/1920, 3*paneHeight/20);
			definition.setSize(1412*paneWidth/1920, 2*paneHeight/10 );

			//loadGear = new EasyImage("Images/Gear.gif", paneWidth/3, paneWidth/3);
			//loadGear.setLocation(paneWidth/3, 3*paneHeight/5);

			if(Saver.gameSaves.size() > 0){
				selectedGameSave = Saver.gameSaves.get(0);
			}
			
			//used for saying that you do not know why something happened, especially when you think there is no good reason for it
	        //Used to convey that one does not know the reason for a particular situation, often with the implication that one finds it strange or surprising.
			
		} 	        
	       
	    settingsButton.setOpaque(true); //content panes must be opaque
	    settingsButton.setLayout(null);
	    settingsButton.setButtonSize(3*paneWidth/17, 3*paneHeight/34 - 10);
	    settingsButton.setLocation(3*paneWidth/8 - settingsButton.getWidth()/2, 7*paneHeight/8 - settingsButton.getHeight()/2 - 3*paneHeight/68 - 5 );
	    settingsButton.setButtonFont(helvetica32);
	    settingsButton.setRightLayout();
	        
	    playButton.setButtonSize(3*paneWidth/17, 3*paneHeight/17);
	    playButton.setLocation(7*paneWidth/8 - playButton.getWidth()/2, 7*paneHeight/8 - playButton.getHeight()/2 );
	    playButton.setLayout(null);
	    playButton.setOpaque(true);
	    playButton.setButtonFont(helvetica16);
	    playButton.setRightLayout();
	        
	    quitButton.setButtonSize(3*paneWidth/17, 3*paneHeight/17);
	    quitButton.setLocation(paneWidth/8 - quitButton.getWidth()/2, 7*paneHeight/8 - quitButton.getHeight()/2);
	    quitButton.setLayout(null);
	    quitButton.setOpaque(true);
	    quitButton.setButtonFont(helvetica16);
	    quitButton.setRightLayout();
	    aboutButton.setRightLayout();

	    if(isGameRunning){
			resumeButton.setText("Resume");
			resumeButton.setButtonSize(3*paneWidth/17, 3*paneHeight/34 - 10);
			resumeButton.setLocation(5*paneWidth/8 - resumeButton.getWidth()/2, 7*paneHeight/8 - resumeButton.getHeight()/2 -3*paneHeight/68 - 5 );
			resumeButton.setButtonFont(helvetica32);
			resumeButton.setSelected(false);
			pane.add(resumeButton);

			loadButton.setButtonSize(3*paneWidth/17, 3*paneHeight/34 - 10);
			loadButton.setLocation(5*paneWidth/8 - loadButton.getWidth()/2, 7*paneHeight/8 - loadButton.getHeight()/2 + 3*paneHeight/68 + 5 );
			loadButton.setButtonFont(helvetica32);
			loadButton.setSelected(false);
			loadButton.setRightLayout();
		}
		else {
			loadButton.setButtonSize(3*paneWidth/17, 3*paneHeight/17);
			loadButton.setLocation(5*paneWidth/8 - loadButton.getWidth()/2, 7*paneHeight/8 - loadButton.getHeight()/2);
			loadButton.setButtonFont(helvetica16);
			loadButton.setSelected(false);
			loadButton.setRightLayout();
		}

	    
	    pane.add(loadButton);
        pane.add(settingsButton);
        pane.add(aboutButton);
        pane.add(devStatus);
		pane.add(title);
		//pane.add(title2);
        pane.add(playButton);
        pane.add(quitButton);
        pane.add(navKeys);
        pane.add(picLabel);
        //KeyAction escapeKey = new KeyAction();
        //pane.add(escapeKey);
        pane.repaint();

        SoundMixer.stopSound();
        SoundMixer.stopBackground();
        SoundMixer.resumeMusic();
        
    }
	
	public static void musicScreen(Container pane){
		pane.setLayout(null);
		pane.removeAll();
		
		header.setText("Choose your music");
		
		for(EasyButton musicButton : musicButtons){
			musicButton.setRightLayout();
			pane.add(musicButton);
		}
		
		/*settingsButton.setButtonSize(4*paneWidth/20, 3*paneHeight/40);
		settingsButton.setLocation(paneHeight/80, 73*paneHeight/80);
		settingsButton.setButtonFont(helvetica32);
		//settingsButton.setButtonBorder(EasyButton.border);
		settingsButton.setRightLayout();*/
		setAsEscapeButton(settingsButton);

		//soundRing.paint(frame.getGraphics());
		soundRing.start();
		soundRing.repaint();


		pane.add(musicProfileChooser);
		pane.add(header);
		//pane.add(soundRing);
		pane.add(settingsButton);
		pane.add(settingsFrame);
		pane.add(picLabel);
		pane.add(navKeys);
		pane.repaint();
	}
	
	public static void themeScreen(Container pane){
		pane.removeAll();
		Theme.classic.demo.setLocation(3*paneWidth/20, 7*paneHeight/40);
		Theme.cyber.demo.setLocation(8*paneWidth/20, 7*paneHeight/40);
		Theme.gamingRed.demo.setLocation(13*paneWidth/20, 7*paneHeight/40);
		Theme.darkPurple.demo.setLocation(3*paneWidth/20, 16*paneHeight/40);
		Theme.monochrome.demo.setLocation(8*paneWidth/20, 16*paneHeight/40);
		Theme.bright.demo.setLocation(13*paneWidth/20, 16*paneHeight/40);
		
		/*settingsButton.setButtonSize(4*paneWidth/20, 3*paneHeight/40);
		settingsButton.setLocation(paneHeight/80, 73*paneHeight/80);
		settingsButton.setButtonFont(helvetica32);
		settingsButton.setButtonBorder(EasyButton.border);
		settingsButton.setRightLayout();*/
		setAsEscapeButton(settingsButton);
		
		header.setText("Choose your theme");
		
		pane.add(Theme.classic.demo);
		pane.add(Theme.cyber.demo);
		pane.add(Theme.gamingRed.demo);
		pane.add(Theme.darkPurple.demo);
		pane.add(Theme.monochrome.demo);
		pane.add(Theme.bright.demo);

		pane.add(header);
		pane.add(settingsButton);
		pane.add(settingsFrame);
		pane.add(picLabel);
		pane.add(navKeys);
		pane.repaint();
	}
	public static void showTheme(Container pane, Theme theme){
		
		EasyButton.border = BorderFactory.createLineBorder(theme.buttonBorderColor, 8*paneHeight/1080);
		EasyButton.mouseBorder = BorderFactory.createLineBorder(theme.buttonBorderColor, 10*paneHeight/1080);
		
		header.setBackground(theme.buttonBgColor);
		header.setForeground(theme.foregroundColor);
		header.setBorder(EasyButton.border);
		
		settingsBorder = BorderFactory.createLineBorder(theme.buttonBorderColor,12*paneWidth/1920);
		settingsFrame.setBorder(settingsBorder);
		settingsFrame.setBackground(theme.backgroundColor);
		settingsFrame.setForeground(theme.foregroundColor);
		
		settingsButton.setBackground(theme.buttonBgColor);
		settingsButton.setForeground(theme.foregroundColor);
		settingsButton.setBorder(EasyButton.border);
		
		
		//pane.add(Theme.classic.demo);
		//pane.add(Theme.cyber.demo);
		//pane.add(Theme.gamingRed.demo);
		//pane.add(header);
		//pane.add(settingsButton);
		//pane.add(settingsFrame);
		//pane.add(picLabel);
		//pane.add(navKeys);
		pane.repaint();
	}
	
	public static void aboutScreen(Container pane, String string) {
		pane.removeAll();
		
		header.setText("About");
		
		/*mainMenuButton.setButtonSize(4*paneWidth/20, 3*paneHeight/40);
        mainMenuButton.setLocation(paneHeight/80, 73*paneHeight/80);
        mainMenuButton.setButtonFont(helvetica32);
        mainMenuButton.setButtonBorder(EasyButton.border);
        mainMenuButton.setRightLayout();*/
		setAsEscapeButton(mainMenuButton);

		//Used to convey that one does not know the reason for a particular situation, often with the implication that one finds it strange or surprising.


		pane.add(header);
		pane.add(definition);
		pane.add(name);
		pane.add(mainMenuButton);
		pane.add(settingsFrame);
		pane.add(picLabel);
		pane.add(navKeys);
		pane.repaint();
	}

	public static void loadScreen(Container pane) {
		pane.removeAll();

		header.setText("Load");


		if(selectedGameSave != null){
			JLabel name = new JLabel("<html>" + selectedGameSave.name + "<html>", JLabel.CENTER);
			name.setSize(paneWidth/3, paneHeight/9);
			name.setLocation(4*Programma.paneWidth/30 - 8*Programma.paneHeight/1080, 10*Programma.paneHeight/60 - 8*Programma.paneHeight/1080);
			name.setFont(helvetica20);
			name.setForeground(currentTheme.buttonBorderColor.darker());
			pane.add(name);




			JPanel imageFrame = new JPanel();
			imageFrame.setSize(paneWidth/3 + 16*Programma.paneHeight/1080, paneHeight/3 + 16*Programma.paneHeight/1080);
			imageFrame.setLocation(4*Programma.paneWidth/30 - 8*Programma.paneHeight/1080, 17*Programma.paneHeight/60 - 8*Programma.paneHeight/1080);
			//EasyImage test = new EasyImage(Saver.gameSaves.get(0).image, 430, 300);
			//test.setLocation(200, 200);

			selectedGameSave.easyImage.setLocation(8*Programma.paneHeight/1080, 8*Programma.paneHeight/1080);

			imageFrame.add(selectedGameSave.easyImage);
			imageFrame.setBackground(Programma.currentTheme.buttonBorderColor);
			pane.add(imageFrame);

			int seconds = (int)((selectedGameSave.playTime/1000) % 60);

			int minutes = (int)(selectedGameSave.playTime/1000 - seconds)/60;

			JLabel gameInfo;
			if(selectedGameSave == Saver.currentGame){
				gameInfo = new JLabel("<html>Last played: " + selectedGameSave.dateLastPlayed + "<br>Total playtime: " + minutes + " minutes and " + seconds + " seconds.<br>currently in-game<html>");
			}
			else {
				gameInfo = new JLabel("<html>Last played: " + selectedGameSave.dateLastPlayed + "<br>Total playtime: " + minutes + " minutes and " + seconds + " seconds.<html>");
			}
			gameInfo.setFont(helvetica48);
			//gameInfo.setVerticalAlignment(JLabel.TOP);
			gameInfo.setForeground(currentTheme.foregroundColor);
			gameInfo.setSize(paneWidth/3, paneHeight/9);
			gameInfo.setLocation(4*Programma.paneWidth/30 - 8*Programma.paneHeight/1080, 38*Programma.paneHeight/60 - 8*Programma.paneHeight/1080);
			pane.add(gameInfo);
		}

		JPanel bar1 = new JPanel();
		bar1.setSize(12*Programma.paneWidth/1920, 8*paneHeight/10);
		bar1.setLocation(5*paneWidth/10 - bar1.getWidth()/2, 1*paneHeight/10);
		bar1.setOpaque(true);
		bar1.setBackground(currentTheme.buttonBorderColor);

		JPanel bar2 = new JPanel();
		bar2.setSize(4*paneWidth/10, 12*Programma.paneWidth/1920);
		bar2.setLocation(5*paneWidth/10, 7*paneHeight/10);
		bar2.setOpaque(true);
		bar2.setBackground(currentTheme.buttonBorderColor);



		playGameSaveButton.setFont(helvetica32);
		playGameSaveButton.setButtonSize(paneWidth/6 , paneHeight/10 );
		playGameSaveButton.setLocation(4*Programma.paneWidth/30 - 8*Programma.paneHeight/1080, 3*Programma.paneHeight/4);
		playGameSaveButton.setRightLayout();

		deleteGameSaveButton.setFont(helvetica32);
		deleteGameSaveButton.setButtonSize(paneWidth/6 , paneHeight/10 );
		deleteGameSaveButton.setLocation(9*Programma.paneWidth/30 + 8*Programma.paneHeight/1080, 3*Programma.paneHeight/4);
		deleteGameSaveButton.setRightLayout();

		createGameSaveButton.setFont(helvetica32);
		createGameSaveButton.setButtonSize(paneWidth/6 , paneHeight/10 );
		createGameSaveButton.setLocation(16*Programma.paneWidth/30 - 8*Programma.paneHeight/1080, 3*Programma.paneHeight/4);
		createGameSaveButton.setRightLayout();

		refreshGameSaveButton.setFont(helvetica32);
		refreshGameSaveButton.setButtonSize(paneWidth/6 , paneHeight/10 );
		refreshGameSaveButton.setLocation(21*Programma.paneWidth/30 + 8*Programma.paneHeight/1080, 3*Programma.paneHeight/4);
		refreshGameSaveButton.setRightLayout();

		/*mainMenuButton.setButtonSize(4*paneWidth/20, 3*paneHeight/40);
        mainMenuButton.setLocation(paneHeight/80, 73*paneHeight/80);
        mainMenuButton.setButtonFont(helvetica32);
        mainMenuButton.setButtonBorder(EasyButton.border);
        mainMenuButton.setRightLayout();*/
		Saver.list.setRightLayout();
		setAsEscapeButton(mainMenuButton);

		//Used to convey that one does not know the reason for a particular situation, often with the implication that one finds it strange or surprising.

		pane.add(refreshGameSaveButton);
		pane.add(createGameSaveButton);
		pane.add(deleteGameSaveButton);
		pane.add(playGameSaveButton);
		pane.add(header);
		pane.add(Saver.list);
		pane.add(bar1);
		pane.add(bar2);
		pane.add(mainMenuButton);
		pane.add(settingsFrame);
		pane.add(picLabel);
		pane.add(navKeys);
		pane.repaint();
	}

	public static void gameIsLoadingScreen(Container pane){
		pane.removeAll();

		ColorText label = new ColorText("Game is loading.", currentTheme.title1Color, currentTheme.title2Color, helvetica8, paneHeight/216);
		label.setSize(paneWidth, paneHeight);

		pane.add(label);
		pane.add(picLabel);
		pane.repaint();
	}
	
	public static void escapeScreen(Container pane, String string) {
		pane.removeAll();
		pane.setLayout(null);
		pane.setBackground(null);
		showCursor();
		gameContainer.pauseGame();
		
		quitButton.setButtonSize(paneWidth/3, paneHeight/10);
        quitButton.setLocation(paneWidth/3, 16*paneHeight/20);
        quitButton.setButtonFont(helvetica20);
        quitButton.setSelected(false);
        
        settingsButton.setButtonSize(paneWidth/3, paneHeight/10);
        settingsButton.setLocation(paneWidth/3, 13*paneHeight/20);
        settingsButton.setButtonFont(helvetica20);
        settingsButton.setBorder(EasyButton.border);
        settingsButton.setSelected(false);
        
        mainMenuButton.setButtonSize(paneWidth/3, paneHeight/10);
        mainMenuButton.setLocation(paneWidth/3, 10*paneHeight/20);
        mainMenuButton.setButtonFont(helvetica20);
        mainMenuButton.setSelected(false);

		resumeButton.setText("Resume Game");
        resumeButton.setButtonSize(paneWidth/3, paneHeight/10);
        resumeButton.setLocation(paneWidth/3, 4*paneHeight/20);
        resumeButton.setButtonFont(helvetica20);
        resumeButton.setSelected(false);
        
        playAgainButton.setButtonSize(paneWidth/3, paneHeight/10);
        playAgainButton.setLocation(paneWidth/3, 7*paneHeight/20);
        playAgainButton.setButtonFont(helvetica20);
        playAgainButton.setSelected(false);
        
        pane.add(escapeTitle);
        pane.add(escapeTitle2);
        pane.add(mainMenuButton);
        pane.add(playAgainButton);
        pane.add(resumeButton);
        pane.add(quitButton);
        pane.add(settingsButton);
		pane.add(picLabel);
		pane.add(navKeys);
		pane.repaint();
	}

	public static void dieScreen(Container pane, String string, boolean isResume){
		pane.removeAll();
		pane.setLayout(null);
		pane.setBackground(null);
		pane.setBackground(Color.BLACK);
		showCursor();
		isDieScreen = true;
		SoundMixer.stopSound();
		SoundMixer.stopBackground();
		SoundMixer.playSound("Sound/Sad_Trombone.wav");
		//SoundMixer.resumeMusic();
		SoundMixer.fadeSoundToMusic(1000);
		deathCount++;
        //SoundMixer.fadeInMusic(1000);
		gameContainer.player.setActive(false);
		if(gameContainer.death != null){
			gameContainer.death.dontDie();
		}
		
		dieLabel.setOpaque(true);
		dieLabel.setBounds(0, 0, paneWidth, paneHeight/3);
		dieLabel.setFont(helvetica8);
		dieLabel.setForeground(Color.RED);
		dieLabel.setBackground(Color.BLACK);
		
		dieLabel1.setOpaque(true);
		dieLabel1.setBounds(0, paneHeight/3, paneWidth, paneHeight/3);
		dieLabel1.setFont(helvetica16);
		dieLabel1.setForeground(Color.RED);
		dieLabel1.setBackground(Color.BLACK);
		dieLabel1.setText("<html><CENTER>" + string + "<CENTER><html>");
		
		mainMenuButton.setButtonFont(helvetica32);
		resumeAfterDeathButton.setButtonFont(helvetica32);
		playAgainButton.setButtonFont(helvetica32);
		
		if(gameMode == "normal" && isResume){
			mainMenuButton.setBounds(paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 7*paneWidth/25, 3*paneHeight/17);
			playAgainButton.setBounds(9*paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 7*paneWidth/25, 3*paneHeight/17);
			resumeAfterDeathButton.setBounds(17*paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 7*paneWidth/25, 3*paneHeight/17);
			pane.add(resumeAfterDeathButton);
		}
		else{ 
			mainMenuButton.setBounds(paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 11*paneWidth/25, 3*paneHeight/17);
			playAgainButton.setBounds(13*paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 11*paneWidth/25, 3*paneHeight/17);
		}
		mainMenuButton.setRightLayout();
		resumeAfterDeathButton.setRightLayout();
		playAgainButton.setRightLayout();
		
		pane.add(playAgainButton);
		pane.add(mainMenuButton);
		pane.add(dieLabel1);
		pane.add(dieLabel);
		pane.add(navKeys);
	}
	
	public static void completedScreen(Container pane){
		pane.removeAll();
		pane.setLayout(null);
		pane.setBackground(null);
		pane.setBackground(Color.YELLOW);
		SoundMixer.stopSound();
		SoundMixer.playBackground("Sound/ode-an-die-freude.wav");
		
		completedLabel.setBounds(0, 0, paneWidth, paneHeight/3);
		completedLabel.setFont(helvetica8);
		//completedLabel.setForeground(Color.RED);
		//completedLabel.setBackground(Color.BLACK);
		
		completedLabel1.setBounds(0, paneHeight/3, paneWidth, paneHeight/4);
		completedLabel1.setFont(helvetica16);
		//completedLabel1.setForeground(Color.BLACK);
		
		mainMenuButton.setBounds(paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 11*paneWidth/25, 3*paneHeight/17);
		playAgainButton.setBounds(13*paneWidth/25, 7*paneHeight/8 - quitButton.getHeight()/2, 11*paneWidth/25, 3*paneHeight/17);
		
		mainMenuButton.setButtonFont(helvetica32);
		resumeAfterDeathButton.setButtonFont(helvetica32);
		playAgainButton.setButtonFont(helvetica32);
		
		mainMenuButton.setRightLayout();
		resumeAfterDeathButton.setRightLayout();
		playAgainButton.setRightLayout();
		
		pane.add(completedLabel);
		pane.add(completedLabel1);
		pane.add(playAgainButton);
		pane.add(mainMenuButton);
		pane.add(navKeys);
	}
	

	public static void createAndShowGUI(String string){
        //Create and set up the window.
		//String currentScreen = null; 
		
		if(string.startsWith("\"")){
			gameContainer.text(string);
			isNavScreen = false;
			isDieScreen = false;
		}
		else if(string.startsWith("@")){
			gameContainer.content(string.substring(1));
			isNavScreen = false;
			isDieScreen = false;
		}
		else if(string.startsWith("*~")){
            gameContainer.dontDie();
			dieScreen(frame.getContentPane(), string.substring(2), false);

			isNavScreen = true;
			isDieScreen = true;
		}
		else if(string.startsWith("*")){
            gameContainer.dontDie();
			dieScreen(frame.getContentPane(), string.substring(1), true);

			isNavScreen = true;
			isDieScreen = true;
		}
		else{
			isDieScreen = false;
			switch(string){
			case "start":




				//System.out.println("scaling factor loadFrame: " + loadFrame.getGraphicsConfiguration().getDefaultTransform().getScaleX());

				//double scale = loadFrame.getGraphicsConfiguration().getDefaultTransform().getScaleX();

                frame = new JFrame("For Some Reason");

                /*if(scale > 1.01){
					GraphicsConfiguration config = loadFrame.getGraphicsConfiguration();
					config.getDefaultTransform().setToScale(1.000, 1.000);
					//Graphics2D gr = (Graphics2D) frame.getGraphics();
					//gr.setTransform(new AffineTransform(float 1.0, float 0.0, float 0.0, float 1.0, float 0.0, float 0.0));


                    frame.getGraphicsConfiguration();
                    frame = new JFrame(config);
                    frame.setTitle("For Some Reason");
				}
				else {

                }*/
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

	        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	//frame.setSize(new Dimension(1920, 1080));
				frame.setUndecorated(true);
				frame.setVisible(true);
				//Saver.main(null);

				//frame.getGraphicsConfiguration().getDefaultTransform().scale(0.8, 0.8);
                //frame.getContentPane().getGraphicsConfiguration().getDefaultTransform().setToScale(0.8, 0.8);


				/*Graphics2D g = (Graphics2D)frame.getContentPane().getGraphics();
				AffineTransform t = g.getTransform();
				double scaling = t.getScaleX(); // Assuming square pixels :P
				t.setToScale(1, 1);
				g.setTransform(t);*/


				
	        	//frame.setSize(960, 540);					
					

	        	/*Dimension frameSize = frame.getSize();
	        	Insets insets = frame.getInsets();
	        	double fakePaneWidth = frameSize.getWidth();
	        	double fakePaneHeight = frameSize.getHeight();
				System.out.println("fakePaneWidth: " + fakePaneWidth + "\t fakePaneHeight: " + fakePaneHeight);


				//calculate real width/height
	        	paneWidth = (int) (fakePaneWidth - (insets.left + insets.right));
	        	paneHeight = (int) (fakePaneHeight - (insets.top + insets.bottom));


	        	System.out.println("Insets hor " + insets.left + " " + insets.right + "\t ver " + insets.top + " " + insets.bottom);
				paneWidth = ProgrammaRunTool.loadFrame.getWidth();
				paneHeight = ProgrammaRunTool.loadFrame.getHeight();
				System.out.println("paneWidth: " + paneWidth + "\t paneHeight: " + paneHeight);
				*/

				deriveFonts();
	        	fullscreen = new Dimension(paneWidth, paneHeight);
	        	Theme.start();
	    		currentTheme = Theme.classic;

	    		dpiScale = frame.getGraphicsConfiguration().getDefaultTransform().getScaleX();

                //System.out.println("scaling factor frame: " + frame.getGraphicsConfiguration().getDefaultTransform().getScaleX());

	        	gameContainer = new Content(paneWidth, paneHeight, "begin",frame.getContentPane());
	        	//gameContainer.loadGame();
	        	System.out.println(paneHeight + "   " + paneWidth );
	        	EasyButton.resetLayout();
	        	Credits.main();
				intro = new Credits(frame.getContentPane());

	        	ShooterScreen.main(null);
	    		WordScreen.main(null);
	    		TextScreen.main(null);
	    		Saver.main(null);
	    		
	    		

	        	BufferedImage icon;
				try {
					icon = ImageIO.read(new File("Images/logo.png"));
					frame.setIconImage(icon);
				} catch (IOException e){e.printStackTrace();}
				finally {
				}

				startScreen(frame.getContentPane(),string);
	        	currentScreen = "main";
                SoundMixer.begin();
	        	resetLayout(LAYOUT_FULL);

	        	//frame.setContentPane(null);
	        	
	        	//KeyAction musicKey = new KeyAction(KeyEvent.VK_ESCAPE);
	        	//frame.getContentPane().add(musicKey);
	        	
	        	//startScreen(frame.getContentPane(),string);
	        	//settingsScreen(frame.getContentPane(),string);
	        	
	        	//KeyAction escapeKey = new KeyAction();
		        //frame.add(escapeKey);

                ProgrammaRunTool.loadFrame.setVisible(false);
	        	break;
	
			case "main":
				mainMenuButton.setRightLayout();
				currentScreen = string;
				startScreen(frame.getContentPane(),string);
				//isGameRunning = false;
				isFromeEscape = false;
				if(!SoundMixer.isMusicRunning()){
					SoundMixer.fadeSoundToMusic(500);
				}
				SoundMixer.stopSound();
				//KeyAction escapeKey = new KeyAction();
		        //frame.add(escapeKey);
				 break;
		        
			case "escape":
				if(!Loader.isLoadingReady){
					break;
				}
				isNavScreen = true;
				isFromeEscape = true;
				playTime = playTime + System.currentTimeMillis() - beginGameTime;
				Saver.save();
				gameContainer.pauseDying();
				gameContainer.player.setActive(false);
				escapeScreen(frame.getContentPane(), string);currentScreen = string;  break;
				
			case "enableMusic": 
				//SoundMixer.isMusicEnabled = !SoundMixer.isMusicEnabled; 
				SoundMixer.enableMusic();
				if(SoundMixer.isMusicEnabled){
					enableMusicButton.setButtonText("Disable Music");
				} 
				else{
					enableMusicButton.setButtonText("Enable Music");	
				}
				if(currentScreen != "settings"){
					break;
				}
				enableMusicButton.repaint();
				
			/*
			case "FSR_theme":
			case "Pirates Of The Caribbean Theme Song":
			case "poep in je hoofd":
			case "Ranger":
			case "Clocks":
			case "introduction et rondo capriccioso":
			case "In the Hall of the Mountain King":
			case "Kinessa Song":
			case "I'm Blue":	 */
			case "settings": settingsButton.setRightLayout(); settingsScreen(frame.getContentPane(),string); currentScreen = string;  settingsScreen(frame.getContentPane(),string);
	          break;

	        case "load": loadScreen(frame.getContentPane()); currentScreen = string; break;
	          
			case "chooseMusic": musicScreen(frame.getContentPane()); currentScreen = string; break;
			
			case "chooseTheme": themeScreen(frame.getContentPane()); currentScreen = string; break;
			
			case "enableSound": 
				SoundMixer.enableSound();
				//System.out.println("sound on: " + SoundMixer.isSoundEnabled);
				
				//enableSoundEffectsButton.repaint();
				createAndShowGUI("settings"); break;
				//settingsScreen(frame.getContentPane(),string); break;
			
			case "quit":
			    SoundMixer.terminate();
				if(!Saver.isSaving){
					Launcher.terminate();
				}
				else {
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(false);
					isQuit = true;
				}
				break;
			
			case "random": 
			case "semi-random": 
			case "select": SoundMixer.setMusicProfile(string); musicScreen(frame.getContentPane()); break;
			
			case "resume": if(isGameRunning){gameContainer.resume(); currentScreen = "game"; isNavScreen = false; } break;
			
			case "resumeAfterDeath": if(isGameRunning){gameContainer.resumeAfterDeath(); currentScreen = "game"; isNavScreen = false; } break;
			
			case "play":
				gameContainer.run();
				deathCount = 0;
				beginGameTime = System.currentTimeMillis();
				playTime = 0;
				currentScreen = "game";
				isGameRunning = true;
				isNavScreen = false;
				break;
			
			case "completed": isGameRunning = false; completedScreen(frame.getContentPane()); break;
			
			case "continue":
				continueButton.setRightLayout();
				//System.out.println(continueButton.isFocusable());
				if(isGameRunning){
					gameContainer.continueGame();
					frame.getContentPane().repaint();
				}

				//continueButton.setButtonCommand("continue");
					
				break;
				
			case "intro": intro.run(); break;
			
			case "about": aboutScreen(frame.getContentPane(),string); currentScreen = string; aboutButton.setRightLayout(); break;
			
			case "die": dieScreen(frame.getContentPane(),"For some reason you died", true); currentScreen = string; break;

			case "playGameSave":
				gameIsLoadingScreen(frame.getContentPane());
				selectedGameSave.play();
				gslt.start();
				break;

			case "deleteGameSave":
				selectedGameSave.delete();
				int i;
				for(i = 0; i < Saver.gameSaves.size(); i++){
					if(selectedGameSave == Saver.gameSaves.get(i)){
						break;
					}
				}
				Saver.gameSaves.remove(selectedGameSave);
				Saver.list.remove(selectedGameSave.button);
				selectedGameSave = Saver.gameSaves.get(i - 1);
				loadScreen(frame.getContentPane());
				break;

			case "normal":
			case "hardcore": gameMode = string; settingsScreen(frame.getContentPane(),string); break;
			
			case "easy": difficulty = string; difficultyLevel = 3;  settingsScreen(frame.getContentPane(),string); break;
			case "medium": difficulty = string; difficultyLevel = 5;  settingsScreen(frame.getContentPane(),string); break;
			case "hard": difficulty = string; difficultyLevel = 7;  settingsScreen(frame.getContentPane(),string); break;
			case "too hard": difficulty = string; difficultyLevel = 10;  settingsScreen(frame.getContentPane(),string); break;
			
			case "low": randomness = string; randomnessLevel = 0;  settingsScreen(frame.getContentPane(),string); break;
			case "mean": randomness = string; randomnessLevel = 1;  settingsScreen(frame.getContentPane(),string); break;
			case "high": randomness = string; randomnessLevel = 2;  settingsScreen(frame.getContentPane(),string); break;
			case "random mess": randomness = string; randomnessLevel = 3;  settingsScreen(frame.getContentPane(),string); break;
			
			case "drinkSea": gameContainer.player.drinkSea(); gameContainer.content("coco"); break;
			
			case "disco":
			}
		
		}
        
		
		// Set this root pane as the root pane in your JFrame window.
		//frame.setRootPane(rootPane);
		/*
		if(string == "escape"){
			escapeScreen(frame.getContentPane(), string);
			frame.setVisible(true);
		}
		else if(string == "start" ){
			

			
			frame = new JFrame("For Some Reason");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       // startScreenMusic();
	        frame.setUndecorated(true);
	        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.setVisible(true);
	        
	        
			Dimension frameSize = frame.getSize();
	        Insets insets = frame.getInsets();
	        double fakePaneWidth = frameSize.getWidth();
	        double fakePaneHeight = frameSize.getHeight();

	        //calculate real width/height
	        paneWidth = (int) (fakePaneWidth - (insets.left + insets.right));
	        paneHeight = (int) (fakePaneHeight - (insets.top + insets.bottom));
	        System.out.println(paneHeight + "   " + paneWidth );
	        
			gameContainer = new Content(paneWidth, paneHeight);
			//frame.setContentPane(gameContainer);
			
	        
	        startScreen(frame.getContentPane(),string);
	        //KeyAction escapeKey = new KeyAction();
	        //frame.add(escapeKey);
            //EasyButtonMaker newContent = new EasyButtonMaker("Red Button", "red", 100, 100, 200, 200, Color.red);
            //frame.getContentPane().add(newContent, null);
	        //frame.setSize(1600,1000);
	        currentScreen = "main";
	        
	        
		}
		else if(string == "settings"){
			settingsScreen(frame.getContentPane(),string);
		}
		else if(string == "main"){
			
			

			
	        
	        startScreen(frame.getContentPane(),"main");
	       KeyAction escapeKey = new KeyAction();
	        frame.add(escapeKey);
	       // frame.requestFocusInWindow();
	        
	        
	       
            //EasyButtonMaker newContent = new EasyButtonMaker("Red Button", "red", 100, 100, 200, 200, Color.red);
            //frame.getContentPane().add(newContent, null);
	        //frame.setSize(1600,1000);
	        currentScreen = "main";
		}
		else if(string == "continue"){
			startScreen(frame.getContentPane(),string);
		}
		else if(string == "red" || string == "green"){
			startScreen(frame.getContentPane(),string);
            //EasyButtonMaker newContent = new EasyButtonMaker("Red Button", "red", 100, 100, 200, 200, Color.red);
            //frame.getContentPane().add(newContent, null);
		}
		else if(string == "play"){
			//frame.setVisible(true);
			//currentScreen = "secondPane";
			//secondPane(frame.getContentPane(),string);
			//KeyAction escapeKey = new KeyAction();
	        //frame.add(escapeKey);
			//frame.setUndecorated(true);
			
			//frame.setUndecorated(true);
			//gameContainer = new Content(paneWidth, paneHeight);
			frame.setContentPane(gameContainer);
		}

		else if(string == "enableMusic"){
			settingsScreen(frame.getContentPane(),string);
			SoundMixer.isMusicEnabled = !SoundMixer.isMusicEnabled; 
			SoundMixer.enableMusic();
			if(SoundMixer.isMusicEnabled){
				enableMusicButton.setButtonText("Disable Music");
			} 
			else{
				enableMusicButton.setButtonText("Enable Music");
			}
		}
		//else if(string == "FSR_theme"){
		//	SoundMixer.mainMusic(string, false);
		//}
		
        //frame.setLayout(null);
		else if(string == "hardcore" || string == "normal"){
			settingsScreen(frame.getContentPane(),string);
		}
		else if(string == "quit"){
			
			Launcher.terminate();
			//frame.setVisible(false);
			
		}
		else if(string == "about"){
			SoundMixer.randomMusic("Clocks","FSR_theme");
		}
		*/
		
           //Display the window.
			//
            //frame.setLocationRelativeTo(null);
		
        //frame.setVisible(true);
		
		
        
		/*
        Dimension frameSize = frame.getSize();
        Insets insets = frame.getInsets();

      
        
        double fakePaneWidth = frameSize.getWidth();
        double fakePaneHeight = frameSize.getHeight();

        //calculate real width/height
        paneWidth = (int) (fakePaneWidth - (insets.left + insets.right));
        paneHeight = (int) (fakePaneHeight - (insets.top + insets.bottom));
        System.out.println(paneHeight + "   " + paneWidth ); 
        
        
        frame.addComponentListener(new ComponentListener(){ 
        	public void componentResized(ComponentEvent e) {
            	//System.out.println(e.getComponent().getSize());
        		Dimension frameSize = frame.getSize();
                Insets insets = frame.getInsets();

                double fakePaneWidth = frameSize.getWidth();
                double fakePaneHeight = frameSize.getHeight();

                //calculate real width/height
                paneWidth = (int) (fakePaneWidth - (insets.left + insets.right));
                paneHeight = (int) (fakePaneHeight - (insets.top + insets.bottom));
                System.out.println(paneHeight + "   " + paneWidth );
                
                createAndShowGUI("continue");
        	}

        	public void componentHidden(ComponentEvent e) {}

        	public void componentMoved(ComponentEvent e) {
        		
        	}

        	public void componentShown(ComponentEvent e) {}

        }); */
	}
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		
		
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI("start");
	            }
	        });
		        
	}
	
	public static String getCurrentScreen(){
		return currentScreen;
	}


	public static void loadFonts(){
		InputStream helv = null;
		InputStream fsys = null;
		try {
			helv = new FileInputStream("helvetica-normal.ttf");
			fsys = new FileInputStream("fixedsys.ttf"); 
		} catch (FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {
			helvetica = Font.createFont(Font.TRUETYPE_FONT, helv);
			fixedsys = Font.createFont(Font.TRUETYPE_FONT, fsys);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fixedsys = fixedsys.deriveFont(Font.PLAIN, (float)(Math.sqrt(paneWidth*paneHeight)/39));
		helvetica = helvetica.deriveFont(Font.BOLD, 80f);
	}

	public static void deriveFonts(){
		helvetica16 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/16));
		helvetica4 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/4));
		//helvetica3 = helvetica.deriveFont(Font.BOLD, 11*(float)(Math.sqrt(paneWidth*paneHeight)/40));
		helvetica8 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/8));
		//helvetica100 = helvetica.deriveFont(Font.BOLD, 100f);
		helvetica32 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/32));
		helvetica20 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/20));
		helvetica64 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/64));
		helveticaPlain64 = helvetica.deriveFont(Font.PLAIN, (float)(Math.sqrt(paneWidth*paneHeight)/64));
		helvetica48 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/48));
	}

	public static void deriveFontsLoadScreen(){
		helvetica8 = helvetica.deriveFont(Font.BOLD, (float)(Math.sqrt(paneWidth*paneHeight)/8));
	}
	
	public static void displayGame(Container content) {
		frame.setContentPane(content);
	}
	
	public static void setCursorLocation(int x, int y) {
		try {
			Robot robot = new Robot();
			//frame.add(robot);
			robot.mouseMove(x, y);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BufferedImage takeScreenShot() {
		try {
			Robot robot = new Robot();
			//frame.add(robot);
			return robot.createScreenCapture(new Rectangle(paneWidth, paneHeight));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void hideCursor(){
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		frame.getContentPane().setCursor(blankCursor);
		//frame.getRootPane().setCursor(null);
	}
	
	public static void showCursor(){
		frame.getContentPane().setCursor(null);
	}

	public static void gunCursor(){
		Image cursorImg;
		try {
			cursorImg = ImageIO.read(new File("Images/scope4_1.png"));
			
			
			
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
					cursorImg, new Point(16, 16), "blank cursor");
				frame.getContentPane().setCursor(cursor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		
	}

	public static void setAsEscapeButton(EasyButton button){
		button.setButtonSize(4*paneWidth/20, 3*paneHeight/40);
		button.setLocation(paneHeight/80, 73*paneHeight/80);
		button.setButtonFont(helvetica32);
		//mainMenuButton.setButtonBorder(EasyButton.border);
		button.setRightLayout();
	}
}





