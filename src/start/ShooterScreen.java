package start;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import components.*;
import entities.*;
import sound.SoundMixer;
//import MouseEvent;

public class ShooterScreen extends JPanel implements MouseInputListener, ActionListener{
	int oldX;
	int oldY;
	int deltaX;
	int deltaY;
	int moveGun = 5;
	boolean isInScope = false;
	boolean isShooting = false;
	
	JPanel black;
	JPanel black2;
	JPanel background;
	JPanel zoomBackground;
	static EasyImage scope;
	static EasyImage gun;
	static EasyImage flash;
	EasyImage backgroundImage;
	JLabel zoomBackgroundImage;
	public static boolean scopeLock;
	public static boolean scopeLockTemp;
	public JLabel scopeLockText;
	Cursor cursor;
	Cursor blankCursor;
	public ArrayList<Animal> animals = new ArrayList<Animal>();
	static EasyButton continueButton = new EasyButton("Continue", "continue");
	//ArrayList<Orc> orcs = new ArrayList<Orc>();
	Timer shooting;
	Timer fps;
	static boolean isReloading = false;
	static Timer reload = new Timer(2956, new BooleanTimeInverter(isReloading));
	static boolean isReloadingSnipe = false;
	static Timer snipe = new Timer(2320, new BooleanTimeInverter(isReloadingSnipe));
	public static ArrayList<JLabel> bullets = new ArrayList<JLabel>();
	ArrayList<Spawner> spawners = new ArrayList<Spawner>();
	
	ArrayList<Color> colors = new ArrayList<Color>();
	Graphics2D g;
	Graphics z;
	int x = 1920;
	int y = 1080;
	int j = 0;
	int k = 0;
	//Bear bear;
	//Deer deer;
	
	public ShooterScreen(){
		super();
		this.setLayout(null);
		this.setOpaque(true);
		setFocusable(true);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setSize(Programma.paneWidth, Programma.paneHeight);
		oldX = Programma.paneWidth/2;
		oldY = Programma.paneHeight/2;
		add(Programma.gameContainer.player);

		shooting = new Timer(50,this);
		fps = new Timer(33, new Movement(this));

		//this.add(scope);
		
		black = new JPanel();
		black.setBounds(0,0, (Programma.paneWidth - scope.getWidth())/2,  Programma.paneHeight);
		black.setBackground(Color.BLACK);
		black.setOpaque(true);
		//add(black);
		
		black2 = new JPanel();
		black2.setBounds(scope.getLocation().x + scope.getWidth(),0, (Programma.paneWidth - scope.getWidth())/2,  Programma.paneHeight);
		black2.setBackground(Color.BLACK);
		black2.setOpaque(true);
		//Programma.setCursorLocation(Programma.paneWidth/2,Programma.paneHeight/2);

		scopeLockText = new JLabel(" ", JLabel.LEFT);
		scopeLockText.setLocation(Programma.paneWidth/96, 2*Programma.paneHeight/3);
		scopeLockText.setSize(black.getWidth(), Programma.paneHeight/10);
		scopeLockText.setLayout(null);
		scopeLockText.setOpaque(false);
		scopeLockText.setFont(Programma.helvetica48);

		background = new JPanel();
		background.setLayout(null);
		background.setSize(Programma.paneWidth, Programma.paneHeight);
		background.setLocation(0,0);
		
		zoomBackground = new JPanel();
		zoomBackground.setLayout(null);
		zoomBackground.setSize(Programma.paneWidth*2, Programma.paneHeight*2);
		zoomBackground.setLocation(Programma.paneWidth/-2, Programma.paneHeight/-2);
		
		Image cursorImg;
		try {
			cursorImg = ImageIO.read(new File("Images/scope4_3.png"));
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(
					cursorImg, new Point(16, 16), "cursor");
				this.setCursor(cursor);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				blankCursorImg, new Point(0, 0), "blank cursor");

		
		colors.add(Color.RED);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		colors.add(Color.CYAN);
		colors.add(Color.BLUE);
		colors.add(Color.MAGENTA);
		//add(black2);
		
		
		//System.out.println("Bullet: " + bullets.get(3).getX() + "  " + bullets.get(3).getY());
		
		
		//black2.setLocation(scope.getLocation().x + scope.getWidth(), 0);
		
		
		//add(black2);
		add(gun);
		add(flash);
		add(background);
						
		this.repaint();
	}
	
