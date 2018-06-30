package start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.Timer;

import components.Disco;
import components.Message;
import components.SoundRing;
import entities.Player;
import entities.Troll;
import keyActions.KeyAction3;
import keyActions.KeyCommands;
import layout.Theme;
import sound.SoundMixer;
import timers.Death;

public class Content  implements Runnable, ActionListener, SpawnerConstants {
	int screenWidth;
	int screenHeigth;
	public String position = "begin";
	String nextScreen;
	JLabel label;
	JLabel label2;
	Container pane;
	int t = 1; 
	Timer fps;
	ArrayList<String> StringNumberCommands = new ArrayList<String>();
	ArrayList<String> StringArrowCommands = new ArrayList<String>();

	//int[] arrowOrder = {1,2,3,4};
	public static final KeyAction3 gameKeys = new KeyAction3(KeyAction3.GAME);
	//public static final KeyCommands gameKeys = new KeyCommands(KeyCommands.GAME);
	//GameScreen screen1;
	//TalkScreen screen2;
	//ShooterScreen screen0;
	Death death;
	public TextScreen introScreen;
	public ArrayList<ShooterScreen> shooterScreens = new ArrayList<ShooterScreen>();
	public ArrayList<GameScreen> gameScreens = new ArrayList<GameScreen>();
	public ArrayList<TalkScreen> talkScreens = new ArrayList<TalkScreen>();
	public ArrayList<TextScreen> textScreens = new ArrayList<TextScreen>();
	public ArrayList<WordScreen> wordScreens = new ArrayList<WordScreen>();
	public SpaceScreen space;
	String currentScreenType = "ShooterScreen";
	public String checkpoint;
	int screenIndex = 0;
	boolean isResume = false;
	boolean isResumeAfterDeath = false;
	boolean replaySound = false;
	public boolean isLoadScreen = false;
	public boolean canTalk = true;
	public int path;
	int paneWidth;
	int paneHeight;
	

	public Player player;
	
	public Content(int width, int height, String startPosition, Container PANE){
		pane = PANE;
		//pane.removeAll();
		//pane.setLayout(null);
		//pane.setBackground(Color.YELLOW);
		screenWidth = width;
		screenHeigth = height;
		position = startPosition;
		player = new Player();
		player.setActive(false);
		Loader.load(0);
		paneWidth = Programma.paneWidth;
		paneHeight = Programma.paneHeight;
		//pane.repaint();
		
		//screen1 = new GameScreen(2,2);
		//screen1.setMainText("This is a test,<br> what would you do?");
		//screen1.setAns("run away", "launch a falcon heavy");
		//screen1.setCommands("@troll", "*This berries are very poisonous");
		//screen1.setPictures("Images/anonymous.jpg", "Images/anonymous.jpg");
	}

	
	public void run() {
		if(death != null){
			death.dontDie();
		}
		death = null;
		//shooterScreens.clear();
		for(ShooterScreen shooterScreen : shooterScreens){
			shooterScreen.stop();
			shooterScreen.clearAnimals();
		}
		this.introScreen.mainText.setSize(Programma.paneWidth, introScreen.mainText.getHeight());
		///shooterScreens.clear();
		//gameScreens.clear();
		//talkScreens.clear();
		//textScreens.clear();
		//System.out.println("number of shootersscreens: " + shooterScreens.size());
		space = null;
		player.reset();
		content("plane");
		
		//player = null;
		//System.gc();
		
		
		//player.reset();
	}
	public void resumeAfterDeath(){
		player.revive();
		player.setActive(true);
		SoundMixer.pauseMusic();
		SoundMixer.stopSound();
		isResumeAfterDeath = true;
		canTalk = true;
		for(GameScreen screen : gameScreens){
			screen.resetBorders();
		}
		for(TalkScreen screen : talkScreens){
			screen.resetBorders();
		}
		//for(ShooterScreen screen : shooterScreens){
			//screen.remo
		//}
		//renewScreen();
		content(checkpoint);
	}
	
	public void resume(){
		player.setActive(true);
		content(position);
		
		isResume = true;
		
		if(currentScreenType == "SpaceScreen"){
			space.meteor.resume();
		}
		
		/*pane.removeAll();
		pane.add(gameKeys);
		/*
		switch(currentScreenType){
		case "ShooterScreen": resumeShooterScreen(); shooterScreens.get(0).renew();
			pane.add(shooterScreens.get(0));
			break;
		case "GameScreen": gameScreens.get(0).resetBorders(); gameScreens.get(0).resetBorders();				
			pane.add(gameScreens.get(0));
			break;
		case "TalkScreen": talkScreens.get(0).resetBorders(); pane.add(talkScreens.get(0));
			break;
		case "TextScreen": textScreens.get(0);
		
			break;
		}
		
		
		switch(position){
		
		case "plane":  
			pane.add(introScreen);
			break;
			
		case "crash":			
			pane.add(introScreen);		
			break;
		
		case "wreck":
			
			
			
			break;
		
		
		/*	case "intro":
				death.resumeDying();		
				associateNumberKeys("@troll", "*This berries are very poisonous");
				break; 
					
			case "troll":
				associateNumberKeys("\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\"");
				associateArrowKeys("\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\"");
				break; 
			}
		pane.repaint(); */
	}
		
	public void content(String Position, boolean...playSound){
		
		//System.out.println("content");
		position = Position;
		pane.removeAll();

		pane.add(gameKeys);
		//resetKeys();
		if(death != null){
			if(death.isDying){
				death.resumeDying();
			}
		}
		if(SoundMixer.isMusicRunning() && SoundMixer.isSoundEnabled){
			SoundMixer.pauseMusic();
			replaySound = true;
		}
		//label = new EasyButton("eljlafk", "boe");
		isLoadScreen = false;




		switch(position){
		
		case "plane":  
			double we = Math.log(0.0);
			we++;

			//Positions
			currentScreenType = "TextScreen";
			position  = "plane";
			checkpoint = "plane";


			if(Chance.of(0.25) && Programma.randomnessLevel > 0){
				nextScreen = "save";
			}
			else {
				nextScreen = "crash";
			}
			
			Loader.load(1);
			
			//Music
			if(SoundMixer.isSoundEnabled){
				SoundMixer.pauseMusic();
			}
			if(replaySound){
				SoundMixer.playBackground("Sound/Inside an airplane .wav", 60);
			}
			
			//introScreen.addLoading();

			//Screens
			pane.add(introScreen);
			break;

		case "save":
			currentScreenType = "TextScreen";
			nextScreen = "completed";
			screenIndex = 3;

			textScreens.get(screenIndex).addContinue();
			textScreens.get(screenIndex).repaint();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "crash":			
			currentScreenType = "TextScreen";
			position  = "crash";
			//checkpoint = "plane";
			//nextScreen = "test";
			//nextScreen = "testSpace";
			if(Chance.of(0.35) && Programma.randomnessLevel > 0){
				nextScreen = "*~You died in a plane crash.";
			}
			else {
				nextScreen = "wreck";
				Loader.load(2);
			}

			//nextScreen = "dimension";
			//nextScreen = "shooter0";
            //nextScreen = "mineEntrance";
			screenIndex = 2;
			
			//Music
			SoundMixer.playBackground("Sound/plane_crash2.wav");
			if(replaySound){
				//SoundMixer.play2SoundsSecondContinuouslyBackground("Sound/plane crash sound effect.wav", "Sound/Rainforest Sound.wav", 70);
			}
				//
			//SoundMixer.setRelativeBackgroundVolume(80);
			
			//Screens


			pane.remove(gameKeys);
            //textScreens.get(0).addContinueIfReady();
			//textScreens.get(0).continueAfterDelay("wreck", 3000);
			textScreens.get(0).repaint();
			pane.add(textScreens.get(0));
			Programma.createAndShowGUI("intro");
			break;
		
		case "wreck":
			currentScreenType = "GameScreen";
			position  = "wreck";
			checkpoint = "wreck";
			nextScreen = " ";
			screenIndex = 0;
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens

			gameScreens.get(0).setQ(2);
			gameScreens.get(0).setAns("Stay", "Explore the island");
			gameScreens.get(0).setCommands("@stay", "@explore");
			gameScreens.get(0).setMainText("You are the only survivor. Do you want to stay at the plane wreck<br>and wait for help or do you want to explore the island?"	);
			gameScreens.get(0).resetBorders();
			pane.add(gameScreens.get(0));
			break;
		
		case "explore":
			currentScreenType = "GameScreen";
			position  = "explore";
			checkpoint = "wreck";
			nextScreen = " ";
			screenIndex = 0;
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");

			//Screens
			gameScreens.get(0).setQ(4);
			gameScreens.get(0).setAns("To the mansion", "To the volcano","To the forest", "To the beach");
			gameScreens.get(0).setCommands("@mansion", "@volcano", "@tiger", "@hedgehog");
			gameScreens.get(0).setMainText("In which direction do you want to go?");
			gameScreens.get(0).resetBorders();
			pane.add(gameScreens.get(0));
			//pane.add(gameScreens.get(1));
			break;
			
		case "stay":
			currentScreenType = "GameScreen";
			position  = "stay";
			checkpoint = "wreck";
			nextScreen = " ";
			screenIndex = 0;
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav", 50);
			//Screens
			gameScreens.get(0).setAns("Get some sleep", "Stay awake");
			gameScreens.get(0).setCommands("@sleep", "@awake");
			gameScreens.get(0).setMainText("Waiting makes you sleepy. <br> Do you want to sleep or do you want to stay awake? ");
			gameScreens.get(0).resetBorders();
			pane.add(gameScreens.get(0));
			break;
			
		case "sleep":
			currentScreenType = "TextScreen";
			position  = "sleep";
			checkpoint = "wreck";
			nextScreen = "*You are powdered by lightning";
			screenIndex = 1;
			
			//Music
			SoundMixer.playSound("Sound/Lightning Sound.wav");
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));
			break;
		
