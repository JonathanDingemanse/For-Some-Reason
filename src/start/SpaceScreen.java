package start;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import components.ColorButton;
import components.EasyImage;
import components.SpaceButton;
import sound.SoundMixer;
import timers.Meteor;

public class SpaceScreen extends JPanel implements ActionListener, DocumentListener {
	JPanel dashboard = new JPanel();
	JPanel dashboardEdge = new JPanel();
	JPanel monitor = new JPanel();
	JPanel monitorScreen = new JPanel();
	static Border monitorEdge = BorderFactory.createLineBorder(Color.GRAY, 8);
	EasyImage monitorPicture;
	EasyImage backgroundImage;
	EasyImage coffee;
	public String action = "start";
	int itt = 0;
	public Timer timer;
	SpaceButton launch;
	boolean hasLaunched = false;
	int vibr = 1;
	
	double height = 0.0;
	double velocity = 0.0;
	String status = "ready to launch";
	JLabel heightText = new JLabel("height: " + height + " km", JLabel.LEFT);
	JLabel velocityText = new JLabel("velocity: " + velocity + " m/s", JLabel.LEFT);
	JLabel statusText = new JLabel("status: " + status, JLabel.LEFT);
	JLabel dotsText  = new JLabel(".........................................................................", JLabel.LEFT);
	JLabel caret = new JLabel(">", JLabel.LEFT);
	JTextField textField = new JTextField();
	JPanel writeLine = new JPanel();
	JPanel DOS = new JPanel();
	ArrayList<JLabel> outputList = new ArrayList<JLabel>();
	Image powerOffIcon;
	
	ColorButton red = new ColorButton("coffee", Color.RED, Programma.paneWidth/16, this);
	ColorButton yellow = new ColorButton("pacman", Color.YELLOW, Programma.paneWidth/16, this);
	ColorButton green = new ColorButton("door", Color.GREEN, Programma.paneWidth/16, this);
	ColorButton cyan = new ColorButton("destruct", Color.CYAN, Programma.paneWidth/16, this);
	ColorButton blue = new ColorButton("autopilot", Color.BLUE, Programma.paneWidth/16, this);
	ColorButton magenta = new ColorButton("mayday", Color.MAGENTA, Programma.paneWidth/16, this);
	
	Meteor meteor;
	
	ColorButton power = new ColorButton("power", Color.DARK_GRAY.brighter(), 16, this);
	boolean isPowerOff = true;
	boolean isAskingDoor = false;
	boolean isDoorOpen = false;
	boolean isAutoPilotActivated = false;
	boolean isAskingDestination = false;
	String destination;
	