	public static void main(String[] args){
		gun = new EasyImage("Images/First_Person_Gun.png", -1, Programma.paneHeight/3);
		gun.setLocation(2*Programma.paneWidth/3, 2*Programma.paneHeight/3);
		
		flash = new EasyImage("Images/gun_flash.png", -1, Programma.paneHeight/5);
		flash.setLocation(gun.getLocation().x + gun.getWidth()/4, gun.getLocation().y + gun.getHeight()/4);
		
		scope = new EasyImage("Images/scope3-1.png",-1, Programma.paneHeight);
		scope.setLocation((Programma.paneWidth - scope.getWidth())/2,0);
		
		reload.setInitialDelay(reload.getDelay());
		reload.setRepeats(false);
		
		snipe.setInitialDelay(snipe.getDelay());
		snipe.setRepeats(false);
		
		continueButton.setBounds(19*Programma.paneWidth/24, 21*Programma.paneHeight/24, Programma.paneWidth/6, 2*Programma.paneHeight/24);
		continueButton.setFont(Programma.helvetica32);
		continueButton.setCursor(null);
		
		int h = 26*Programma.paneHeight/27;
		
		for(int i = 0; i < Programma.gameContainer.player.MAX_AMMO; i++){
			bullets.add(new EasyImage("Images/bullet.png",-1, Programma.paneHeight/27));
			bullets.get(i).setLocation(i*bullets.get(i).getWidth(), h);
		}
	}
	
	public void addImage(String string){
		backgroundImage = new EasyImage(string, Programma.paneWidth, Programma.paneHeight);
		backgroundImage.setLocation(0,0);
		zoomBackgroundImage = backgroundImage.getScaledInstance(2*Programma.paneWidth, 2*Programma.paneHeight);
		zoomBackgroundImage.setLocation(0,0);
		//zoomBackground.setLocation(Programma.paneWidth/-2, Programma.paneWidth/-2); //Programma.paneWidth/-2, Programma.paneWidth/-2
		//System.out.println(zoomBackground.getLocation().x + "    " + zoomBackground.getLocation().y);
		
		//this.add(zoomBackground);
		//this.remove(zoomBackground);
		//add(Programma.gameContainer.player);
		background.add(backgroundImage);
		background.repaint();
		zoomBackground.add(zoomBackgroundImage);
		zoomBackground.repaint();
		//this.repaint();
		//add(Programma.gameContainer.player);
		this.renew();
		this.repaint();
	}
	