		case "awake":
			currentScreenType = "TextScreen";
			position  = "awake";
			checkpoint = "wreck";
			nextScreen = "zzz";
			screenIndex = 2;
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			textScreens.get(2).addContinue();
			pane.add(textScreens.get(2));
			break;
		
		case "zzz":
			currentScreenType = "WordScreen";
			position  = "zzz";
			checkpoint = "wreck";
			nextScreen = "zombies";
			screenIndex = 0;
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			wordScreens.get(0).addContinue();
			pane.add(wordScreens.get(0));
			break;
			
		case "zombies":
			currentScreenType = "GameScreen";
			position  = "zombies";
			checkpoint = "wreck";
			nextScreen = " ";
			screenIndex = 1;
			
			death = new Death("The zombies are now enjoying your brains.", 30000/Programma.difficultyLevel);

			//Music
			SoundMixer.playBackground("Sound/zombies.wav");

			//Screens
			pane.add(gameScreens.get(1));
			break;
		
		case "fightZombie":
			currentScreenType = "TextScreen";
			position  = "fightZombie";
			checkpoint = "wreck";
			nextScreen = "fightZombie2";
			screenIndex = 4;
			//timers
			death.dontDie();
			
			//Music
			SoundMixer.playBackground("Sound/zombies.wav");
			//Screens
			textScreens.get(screenIndex).setMainText("Wow! You defeated nearly all the zombies.");
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "fightZombie2":
			currentScreenType = "TextScreen";
			position  = "fightZombie2";
			checkpoint = "wreck";
			nextScreen = "*The zombies are now enjoying your brains.";
			screenIndex = 4;
			//timers
			death.dontDie();
			//Music
			SoundMixer.playBackground("Sound/zombies.wav");
			//Screens
			textScreens.get(screenIndex).setMainText("But in the end you fall over a tree's root<br>and get devourded by the last zombies.");
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "fleeZombie":
			currentScreenType = "TextScreen";
			position  = "fleeZombie";
			checkpoint = "wreck";
			nextScreen = "explore";
			screenIndex = 4;
			
			//timers
			death.dontDie();
			
			//Music
			SoundMixer.playBackground("Sound/zombies.wav");
			//Screens
			textScreens.get(screenIndex).setMainText("Fleeing for zombies is smart. <br> They are too slow to catch you.");
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "actZombily":
			currentScreenType = "TextScreen";
			position  = "actZombily";
			checkpoint = "wreck";
			nextScreen = "explore";
			screenIndex = 4;
			
			//timers
			death.dontDie();
			
			//Music
			SoundMixer.playBackground("Sound/zombies.wav");
			//Screens
			textScreens.get(screenIndex).setMainText("You are genious! Zombies don't see any difference <br> between a hobbling human and a zombie.");
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "volcano":
			currentScreenType = "GameScreen";
			position  = "volcano";
			checkpoint = "explore";
			nextScreen = " ";
			screenIndex = 2;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			//gameScreens.get(3).setMainText("You are genious! Zombies don't see any difference between a hobbling human and a zombie.");
			//gameScreens.get(3).addContinue();
			pane.add(gameScreens.get(2));
			
			
			break;
			
		case "tiger":
			currentScreenType = "GameScreen";
			position  = "tiger";
			checkpoint = "explore";
			nextScreen = " ";
			screenIndex = 3;
			
			//timers
			death = new Death("The tiger ate you.", 25000/Programma.difficultyLevel);
			//Music
			SoundMixer.playSound("Sound/tiger.wav");
			//Screens
			pane.add(gameScreens.get(3));
			
			break;
			
		case "deadTiger":
			currentScreenType = "TextScreen";
			position  = "deadTiger";
			checkpoint = "explore";
			nextScreen = "food";
			screenIndex = 5;
			Loader.load(4);

			//timers
			death.dontDie();
			//Music
			SoundMixer.stopSound();
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			textScreens.get(screenIndex).addContinueIfReady();
			pane.add(textScreens.get(screenIndex));
			
			break;
		
		case "tigerRiver":
			currentScreenType = "GameScreen";
			position  = "tigerRiver";
			checkpoint = "tiger";
			nextScreen = " ";
			screenIndex = 4;
			//timers
			death.restart();
			//Music
			SoundMixer.playBackground("Sound/river.wav");
			//Screens
			pane.add(gameScreens.get(4));
		
			break;
			
		case "piranha":
			currentScreenType = "TextScreen";
			position  = "piranha";
			checkpoint = "explore";
			nextScreen = "*You are killed by piranhas";
			screenIndex = 6;
			//timers
			death.dontDie();
			//Music
			SoundMixer.stopSound();
			SoundMixer.playBackground("Sound/river.wav");
			//Screens
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "run":
			currentScreenType = "TextScreen";
			position  = "run";
			checkpoint = "explore";
			nextScreen = "*The tiger ate you.";
			screenIndex = 10;
			//timers
			death.dontDie();
			//Music
			
			//Screens
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "mansion":
			currentScreenType = "TextScreen";
			position  = "mansion";
			checkpoint = "explore";
			nextScreen = "mansion2";
			screenIndex = 9;
			Loader.load(3);
			
			//timers

			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			
			//Screens
			textScreens.get(screenIndex).addContinueIfReady();
			textScreens.get(screenIndex).addLoading();
			pane.add(textScreens.get(screenIndex));
			break;
			
		case "mansion2":
			currentScreenType = "GameScreen";
			checkpoint = "mansion2";
			nextScreen = " ";
			screenIndex = 0;
			//timers
			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			//Screens
			pane.add(gameScreens.get(0));
			break;
			
		case "weight":
			currentScreenType = "TextScreen";
			checkpoint = "mansion2";
			nextScreen = "squeeze";
			screenIndex = 0;
			//timers

			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			
			//Screens
			textScreens.get(0).addContinue();
			pane.add(textScreens.get(0));
			break;
			
		case "squeeze": 	
			currentScreenType = "WordScreen";
			checkpoint = "mansion2";
			nextScreen = "*You are squeezed to death";
			screenIndex = 0;
			//Music
			//Screens
			wordScreens.get(0).addContinue();
			pane.add(wordScreens.get(0));
			break;
			
