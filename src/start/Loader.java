package start;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import components.EasyButton;
import entities.Troll;
import layout.Theme;

public class Loader implements Runnable, ActionListener{
	//String shooterImage;
	static Timer timer;
	//String[] strings;
	static String mainText;
	public static int index = 0;
	static boolean isIndexOne = false;
	static boolean isIndexTwo = false;
	public static boolean isLoadingReady = true;

	public final static int LOAD_1_AND_2 = 1353;
	//int Q;
	//nt P;
	//static String image;
	/*
	public void shooterScreen(String image){
		//image = Image;
		//Timer timer = new Timer(30, this);
		typeToLoad = 1;
		timer.restart();
		shooterImage = image;
	}
	
	public void gameScreen(int q, int p, String maintext, String...Strings){
		mainText = maintext;
		timer.restart();
		typeToLoad = 2;
		Q = q;
		P = p;
	} */

	@Override
	public void run() {
		timer = new Timer(10, this);
		timer.setInitialDelay(10);
		timer.setRepeats(false);
	}
	
	public static void load(int Index){
		if(Index < 0){
			return;
		}
		index = Index;
		isLoadingReady = false;
		if(index > 2){
			Programma.gameContainer.isLoadScreen = true;
		}
		timer.start();
		if(index != 0){
			for(TextScreen screen : Programma.gameContainer.textScreens){
				screen.addLoading();
				screen.repaint();
			}
		}
	}
	