	public void scope(MouseEvent e){
		//int x = e.getX(); // System.out.println(x);
		//int y = e.getY(); // System.out.println(y);
		
		
		this.setCursor(blankCursor);
		this.removeAll();
		//remove(background);
		//remove(gun);

		if(scopeLock){
			scopeLockText.setText("<html> <span style=\"color:rgb(255,255,255);\">scope lock </span> <span style=\"color:rgb(0,255,0);\">on</span> <html> ");
		}
		else{
			scopeLockText.setText("<html> <span style=\"color:rgb(255,255,255);\">scope lock </span> <span style=\"color:rgb(255,0,0);\">off</span> <html> ");
		}
		add(scopeLockText);

		add(scope);
		add(Programma.gameContainer.player);
		add(black);
		add(black2);
		
		zoomBackground.removeAll();
		for( Animal ani : animals){
			//zoomBackground.remove(orc);
			ani.zoom(true);
			zoomBackground.add(ani);
			//JLabel label = orc.getImage();
			//label.setLocation(500, 400);
			//label.setOpaque(false);
			//zoomBackground.add(label);
			//zoomBackground.repaint();
			repaint();
		}
		zoomBackground.add(zoomBackgroundImage);
		zoomBackground.repaint();
		
		
		if(isShooting){
			shooting.stop();
			isShooting = false;
			SoundMixer.stopSound();
		}
		/*
		int x;
		int y;
		int f = (Programma.paneWidth - Programma.paneHeight)/4;
		//movement(e);
		/*if(e.getX() < Programma.paneHeight/4 && false){
			x = f;
		}
		else if(e.getX() > Programma.paneWidth -  Programma.paneHeight/4 && false){
			x = -Programma.paneWidth - f;
		}
		else{
			
			//x = -e.getX()/2;
		}
		
		if(e.getY() < Programma.paneHeight/4 && false){
			y = 0;
		}
		else if(e.getY() > 3*Programma.paneHeight/4 && false){
			y = -Programma.paneHeight;
		}
		else{
			
			//y = -e.getY()/2;
		} */
		
		//y = -2*e.getY();
		//x = -2*e.getX();
		
		//this.removeMouseMotionListener(this);
		//x = -e.getX()* + f;
		//y = -e.getY();
		//Programma.setCursorLocation(Programma.paneWidth/2, Programma.paneHeight/2);
		//
		//zoomBackground.setLocation(x, y);
		
		//this.addMouseMotionListener(this);
		
		//Point l = zoomBackground.getLocation();
		//System.out.println("\r Mouseclick point: (" + e.getX() +", " + e.getY() + ") \r Background point: (" + l.x + ", " + l.y + ")" );
		//System.out.println(zoomBackground.getWidth() + "\t" + zoomBackground.getHeight());
		//remove(background);
		//Programma.setCursorLocation(Programma.paneWidth/2,Programma.paneHeight/2);
		//zoomBackground.setLocation(Programma.paneWidth/-2, Programma.paneHeight/-2);
		/*
		if(Math.abs(x - Programma.paneWidth/2 ) > Programma.paneWidth/4 && Math.abs(y - Programma.paneHeight/2 ) > Programma.paneHeight/4 ){
			System.out.println("corner");
		/}
		else if(Math.abs(x - Programma.paneWidth/2 ) > Programma.paneWidth/4 ){
			if(x - Programma.paneWidth/2 > Programma.paneWidth/4){
				System.out.println("right");
				Programma.setCursorLocation(Programma.paneWidth*(x - Programma.paneWidth/2)*2/(Programma.paneWidth*2 - scope.getWidth()) + Programma.paneWidth/2, (y - Programma.paneHeight/2)*2 - Programma.paneWidth*2 + scope.getWidth());
			}
			else{
				System.out.println("left");
				Programma.setCursorLocation(Programma.paneWidth*(x - Programma.paneWidth/2)*2/(Programma.paneWidth*2 - scope.getWidth()) + Programma.paneWidth/2, (y - Programma.paneHeight/2)*2 + (Programma.paneWidth - scope.getWidth())/2);
			}
			
		}
		else if( Math.abs(y - Programma.paneHeight/2 ) > Programma.paneHeight/4 ){
			if(y - Programma.paneHeight/2 > Programma.paneHeight/4){
				//down
				zoomBackground.setLocation(Programma.paneWidth/-2, Programma.paneHeight/-2);
				Programma.setCursorLocation(Programma.paneWidth*(x - Programma.paneWidth/2)*2/(Programma.paneWidth*2 - scope.getWidth()) + Programma.paneWidth/2, (y - Programma.paneHeight/2)*2 + Programma.paneHeight/2);

				System.out.println("down");
			}
			else{
				System.out.println("up");
			}
			
			//System.out.println("up or down");
			
		}
		else{
			//zoomBackground.setLocation(x*-1, y*-10/7);
			//zoomBackground.setLocation( -(Programma.paneWidth*2 - scope.getWidth())*e.getX()/Programma.paneWidth + (Programma.paneWidth - scope.getWidth())/2, -1*e.getY());
			Programma.setCursorLocation(Programma.paneWidth*(x - Programma.paneWidth/2)*2/(Programma.paneWidth*2 - scope.getWidth()) + Programma.paneWidth/2, (y - Programma.paneHeight/2)*2 + Programma.paneHeight/2);
		}
		//zoomBackground.setLocation(e.getX()*-1, e.getY()*-10/7);
		//System.out.println((x - Programma.paneWidth/2)*2 + Programma.paneWidth/2);
		//System.out.println((y - Programma.paneHeight/2)/2 + Programma.paneHeight/2);
		
		*/
		//Programma.hideCursor();
		//Programma.setCursorLocation(Programma.paneWidth*(x - Programma.paneWidth/2)*2/(Programma.paneWidth*2 - scope.getWidth()) + Programma.paneWidth/2, (y - Programma.paneHeight/2)*2 + Programma.paneHeight/2);
		
		
		//Programma.setCursorLocation(x, y);
		add(zoomBackground);
		this.repaint();
		isInScope = true;
		movement(e);
	}
	