	public SpaceScreen(){	
		this.setSize(Programma.fullscreen);
		this.setLocation(0, 0);
		this.setLayout(null);
		dashboard.setLocation(0, Programma.paneHeight/2);
		dashboard.setSize(Programma.paneWidth, Programma.paneHeight/2);
		dashboard.setOpaque(true);
		dashboard.setBackground(Color.DARK_GRAY);
		dashboard.setLayout(null);
		
		dashboardEdge.setSize(Programma.paneWidth, Programma.paneHeight/54);
		dashboardEdge.setLocation(0, Programma.paneHeight/2 - Programma.paneHeight/54);
		dashboardEdge.setOpaque(true);
		dashboardEdge.setBackground(Color.GRAY);
		dashboardEdge.setLayout(null);
		
		
		monitor.setSize(3*Programma.paneWidth/8, 3*Programma.paneHeight/8);
		monitor.setLocation(5*Programma.paneWidth/16, 9*Programma.paneHeight/16);
		monitor.setBorder(monitorEdge);
		monitor.setOpaque(true);
		monitor.setBackground(Color.BLACK);
		monitor.setLayout(null);
		
		launch = new SpaceButton("Launch", "launch", this);
		launch.setSize(2*Programma.paneWidth/16, 2*Programma.paneHeight/16 + Programma.paneWidth/16);
		launch.setLocation(5*Programma.paneWidth/32, 11*Programma.paneHeight/16);
		launch.setForeground(Color.WHITE);
		launch.setFont(Programma.helvetica32);
		
		coffee = new EasyImage("Images/java_logo.png", Programma.paneWidth/16, Programma.paneWidth/16);
		coffee.setLocation(5*Programma.paneWidth/32, 9*Programma.paneHeight/16);
		
		monitorScreen.setSize(monitor.getWidth() - 16, monitor.getHeight() -16);
		monitorScreen.setLocation(8, 8);
		monitorScreen.setLayout(null);
		
		heightText.setSize(monitorScreen.getWidth()/2 - 16, monitorScreen.getHeight()/8);
		heightText.setLocation(8, 8);
		heightText.setFont(Programma.fixedsys);
		heightText.setForeground(Color.GREEN.darker());
		
		velocityText.setSize(monitorScreen.getWidth()/2 - 16, monitorScreen.getHeight()/8);
		velocityText.setLocation(8 + monitorScreen.getWidth()/2, 8);
		velocityText.setFont(Programma.fixedsys);
		velocityText.setForeground(Color.GREEN.darker());
		
		statusText.setSize(monitorScreen.getWidth() - 16, monitorScreen.getHeight()/8);
		statusText.setLocation(8, monitorScreen.getHeight()/8 + 16);
		statusText.setFont(Programma.fixedsys);
		statusText.setForeground(Color.GREEN.darker());
		
		dotsText.setSize(monitorScreen.getWidth() - 16, monitorScreen.getHeight()/8);
		dotsText.setLocation(8, monitorScreen.getHeight()/4);
		dotsText.setFont(Programma.fixedsys);
		dotsText.setForeground(Color.GREEN.darker());
		
		caret.setSize(monitorScreen.getWidth()/32, monitorScreen.getHeight()/8);
		caret.setLocation(0, 0);
		caret.setFont(Programma.fixedsys);
		caret.setForeground(Color.GREEN.darker());
		
		textField.setSize(monitorScreen.getWidth() - caret.getWidth() - 16, monitorScreen.getHeight()/8);
		textField.setLocation(	caret.getWidth(), 0);
		textField.setFont(Programma.fixedsys);
		textField.setForeground(Color.GREEN.darker());
		textField.setCaretColor(Color.GREEN.darker());
		textField.setBackground(Color.BLACK);
		textField.setBorder(null);
		//textField.getDocument().addDocumentListener(this);
		
		writeLine.setSize(monitorScreen.getWidth() - 16, monitorScreen.getHeight()/8);
		writeLine.setLocation(8, 3*monitorScreen.getHeight()/8);
		writeLine.setLayout(null);
		writeLine.setOpaque(false);
		writeLine.add(caret);
		writeLine.add(textField);
		writeLine.repaint();
		
		DOS.setSize(monitorScreen.getWidth() - 16, monitorScreen.getHeight()/2);
		DOS.setLocation(8, 3*monitorScreen.getHeight()/8);
		DOS.setLayout(null);
		DOS.setOpaque(false);
		
		monitorScreen.add(heightText);
		monitorScreen.add(velocityText);
		monitorScreen.add(statusText);
		monitorScreen.add(dotsText);
		//monitorScreen.add(textField);
		//monitorScreen.add(caret);
		monitorScreen.add(writeLine);
		monitorScreen.add(DOS);
		monitorScreen.setBackground(Color.BLACK);
		monitorScreen.repaint();
		
		power.setSize(2*Programma.paneWidth/16, 2*Programma.paneHeight/16 + Programma.paneWidth/16);
		power.setLocation(23*Programma.paneWidth/32, 11*Programma.paneHeight/16);
		Image im;
		try {
			im = ImageIO.read(new File("Images/power-button.png"));
			powerOffIcon = im.getScaledInstance(power.getWidth()-16, power.getHeight()-16, Image.SCALE_SMOOTH);
			power.setIcon(new ImageIcon(powerOffIcon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		red.setLocation(Programma.paneWidth/16, 9*Programma.paneHeight/16);
		green.setLocation(Programma.paneWidth/16, 11*Programma.paneHeight/16);
		blue.setLocation(Programma.paneWidth/16, 13*Programma.paneHeight/16);
		
		yellow.setLocation(14*Programma.paneWidth/16, 9*Programma.paneHeight/16);
		cyan.setLocation(14*Programma.paneWidth/16, 11*Programma.paneHeight/16);
		magenta.setLocation(14*Programma.paneWidth/16, 13*Programma.paneHeight/16);


		

		setBackgroundPicture("Images/australian_landscape_rocket.jpg");
	}

	public void reset(){
		isPowerOff = true;
		DOS.removeAll();
		DOS.repaint();
		outputList.clear();
		writeLine.setLocation(8, 3*monitorScreen.getHeight()/8);
		monitor.removeAll();
		monitor.repaint();
		power.setIcon(new ImageIcon(powerOffIcon));
		power.repaint();

		isAskingDoor = false;
		isDoorOpen = false;
		isAutoPilotActivated = false;
		isAskingDestination = false;
		destination = null;
		hasLaunched = false;



		if(timer != null){
			if(timer.isRunning()){
				timer.stop();
			}
		}

		vibr = 1;
		height = 0.0;
		velocity = 0.0;

		if(meteor != null){
			meteor.stop();
		}
		meteor = new Meteor();
		this.removeAll();
		this.add(power);
		this.add(blue);
		this.add(red);
		this.add(green);
		this.add(yellow);
		this.add(magenta);
		this.add(cyan);

		this.add(launch);
		this.add(monitor);
		this.add(dashboardEdge);
		this.add(dashboard);
		this.repaint();
		setBackgroundPicture("Images/australian_landscape_rocket.jpg");


	}
	
	public void repaintMonitorScreen(){
		heightText.setText("height: " + (int)height + " km");
		heightText.repaint();
		velocityText.setText("velocity: " + (int)velocity + " m/s");
		velocityText.repaint();
		statusText.setText("status: " + status);
		statusText.repaint();
		monitorScreen.repaint();
	}
	
	public void power(){
		Image im;
		if(isPowerOff){
			try {
				im = ImageIO.read(new File("Images/power-button_on.png"));
				Image icon = im.getScaledInstance(power.getWidth()-16, power.getHeight()-16, Image.SCALE_SMOOTH);
				power.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			action = "power";
			timer = new Timer(500, this);
			timer.setInitialDelay(300);
			timer.start();
			itt = 0;
			isPowerOff = false;
			SoundMixer.playSound("Sound/boot-sound.wav");
		}
		else{
			try {
				im = ImageIO.read(new File("Images/power-button.png"));
				Image icon = im.getScaledInstance(power.getWidth()-16, power.getHeight()-16, Image.SCALE_SMOOTH);
				power.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shutdown();
		}
	}
	
	public void autoPilot(){
		
		if(!isAutoPilotActivated){
			output("Auto-pilot has been activated");
			if(meteor != null){
				meteor.stop();
			}
			if(status == "in orbit"){
				output("  Auto-pilot dodged the meteor");
				SoundMixer.stopBackground();
				SoundMixer.playBackground("Music/Sky High.wav", 60);
			}
			SoundMixer.playSound("Sound/auto-pilot.wav");
		}
		if(destination == null){
			output("Where do you want to go?");
			output("  (mars/moon/new-york)");
			isAskingDestination = true;
		}
		else{
			output("your destination is: " + destination);
		}
		isAutoPilotActivated = true;
	}
	
	public void launch(){
		
		if(!hasLaunched){
			output("Spacecraft will launch in 10 seconds");
			SoundMixer.playSound("Sound/liftoff.wav");
			action = "launch";
			status = "launching...";
			repaintMonitorScreen();
			timer = new Timer(70, this);
			timer.setInitialDelay(10000);
			timer.start();
			hasLaunched = true;
		}
		else{
			output("Spacecraft cannot launch anymore");
		}
	}
	public void selfDestruct(){
		output("Spacecraft will self-destruct");
		SoundMixer.playSound("Sound/self-destruct.wav");
		action = "destruct";
		timer = new Timer(70, this);
		timer.setInitialDelay(5270);
		timer.start();
		
	}
	
	public void door(){
		isAskingDoor = true;
		if(isDoorOpen){
			output("The door is currently opened");
			output("Do you want to close the door? (Y/N)");
			//SoundMixer.playSound("Sound/open_door.wav");
		}
		else{
			output("The door is currently closed");
			output("Do you want to open the door? (Y/N)");
			SoundMixer.playSound("Sound/open_door.wav");
		}
		
	}
	
	public void dontOpen(){
		isAskingDoor = false;
		if(isDoorOpen){
			output("Door has not been closed");
		}
		else{
			output("Door has not been opened");
		}
	}
	
	public void pacman(){
		if(isPowerOff){
			return;
		}
		output("Running pacman...");
		action = "pacman";
		timer = new Timer(1000, this);
		timer.setInitialDelay(800);
		timer.start();
	}
	
	public void shutdown(){
		isPowerOff = true;
		SoundMixer.playSound("Sound/shutdown_sound.wav");
		DOS.removeAll();
		DOS.repaint();
		outputList.clear();
		writeLine.setLocation(8, 3*monitorScreen.getHeight()/8);
		monitor.removeAll();
		monitor.repaint();
		power.setIcon(new ImageIcon(powerOffIcon));
		power.repaint();
	}
	
	public void coffee(){
		output("Your coffee is coming");
		SoundMixer.playSound("Sound/brewing_coffee.wav");
		action = "coffee";
		timer = new Timer(70, this);
		timer.setInitialDelay(18000);
		timer.start();
	}
	
	public void mayday(){
		SoundMixer.playSound("Sound/mayday.wav");
		output("A mayday has been send to earth");
		output("  Maybe someone will help you");
	}
	
	public void setMonitorPicture(String picture){
		monitor.removeAll();
		monitorPicture = new EasyImage(picture, monitor.getWidth()-16, monitor.getHeight()-16);
		monitorPicture.setLocation(8, 8);
		monitor.add(monitorPicture);
		monitor.repaint();
		this.repaint();
	}
	
	public void setBackgroundPicture(String picture){
		try{
			this.remove(backgroundImage);
		}catch(Exception e){}
		
		backgroundImage = new EasyImage(picture, Programma.paneWidth, 2*Programma.paneHeight/3);
		backgroundImage.setLocation(0, Programma.paneHeight/-8);
		this.add(backgroundImage);
		this.repaint();
	}
	
	public void enter(){
		String rawText = textField.getText();
		if(rawText.length() == 0){
			return;
		}
		String com = rawText.toLowerCase();
		String output;
		textField.setText("");
		textField.repaint();
		System.out.println("com: " + com);
		
		if(isTimerRunning() && com != "help"){
			output("Command: \"" + rawText + "\" cannot be executed");
			output("Wait until previous task has ended");
		}
		else if(com.contains("y") && isAskingDoor ){
			isAskingDoor = false;
			if(isDoorOpen){
				isDoorOpen = false;
				output("Door has been closed");
			}
			else{
				if(status == "in orbit"){
					Programma.createAndShowGUI("*Maybe you shouldn't open the door in space");
					meteor.stop();				}
				else{
					isDoorOpen = true;
					output("Door has been opened");
				}	
			}
			
		}
		else if(com.contains("n") && isAskingDoor){
			dontOpen();
		}
		else if(isAskingDoor){
			output = "Unknown command: \"" + rawText +"\"";
			output(output);
			isAskingDoor = false;
		}
		else if(com.contains("mars") && isAskingDestination){
			if(hasLaunched){
				status = "heading for mars...";
				repaintMonitorScreen();
				timer.setInitialDelay(4000);
				action = "mars";
				timer.restart();
			}
			output("Your destination is mars");
			isAskingDestination = false;
			destination = "mars";
		}
		else if(com.contains("moon") && isAskingDestination){
			if(hasLaunched){
				status = "heading for the moon...";
				repaintMonitorScreen();
				timer.setInitialDelay(4000);
				action = "moon";
				timer.restart();
			}
			status = "heading for the moon...";
			output("Your destination is the moon");
			isAskingDestination = false;
			destination = "moon";
		}
		else if(com.contains("new") && com.contains("york") && isAskingDestination){
			if(hasLaunched){
				status = "heading for New-York...";
				repaintMonitorScreen();
				timer.setInitialDelay(4000);
				action = "new-york";
				timer.restart();
			}
			output("Your destination is New-York");
			isAskingDestination = false;
			destination = "new-york";
		}
		else if(isAskingDestination){
			output = "Unknown destination: \"" + rawText +"\"";
			output(output);
			isAskingDestination = false;
		}
		else if(com.contains("launch")){
			launch();
		}
		else if(com.contains("coffee")){
			coffee();
		}
		else if(com.contains("pacman")){
			pacman();
		}
		else if(com.contains("destruct") && com.contains("self")){
			selfDestruct();
		}
		else if(com.contains("auto") && com.contains("pilot")){
			autoPilot();
		}
		else if(com.contains("mayday")){
			mayday();
		}
		else if(com.contains("door")){
			door();
		}
		else if(com.contains("shutdown")){
			shutdown();
		}
		else if(com.contains("help")){
			output("Commands:");
			output("launch / self-destruct / door");
			output("coffee / pacman / mayday");
			output("auto-pilot / shutdown");
		}
		else{
			output = "Unknown command: \"" + rawText +"\"";
			output(output);
			isAskingDoor = false;
		}
		
	}
	
	public void output(String output){
		if(isPowerOff){
			return;
		}
		DOS.removeAll();
		JLabel jl = new JLabel(output, JLabel.LEFT);
		jl.setSize(monitorScreen.getWidth() - 16, monitorScreen.getHeight()/8);
		jl.setFont(Programma.fixedsys);
		jl.setForeground(Color.GREEN.darker());
		jl.setLayout(null);
		
		while(outputList.size() > 3){
			outputList.remove(0);
		}
		outputList.add(jl);
		for(int i = 0; i < outputList.size(); i++){
			outputList.get(i).setLocation(8, i*monitorScreen.getHeight()/8);
			outputList.get(i).repaint();
			DOS.add(outputList.get(i));
		}
		DOS.repaint();
		writeLine.setLocation(8, (3+outputList.size())*monitorScreen.getHeight()/8);
		writeLine.repaint();
	}
	
	public void spaceButton(String command){
		if(isTimerRunning()){return;}
		isAskingDoor = false;
		isAskingDestination = false;
		
		switch(command){
		case "launch":
			launch();
			break;
		
		case "power":
			power();
			break;
		
		case "coffee":
			coffee();
			break;
			
		case "mayday":
			mayday();
			break;
			
		case "destruct":
			selfDestruct();
			break;
			
		case "autopilot":
			autoPilot();
			break;
			
		case "door":
			door();
			break;
			
		case "pacman":
			pacman();
			break;
		}
	}
	
	public boolean isTimerRunning(){
		if(timer != null){
			if(timer.isRunning()){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		switch(action){
		case "power":
			if(itt == 11){
				//setMonitorPicture("Images/grafiek.PNG");
				monitor.add(monitorScreen);
				monitor.setBackground(Color.BLACK);
				monitor.repaint();
				itt = 0;
				timer.stop();
				return;
			}
			else if(itt % 2 == 0){
				monitor.setBackground(Color.BLACK);
			}
			else{
				monitor.setBackground(Color.GREEN.darker());
				if(itt == 1){
					//timer.setDelay(timer.getDelay() - 5);
					timer.setDelay(50);
				}
			}
			break;
			
		case "launch":
			if(itt == 40){
				setBackgroundPicture("Images/earth.jpg");
				if(isDoorOpen){
					Programma.createAndShowGUI("*Maybe you should not open the door in outer space.");
					return;
				}
				if(destination == null){
					status = "in orbit";
				}
				else{
					switch(destination){
					case "mars": status = "heading for mars..."; break;
					case "moon": status = "heading for the moon..."; break;
					case "new-york": status = "heading for New-York..."; break;
					}
					timer.setInitialDelay(4000);
					action = destination;
					timer.restart();
				}
				repaintMonitorScreen();
				timer.setDelay(1000);
			}
			else if(itt == 41){
				
				if(isDoorOpen){
					Programma.createAndShowGUI("*Maybe you should close the door before launching");
				}
				else if(isAutoPilotActivated){
					SoundMixer.playBackground("Music/Sky High.wav", 60);
				}
				else{
					output("warning: a meteor is going to hit");
					output("  you within a minute");
					SoundMixer.playBackground("Sound/alarm.wav", 50);
					SoundMixer.playSound("Sound/meteor_minute.wav");
					meteor.start();	
				}
				itt = 0;
				timer.stop();
			}
			else{
				int m = (int) (vibr*Math.sqrt(itt+4)*Math.random());
				backgroundImage.setLocation(backgroundImage.getLocation().x, backgroundImage.getLocation().y + m);
				vibr = vibr*-1;
				velocity = velocity*1.1 + 2;
				height = height + velocity/13;
				repaintMonitorScreen();
			}
			
			break;
			
		case "coffee":
			this.remove(dashboard);
			this.remove(backgroundImage);
			this.add(coffee);
			dashboard.setLocation(0, Programma.paneHeight/2);
			dashboard.setSize(Programma.paneWidth, Programma.paneHeight/2);
			this.add(dashboard);
			this.add(backgroundImage);
			this.repaint();
			timer.stop();
			itt = 0;
			break;
			
		case "pacman":
			JLabel label = new JLabel("", JLabel.CENTER);
			Icon icon = new ImageIcon("Images/Pacman.gif");
			label.setIcon(icon);
			label.setSize(monitor.getWidth()-16, monitor.getHeight()-16);
			label.setLocation(8,8);
			monitor.removeAll();
			monitor.add(label);
			monitor.repaint();
			SoundMixer.playSoundContinuously("Sound/pacman.wav");
			timer.stop();
			itt = 0;
			break;
			
			
		case "destruct":
			Programma.createAndShowGUI("@self-destruct");
			timer.stop();
			meteor.stop();
			itt = 0;
			break;

		case "mars":
			timer.stop();
			itt = 0;
			Programma.createAndShowGUI("@mars");
			break;

		case "new-york":
			timer.stop();
			itt = 0;
			Programma.createAndShowGUI("@newYork");
			break;

		case "moon":
			timer.stop();
			itt = 0;
			Programma.createAndShowGUI("@blackHole");
			break;

		}
		itt++;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("changed: " + e.getLength());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("insert: " + e.toString());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("remove: " + e.toString());
	}

}