	public static void clearScreens(){
		Programma.gameContainer.gameScreens.clear();
		Programma.gameContainer.textScreens.clear();
		Programma.gameContainer.wordScreens.clear();
		Programma.gameContainer.talkScreens.clear();
		Programma.gameContainer.shooterScreens.clear();
		System.gc();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.stop();
		//int type = typeToLoad;
		//int p = P;
		//int q = Q;
		
		switch(index){
			case 0:

				//TextScreen text = new TextScreen("You are flying in an airplane. Everything goes well untill...  ");
				//text.setText("lsdfkljsld");
				//text.setImage("Images/Airplane_Window.jpg");
				Programma.gameContainer.introScreen = new TextScreen("You are flying in an airplane. <br> Everything goes well untill...  ");
				Programma.gameContainer.introScreen.setImage("Images/Airplane_Window.jpg");
				break;

			case 1:
			case LOAD_1_AND_2:

				GameScreen explore = new GameScreen(2,1); // 0
				explore.setAns("Stay", "Explore the island");
				explore.setCommands("@stay", "@explore");
				explore.setPictures("Images/plane-wreck.jpg");
				explore.setMainText("You are the only survivor. Do you want to stay at the plane wreck<br>and wait for help or do you want to explore the island?");

				//Programma.gameContainer.space = new SpaceScreen();

				if(!isIndexOne){
					clearScreens();

					TextScreen crash = new TextScreen("... the airplane crashes for some reason on a lonely island. "); // 0
					crash.setImage("Images/crashing plane.jpg");
					Programma.gameContainer.textScreens.add(crash);

					TextScreen sleep = new TextScreen("A lightning bolt stroke you in your sleep. <br> You are powdered."); // 1
					sleep.setImage("Images/lightning.jpg");
					Programma.gameContainer.textScreens.add(sleep);

					TextScreen sleep2 = new TextScreen("Its so hard to stay awake when you are sleepy. <br> You really can't stay awake any longer now."); // 2
					sleep2.setImage("Images/plane-wreck.jpg");
					Programma.gameContainer.textScreens.add(sleep2);

					Programma.gameContainer.gameScreens.add(explore);

					//String[] ans = {"\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\""};
					//TalkScreen talk = new TalkScreen("Talk with somebody", ans );
					//talk.setPictures("Images/anonymous.jpg");
					//Programma.gameContainer.talkScreens.add(talk);

					TextScreen save = new TextScreen("... the airplane reaches your destination."); // 3
					save.setImage("Images/airport.jpg");
					Programma.gameContainer.textScreens.add(save);

				}
				else{
					Programma.gameContainer.gameScreens.remove(0);
					Programma.gameContainer.gameScreens.add(0, explore);
				}
				isIndexOne = true;





				/*GameScreen direction = new GameScreen(4,1);
				direction.setAns("To the mansion", "To the volcano","To the forest", "To the beach");
				direction.setCommands("@mansion", "@volcano", "@forest", "@beach");
				direction.setPictures("Images/plane-wreck.jpg");
				direction.setMainText("In wich direction do you want to go?");
				Programma.gameContainer.gameScreens.add(direction);




				/*GameScreen game  = new GameScreen(2,2);
				game.setAns("run away", "launch a falcon heavy");
				game.setCommands("@troll", "*These berries are very poisonous");
				game.setPictures("Images/anonymous.jpg", "Images/anonymous.jpg");
				game.setMainText("This is a test,<br> what would you do?");

				ShooterScreen shooter = new ShooterScreen();
				shooter.addImage("Images/landscape.jpeg");

				String[] ans = {"\"something\"" , "\"something else\"", "\"another thing\"","\"nothing\""};
				TalkScreen talk = new TalkScreen("Talk with somebody", ans );
				talk.setPictures("Images/anonymous.jpg");

				Programma.gameContainer.shooterScreens.add(shooter);
				Programma.gameContainer.gameScreens.add(game);
				Programma.gameContainer.talkScreens.add(talk);

				ShooterScreen shooter2 = new ShooterScreen();
				shooter2.addImage("Images/tropisch-eiland-1728x1080.jpg");


				GameScreen game2  = new GameScreen(2,2);
				game2.setAns("run away", "launch a falcon heavy");
				game2.setCommands("@troll", "*These berries are very poisonous");
				game2.setPictures("Images/grafiek.PNG", "Images/grafiek.PNG");
				game2.setMainText("This is a test,<br> what would you do?");


				TalkScreen talk2 = new TalkScreen("Talk with somebody", ans );
				talk2.setPictures("Images/Formule lol.PNG");

				Programma.gameContainer.shooterScreens.add(shooter2);
				Programma.gameContainer.gameScreens.add(game2);
				Programma.gameContainer.talkScreens.add(talk2); */
				Programma.continueButton.setRightLayout();
				Programma.gameContainer.introScreen.addContinue();
				if(index != LOAD_1_AND_2){
					break;
				}

			case 2:
				if(!isIndexTwo){
					WordScreen zzz = new WordScreen("Zzzzzz."); // 0
					zzz.setTextColor(Theme.darkOrange);
					Programma.gameContainer.wordScreens.add(zzz);

					WordScreen boom = new WordScreen("boom");
					boom.addExplosion();
					boom.setTextColor(Color.BLACK);
					Programma.gameContainer.wordScreens.add(boom);

					TextScreen fight = new TextScreen("Wow! You defeated nearly all the zombies."); // 4
					fight.setImage("Images/zombies.jpg");
					Programma.gameContainer.textScreens.add(fight);

					GameScreen zombies = new GameScreen(4,1); // 1
					zombies.setAns("Flee", "Fight", "Act zombily", "Stop dreaming");
					zombies.setCommands("@fleeZombie", "@fightZombie", "@actZombily", "*Fool, this wasn't a dream. Zombies are now enjoying your brains.");
					zombies.setPictures("Images/zombies.jpg");
					zombies.setMainText("When you wake up, you are surrounded by zombies <br> What will you do?");
					Programma.gameContainer.gameScreens.add(zombies);

					GameScreen vulcano = new GameScreen(3,1); // 2
					vulcano.setAns("Walk on lava", "Climb a rock", "Swim in lava");
					vulcano.setCommands("*Wuuut!? How stupid are you?", "*Didn't you know that lava is hot enough to melt rock (and you)?", "*Even I don't know what to say, for this proves you IQ is negative.");
					vulcano.setPictures("Images/volcano.jpg");
					vulcano.setMainText("When you arrive at the vulcano, it erupts. <br>The lava flows in your direction. What next?");
					Programma.gameContainer.gameScreens.add(vulcano);

					GameScreen tiger = new GameScreen(4,1); // 3
					tiger.setAns("Flee", "Fight","Stare at him", "Climb a tree");
					tiger.setCommands("@tigerRiver", "*Foolish you, everyone knows that human beings can't fight tigers.", "@deadTiger", "*Didn't you know that tigers can climb trees?");
					tiger.setPictures("Images/tiger.jpg");
					tiger.setMainText("The first thing you meet is a hungry tiger.");
					Programma.gameContainer.gameScreens.add(tiger);

					TextScreen deadTiger = new TextScreen("For some reason, the tiger <br> died when you stared at him."); // 5
					deadTiger.setImage("Images/dead_tiger.jpg");
					Programma.gameContainer.textScreens.add(deadTiger);

					GameScreen river = new GameScreen(2,1); // 4
					river.setAns("Swim over the river", "Follow the river");
					river.setCommands("@piranha", "@run");
					river.setPictures("Images/river.png");
					river.setMainText("The tiger is now chasing you. <br> After a long run a river crosses your path.");
					Programma.gameContainer.gameScreens.add(river);

					TextScreen piranha = new TextScreen("Too bad, the river is filled with piranhas. <br> Your skelleton now lies at the bottom of river."); // 6
					piranha.setImage("Images/pirahna.jpg");
					Programma.gameContainer.textScreens.add(piranha);

					GameScreen hedgehog = new GameScreen(3,1); // 5
					hedgehog.setAns("Eat him", "Leave him", "Take him with you");
					hedgehog.setCommands("*You choked in one of the spines of the hedgehog and died.", "@tsunami", "@pet");
					hedgehog.setPictures("Images/hedgehog.jpg");
					hedgehog.setMainText("The first thing you meet is a cute hedgehog.");
					Programma.gameContainer.gameScreens.add(hedgehog);

					TextScreen tsunami = new TextScreen("At that moment, a tsunami kills <br> both you and the hedgehog"); // 7
					tsunami.setImage("Images/tsunami.jpg");
					Programma.gameContainer.textScreens.add(tsunami);

					TextScreen pet = new TextScreen("That sound like a great idea!! <br> Hedgehogs are really good pets."); // 8
					pet.setImage("Images/hedgehog.jpg");
					Programma.gameContainer.textScreens.add(pet);

					TextScreen blackSky = new TextScreen("When you arrive at the dark, scary mansion, <br> the sky turns black."); // 9
					blackSky.setImage("Images/scary_mansion.png");
					Programma.gameContainer.textScreens.add(blackSky);

					TextScreen tired = new TextScreen("Nice try, but you are tired, so the tiger gets you. <br> (He said you tasted good)"); // 10
					tired.setImage("Images/tiger.jpg");
					Programma.gameContainer.textScreens.add(tired);

				}


				isIndexTwo = true;
				break;

			case 3: // richting mansion
				isIndexOne = false;
				isIndexTwo = false;

				TextScreen first8 = Programma.gameContainer.textScreens.get(9);

				clearScreens();

				GameScreen mansion = new GameScreen(3,1); // 0
				mansion.setAns("Front door", "Back door", "Secret door");
				mansion.setCommands("@weight", "@hall", "@acid");
				mansion.setPictures("Images/scary_mansion.png");
				mansion.setMainText("There are 3 doors in the mansion. <br> Do you take the front door, the back door or the secret door?");
				Programma.gameContainer.gameScreens.add(mansion);

				TextScreen weight = new TextScreen("When you touch the door, a <br> 16-tons weight falls from above."); // 0
				weight.setImage("Images/16_tons.jpg");
				Programma.gameContainer.textScreens.add(weight);

				WordScreen squeeze = new WordScreen("Squeeeeze!"); // 0
				squeeze.setTextColor(Theme.darkOrange);
				squeeze.addContinue();
				squeeze.addBlood();
				Programma.gameContainer.wordScreens.add(squeeze);

				TextScreen acid = new TextScreen("When you open secret the door, <br> a big wave of sulfuric acid comes out."); // 1
				acid.setImage("Images/splash.jpeg");
				Programma.gameContainer.textScreens.add(acid);

				TextScreen hall = new TextScreen("Slowly the back door open. A long hall lies <br> before you. At the end is a dark pit."); // 2
				hall.setImage("Images/mansion_hall.jpg");
				Programma.gameContainer.textScreens.add(hall);

				GameScreen pit = new GameScreen(2,1); // 1
				pit.setAns("Yes", "Nope");
				pit.setCommands("@minecartPit", "@hammer");
				pit.setPictures("Images/pit.png");
				pit.setMainText("Do you want to descend into the pit?");
				Programma.gameContainer.gameScreens.add(pit);

				TextScreen minecart1 = new TextScreen("Inside the pit is a minecart  on rails.<br>Of course you will make a ride."); // 3
				minecart1.setImage("Images/minecart1.jpg");
				Programma.gameContainer.textScreens.add(minecart1);

				TextScreen trap = new TextScreen("When you walk through the long hall,<br> suddenly a big hammer comes out of the <br> ceiling and beats your head."); // 4
				trap.setImage("Images/mansion_hall.jpg");
				Programma.gameContainer.textScreens.add(trap);

				first8.addContinue();

				/*
				GameScreen screen = new GameScreen(Q, P);
				screen.setMainText(mainText);

				switch(Q){
				case 1: screen.setAns(strings[0]); screen.setCommands(strings[2]); break;
				case 2: screen.setAns(strings[0], strings[1]); screen.setCommands(strings[2], strings[3]); break;
				case 3: screen.setAns(strings[0], strings[1], strings[2]); screen.setCommands(strings[3], strings[4], strings[5]); break;
				}
				switch(P){
				case 1: screen.setPictures(strings[strings.length - 1]); break;
				case 2: screen.setPictures(strings[strings.length - 2], strings[strings.length - 1]); break;
				case 3: screen.setPictures(strings[strings.length - 3], strings[strings.length - 2], strings[strings.length - 1]); break;
				} */
				break;

			case 4:
				isIndexOne = false;
				isIndexTwo = false;

				TextScreen first4 = Programma.gameContainer.textScreens.get(4);

				/*for(int i = 0; Programma.gameContainer.gameScreens.size() > 0; i++ ){
					Programma.gameContainer.gameScreens.remove(0);
					System.gc();
				}
				for(int i = 0; Programma.gameContainer.textScreens.size() > 0; i++ ){
					Programma.gameContainer.textScreens.remove(0);
					System.gc();
				} */

				GameScreen river2 = Programma.gameContainer.gameScreens.get(4);
				river2.setCommands("@piranha2", "@bridge");
				river2.setMainText("After some time, a river crosses your path.");

				TextScreen piranha2 = Programma.gameContainer.textScreens.get(6);

				clearScreens();

				GameScreen food = new GameScreen(3,3); // 0
				food.setAns("Raw tiger meat", "Red fruit", "Green fruit");
				food.setCommands("*Noob, even a child knows raw meat is lethal.", "@redFruit", "*Green fruit tastes great, but it's very poisonous.");
				food.setPictures("Images/dead_tiger.jpg", "Images/red_fruit.jpg", "Images/green_fruit.jpg");
				food.setMainText("Staring makes you hungy, what are you going to eat?");
				Programma.gameContainer.gameScreens.add(food);

				Programma.gameContainer.gameScreens.add(river2); // 1

				TextScreen fruit = new TextScreen("Red fruit tastes good and gives new energy."); // 0
				fruit.setImage("Images/red_fruit.jpg");
				Programma.gameContainer.textScreens.add(fruit);

				Programma.gameContainer.textScreens.add(piranha2); // 1

				GameScreen bridge = new GameScreen(2, 1); // 2
				bridge.setAns("Walk across the bridge", "Continue on your way");
				bridge.setCommands("@junction", "@forestFire");
				bridge.setPictures("Images/bridge.jpg");
				bridge.setMainText("After some time you find a bridge across the river. <br> Do you want to walk across the bridge?");
				Programma.gameContainer.gameScreens.add(bridge);

				TextScreen fire = new TextScreen("For some reason, the forest catches fire."); // 2
				fire.setImage("Images/forest_fire.jpg");
				Programma.gameContainer.textScreens.add(fire);

				TextScreen junction = new TextScreen("You find yourself at a junction."); // 3
				junction.setImage("Images/junction_4_paths.jpg");
				Programma.gameContainer.textScreens.add(junction);

				/*GameScreen river = new GameScreen(3,1); // 1
				river.setAns("Swim over the river", "Follow the river");
				river.setCommands("@tigerRiver", "*Foolish you, everyone knows that human beings can't fight tigers.", "@deadTiger", "*Didn't you know that tigers can climb trees?");
				river.setPictures("Images/river.png");
				river.setMainText("After some time, a river crosses your path.");
				Programma.gameContainer.gameScreens.add(river); */

				first4.addContinue();
				break;

			case 5:
				isIndexOne = false;
				isIndexTwo = false;

				//TextScreen first5 = Programma.gameContainer.textScreens.get(7);

				clearScreens();

				GameScreen palm = new GameScreen(2, 1); // 0
				palm.setAns("Eat a coco fruit", "Drink from the sea");
				palm.setCommands("@coco", "drinkSea");
				palm.setPictures("Images/beach_coco_tree.jpg");
				palm.setMainText("After a long walk, you arrive at the beach. <br> You are hungry and thirsty. What to do?");
				palm.soundButton("Sound/The_Coconut_Song.wav", 0);
				Programma.gameContainer.gameScreens.add(palm);

				GameScreen beach = new GameScreen(3, 1); // 1
				beach.setAns("To the left", "Straight ahead", "To the right");
				beach.setCommands("@boomerang", "*You drowned in the sea", "@minecartBeach");
				beach.setPictures("Images/beach_coco_tree.jpg");
				beach.setMainText("That tastes good! <br> In which direction do you want to go now?");
				Programma.gameContainer.gameScreens.add(beach);

				GameScreen boomerang = new GameScreen(2, 1); // 2
				boomerang.setAns("Throw it", "Leave it");
				boomerang.setCommands("@boomerangDeath", "@smart");
				boomerang.setPictures("Images/beach_and_boomerang.png");
				boomerang.setMainText("At the beach, you find a boomerang. <br> What do you want to do with it?");
				Programma.gameContainer.gameScreens.add(boomerang);

				TextScreen boomerangDeath = new TextScreen("This is SO predictable! The boomerang <br> comes back and chops off your head."); // 0
				boomerangDeath.setImage("Images/beach_and_boomerang.png");
				Programma.gameContainer.textScreens.add(boomerangDeath);

				GameScreen minecart = new GameScreen(2, 1); // 3
				minecart.setAns("Make a ride", "Leave it");
				minecart.setCommands("@beachRide", "@crocodile");
				minecart.setPictures("Images/minecart0.jpg");
				minecart.setMainText("After some time, you find a mineshaft with a minecart. <br> What do you want to do?");
				Programma.gameContainer.gameScreens.add(minecart);

				TextScreen crocodile = new TextScreen("At that moment, a crocoldile eats you."); // 1
				crocodile.setImage("Images/crocodile.jpg");
				Programma.gameContainer.textScreens.add(crocodile);

				TextScreen go = new TextScreen("Ok, there you go."); // 2
				go.setImage("Images/mineshaft_rails1.jpg");
				Programma.gameContainer.textScreens.add(go);

				/*TextScreen smart = new TextScreen("You are smart."); // 3
				smart.setImage("Images/beach_and_boomerang.png");
				Programma.gameContainer.textScreens.add(smart);*/

				TextScreen hut = new TextScreen("At that moment you see a Zulu."); // 3
				hut.setImage("Images/zulu.jpg");
				Programma.gameContainer.textScreens.add(hut);

				//first5.addContinue();
				break;

			case 6:
				clearScreens();

				TextScreen light = new TextScreen("Finally, light at the end."); // 0
				light.setImage("Images/mineshaft_rails1.jpg");
				Programma.gameContainer.textScreens.add(light);

				GameScreen mineshaft = new GameScreen(2, 1); // 0
				mineshaft.setAns("To the left", "To the right.");
				mineshaft.setCommands("", "");
				mineshaft.setPictures("Images/mineshaft_rails0.jpg");
				mineshaft.setMainText("After a wild ride through caves, the rail splits. <br> What do you want to do?");
				Programma.gameContainer.gameScreens.add(mineshaft);

				TextScreen pool = new TextScreen("Uh oh, the rails ands in a toxic pool. <br> Didn't you see the warning sign?"); // 1
				pool.setImage("Images/toxic_pool.jpg");
				Programma.gameContainer.textScreens.add(pool);

				GameScreen cave = new GameScreen(2, 1); // 1
				cave.setAns("Go outside", "Continue your ride.");
				cave.setCommands("@hacker", "@toxic");
				cave.setPictures("Images/cave_light.jpg");
				cave.setMainText("After a wild ride, you find a cave with light at the end. <br> Do you want to leave your minecart and go outside or do you want to continue your ride?");
				Programma.gameContainer.gameScreens.add(cave);

				TextScreen hacker = new TextScreen("At the exit of the cave, <br> you meet a mysterious person."); // 2
				hacker.setImage("Images/arabic_hacker.jpg");
				Programma.gameContainer.textScreens.add(hacker);

				/*GameScreen river = new GameScreen(3,1); // 0
				river.setAns("Swim over the river", "Follow the river");
				river.setCommands("@piranha", "addsf");
				river.setPictures("Images/river.png");
				river.setMainText("After some time, a river crosses your path.");
				Programma.gameContainer.gameScreens.add(river);


				TextScreen piranha = new TextScreen("Too bad, the river is filled with piranhas. <br> Your skelleton now lies at the bottom of river."); // 5
				piranha.setImage("Images/pirahna.jpg");
				Programma.gameContainer.textScreens.add(5, piranha); */

				break;

			case 7:
				clearScreens();

				GameScreen hacker1 = new GameScreen(2, 1); // 0
				hacker1.setAns("Run away", "Talk with him");
				hacker1.setCommands("@snake1_back", "@hacker1");
				hacker1.setPictures("Images/arabic_hacker.jpg");
				hacker1.setMainText("At the exit of the cave, you meet a mysterious person.<br>What do you want to do?");
				Programma.gameContainer.gameScreens.add(hacker1);

				GameScreen snake0 = new GameScreen(2, 1); // 1
				snake0.setAns("Run back", "Jump over the snake");
				snake0.setCommands("@snake", "@hacker0");
				snake0.setPictures("Images/snake2.jpg");
				snake0.setMainText("At that moment, you see a snake at the ground.");
				Programma.gameContainer.gameScreens.add(snake0);

				GameScreen snake1 = new GameScreen(2, 1); // 2
				snake1.setAns("Run back", "Duck");
				snake1.setCommands("@snake0_back", "@hacker0");
				snake1.setPictures("Images/snake.jpg");
				snake1.setMainText("At that moment, you see a snake in a tree.");
				Programma.gameContainer.gameScreens.add(snake1);

				TextScreen hacker2 = new TextScreen("\"I am the wise Ali Isgon Nak'illu. I discovered that our universe is actually a simulation in a Bolzmann brain.\""); // 0
				hacker2.setImage("Images/arabic_hacker.jpg");
				Programma.gameContainer.textScreens.add(hacker2);

				TextScreen pills0 = new TextScreen("\"I have 2 pills for you. If you take the red one, I will tell you the whole truth. If you take the blue one, you'll forget everything about this.\""); // 1
				pills0.setImage("Images/red_and_blue_pill.jpg");
				Programma.gameContainer.textScreens.add(pills0);

				GameScreen pills1 = new GameScreen(2, 1); // 3
				pills1.setAns("Red pill", "Blue pill");
				pills1.setCommands("*The truth is: both pills contain poison.", "*Did you ever see a dead person remembering something?");
				pills1.setPictures("Images/red_and_blue_pill.jpg");
				pills1.setMainText("Which pill do you take?");
				Programma.gameContainer.gameScreens.add(pills1);

				GameScreen roadJunction = new GameScreen(3, 1); // 4
				roadJunction.setAns("To the left", "Straight ahead", "To the right");
				roadJunction.setCommands("al;fkdj", "afds ahead", "To the af");
				roadJunction.setPictures("Images/junction_4_paths.jpg");
				roadJunction.setMainText("You find yourself at a junction.<br>In which direction do you want to go?");
				Programma.gameContainer.gameScreens.add(roadJunction);

				TextScreen fire1 = new TextScreen("For some reason, the forest around you catches fire."); // 2
				fire1.setImage("Images/forest_fire.jpg");
				Programma.gameContainer.textScreens.add(fire1);

				String[] ans1 = {"\"Do you have food?\"" , "\"Do you speak English?\"", "\"Can I have your game PC please?\"","Go back"};
				TalkScreen zulu = new TalkScreen("Talk with a zulu", ans1 );
				zulu.setPictures("Images/zulu.jpg");
				zulu.hideMainText(true);
				//zulu.setOneCommand(/*de plaats waarnaar terug te gaan, 3);
				Programma.gameContainer.talkScreens.add(zulu);

				String[] ans2 = {"\"I am the only survivor of a plane crash.\"", "\"I am a dangerous alien.\"", "\"I do not know.\"", "Continue on your way"};
				TalkScreen woman = new TalkScreen("Talk with a woman", ans2 );
				woman.hideMainText(true);
				woman.setPictures("Images/woman_and_cabin.png");
				//zulu.setOneCommand(/*de plaats waarnaar terug te gaan, 3);
				Programma.gameContainer.talkScreens.add(woman);

				ShooterScreen firstBear = new ShooterScreen();
				firstBear.addImage("Images/forest_1.jpg");
				Programma.gameContainer.shooterScreens.add(firstBear);

				TextScreen nice = new TextScreen("Nice, you killed the bear."); // 2
				nice.setImage("Images/forest_1.jpg");
				Programma.gameContainer.textScreens.add(nice);
				break;

			case 8:
				clearScreens();

				ShooterScreen shooter0 = new ShooterScreen();  //0
				shooter0.addImage("Images/forest_3.jpg");
				Programma.gameContainer.shooterScreens.add(shooter0);

				ShooterScreen shooter1 = new ShooterScreen(); //1
				shooter1.addImage("Images/flower_field_forest.jpg");
				Programma.gameContainer.shooterScreens.add(shooter1);

				ShooterScreen shooter2 = new ShooterScreen(); //2
				shooter2.addImage("Images/forest_2.jpg");
				Programma.gameContainer.shooterScreens.add(shooter2);

				ShooterScreen shooter3 = new ShooterScreen(); //3
				shooter3.addImage("Images/forest_4.jpg");
				Programma.gameContainer.shooterScreens.add(shooter3);

				ShooterScreen shooter4 = new ShooterScreen(); //4
				shooter4.addImage("Images/forest_8.jpg");
				Programma.gameContainer.shooterScreens.add(shooter4);

				ShooterScreen shooter5 = new ShooterScreen(); //5
				shooter5.addImage("Images/forest_7.jpg");
				Programma.gameContainer.shooterScreens.add(shooter5);

				ShooterScreen shooter6 = new ShooterScreen(); //6
				shooter6.addImage("Images/forest_6.jpg");
				Programma.gameContainer.shooterScreens.add(shooter6);

				ShooterScreen shooter7 = new ShooterScreen(); //7
				shooter7.addImage("Images/forest_11.png");
				Programma.gameContainer.shooterScreens.add(shooter7);

				ShooterScreen shooter8 = new ShooterScreen(); //8
				shooter8.addImage("Images/forest_painting_1.jpg");
				Programma.gameContainer.shooterScreens.add(shooter8);

				ShooterScreen shooterMineEntrance = new ShooterScreen(); //7
				shooterMineEntrance.addImage("Images/mine_entrance.jpg");
				Programma.gameContainer.shooterScreens.add(shooterMineEntrance);

				TextScreen portalLoad = new TextScreen("Suddenly, a portal apears."); // 2
				portalLoad.setImage("Images/mine_entrance.png");
				Programma.gameContainer.textScreens.add(portalLoad);

				break;

			case 9:
				clearScreens();

				ShooterScreen shooterScreenTroll = new TrollScreen();

				GameScreen portal = new GameScreen(2, 1); // 0
				portal.setAns("Step through the portal", "Enter the mine");
				portal.setCommands("@dimension", "@shooter10");
				portal.setPictures("Images/mine_entrance.png");
				portal.setMainText("Do you want to step through the portal or do you want to enter the mine?");
				Programma.gameContainer.gameScreens.add(portal);

				ShooterScreen shooter10 = new ShooterScreen(); // 0
				shooter10.addImage("Images/mineshaft0.jpg");
				Programma.gameContainer.shooterScreens.add(shooter10);

				ShooterScreen shooter11 = new ShooterScreen(); // 1
				shooter11.addImage("Images/mineshaft1.jpg");
				Programma.gameContainer.shooterScreens.add(shooter11);

				ShooterScreen shooter12 = new ShooterScreen(); // 2
				shooter12.addImage("Images/mineshaft2.jpg");
				Programma.gameContainer.shooterScreens.add(shooter12);

				ShooterScreen shooter13 = new ShooterScreen(); // 3
				shooter13.addImage("Images/throne_room.jpg");
				Programma.gameContainer.shooterScreens.add(shooter13);

				//ShooterScreen shooterScreenTroll = new ShooterScreen();
				//shooterScreenTroll.addImage("Images/throne_room.jpg");
				Programma.gameContainer.shooterScreens.add(shooterScreenTroll); // 4

				TextScreen exit = new TextScreen("After a long search, you find the exit of the mine."); // 2
				exit.setImage("Images/cave_exit_hall.jpg");
				Programma.gameContainer.textScreens.add(exit);
				break;

			 case 10:
			 	clearScreens();

			 	ShooterScreen shooterKangeroo = new ShooterScreen(); // 0
				shooterKangeroo.addImage("Images/australian_landscape.jpg");
				Programma.gameContainer.shooterScreens.add(shooterKangeroo);

				ShooterScreen mighty = new ShooterScreen(); // 1
				mighty.addImage("Images/bamboo_forest3.jpg");
				Programma.gameContainer.shooterScreens.add(mighty);

				GameScreen canyon = new GameScreen(4, 1); // 0
				canyon.setAns("Jump over it", "Climb down", "Jump down", "Go back");
				canyon.setCommands("*Maybe the canyon was a bit to wide?", "@kangaroos", "*Did you enjoy your flight?", "@mighty" );
				canyon.setPictures("Images/canyon.jpg");
				canyon.setMainText("After a few hours walking, a deep canyon blocks your way. How are you going to pass it?");
				Programma.gameContainer.gameScreens.add(canyon);

				GameScreen box = new GameScreen(4, 4); // 1
				box.setAns("Climbing gear", "Helicopter", "Drone", "Space shuttle");
				box.setCommands("@madeInChina", "@helicopter", "@drone", "@loadSpaceShuttle");
				box.setPictures("Images/climbing-gear.jpg", "Images/helicopter.jpg", "Images/drone.jpg", "Images/space_shuttle.jpg");
				box.setMainText("Well, that works! Down in the canyon you find a box. It contains some useful stuff.");
				Programma.gameContainer.gameScreens.add(box);

				TextScreen madeInChina = new TextScreen("Halfway your climb you discover the climbing suit is made in China...."); // 0
				madeInChina.setImage("Images/made_in_China.jpg");
				Programma.gameContainer.textScreens.add(madeInChina);

				WordScreen crack = new WordScreen("Crack."); // 0
				//crack.setTextColor(Theme.darkerGreen);
				Programma.gameContainer.wordScreens.add(crack);

				WordScreen explosion = new WordScreen("Boom!!!"); // 1
				explosion.addExplosion();
				Programma.gameContainer.wordScreens.add(explosion);

				TextScreen warning = new TextScreen("While the engine is warming up, a display gives the following error:"); // 1
				warning.setImage("Images/helicopter.jpg");
				Programma.gameContainer.textScreens.add(warning);

				WordScreen fuel = new WordScreen("WARNING, LEAK IN FUEL SUPPLY"); // 0
				fuel.setTextColor(Color.RED);
				fuel.setBackground(Color.BLACK);
				fuel.setTextFont(Programma.helvetica20);
				Programma.gameContainer.wordScreens.add(fuel);

				TextScreen drone = new TextScreen("For some reason, this drone cannot fly (yet) :-("); // 2
				// drone.setImage("Images/drone.jpg");
				Programma.gameContainer.textScreens.add(drone);

				TextScreen spaceShuttle = new TextScreen("You are now in the cockpit of the space shuttle"); // 3
				spaceShuttle.setImage("Images/cockpit.png");
				Programma.gameContainer.textScreens.add(spaceShuttle);


				 break;

			case 11:
				clearScreens();

				Programma.gameContainer.space = new SpaceScreen();

				WordScreen explosion2 = new WordScreen("Boom!!!"); // 1
				explosion2.addExplosion();
				Programma.gameContainer.wordScreens.add(explosion2);

				TextScreen blackHole = new TextScreen("Suddenly, out of nothing a black hole appears."); // 0
				blackHole.setImage("Images/black_hole.jpg");
				Programma.gameContainer.textScreens.add(blackHole);

				TextScreen newYork = new TextScreen("After a short flight, you reach New York."); // 0
				newYork.setImage("Images/new_york.jpg");
				Programma.gameContainer.textScreens.add(newYork);

				GameScreen mars = new GameScreen(2, 1); // 0
				mars.setAns("Yes", "No");
				mars.setCommands("completed", "*You died from homesickness");
				mars.setPictures("Images/mars_city.png");
				mars.setMainText("After a long flight you reach Mars. You see this lovely village.<br>Do you accept it as your new home?");
				Programma.gameContainer.gameScreens.add(mars);

				case 42:
				//ShooterScreen shooterScreenTest = new ShooterScreen();
				//shooterScreenTest.addImage("Images/throne_room.jpg");
				//shooterScreenTest.addAnimal(new Troll(), 9*Programma.paneWidth/20, 2*Programma.paneHeight/3);
				TrollScreen shooterScreenTest = new TrollScreen();
				Programma.gameContainer.shooterScreens.add(shooterScreenTest);
				break;


		}

		System.out.println("loading " + index + " done");

		for(GameScreen screen : Programma.gameContainer.gameScreens){
			screen.resetBorders();
		}
		
		for(TextScreen screen : Programma.gameContainer.textScreens){
			screen.resetBorders();
		}
		for(TalkScreen screen : Programma.gameContainer.talkScreens){
			screen.resetBorders();
		}
		isLoadingReady = true;

	}

}