	public void unscope(MouseEvent e){
		//int x = e.getX(); //System.out.println(x);
		//int y = e.getY(); //System.out.println(y);
		
		this.setCursor(cursor);
		isInScope = false;
		//this.remove(scope);
		//remove(black);
		//remove(black2);
		//remove(zoomBackground);
		
		//Programma.setCursorLocation(65*Programma.paneWidth*(x - Programma.paneWidth/2)/(Programma.paneWidth*2 - scope.getWidth())/60 + Programma.paneWidth/2, (y - Programma.paneHeight/2)/2 + Programma.paneHeight/2);
		
		//Programma.setCursorLocation(-1*zoomBackground.getLocation().x, -1*zoomBackground.getLocation().y);
		
		add(gun);
		
		background.removeAll();
		for( Animal ani : animals){
			ani.zoom(false);
			background.add(ani);
			//background.repaint();
		}
		background.add(backgroundImage);
		background.repaint();
		if(hasPlayerShotEverything()){
			continueButton.setRightLayout();
			add(continueButton);		
		}
		renew();
		/*
		
		add(background);
		repaint();
		
		//renew();
		//Programma.gunCursor();*/
	}
	
	public void renew(){
		removeAll();
		//this.repaint();
		add(Programma.gameContainer.player);
		if(isInScope){
			zoomBackground.removeAll();

			if(scopeLock){
				scopeLockText.setText("<html> <span style=\"color:rgb(255,255,255);\">scope lock </span> <span style=\"color:rgb(0,255,0);\">on</span> <html> ");
			}
			else{
				scopeLockText.setText("<html> <span style=\"color:rgb(255,255,255);\">scope lock </span> <span style=\"color:rgb(255,0,0);\">off</span> <html> ");
			}
			add(scopeLockText);
			add(scope);
			add(black);
			add(black2);
			
			for( Animal ani : animals){
				if(!ani.isDead){
					zoomBackground.add(ani);
				}
			}
			zoomBackground.add(zoomBackgroundImage);
			zoomBackground.repaint();
			add(zoomBackground);
		}
		else{
			background.removeAll();
			if(hasPlayerShotEverything()){
				continueButton.setRightLayout();
				add(continueButton);		
			}
			if(Programma.gameContainer.player.hasGun){
				add(gun);
				for(int i = Programma.gameContainer.player.ammo - 1; i >= 0; i--){
					this.add(bullets.get(i));
				}
				if(isShooting){
					add(flash);
				}
			}
			
			
			/*for(int i = 0; i < Programma.gameContainer.player.ammo; i++){
				this.add(Programma.gameContainer.player.bullets.get(i));
			}
			EasyImage bullet = new EasyImage("Images/bullet.png",-1, Programma.paneHeight/54);
			bullet.setLocation(200,200);
			this.add(bullet); */
			//bullets.get(5).setLocation(200,200);
			//this.add(bullets.get(5));
			//this.add(bullets.get(4));
			
			
			for( Animal ani : animals){
				if(!ani.isDead){
					background.add(ani);
				}
			}
			
			
			background.add(backgroundImage);
			background.repaint();
			add(background);
			
		}
		this.repaint();
	}
	public void addAnimal(Animal animal, int x, int y){
		animal.setLocation(x,y);
		//animal.repaint();
		//System.out.println("x: " + x + "  y: " + y);
		
		//System.out.println("Result: x: " + animal.getLocation().x + "  y: " + animal.getLocation().y);
		
		animals.add(animal);
		//animals.get(animals.size() - 1).setLocation(500, y);
		//System.out.println("animal x: " + animals.get(animals.size() - 1).getLocation().x);
		
		
		
		if(isInScope){
			animals.get(animals.size()-1).zoom(true);
			zoomBackground.removeAll();
			for(Animal ani : animals){
				zoomBackground.add(ani);
				zoomBackground.repaint();
			}
			zoomBackground.add(zoomBackgroundImage);
		}
		else{
			background.removeAll();
			for( Animal ani : animals){
				//orc.zoom(true);
				background.add(ani);
				background.repaint();
				//orc.zoom(true);
			//	zoomBackground.add(orc);
			//	background.repaint();
			}
			background.add(backgroundImage);
		}	
		this.repaint();
	}
	