		case "acid":
			currentScreenType = "TextScreen";
			checkpoint = "mansion2";
			nextScreen = "*You are dissolved";
			screenIndex = 1;
			//timers

			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));
			break;
			
		case "hall":
			currentScreenType = "TextScreen";
			checkpoint = "mansion2";
			nextScreen = "pit";
			screenIndex = 2;
			//timers

			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			
			//Screens
			textScreens.get(2).addContinue();
			pane.add(textScreens.get(2));
			break;
			
		case "pit":
			currentScreenType = "GameScreen";
			checkpoint = "mansion2";
			nextScreen = " ";
			screenIndex = 1;
			//timers
			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			//Screens
			pane.add(gameScreens.get(1));
			break;
			
		case "minecartPit":
			currentScreenType = "TextScreen";
			checkpoint = "mansion2";
			nextScreen = "pitRailJunction0";
			screenIndex = 3;
			//timers
			Loader.load(6);

			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			
			//Screens
			textScreens.get(3).addLoading();
			textScreens.get(3).addContinueIfReady();
			pane.add(textScreens.get(3));
			break;
			
		case "hammer":
			currentScreenType = "TextScreen";
			checkpoint = "mansion2";
			nextScreen = "*(for the noobs: <br> that was a booby trap.)";
			screenIndex = 4;
			//timers

			//Music
			SoundMixer.playBackground("Sound/thunderstorm.wav");
			
			//Screens
			textScreens.get(4).addContinue();
			pane.add(textScreens.get(4));
			break;
			
		case "pitRailJunction0":
			currentScreenType = "GameScreen";
			//position  = "minecartBeach";
			checkpoint = "pitRailJunction0";
			nextScreen = " ";
			screenIndex = 0;
		
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			gameScreens.get(0).setMainText("After a wild ride through caves, the rail splits. <br> What do you want to do?");
			gameScreens.get(0).setCommands("@pitRailJunction1","@caveRail");
			pane.add(gameScreens.get(0));
			
			break;
			
		case "pitRailJunction1":
			currentScreenType = "GameScreen";
			//position  = "minecartBeach";
			checkpoint = "pitRailJunction0";
			nextScreen = " ";
			screenIndex = 0;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			gameScreens.get(0).setMainText("After another wild ride through caves, the rail splits again. <br> What do you want to do?");
			gameScreens.get(0).setCommands("@light","@toxic");
			pane.add(gameScreens.get(0));
			
			break;
			
		case "caveRail":
			currentScreenType = "GameScreen";
			//position  = "minecartBeach";
			checkpoint = "pitRailJunction0";
			nextScreen = " ";
			screenIndex = 1;
		
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			pane.add(gameScreens.get(1));
		
		 	break;
		 	
		case "light":
			currentScreenType = "TextScreen";
			checkpoint = "pitRailJunction0";
			nextScreen = "beachRail";
			screenIndex = 0;
			
			//timers
			Loader.load(5);
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			
			//Screens
			textScreens.get(0).addContinueIfReady();
			textScreens.get(0).addLoading();
			pane.add(textScreens.get(0));
			break;
			
		case "beachRail":
			currentScreenType = "GameScreen";
			position  = "beachRail";
			checkpoint = "beachRail";
			nextScreen = " ";
			screenIndex = 1;
		
			//timers
			
			//Music
			SoundMixer.stopSound();
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			gameScreens.get(1).setCommands("@boomerang", "*You drowned in the sea", "@crocodile1");
			gameScreens.get(1).setMainText("The rail ends at the beach. <br> In which direction do you want to go now?");
			pane.add(gameScreens.get(1));
			break;	
			
		case "hedgehog":
			currentScreenType = "GameScreen";
			position  = "hedgehog";
			checkpoint = "explore";
			nextScreen = " ";
			screenIndex = 5;
		
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			pane.add(gameScreens.get(5));
		
			break;
			
		case "pet":
			currentScreenType = "TextScreen";
			position  = "pet";
			checkpoint = "hedgehog";
			nextScreen = "palm";
			screenIndex = 8;
			
			//timers
			Loader.load(5);
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			//textScreens.get(7).addContinue();
			textScreens.get(screenIndex).addContinueIfReady();
			textScreens.get(screenIndex).addLoading();
			pane.add(textScreens.get(screenIndex));
		
			break;
			
		case "tsunami":
			currentScreenType = "TextScreen";
			position  = "pet";
			checkpoint = "explore";
			nextScreen = "*You died in a tsunami.";
			screenIndex = 7;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			textScreens.get(screenIndex).addContinue();
			pane.add(textScreens.get(screenIndex));
		
			break;	
			
		case "palm":
			currentScreenType = "GameScreen";
			position  = "palm";
			checkpoint = "palm";
			nextScreen = " ";
			screenIndex = 0;
		
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			pane.add(gameScreens.get(0));
			
			
			break;
			
		case "coco":
			currentScreenType = "GameScreen";
			position  = "coco";
			checkpoint = "palm";
			nextScreen = " ";
			screenIndex = 1;
		
			//timers
			
			//Music
			SoundMixer.stopSound();
			
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			gameScreens.get(1).setCommands("@boomerang", "*You drowned in the sea", "@minecartBeach");
			gameScreens.get(1).setMainText("That tastes good! <br> In which direction do you want to go now?");
			pane.add(gameScreens.get(1));
			break;	
			
		case "minecartBeach":
			currentScreenType = "GameScreen";
			position  = "minecartBeach";
			checkpoint = "coco";
			nextScreen = " ";
			screenIndex = 3;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			pane.add(gameScreens.get(3));
			break;	
			
		case "crocodile":
			currentScreenType = "TextScreen";
			position  = "crocodile";
			checkpoint = "minecartBeach";
			nextScreen = "* A crocodile ate you.";
			screenIndex = 1;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));
			break;
			
		case "crocodile1":
			currentScreenType = "TextScreen";
			//position  = "crocodile";
			checkpoint = "beachRail";
			nextScreen = "* A crocodile ate you.";
			screenIndex = 1;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));
			break;	
			
		case "beachRide":
			currentScreenType = "TextScreen";
			checkpoint = "minecartBeach";
			nextScreen = "beachRailJunction";
			screenIndex = 2;
			//timers
			Loader.load(6);
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			//textScreens.get(2).addContinue();
			textScreens.get(2).addContinueIfReady();
			textScreens.get(2).addLoading();
			pane.add(textScreens.get(2));
			
			break;
			
		case "boomerang":
			currentScreenType = "GameScreen";
			position  = "boomerang";
			checkpoint = "palm";
			nextScreen = " ";
			path = 1;
			screenIndex = 2;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			pane.add(gameScreens.get(2));
			break;	
			
			
		case "boomerangDeath":
			currentScreenType = "TextScreen";
			position  = "boomerangDeath";
			checkpoint = "boomerang";
			nextScreen = "*You could have known known that.";
			screenIndex = 0;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			textScreens.get(0).setMainText("This is SO predictable! The boomerang <br> comes back and chops off your head.");
			textScreens.get(0).addContinue();
			pane.add(textScreens.get(0));
			break;
			
		case "smart":
			currentScreenType = "TextScreen";
			checkpoint = "";
			nextScreen = "hut";
			screenIndex = 0;
			//timers
			//Music
			SoundMixer.playBackground("Sound/sea_sound.wav");
			//Screens
			//textScreens.get(2).addContinue();
			textScreens.get(0).addContinue();
			textScreens.get(0).setMainText("You are smart!");
			//textScreens.get(2).addLoading();
			pane.add(textScreens.get(0));
			
			break;
			
		case "hut":
			currentScreenType = "TextScreen";
			checkpoint = "hut";
			nextScreen = "zulu1";
			screenIndex = 3;
			//timers
			Loader.load(7);
			//Music
			SoundMixer.playBackground("Sound/birds_forest.wav");
			//Screens
			//textScreens.get(2).addContinue();
			textScreens.get(3).addContinueIfReady();
			//textScreens.get(2).addLoading();
			pane.add(textScreens.get(3));
			
			break;
			
		case "toxic":
			currentScreenType = "TextScreen";
			//checkpoint = "junctionBeach";
			nextScreen = "*You died in the pool.";
			screenIndex = 1;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));
			break;
			
		case "food":
			currentScreenType = "GameScreen";
			position  = "food";
			checkpoint = "food";
			nextScreen = " ";
			screenIndex = 0;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			pane.add(gameScreens.get(0));
			break;
			
		case "redFruit": 
			currentScreenType = "TextScreen";
			position  = "redFruit";
			checkpoint = "food";
			nextScreen = "river";
			screenIndex = 0;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/Rainforest Sound.wav");
			//Screens
			textScreens.get(0).addContinue();
			pane.add(textScreens.get(0));
			
			break;	
			
		case "river":
			currentScreenType = "GameScreen";
			checkpoint = "river";
			nextScreen = " ";
			screenIndex = 1;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/river.wav");
			//Screens
			pane.add(gameScreens.get(1));
			break;
			
		case "piranha2":
			currentScreenType = "TextScreen";
			checkpoint = "river";
			nextScreen = "*You are killed by piranhas";
			screenIndex = 1;
			
			//timers
			//Music
			SoundMixer.playBackground("Sound/river.wav");
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));			
			break;
			
		case "bridge":	
			currentScreenType = "GameScreen";
			checkpoint = "river";
			nextScreen = " ";
			screenIndex = 2;
			path = 2;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/river.wav");
			//Screens
			pane.add(gameScreens.get(2));
			break;
			
		case "forestFire":
			currentScreenType = "TextScreen";
			checkpoint = "river";
			nextScreen = "*You are killed in a forest fire";
			screenIndex = 2;
			//timers
			//Music
			SoundMixer.playBackground("Sound/forest_fire.wav");
			//Screens
			textScreens.get(2).addContinue();
			pane.add(textScreens.get(2));			
			break;
			
		case "beachRailJunction":
			currentScreenType = "GameScreen";
			//position  = "minecartBeach";
			checkpoint = "beachRailJunction";
			nextScreen = " ";
			screenIndex = 0;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/minecart.wav");
			//Screens
			gameScreens.get(0).setMainText("After a wild ride through caves, the rail splits. <br> What do you want to do?");
			gameScreens.get(0).setCommands("@caveRail","@toxic");
			pane.add(gameScreens.get(0));
			
			break;
			
		case "hacker":
			currentScreenType = "TextScreen";
			checkpoint = "";
			nextScreen = "hacker00";
			path = 3;
			screenIndex = 2;
			
			Loader.load(7);
			//timers	
			//Music

			SoundMixer.playBackground("Sound/birds_forest.wav");
			//Screens
			//textScreens.get(2).addContinue();
			textScreens.get(2).addContinueIfReady();
			pane.add(textScreens.get(2));
			break;
			
			
		case "hacker0":
			currentScreenType = "GameScreen";
			checkpoint = "hacker0";
			nextScreen = "";
			screenIndex = 0;
			
			//timers
			if(death != null){
				death.dontDie();
			}
			
			//Music
			SoundMixer.stopSound();
			SoundMixer.playBackground("Sound/birds_forest.wav");
			//Screens
			gameScreens.get(0).setMainText("After some time you see a mysterious person.<br>What do you want to do?");
			pane.add(gameScreens.get(0));			
			break;
			
		case "hacker00":
			currentScreenType = "GameScreen";
			checkpoint = "hacker0";
			nextScreen = "";
			screenIndex = 0;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/birds_forest.wav");
			//Screens
			gameScreens.get(0).setMainText("At the exit of the cave, you meet a mysterious person.<br>What do you want to do?");
			pane.add(gameScreens.get(0));			
			break;
			
		case "hacker1":
			currentScreenType = "TextScreen";
			checkpoint = "hacker0";
			nextScreen = "hacker2";
			screenIndex = 0;
			//timers	
			//Music
			//SoundMixer.playBackground("");
			//Screens
			textScreens.get(0).setMainText("\"I am the wise Ali Isgon Nak'illu. I discovered that our<br>universe is actually a simulation in a Bolzmann brain.\"");
			textScreens.get(0).addContinue();
			pane.add(textScreens.get(0));			
			break;
			
		case "hacker2":
			currentScreenType = "TextScreen";
			checkpoint = "hacker0";
			nextScreen = "pills0";
			screenIndex = 0;
			//timers	
			//Music
			//SoundMixer.playBackground("");
			//Screens
			textScreens.get(0).setMainText("\"Maybe this is really shocking to you,<br>but no worries: I found The Bug.\"");
			textScreens.get(0).addContinue();
			pane.add(textScreens.get(0));			
			break;
			
		case "pills0":
			currentScreenType = "TextScreen";
			checkpoint = "hacker0";
			nextScreen = "pills1";
			screenIndex = 1;
			//timers	
			//Music
			//SoundMixer.playBackground("");
			//Screens
			textScreens.get(1).addContinue();
			pane.add(textScreens.get(1));			
			break;
			
		case "pills1":
			currentScreenType = "GameScreen";
			checkpoint = "hacker0";
			nextScreen = "";
			screenIndex = 3;
			//timers
			
			//Music
			
			//Screens
			pane.add(gameScreens.get(3));
			break;
			
		case "snake0":
			currentScreenType = "GameScreen";
			//checkpoint = "roadJunctionW";
			nextScreen = "";
			screenIndex = 1;
		
			//timers
			if(death != null){
				death.dontDie();
			}
			death = new Death("A poisonous snake bit you.", 10000/Programma.difficultyLevel);
			//Music
			SoundMixer.playSound("Sound/Snake_hiss_sound.wav");
			
			//Screens
			gameScreens.get(1).setCommands("@roadJunctionW", "@snake1");
			pane.add(gameScreens.get(1));
			break;
			
		case "snake1":	
			currentScreenType = "GameScreen";
			//checkpoint = "roadJunctionW";
			nextScreen = "";
			screenIndex = 2;
			//timers
			death.dontDie();
			death = new Death("A poisonous snake bit you.", 10000/Programma.difficultyLevel);
			//Music
			SoundMixer.playSound("Sound/Snake_hiss_sound.wav");
			//Screens
			gameScreens.get(2).setCommands("@snake0_back", "@hacker0");
			pane.add(gameScreens.get(2));
			break;	
			
		case "snake0_back":
			currentScreenType = "GameScreen";
			//checkpoint = "hacker0";
			nextScreen = "";
			screenIndex = 1;
		
			//timers
			death.dontDie();
			death = new Death("A poisonous snake bit you.", 10000/Programma.difficultyLevel);
			//Music
			SoundMixer.playSound("Sound/Snake_hiss_sound.wav");
			//Screens
			gameScreens.get(1).setCommands("@snake1", "@roadJunctionW");
			pane.add(gameScreens.get(1));
			break;
			
		case "snake1_back":	
			currentScreenType = "GameScreen";
			checkpoint = "hacker0";
			nextScreen = "";
			screenIndex = 2;
		
			//timers
			if(death != null){
				death.dontDie();
			}
			death = new Death("A poisonous snake bit you.", 10000/Programma.difficultyLevel);
			
			//Music
			SoundMixer.playSound("Sound/Snake_hiss_sound.wav");
			//Screens
			gameScreens.get(2).setCommands("@hacker0", "@snake0_back");
			pane.add(gameScreens.get(2));
			break;	
			
		case "junction":
			currentScreenType = "TextScreen";
			checkpoint = "junction";
			nextScreen = "roadJunctionS";
			screenIndex = 3;
			//timers	
			//Music
			SoundMixer.playBackground("Sound/birds_forest.wav");
			//Loader
			Loader.load(7);
			//Screens
			textScreens.get(3).addContinueIfReady();
			pane.add(textScreens.get(3));			
			break;
			
		case "roadJunctionW":
			currentScreenType = "GameScreen";
			checkpoint = "roadJunctionW";
			nextScreen = "";
			screenIndex = 4;
			//timers
			death.dontDie();
			//Music
			SoundMixer.playBackground("Sound/birds_forest.wav");
			SoundMixer.stopSound();
			
			//Screens
			gameScreens.get(4).setCommands("@firstBear", "@woman0", "@forestFireW");
			pane.add(gameScreens.get(4));
			break;	
		
		case "roadJunctionS":
			currentScreenType = "GameScreen";
			checkpoint = "roadJunctionS";
			nextScreen = "";
			screenIndex = 4;
			//timers
			
			//Music
			SoundMixer.playBackground("Sound/birds_forest.wav");
			
			//Screens
			gameScreens.get(4).setCommands("@snake0", "@firstBear", "@woman0");
			pane.add(gameScreens.get(4));
			break;	
		
		//case "roadJunctionEnext":
			
		case "roadJunctionE":
			currentScreenType = "GameScreen";
			checkpoint = "roadJunctionE";
			nextScreen = "";
			screenIndex = 4;
			//timers
			
			//Music
			
			SoundMixer.playBackground("Sound/birds_forest.wav");
			
			//Screens
			gameScreens.get(4).setCommands("@forestFireE", "@snake0", "@firstBear");
			pane.add(gameScreens.get(4));
			break;
			
		case "forestFireE":
			currentScreenType = "TextScreen";
			checkpoint = "roadJunctionE";
			nextScreen = "*You are killed in a forest fire";
			screenIndex = 2;
			//timers
			//Music
			SoundMixer.playBackground("Sound/forest_fire.wav");
			//Screens
			textScreens.get(2).addContinue();
			pane.add(textScreens.get(2));			
			break;
				
		case "forestFireW":
			currentScreenType = "TextScreen";
			checkpoint = "roadJunctionW";
			nextScreen = "*You are killed in a forest fire";
			screenIndex = 2;
			//timers
			//Music
			SoundMixer.playBackground("Sound/forest_fire.wav");
			//Screens
			textScreens.get(2).addContinue();
			pane.add(textScreens.get(2));			
			break;
				
			
		case "woman0": 
			currentScreenType = "TalkScreen";
			checkpoint = "woman0";
			screenIndex = 1;
			
			SoundMixer.playBackground("Sound/birds_forest.wav");
			
			
			//System.out.println("tROLOFKJEOjofnv");
						
			if(player.hasGun){
				String[] ans = {"Continue on your way", "Go back"};
				String[] com = {"@zulu0", "@roadJunctionE"};
				talkScreens.get(screenIndex).setComAndAns(com, ans);
			}		
			else if(!isResumeAfterDeath && !isResume && !player.hasMetHelgaBefore){
				talkScreens.get(1).addMessage("Good morning, who are you?",Message.IN);
				talkScreens.get(1).setOneCommand("@zulu0", 3);
				//talkScreens.get(1).setOneAns("Continue on your way", 3);
			}
			else if(!isResumeAfterDeath && !isResume && player.hasGamePC){
				talkScreens.get(1).addMessage("You got a nice game PC!! Can I have it?",Message.IN);
				talkScreens.get(screenIndex).setThingsToSay("\"Ok, here is my game PC.\"", "\"I am not gonna give you my game PC!\"");
			}
			else if(talkScreens.get(1).messages.size() == 1){
				talkScreens.get(1).setOneCommand("@zulu0", 3);
			}
			else if(talkScreens.get(1).messages.size() > 5){
				String[] ans = {"Continue on your way", "Go back"};
				String[] com = {"@zulu0", "@roadJunctionE"};
				talkScreens.get(screenIndex).setComAndAns(com, ans);
			}
			talkScreens.get(1).repaintButtons();
			
			//System.out.println("has player met Helga before = " + player.hasMetHelgaBefore);
			player.hasMetHelgaBefore = true;
			talkScreens.get(1).recover();
			pane.add(talkScreens.get(1));
			break;
			
		case "woman1": 
			currentScreenType = "TalkScreen";
			checkpoint = "woman1";
			screenIndex = 1;
			
			SoundMixer.playBackground("Sound/birds_forest.wav");
			
			System.out.println("irad: " + isResumeAfterDeath + "  is: " + isResume + "  hmhb: " + player.hasMetHelgaBefore);
			
			if(player.hasGun){
				String[] answ = {"Continue on your way", "Go back"};			
				String[] comm = {"@roadJunctionE", "@zulu0"};
				talkScreens.get(screenIndex).setComAndAns(comm, answ);
			}
			else if(!isResumeAfterDeath && !isResume && !player.hasMetHelgaBefore){
				talkScreens.get(1).addMessage("Good morning, who are you?",Message.IN);
			}
			else if(!isResumeAfterDeath && !isResume && player.hasGamePC){
				talkScreens.get(1).addMessage("You got a nice game PC, can I have it?",Message.IN);
				talkScreens.get(screenIndex).setThingsToSay("\"Ok, here is my game PC.\"", "\"I am not gonna give you my game PC!\"");
			}
			else if(talkScreens.get(1).messages.size() > 5){
				String[] answ = {"Continue on your way", "Go back"};
				String[] comm = {"@roadJunctionE", "@zulu0"};
				talkScreens.get(screenIndex).setComAndAns(comm, answ);
			}
			//talkScreens.get(1).setOneAns("Continue on your way", 3);
			talkScreens.get(1).setOneCommand("@roadJunctionE", 3);
			talkScreens.get(1).repaintButtons();
			//System.out.println("has player met Helga before = " + player.hasMetHelgaBefore);
			player.hasMetHelgaBefore = true;
			talkScreens.get(1).recover();
			pane.add(talkScreens.get(1));

			break;	
			
		case "zulu0":
			currentScreenType = "TalkScreen";
			checkpoint = "zulu";
			screenIndex = 0;
			
			if(!isResumeAfterDeath && !isResume){
				if(talkScreens.get(0).messages.size() < 2){
					if(talkScreens.get(0).messages.size() == 0){
						talkScreens.get(0).addMessage("Hi okhona lapho!",Message.IN);
					}
					// Spraak afspelen hier
				}
				int i = talkScreens.get(0).queNum - 1;
				talkScreens.get(0).setOneCommand("@woman1", i);
				talkScreens.get(0).setOneAns("Go back", i);
			}
			talkScreens.get(0).repaintButtons();
			
			pane.add(talkScreens.get(0));
			break;
			
		case "zulu1":
			currentScreenType = "TalkScreen";
			checkpoint = "zulu";
			screenIndex = 0;
			
			if(!isResumeAfterDeath && !isResume){
				talkScreens.get(0).addMessage("Hi okhona lapho!",Message.IN);
				talkScreens.get(0).setOneCommand("@woman1", 3);
				talkScreens.get(0).setOneAns("Continue on your way", 3);
			}
			talkScreens.get(0).repaintButtons();
			pane.add(talkScreens.get(0));
			break;	
			
		case "firstBear":
			currentScreenType = "ShooterScreen";
			screenIndex = 0;
			nextScreen = "shooterLoad_0";
			if(player.hasGun){
				checkpoint = "firstBear";
			}


			//SoundMixer.playSound("Sound/bear_roar.wav");

			if(!isResume){

				if(isResumeAfterDeath){
					shooterScreens.get(0).clearAnimals();
				}
				shooterScreens.get(0).addBear(Programma.paneHeight/2, Programma.paneWidth/2, Programma.paneHeight/2);
			}
			shooterScreens.get(0).start();
			pane.add(shooterScreens.get(0));
			pane.repaint();
			break;
			
		case "shooterLoad_0":	
			currentScreenType = "TextScreen";
			checkpoint = "shooterLoad_0";
			nextScreen = "shooter0";
			screenIndex = 3;
			//timers	
			//Music

			//Loader
			shooterScreens.get(0).stop();
			Loader.load(8);
			//Screens
			textScreens.get(3).addContinueIfReady();
			pane.add(textScreens.get(3));			
			break;

		case "shooter0":
			currentScreenType = "ShooterScreen";
			screenIndex = 0;
			nextScreen = "shooter1";
			checkpoint = "shooter0";
			player.hasGun = true;
			SoundMixer.playBackground("Sound/birds_forest.wav");

			if(!isResume){

				if(isResumeAfterDeath){
					shooterScreens.get(0).clearAnimals();
					System.out.println("clear animals");
				}
				System.out.println("new bear");
				shooterScreens.get(0).addBear(Programma.paneHeight/2, Programma.paneWidth/4, Programma.paneHeight/2);
			}

			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter1":
			currentScreenType = "ShooterScreen";
			screenIndex = 1;
			nextScreen = "shooter2";
			checkpoint = "shooter1";

			SoundMixer.playBackground("Sound/birds_forest.wav");

			//player.hasGun = true;

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addDeer(Programma.paneHeight/3, 2*Programma.paneWidth/5, 2*Programma.paneHeight/5);
			}
			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter2":
			currentScreenType = "ShooterScreen";
			screenIndex = 2;
			nextScreen = "shooter3";
			checkpoint = "shooter2";

			SoundMixer.playBackground("Sound/birds_forest.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
					System.out.println("clear animals");
				}
				//System.out.println("new bear");
				shooterScreens.get(screenIndex).addBear(Programma.paneHeight/3, Programma.paneWidth/3, 2*Programma.paneHeight/3);
			}
			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter3":
			currentScreenType = "ShooterScreen";
			screenIndex = 3;
			nextScreen = "shooter4";
			checkpoint = "shooter3";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(2*paneHeight/5, 4, 2*paneWidth/3, 2*paneHeight/5);
				shooterScreens.get(screenIndex).addOrc(2*paneHeight/5, 1, 2*paneWidth/5, 2*paneHeight/5);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter4":
			currentScreenType = "ShooterScreen";
			screenIndex = 4;
			nextScreen = "shooter5";
			checkpoint = "shooter4";
			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				//shooterScreens.get(screenIndex).addOrc(paneHeight/3, 4, 2*paneWidth/3, 2*paneHeight/5);
				shooterScreens.get(screenIndex).addOrc(2*paneHeight/5, 1, paneWidth/2, paneHeight/2);
				shooterScreens.get(screenIndex).spawn(ORC, 3000, 0, 5, 2*paneHeight/5, 2*paneWidth/5, 3*paneWidth/5, paneHeight/2);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;


		case "shooter5":
			currentScreenType = "ShooterScreen";
			screenIndex = 5;
			nextScreen = "shooter6";
			checkpoint = "shooter5";
			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(2*paneHeight/5, 1, paneWidth/2, paneHeight/2);
				shooterScreens.get(screenIndex).spawn(ORC, 3000, 0, 5, 2*paneHeight/5, 2*paneWidth/5, 3*paneWidth/5, paneHeight/2);
				shooterScreens.get(screenIndex).spawn(ORC, 3000, 17000, 2, 2*paneHeight/5, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);

			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter6":
			currentScreenType = "ShooterScreen";
			screenIndex = 6;
			nextScreen = "shooter7";
			checkpoint = "shooter6";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(paneHeight/3, 1, 2*paneHeight/5, paneHeight/2);
				shooterScreens.get(screenIndex).spawn(ORC, 3000, 0, 5, 2*paneHeight/5, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);
				shooterScreens.get(screenIndex).spawn(ORC, 3000, 19500, 5, 2*paneHeight/5, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter7":
			currentScreenType = "ShooterScreen";
			screenIndex = 7;
			nextScreen = "shooter8";
			checkpoint = "shooter7";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(paneHeight/3, 1, 2*paneHeight/5, paneHeight/2);
				shooterScreens.get(screenIndex).spawn(BEAR, 4000, 800, 3, 2*paneHeight/5, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);
				//shooterScreens.get(screenIndex).spawn(ORC, 3000, 19500, 5, 2*paneHeight/5, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter8":
			currentScreenType = "ShooterScreen";
			screenIndex = 8;
			nextScreen = "shooterMineEntrance";
			checkpoint = "shooter8";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addBear(paneHeight/3,  2*paneHeight/5, 2*paneHeight/3);
				shooterScreens.get(screenIndex).spawn(ORC, 3000, 0, 2, 2*paneHeight/5, 3*paneWidth/8, 4*paneWidth/7, 2*paneHeight/3);
				//shooterScreens.get(screenIndex).spawn(ORC, 3000, 19500, 5, 2*paneHeight/5, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;



		case "shooterMineEntrance":
			currentScreenType = "ShooterScreen";
			screenIndex = 9;
			nextScreen = "loadMineEntrance";
			checkpoint = "shooter8";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(3*paneHeight/5, 4, 2*paneWidth/3, paneHeight/2);
				//shooterScreens.get(screenIndex).spawn(ORC, 3, 0, 5, paneHeight/3, 2*paneWidth/6, 3*paneWidth/6, paneHeight/2);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "loadMineEntrance":
			currentScreenType = "TextScreen";
			checkpoint = "loadMineEntrance";
			nextScreen = "mineEntrance";
			screenIndex = 0;
			//timers
			//Music

			//Loader
			shooterScreens.get(shooterScreens.size() - 1).stop();
			ShooterScreen.stopTimers();
			Loader.load(9);
			//Screens
			textScreens.get(0).addContinueIfReady();
			pane.add(textScreens.get(0));
			break;


		case "mineEntrance":
			currentScreenType = "GameScreen";
			checkpoint = "mineEntrance";
			nextScreen = "";
			screenIndex = 0;

			//timers
			//Music
			SoundMixer.playBackground("Music/Morgenstimmung.wav");
			Programma.gameContainer.player.hasGun = true;
			//Screens

			pane.add(gameScreens.get(0));
			break;

		case "dimension":
			death = new Death("You died in a strange dimension.", 15700);
			SoundMixer.playBackground("Sound/dimension.wav", 75);
			pane.add(new Disco());
			pane.repaint();
			break;

		case "shooter10":
			currentScreenType = "ShooterScreen";
			screenIndex = 0;
			nextScreen = "shooter11";
			checkpoint = "shooter10";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(3*paneHeight/4, 1, paneHeight/2, paneHeight/3);
			}

			//shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter11":
			currentScreenType = "ShooterScreen";
			screenIndex = 1;
			nextScreen = "shooter12";
			checkpoint = "shooter11";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(3*paneHeight/4, 1, 2*paneHeight/5, paneHeight/4);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "shooter12":
			currentScreenType = "ShooterScreen";
			screenIndex = 2;
			nextScreen = "troll";
			checkpoint = "shooter12";

			SoundMixer.playBackground("Music/Morgenstimmung.wav");

			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				shooterScreens.get(screenIndex).addOrc(3*paneHeight/4, 1, 2*paneWidth/5, 2*paneHeight/7);
			}

			shooterScreens.get(screenIndex - 1).stop();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			break;

		case "troll":
			currentScreenType = "ShooterScreen";
            nextScreen = "exitMine";
			screenIndex = shooterScreens.size() - 1 ;

			for(int i = 0; i < screenIndex; i++ ){
				shooterScreens.get(i).stop();
			}


			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(screenIndex).clearAnimals();
				}
				//shooterScreens.get(screenIndex).addAnimal(new Troll(), 9*Programma.paneWidth/20, 2*Programma.paneHeight/3);
			}
            KeyAction3 trollKeys = new KeyAction3(KeyAction3.GAME_RESTRICTED);
            pane.remove(gameKeys);
            pane.add(trollKeys);
			//SoundMixer.playBackground("Music/In the Hall of the Mountain King.wav");
			SoundMixer.play2SoundsSecondContinuouslyBackground("Music/In the Hall of the Mountain King.wav", "Sound/silence.wav");
			player.hasGun = true;
			shooterScreens.get(screenIndex).renew();
			shooterScreens.get(screenIndex).start();
			pane.add(shooterScreens.get(screenIndex));
			shooterScreens.get(screenIndex).renew();
			pane.repaint();
			//nextScreen = "spot";
			break;

		case "exitMine":
			currentScreenType = "TextScreen";
			checkpoint = "exitMine";
			nextScreen = "canyon";
			screenIndex = 0;
			//timers
			//Music
            //SoundMixer.playBackground("Music/Pirates Of The Caribbean Theme Song.wav");

			SoundMixer.playBackground("Sound/birds_forest.wav");

			//Loader
			shooterScreens.get(shooterScreens.size() -  1).stop();
			Loader.load(10);
			//Screens
			textScreens.get(0).removeContinue();
			textScreens.get(0).addContinueIfReady();
			pane.add(textScreens.get(0));
			break;


        case "canyon":
		    currentScreenType = "GameScreen";
			checkpoint = "canyon";
			nextScreen = "";
			screenIndex = 0;
			//timers

            for(int i = 0; i < screenIndex; i++ ){
                shooterScreens.get(i).stop();
            }

			//Music
			SoundMixer.playBackground("Sound/birds_forest.wav");

			//
			gameScreens.get(screenIndex).repaintButtons();
			pane.add(gameScreens.get(screenIndex));
			break;

        case "kangaroos":
            currentScreenType = "ShooterScreen";
            screenIndex = 0;
            nextScreen = "box";
            checkpoint = "kangaroos";

            //SoundMixer.playBackground("");

            if(!isResume){
                if(isResumeAfterDeath){
                    shooterScreens.get(screenIndex).clearAnimals();
                }
                shooterScreens.get(screenIndex).addKangaroo(paneHeight/2,  2*paneWidth/5, 2*paneHeight/7);
                shooterScreens.get(screenIndex).addKangaroo(paneHeight/2,  paneWidth/2, 2*paneHeight/7);
                shooterScreens.get(screenIndex).spawn(KANGAROO, 2000,  2000, 4, paneHeight/2,2*paneHeight/5,  3*paneWidth/5, 2*paneHeight/7);
            }

            shooterScreens.get(screenIndex).start();
            pane.add(shooterScreens.get(screenIndex));
            break;

        case "mighty":
            currentScreenType = "ShooterScreen";
            screenIndex = 1;
            nextScreen = "";
            checkpoint = "canyon";

            //SoundMixer.playBackground("");

            if(!isResume){
                if(isResumeAfterDeath){
                    shooterScreens.get(screenIndex).clearAnimals();
                }
                //shooterScreens.get(screenIndex).addBear(paneHeight/2,  paneWidth/3, 2*paneHeight/7);
                shooterScreens.get(screenIndex).addBear(paneHeight/2,  3*paneWidth/5, paneHeight/2);
                shooterScreens.get(screenIndex).spawn(ORC, 1500,  2000, 40, paneHeight/2,2*paneHeight/5,  3*paneWidth/5, paneHeight/2);
                shooterScreens.get(screenIndex).spawn(BEAR, 2000,  9000, 30, paneHeight/2,2*paneHeight/5,  3*paneWidth/5, paneHeight/2);
            }

            shooterScreens.get(screenIndex).start();
            pane.add(shooterScreens.get(screenIndex));
            break;

        case "madeInChina":
            currentScreenType = "TextScreen";
            //checkpoint = "exitMine";
            nextScreen = "crack";
            screenIndex = 0;
            //timers
            //Music

            //Loader
            //Screens
            textScreens.get(0).addContinue();
            pane.add(textScreens.get(screenIndex));
            break;

        case "crack":
            currentScreenType = "WordScreen";
            nextScreen = "fall";

            wordScreens.get(0).setText("Crack");
            wordScreens.get(0).setTextColor(Theme.darkerGreen);
            wordScreens.get(0).addContinue();

            pane.add(wordScreens.get(0));
            break;

        case "fall":
            currentScreenType = "WordScreen";
            nextScreen = "smash";

            wordScreens.get(0).setText("Fall");
            wordScreens.get(0).setTextColor(Color.BLUE);
            //wordScreens.get(0).addContinue();

            pane.add(wordScreens.get(0));
            break;

        case "smash":
            currentScreenType = "WordScreen";
            nextScreen = "*You fell from a high place";

            //wordScreens.get(0).addContinue();
            wordScreens.get(0).setText("Smash");
            wordScreens.get(0).setTextColor(Color.RED);

            pane.add(wordScreens.get(0));
            break;

        case "helicopter":
            currentScreenType = "TextScreen";
            //checkpoint = "exitMine";
            nextScreen = "warning";
            screenIndex = 1;
            //timers
            //Music
			SoundMixer.playSound("Sound/helicopter_sound.wav");
            //Loader
            //Screens
            textScreens.get(screenIndex).addContinue();
            pane.add(textScreens.get(screenIndex));
            break;


        case "warning":
			currentScreenType = "WordScreen";
			nextScreen = "explodingHeli";

            wordScreens.get(2).addContinue();
			pane.add(wordScreens.get(2));
			break;

        case "explodingHeli":
			currentScreenType = "WordScreen";
			nextScreen = "*The helicopter exploded.<br>(Look at it from the bright side, you never have to go to school from now on.)";

			SoundMixer.playSound("Sound/explosion.wav");

            wordScreens.get(1).addContinue();
			pane.add(wordScreens.get(1));
			break;


        case "box":
            currentScreenType = "GameScreen";
            checkpoint = "box";
            nextScreen = "";
            screenIndex = 1;
            //timers

			for(int i = 0; i < screenIndex; i++ ){
				shooterScreens.get(i).stop();
			}
			ShooterScreen.stopTimers();
            //Music
			SoundMixer.playBackground("Sound/birds_forest.wav");
            //Screens
            pane.add(gameScreens.get(screenIndex));
            break;

        case "drone":
            currentScreenType = "TextScreen";
            //checkpoint = "exitMine";
            nextScreen = "box";
            screenIndex = 2;
            //timers
            //Music

            //Loader
            //Screens
            textScreens.get(screenIndex).addContinue();
            pane.add(textScreens.get(screenIndex));
            break;

        case "loadSpaceShuttle":
			currentScreenType = "TextScreen";
			checkpoint = "loadSpaceShuttle";
			nextScreen = "spaceShuttle";
			screenIndex = 3;
			//timers
			//Music
            //SoundMixer.playBackground("Music/Pirates Of The Caribbean Theme Song.wav");

			//Loader
			Loader.load(11);
			//Screens
			textScreens.get(screenIndex).addContinueIfReady();
			pane.add(textScreens.get(screenIndex));
			break;

        case "spaceShuttle":
            checkpoint = "spaceShuttle";
			currentScreenType = "SpaceScreen";

            SoundMixer.play2SoundsSecondContinuouslyBackground("Sound/final_countdown_0.wav", "Sound/final_countdown_1.wav", 60);
            space.reset();

			pane.add(space);
			pane.repaint();
			break;

		case "meteor":
			currentScreenType = "WordScreen";
			nextScreen = "*You are killed by a meteor";

            wordScreens.get(0).setBackground(Color.BLACK);
            wordScreens.get(0).setTextColor(Color.GRAY);

            wordScreens.get(0).addContinue();
			pane.add(wordScreens.get(0));
			break;

		case "self-destruct":
			currentScreenType = "WordScreen";
			nextScreen = "*You just committed suicide";

            wordScreens.get(0).setBackground(Color.WHITE);
            wordScreens.get(0).setTextColor(Color.DARK_GRAY);

			wordScreens.get(0).addContinue();
			pane.add(wordScreens.get(0));
			break;

		case "newYork":
            currentScreenType = "TextScreen";
            nextScreen = "completed";
            screenIndex = 1;
            //timers
            //Music

            //Loader
            //Screens
            textScreens.get(screenIndex).addContinue();
            pane.add(textScreens.get(screenIndex));
            break;

        case "blackHole":
            currentScreenType = "TextScreen";
            nextScreen = "blackHole0";
            screenIndex = 0;
            //timers
            //Music

            //Loader
            //Screens
            textScreens.get(screenIndex).addContinue();
            pane.add(textScreens.get(screenIndex));
            break;

        case "blackHole0":
            currentScreenType = "TextScreen";
            nextScreen = "*You died in a black hole.";
            screenIndex = 0;
            //timers
            //Music

            //Loader
            //Screens
			textScreens.get(screenIndex).setText("Suddenly, out of nothing a black hole appears.<br>It swallows you.");
            textScreens.get(screenIndex).addContinue();
            pane.add(textScreens.get(screenIndex));
            break;

        case "mars":
		    currentScreenType = "GameScreen";
			checkpoint = "mars";
			nextScreen = "";
			screenIndex = 0;
			//timers

			//Music

			//Screens
			pane.add(gameScreens.get(screenIndex));
			break;

        case "completed":
            Programma.createAndShowGUI("completed");
            break;

		case "test":
			//shooterScreens.get(1);
			//position  = "begin";
			//death.dontDie();
			currentScreenType = "ShooterScreen";
			screenIndex = 0;
			//screenIndex = 0;
			if(!isResume){
				if(isResumeAfterDeath){
					shooterScreens.get(0).clearAnimals();
				}
				//shooterScreens.get(0).addBear(300, 1350, 650);
				//shooterScreens.get(0).addKangaroo(300, 650, 350);
				//shooterScreens.get(0).addKangaroo(300, 750, 350);
				//shooterScreens.get(0).addDeer(300, 450, 450);
				//shooterScreens.get(0).addDisco();
				//shooterScreens.get(0).addAnimal(new Troll(), 9*Programma.paneWidth/20, 2*Programma.paneHeight/3);
			}

			SoundMixer.playBackground("Music/In the Hall of the Mountain King.wav", 100);
			//SoundMixer.setRelativeBackgroundVolume(80);
			//if(shooterScreens.isEmpty()){
			//shooterScreens.get(0).spawn(KANGAROO, 100, 3000, 20, 400, 500, 505, 520);
			//shooterScreens.get(0).spawn(4000, 2000, 2, 400, 200, 500, 450);
			
			player.hasGun = true;
			shooterScreens.get(0).renew();
			//SoundMixer.fadeOutSoundAndStartNewSound("Music/I'm Blue.wav", 500);
			//SoundMixer.fadeOutSound(800);
			
			//shooterScreens.get(0).repaint();
			shooterScreens.get(0).start();
			pane.add(shooterScreens.get(0));
			shooterScreens.get(0).renew();
			pane.repaint();
			//nextScreen = "spot";
			break;
			
		case "testSpace":
			currentScreenType = "SpaceScreen";
			
			pane.add(space);
			pane.repaint();
			break;
			
		case "testTalk":
			currentScreenType = "TalkScreen";
			
			pane.add(talkScreens.get(0));
			pane.repaint();
			break;	
			

			
			
			/*	case "intro":
			if(death == null){
				death = new Death("a tiger ate you", 13000);
			}
			else{
				death.resumeDying();
				//screen0.addOrc(200, 4, 500, 520);
			}
			screenIndex = 0;
			currentScreenType = "GameScreen";
			position  = "intro";
			//SoundMixer.fadeOutMusic(2000);
			SoundMixer.fadeMusicToNewSound("Music/introduction et rondo capriccioso.wav", 2000);

			//Programma.gunCursor();
			
			//if(gameScreens.isEmpty()){
				//gameScreens.add(new GameScreen(2,2));
			///	gameScreens.get(0).setAns("run away", "launch a falcon heavy");
				//gameScreens.get(0).setCommands("@begin", "*These berries are very poisonous");
			//	gameScreens.get(0).setPictures("Images/anonymous.jpg", "Images/anonymous.jpg");
			//	gameScreens.get(0).setMainText("This is a test,<br> what would you do?");
			//}
			//gameScreens.get(0).resetBorders();

			
			associateNumberKeys("@troll", "*This berries are very poisonous");
			
			pane.add(gameScreens.get(0));
			pane.repaint();
			
			//shooterScreens.add(new ShooterScreen());
		//	shooterScreens.get(0).addImage("Images/landscape.jpeg");
			
			//shooterScreens.add(new ShooterScreen());
			//shooterScreens.get(1).addImage("Images/landscape.jpeg");
			
			//next screen
		//	if(talkScreens.isEmpty()){
				//String[] ans = {"\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\""};
				//talkScreens.add(new TalkScreen("Talk with somebody", ans ));
				//screen2  = ;
			//	talkScreens.get(0).setPictures("Images/anonymous.jpg");	
		//	}
			
			//loadShooter();
			break;
			//fps = new Timer(25, this);
			//fps.start(); break; 
			
		case "troll":
			position  = "troll";
			screenIndex = 0;
			death.dontDie();
			currentScreenType = "TextScreen";
			SoundMixer.fadeSoundToNewSound("Music/Clocks.wav", 500);
			//SoundMixer.fadeOutSoundAndStartNewSound("Music/Clocks.wav", 500);

			

			//TextScreen screen3 = new TextScreen("TROLLOLOL");
			//screen3.setText("lsdfkljsld");
			//screen3.setImage("Images/anonymous.jpg");
			//te
			textScreens.get(screenIndex).setText("ddsdflaskfjlaf");
			
			textScreens.get(screenIndex).setImage("Images/anonymous.jpg");
			pane.add(textScreens.get(screenIndex));
			pane.repaint();
			nextScreen = "begin";
			
			break;
			
		
		case "test":
			//resetKeys();
			position  = "test";
			death.dontDie();
			position  = "test";
			currentScreenType = "TalkScreen";
			SoundMixer.fadeOutMusic(5000);

			associateNumberKeys("\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\"");
			associateArrowKeys("\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\"");

			pane.add(talkScreens.get(0));
			pane.repaint();
			break;
			
		case "spot":
			position  = "spot";
			death.dontDie();
			screenIndex = 1;
			currentScreenType = "ShooterScreen";
			
			//if(shooterScreens.isEmpty()){
			//shooterScreens.get(0).spawn(2000, 3000, 2, 200, 500, 1066, 520);
			//shooterScreens.get(0).spawn(4000, 2000, 2, 400, 200, 500, 450);
			//shooterScreens.remove(0);
			shooterScreens.get(screenIndex).addOrc(300, 1, 1500, 400);
			shooterScreens.get(screenIndex).renew();

			SoundMixer.fadeInSound("Music/Kinessa Song.wav",2000);
			//shooterScreens.get(0).repaint();
			pane.add(shooterScreens.get(screenIndex));
			pane.repaint();
			nextScreen = "test";
			
			break; */
		}
		if(currentScreenType == "GameScreen"){
			for(GameScreen screen : gameScreens){
				screen.repaintButtons();
			}
		}
		pane.repaint();
		isResume = false;
		isResumeAfterDeath = false;
		replaySound = true;
		pane.repaint();
		//pane.add(gameKeys);
	}
	
	public void continueGame(){

		//System.out.println("type: " + currentScreenType + " loading ready: "+ Loader.isLoadingReady + " next screen: " + nextScreen);

		if(currentScreenType == "ShooterScreen" && shooterScreens.get(0).hasPlayerShotEverything() ){
			content(nextScreen);
		}
		else if((currentScreenType == "TextScreen" && Loader.isLoadingReady) || currentScreenType == "WordScreen" ){
			if(nextScreen.startsWith("*")){
				Programma.createAndShowGUI(nextScreen);
			}
			else{
				content(nextScreen);
				pane.repaint();
			}
		}
		else if(currentScreenType == "SpaceScreen"){
			space.enter();
		}
	}
	
	public void renewScreen(){
		pane.removeAll();
		pane.add(gameKeys);
		
		switch(position){
		
		
		/*case "begin":
				//shooterScreens.get(1);
			
			//shooterScreens.get(0).renew();
			//shooterScreens.remove(0);
			//shooterScreens.add(1, new ShooterScreen());
			//shooterScreens.get(1).addImage("Images/landscape.jpeg");
			
			//shooterScreens.get(0).spawn(2000, 3000, 0, 200, 500, 1066, 520);
			//shooterScreens.get(0).spawn(4000, 2000, 2, 400, 200, 500, 450);
			//S/ystem.out.println("new shooterScreen");
			shooterScreens.get(0).renew();
			//screen0.renew();
			pane.add(shooterScreens.get(0));
			pane.repaint();
			break;
				
		case "intro":

			death.resumeDying();
				
			position  = "intro";
			//Programma.gunCursor();
			
			gameScreens.get(0).resetBorders();				
			associateNumberKeys("@troll", "*This berries are very poisonous");
				
			pane.add(gameScreens.get(0));
			pane.repaint();
				
			break; 
				
		case "troll":
			//death.dontDie();
			//TextScreen screen3 = new TextScreen("TROLLOLOL");
			//screen3.setText("lsdfkljsld");
			//screen3.setImage("Images/anonymous.jpg");
			//pane.add(screen3);
			//pane.repaint();
			//break;
			
		case "test":
				//resetKeys();
			//death.dontDie();
			position  = "test";
			
			associateNumberKeys("\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\"");
			associateArrowKeys("\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\"");
	
			pane.add(talkScreens.get(0));
			pane.repaint();
			break;  */
		}

	}
	
	public void text(String text1){
		if(!canTalk){
			return;
		}
		
		String text = text1.substring(1, text1.length()-1);
		
		talkScreens.get(screenIndex).addMessage(text,Message.OUT);
		switch(text){
		
		case "":  talkScreens.get(screenIndex).addMessage("",Message.IN); break;
		
		//Gesprek 1
		
		case "I am a dangerous alien.":
			canTalk = false;
			player.hasMetHelgaBefore = false;
			pane.remove(gameKeys);
			talkScreens.get(screenIndex).addMessage("Death to the aliens!!!",Message.IN);
			death = new Death("Helga killed you", 1000);
			talkScreens.get(screenIndex).messages.remove(talkScreens.get(screenIndex).messages.size()-1);
			talkScreens.get(screenIndex).messages.remove(talkScreens.get(screenIndex).messages.size()-1);
			break;
			
		case "I am the only survivor of a plane crash.":
		case "I do not know.":	
			talkScreens.get(screenIndex).addMessage("Ok, I am Helga, nice to meet you.",Message.IN); 
			
			talkScreens.get(screenIndex).setThingsToSay("\"Do you know how I can get off this island?\"", "\"Do you have something to eat for me?\"");
			break;
			
		case "Do you know how I can get off this island?":
			talkScreens.get(screenIndex).addMessage("Yes, there is a rocket somewere.",Message.IN);
			talkScreens.get(screenIndex).addMessage("Do you have a weapon?",Message.IN);
			talkScreens.get(screenIndex).setThingsToSay("\"No, do I need one?\"", "\"No, but I killed a tiger with my bare eyes!\"");
			break;
	
		case "Do you have something to eat for me?":  
			talkScreens.get(screenIndex).addMessage("Do you like pizza?",Message.IN); 
			talkScreens.get(screenIndex).setThingsToSay("\"Yes, I love pizza\"", "\"No, I don't like pizza\"");
			break;
		
		case "Yes, I love pizza":  
			talkScreens.get(screenIndex).addMessage("Nice, here is some pizza for you.",Message.IN);
			talkScreens.get(screenIndex).addMessage("Do you have a weapon?",Message.IN);
			talkScreens.get(screenIndex).setThingsToSay("\"No, do I need one?\"", "\"No, but I killed a tiger with my bare eyes!\"");
			break;
		
		case "No, I don't like pizza":
			canTalk = false;
			pane.remove(gameKeys);
			talkScreens.get(screenIndex).addMessage("Shame on you, you should love pizza.",Message.IN);
			talkScreens.get(screenIndex).messages.remove(talkScreens.get(screenIndex).messages.size()-1);
			talkScreens.get(screenIndex).messages.remove(talkScreens.get(screenIndex).messages.size()-1);
			death = new Death("You were killed by Helga", 4000);
			break;
			
		case "No, do I need one?":
			talkScreens.get(screenIndex).addMessage("Yes",Message.IN); 
		case "No, but I killed a tiger with my bare eyes!": 
			talkScreens.get(screenIndex).addMessage("You definitely need a weapon.",Message.IN);
			talkScreens.get(screenIndex).addMessage("There are dangerous animals and monsters on this island!",Message.IN);
			if(player.hasGamePC){
				talkScreens.get(screenIndex).addMessage("You can have my old gun if you give me your game pc.",Message.IN);
				talkScreens.get(screenIndex).setThingsToSay("\"Ok, here is my game PC.\"", "\"I am not gonna give you my game PC!\"");
			}
			else{
				talkScreens.get(screenIndex).addMessage("You can have my old gun if you bring me a game pc.",Message.IN);
				String[] ans = {"Continue on your way", "Go back"};
				if(checkpoint == "woman0"){
					String[] com = {"@zulu0", "@roadJunctionE"};
					talkScreens.get(screenIndex).setComAndAns(com, ans);
					//System.out.println("tROLOFKJEOjofnv");
				}
				else{
					String[] com = {"@roadJunctionE", "@zulu0"};
					talkScreens.get(screenIndex).setComAndAns(com, ans);
				}
			}
			break;
			
		case "I am not gonna give you my game PC!":
			talkScreens.get(screenIndex).addMessage("Then I will take it!!",Message.IN);
			death = new Death("You were killed by Helga", 1000);
			talkScreens.get(screenIndex).messages.remove(talkScreens.get(screenIndex).messages.size()-1);
			talkScreens.get(screenIndex).messages.remove(talkScreens.get(screenIndex).messages.size()-1);
			break;
			
		case "Ok, here is my game PC.":
			player.hasGamePC = false;
			player.hasGun = true;
			talkScreens.get(screenIndex).addMessage("So here is my DuelGun 101.",Message.IN);
			//talkScreens.get(screenIndex).addMessage("It is a DuelGun 101.",Message.IN);
			talkScreens.get(screenIndex).addMessage("If you click your left mouse button, it will fire automatically.",Message.IN);
			talkScreens.get(screenIndex).addMessage("If you click your right mouse button, you can use the gun as a sniper riffle.",Message.IN);
			talkScreens.get(screenIndex).addMessage("Press \"L\" to lock your scope.",Message.IN);
			talkScreens.get(screenIndex).addMessage("Press \"R\" to reload.",Message.IN);
			String[] ans = {"Continue on your way", "Go back"};
			if(checkpoint == "woman0"){
				String[] com = {"@zulu0", "@roadJunctionE"};
				talkScreens.get(screenIndex).setComAndAns(com, ans);
				//System.out.println("tROLOFKJEOjofnv");
			}
			else{
				String[] com = {"@roadJunctionE", "@zulu0"};
				talkScreens.get(screenIndex).setComAndAns(com, ans);
			}
			break;
		//case "Yes, I   pizza very much":  talkScreens.get(screenIndex).addMessage("Do you like pineapple on your pizza?",Message.IN); break;
		
		//case "No, I  don't like pizza":  talkScreens.get(screenIndex).addMessage("",Message.IN); break;
		
		//Gesprek 2
		case "Can I have your game PC please?": 
			talkScreens.get(screenIndex).addMessage("Here is it, do with it what you want.",Message.IN);
			talkScreens.get(screenIndex).removeCommand(text1);
			player.hasGamePC = true;
			break;
			
		case "Do you have food?":  
			talkScreens.get(screenIndex).addMessage("Nononononononononono",Message.IN); 
			talkScreens.get(screenIndex).removeCommand(text1);
			break;
		
		case "Do you speak English?":
			talkScreens.get(screenIndex).addMessage("Of course my friend",Message.IN); 
			talkScreens.get(screenIndex).removeCommand(text1);
			break;
		
		case " ":  talkScreens.get(screenIndex).addMessage("",Message.IN); break;
		
		case "nothing": talkScreens.get(screenIndex).addMessage("Hey, why don't you say something?",Message.IN); break;
		
		default: 
			talkScreens.get(screenIndex).addMessage("Hey, don't say something to me!", Message.IN);
		}
		
		//System.out.println(text);
		pane.repaint();
		
		//switch(position){
			
		
		//}
	}

	public void lockScopeShooter(){
		if(currentScreenType == "ShooterScreen"){
			ShooterScreen.lockScope();
			shooterScreens.get(screenIndex).renew();
		}
	}
	
	
	public void associateNumberKeys(String...commands){
		StringNumberCommands.clear();
		for(String command : commands ){
			StringNumberCommands.add(command);
		}
		for(int i = 4 - commands.length; i > 0; i-- ){
			StringNumberCommands.add("adasfd");
		}
		//for(String command : StringNumberCommands){
		//	System.out.print(command + "    ");
		//}
		//System.out.print(" ");
	}
	
	public void associateArrowKeys(String...commands){
		StringArrowCommands.clear();
		for(String command : commands ){
			StringArrowCommands.add(command);
		}
		for(int i = 4 - commands.length ; i > 0; i-- ){
			StringArrowCommands.add("adasfd");
		}
	}
	
	public void resetKeys(){
		StringNumberCommands.clear();
		StringArrowCommands.clear();
		for(int i = 4; i > 0; i-- ){
			StringNumberCommands.add("adasfd");
			StringArrowCommands.add("adasfd");
		}
	}
	
	public void runKey(int key){
		int bi;

		switch(key){
		case KeyEvent.VK_1:
		case KeyEvent.VK_NUMPAD1:
			bi = 0;
			break;

		case KeyEvent.VK_2:
		case KeyEvent.VK_NUMPAD2:
			bi = 1;
		break;

		case KeyEvent.VK_3:
		case KeyEvent.VK_NUMPAD3:
			bi = 2;
		break;

		case KeyEvent.VK_4:
		case KeyEvent.VK_NUMPAD4:
			bi = 3;
		break;

		default:
			bi = 1000;
		}

		if(currentScreenType == "GameScreen" && bi < gameScreens.get(screenIndex).buttonList.size()){
			Programma.createAndShowGUI(gameScreens.get(screenIndex).buttonList.get(bi).getCommand());
		}
		else if(currentScreenType == "TalkScreen" && bi < talkScreens.get(screenIndex).buttonList.size()){
			Programma.createAndShowGUI(talkScreens.get(screenIndex).buttonList.get(bi).getCommand());
		}
	}
	
	public void pauseDying(){
		if(death != null){
			death.pauseDying();
			if(currentScreenType == "SpaceScreen"){
				space.meteor.pause();
			}
		}
	}
	public void dontDie(){
		if(death != null){
			death.dontDie();
		}
		stopShooterScreen();
	}

	
	public void actionPerformed(ActionEvent arg0) {
		switch(position){
		case "intro": break;
		
		case "test": 
		
			
		
		}
		
		
	}
	public void stopShooterScreen(){
		if(!shooterScreens.isEmpty()){
			//shooterScreens.get(screenIndex).stop();
			for(int i = 0; i < shooterScreens.size(); i++){
				shooterScreens.get(i).stop();
			}
			ShooterScreen.stopTimers();
		}
	}
	public void pauseGame(){
		if(currentScreenType == "ShooterScreen"){
			if(!shooterScreens.isEmpty() && currentScreenType == "ShooterScreen"){
				shooterScreens.get(0).pause();
				System.out.println("pause");
			}
		}
		else if(currentScreenType == "SpaceScreen"){
			space.meteor.pause();
		}
		
	}
	public void resumeGame(){		
		if(currentScreenType == "ShooterScreen"){
			if(!shooterScreens.isEmpty() && currentScreenType == "ShooterScreen"){
				shooterScreens.get(0).resume();
			}
		}
		else if(currentScreenType == "SpaceScreen"){
			space.meteor.resume();
		}
	}
	public void repaintScreen(){
		switch(currentScreenType){
		case "ShooterScreen": shooterScreens.get(screenIndex).renew();
		}
	}
	public void reloadShooter(){
	    if(shooterScreens.size() > screenIndex){
            shooterScreens.get(screenIndex).reload();
        }
	}
	
	public void addContinue(){
		for(TextScreen screen : textScreens){
			screen.addContinue();
		}
	}


	
	
	
}