	public void addOrc(int height, int type, int x, int y, int...amount){
		addAnimal(new Orc(height, type), x, y);
	}

	public void addKangaroo(int height, int x, int y, int...amount){
		addAnimal(new Kangaroo(height, y), x, y);
	}

	public void addBear(int height, int x, int y){
		addAnimal(new Bear(height), x, y);
		SoundMixer.playSound("Sound/bear_roar.wav");
		//bear = new Bear(height);
		//bear.setLocation(x,y);
		//renew();
	}
	
	public void addDeer(int height, int x, int y){
		addAnimal(new Deer(height), x, y);
		//deer.setLocation(x,y);
		//addAnimal(deer, x,y );
		//this.add(deer);
		//renew();
	}
	
	public void snipe(){
		//this.removeMouseMotionListener(this);
		if(!snipe.isRunning()){
			snipe.restart();
			isReloadingSnipe = true;
			SoundMixer.playSound("Sound/sniper.wav", 80);
			Point hitPoint = new Point(Programma.paneWidth/2, Programma.paneHeight/2);
			Rectangle rec = new Rectangle();
			
			for(Animal ani : animals){
				ani.moveWhenHearShots();
			}
			
			for( int i = 0; i < animals.size(); i++){
				if(animals.get(i).isDead){
					animals.remove(i);
					i--;
					continue;
				}
				
				rec.setBounds(animals.get(i).getLocationOnScreen().x, animals.get(i).getLocationOnScreen().y, animals.get(i).getWidth(), animals.get(i).getHeight()); 
				if(rec.contains(hitPoint)){
					if(!animals.get(i).headshot(hitPoint)){
						animals.get(i).decreaseHealth(2000);
					}
					if(animals.get(i).isDead){
						animals.remove(i);
						renew();
					}
					break;
				}
			}
		}
		
		

		
		
		/*for( Orc orc : orcs){
			
			//background.repaint();
		}
		
		/*try {
			Robot robot = new Robot();
			robot.mouseMove(Programma.paneWidth/2, Programma.paneHeight/2);
			robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
			robot.mouseMove(x, y);
			System.out.println("I shot you!");
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addMouseMotionListener(this); */
	}
	public void spawn(int animal,int rate, int initialDelay, int Amount, int Height, int X1, int X2, int Y){ 
		spawners.add(new Spawner(this, animal, rate, initialDelay, Amount, Height, X1, X2, Y));
	}

	public void start(){
		this.setCursor(cursor);
		for(Animal ani : animals){
			ani.start();
		}
		fps.start();
	}

	public void stop(){
		for( Spawner spawner : spawners){
			spawner.stop();
		}
		for(Animal ani : animals){
			if(ani instanceof AttackingAnimal){
				((AttackingAnimal) ani).stop();
			}
		}

		isInScope = false;
		stopShooting();
		fps.stop();
	}


	public static void stopTimers(){
		if(snipe.isRunning()){
			snipe.stop();
		}
		if(reload.isRunning()){
			reload.stop();
		}

	}

	public static void lockScope(){
		scopeLock = !scopeLock;
	}

	public void pause(){
		for( Spawner spawner : spawners){
			spawner.pause();
		}
		if(isShooting){
			stopShooting();
		}
		fps.stop();
	}
	public void resume(){
		for( Spawner spawner : spawners){
			spawner.resume();
		}
		fps.start();
	}
	/*public void paint(Graphics z){
		//g = (Graphics2D)this.getGraphics();
		
		
		int strokeWidth = 1;
		g = (Graphics2D)z;
		//g.setStroke(new BasicStroke(strokeWidth));
		
		//g.setColor(Color.WHITE);
		for(int i = 0; i*strokeWidth < (int)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/2; i++){
			strokeWidth = 1*(i + 1);
			g.setStroke(new BasicStroke(2*strokeWidth));
			g.setColor(colors.get((i + j) % 6));
			g.drawOval(x/2 - i*strokeWidth + strokeWidth*(k % 6)/2, y/2 - i*strokeWidth + strokeWidth*(k % 6)/2, 2*i*strokeWidth - strokeWidth*(k % 6), 2*i*strokeWidth - strokeWidth*(k % 6));
		}
		k++;
		//j++;
		if(k % 6 == 0){
			j++;
		}
	}	*/
	
	public void stopShooting(){
		shooting.stop();
		shooting.setInitialDelay(0);
		isShooting = false;
		if(!reload.isRunning()){
			SoundMixer.stopSound();
		}
		remove(flash);
		repaint();
		renew();
	}
	public void startShooting(){
		if(reload.isRunning()){
			shooting.setInitialDelay((int)SoundMixer.getSoundMicrosToGo()/1000);
			//isReloading = true;
			isShooting = false;
		}
		else{
			isShooting = true;
			for(Animal ani : animals){
				ani.moveWhenHearShots();
			}
			SoundMixer.playSoundContinuously("Sound/Riffle_sound.wav", 50);
			flash.setLocation(gun.getLocation().x - 2*gun.getWidth()/40, gun.getLocation().y + gun.getHeight()/15);			
			renew();
		}
		shooting.restart();
		
	}
	
	public void stopSpawning(){
		for( Spawner spawner : spawners){
			spawner.stop();
		}
		for(Animal ani : animals){
			if(ani instanceof AttackingAnimal){
				((AttackingAnimal) ani).stop();
			}
		}
	}
	
	public void reload(){
		Programma.gameContainer.player.ammo = Programma.gameContainer.player.MAX_AMMO;
		if(isShooting){
			shooting.setInitialDelay(2956);
			shooting.restart();
		}
		isShooting = false;
		renew();
		//isReloading = true;
		reload.restart();
		SoundMixer.playSound("Sound/reload.wav");
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!Programma.gameContainer.player.hasGun){
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			if(isInScope){
				scopeLockTemp = false;
			}
			else{	
				scope(e);
				scopeLockTemp = true;
			}
			//e.consume();
		}
		else if (e.getButton() == MouseEvent.BUTTON1){
			//System.out.println("left mouse button");
			if(isInScope){
				if(!snipe.isRunning()){
					snipe();
				}
			}
			else{
				startShooting();
			}
		}
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!Programma.gameContainer.player.hasGun){
		}
		else if(e.getButton() == MouseEvent.BUTTON3 && (!scopeLock || !scopeLockTemp)){
			unscope(e);
		}
		else if (e.getButton() == MouseEvent.BUTTON1){
			//System.out.println("left mouse button");
			if(!isInScope){
				stopShooting();
			}
		}
		scopeLockTemp = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		movement(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		movement(e);
		
	}

	public void movement(MouseEvent e){
		if(isInScope){
			//int q = ((Programma.paneWidth - scope.getWidth())/2) + (Programma.paneWidth)/(Programma.paneWidth);
			//Programma.paneWidth/Programma.paneHeight
			//double d = -(Programma.paneWidth/Programma.paneHeight)*e.getX() + (Programma.paneWidth - scope.getWidth())/2;
			//double d = (Programma.paneWidth + (Programma.paneWidth - scope.getWidth())/2 + scope.getWidth())/Programma.paneWidth;
			//zoomBackground.setLocation(-(Programma.paneWidth*2 - scope.getWidth())*e.getX()/Programma.paneWidth + (Programma.paneWidth - scope.getWidth())/2, -1*e.getY());
			/*int x;
			
			
			
			
			//x = -e.getX()* + f;
			//y = -e.getY();
			
			zoomBackground.setLocation(x, y);
			deltaX = e.getX() - oldX;
			deltaY = e.getY() - oldY;
			zoomBackground.setLocation(zoomBackground.getLocation().x - 10*deltaX/7, zoomBackground.getLocation().y - deltaY);
			
			oldX = e.getX();
			oldY = e.getY(); */
			
			//int dx = e.getX() - Programma.paneWidth/2;
			//int dy = e.getY() - Programma.paneHeight/2;
			//Programma.setCursorLocation(Programma.paneWidth/2, Programma.paneHeight/2);
			int x;
			int y;
			int f = (Programma.paneWidth - Programma.paneHeight)/2;
			
			if(e.getX() < Programma.paneHeight/4){
				x = f;
			}
			else if(e.getX() > Programma.paneWidth - Programma.paneHeight/4){
				x = -Programma.paneWidth - f;
			}
			else{
				//x = -2*e.getX()*(Programma.paneWidth + f)/Programma.paneWidth + f/4 + Programma.paneWidth/2;\
				x = Programma.paneWidth/2 - 2*e.getX();
			}
			
			if(e.getY() < Programma.paneHeight/4){
				//y = 0;
				y = 0;
			}
			else if(e.getY() > 3*Programma.paneHeight/4 ){
				//y = -Programma.paneHeight;
				y = -Programma.paneHeight;
			}
			else{
				//y = -2*e.getY() + Programma.paneHeight/2;
				//y = e.getY();
				y = Programma.paneHeight/2 -2*e.getY();
			}
			//Programma.setCursorLocation(x, y);
			
			zoomBackground.setLocation(x, y);
		}
		else{
			if(isShooting){
				gun.setLocation(2*Programma.paneWidth/3 - (Programma.paneWidth - e.getX())/10, 7*Programma.paneHeight/9 - (Programma.paneHeight - e.getY())/10 + moveGun);
				flash.setLocation(gun.getLocation().x - 2*gun.getWidth()/40, gun.getLocation().y + gun.getHeight()/15);			}
			else{
				gun.setLocation(2*Programma.paneWidth/3 - (Programma.paneWidth - e.getX())/10, 7*Programma.paneHeight/9 - (Programma.paneHeight - e.getY())/10);
			}
			 
		}
		//zoomBackground.setLocation(zoomBackground.getLocation().x - 10*deltaX/7, zoomBackground.getLocation().y - deltaY);
		
		//System.out.println("mouse moved " + deltaX + " horizontal pixels and " + deltaY + " vertical pixels" );
	}
	public void clearAnimals(){
		for(int i = 0; i < animals.size(); i++){
			if(animals.get(i).isDead){
				animals.remove(i);
			}
			animals.get(i).stop();
		}
		animals.clear();
		System.gc();
	}
	
	public boolean hasPlayerShotEverything(){
		for(Spawner spawner : spawners){
			if(spawner.isSpawning){
				return false;
			}
		}
		if(!animals.isEmpty()){
			return false;
		}
		else{
			return true;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if(isReloading){
			//return;
		//}
		
		/*if(SoundMixer.getBackGroundSound() != "Sound/Riffle_sound.wav"){
			SoundMixer.playSoundContinuously("Sound/Riffle_sound.wav", 40);
		}*/
		
		for( Animal ani : animals){
			if(ani.getMousePosition() != null){

				ani.decreaseHealth(200);
				ani.moveWhenHit();
				//ani.setLocation(ani.getLocation().x + (int)(50*Math.random()) - 25, ani.getLocation().y);
				//if(orc.isDead){
					//renew();
				//}
			}
			
		}
		/*if(bear != null){
			if(!bear.isDead && bear.getMousePosition() != null){
				bear.decreaseHealth(200);
			}
		}
		if(deer != null){
			if(!deer.isDead && deer.getMousePosition() != null){
				deer.decreaseHealth(200);
			}
		}*/
		for( int i = 0; i < animals.size(); i++){
			if(animals.get(i).isDead){
				animals.remove(i);
				renew();
				//System.out.println("damage done");
			}
		}
		moveGun = -1*moveGun;
		gun.setLocation(gun.getLocation().x, gun.getLocation().y + moveGun);
		flash.setLocation(gun.getLocation().x - 2*gun.getWidth()/40, gun.getLocation().y + gun.getHeight()/15);
		
		if(!isShooting){
			isShooting = true;
			for(Animal ani : animals){
				ani.moveWhenHearShots();
			}
			SoundMixer.playSoundContinuously("Sound/Riffle_sound.wav", 50);
			flash.setLocation(gun.getLocation().x - 2*gun.getWidth()/40, gun.getLocation().y + gun.getHeight()/15);			
			renew();
		}
		
		Programma.gameContainer.player.ammo--;
		
		if(Programma.gameContainer.player.ammo == 0){
			reload();
		}
		else{
			this.remove(bullets.get(Programma.gameContainer.player.ammo));
			this.repaint();
		}
		
		
	}
	

	
	
	

}
