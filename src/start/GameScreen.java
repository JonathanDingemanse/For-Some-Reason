package start;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import start.EasyButton;
import components.*;
import keyActions.KeyAction;
import keyActions.KeyAction3;


public class GameScreen extends JPanel  {
	int picNum;
	int queNum;
	JLabel mainText;
	JPanel horLine;
	JPanel horLine2;
	JPanel backGr;
	JLabel picture;
	ArrayList<EasyButton2> buttonList = new ArrayList<EasyButton2>();
	ArrayList<KeyAction> numberActions = new ArrayList<KeyAction>();
	ArrayList<KeyAction> numpadActions = new ArrayList<KeyAction>();
	boolean isMainTextHidden = false;

	public boolean isLoaded = false;
	
	
	//public static final KeyAction3 gameKeys = new KeyAction3(KeyAction3.GAME);
	EasyImage mainImage;
	
	
	public GameScreen(int q, int p, boolean...mainTextHidden){
		if(mainTextHidden.length > 0){
			isMainTextHidden = mainTextHidden[0];
		}
		isLoaded = true;
		picNum = p;
		queNum = q;
		this.setLayout(null);
		this.setOpaque(true);
		this.setBounds(0, 0, Programma.paneWidth, Programma.paneHeight);
		this.setBackground(Programma.currentTheme.backgroundColor);
		
		horLine = new JPanel();
		horLine.setLayout(null);
		horLine.setOpaque(true);
		horLine.setBounds(0, 3*Programma.paneHeight/24, Programma.paneWidth, 15*Programma.paneHeight/1080);
		horLine.setBackground(Programma.currentTheme.buttonBorderColor);
		
		horLine2 = new JPanel();
		horLine2.setLayout(null);
		horLine2.setOpaque(true);
		horLine2.setBounds(0, 20*Programma.paneHeight/24, Programma.paneWidth, 15*Programma.paneHeight/1080);
		horLine2.setBackground(Programma.currentTheme.buttonBorderColor);
		
		backGr = new JPanel();
		backGr.setLayout(null);
		backGr.setOpaque(true);
		backGr.setBackground(Programma.currentTheme.backgroundColor);
		
		mainText = new JLabel("lol lol lol", JLabel.CENTER);
		mainText.setOpaque(true);
		mainText.setFont(Programma.helvetica32);
		mainText.setBounds(0, 0, Programma.paneWidth, 3*Programma.paneHeight/24 - horLine.getHeight());
		mainText.setBackground(Programma.currentTheme.backgroundColor);
		
		KeyAction one = new KeyAction(KeyEvent.VK_1);
		numberActions.add(one);
		KeyAction numpadOne = new KeyAction(KeyEvent.VK_NUMPAD1);
		numpadActions.add(numpadOne);
		
		KeyAction two = new KeyAction(KeyEvent.VK_2);
		numberActions.add(two);
		KeyAction numpadTwo = new KeyAction(KeyEvent.VK_NUMPAD2);
		numpadActions.add(numpadTwo);
		
		KeyAction three = new KeyAction(KeyEvent.VK_3);
		numberActions.add(three);
		KeyAction numpadThree = new KeyAction(KeyEvent.VK_NUMPAD3);
		numpadActions.add(numpadThree);
		
		KeyAction four = new KeyAction(KeyEvent.VK_4);
		numberActions.add(four);
		KeyAction numpadFour = new KeyAction(KeyEvent.VK_NUMPAD4);
		numpadActions.add(numpadFour);
		
		
		
		for(int i = 0; i < queNum; i++){
			buttonList.add(new EasyButton2(" "," "));
			buttonList.get(i).setOpaque(true);
			buttonList.get(i).setButtonFont(Programma.helvetica48);
			if(picNum == 1){
				buttonList.get(i).setLocation((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25, 21*Programma.paneHeight/24);
				buttonList.get(i).setButtonSize(((24-queNum)/queNum)*Programma.paneWidth/25, 5*Programma.paneHeight/50);
			}
			else if(picNum > 1){
				buttonList.get(i).setBounds((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25 , 5*Programma.paneHeight/24, ((24-queNum)/queNum)*Programma.paneWidth/24, 3*Programma.paneHeight/4);
			}
			buttonList.get(i).add(numberActions.get(i));
			buttonList.get(i).add(numpadActions.get(i));
			add(buttonList.get(i));
			//System.out.println(i);
			//System.out.println((24-queNum)/queNum);
		}
		/*
		switch(queNum){
		case 4: EasyButton b4 = new EasyButton("Lol One", "one");
			b4.setBounds( Programma.paneWidth/24, 21*Programma.paneHeight/24, ((24-queNum)/queNum)*Programma.paneWidth/24, 2*Programma.paneHeight/24);
			b4.setButtonFont(Programma.helvetica48); add(b4);
		
		case 3:
			EasyButton b3 = new EasyButton("Lol One", "one");
			b3.setBounds(Programma.paneWidth/24, 21*Programma.paneHeight/24, (23-queNum)/queNum*Programma.paneWidth/24, 2*Programma.paneHeight/24);
			b3.setButtonFont(Programma.helvetica48); add(b3);
		
		case 2:
			EasyButton b2 = new EasyButton("Lol One", "one");
			b2.setBounds(Programma.paneWidth/24, 21*Programma.paneHeight/24, (23-queNum)/queNum*Programma.paneWidth/24, 2*Programma.paneHeight/24);
			b2.setButtonFont(Programma.helvetica48); add(b2);
			
		case 1:
			EasyButton b1 = new EasyButton("Lol One", "one");
			b1.setBounds(Programma.paneWidth/24, 21*Programma.paneHeight/24, (23-queNum)/queNum*Programma.paneWidth/24, 2*Programma.paneHeight/24);
			b1.setButtonFont(Programma.helvetica48); add(b1);
		} */
		
		if(picNum == 1){
			backGr.setBounds(0, 20*Programma.paneHeight/24 , Programma.paneWidth, Programma.paneHeight);
			this.add(horLine2);
		}
		else if(picNum > 1){
			backGr.setBounds(0, 4*Programma.paneHeight/24 , Programma.paneWidth, Programma.paneHeight);
		}
		recolor();
		
		//add(gameKeys);
		//add(Programma.gameContainer.player);
		add(horLine);
		add(mainText);
		add(backGr);
		//add(gameKeys);
		//this.add(Programma.picLabel);
		this.repaint(); 
	}
	
	public void recolor(){
		setBackground(Programma.currentTheme.backgroundColor);
		horLine.setBackground(Programma.currentTheme.buttonBorderColor);
		horLine2.setBackground(Programma.currentTheme.buttonBorderColor);
		backGr.setBackground(Programma.currentTheme.backgroundColor);
		mainText.setBackground(Programma.currentTheme.backgroundColor);
		mainText.setForeground(Programma.currentTheme.foregroundColor);
		
		for(EasyButton2 button : buttonList){
			button.setRightBorder();
		}
		repaint();
	}
	
	public void setMainText(String text){
		mainText.setText("<html><CENTER>" + text + "<CENTER><html>");
	}
	
	public void recover(){
		this.removeAll();
		for(int i = 0; i < buttonList.size(); i++){
			add(buttonList.get(i));
		}
		if(picNum == 1){
			backGr.setBounds(0, 20*Programma.paneHeight/24 , Programma.paneWidth, Programma.paneHeight);
			this.add(horLine2);
		}
		else if(picNum > 1){
			backGr.setBounds(0, 4*Programma.paneHeight/24 , Programma.paneWidth, Programma.paneHeight);
		}
		//add(esc);
		//add(gameKeys);
		if(!isMainTextHidden){
			add(mainText);
			add(horLine);
		}
		add(backGr);
		if(picNum == 1 && mainImage != null	){
			add(mainImage);
		}
		//this.add(Programma.picLabel);
		this.repaint(); 
	}
	
	public void hideMainText(boolean hide){
		isMainTextHidden = hide;
		if(picNum == 1 && mainImage != null){
			if(hide){
				mainImage.setBounds(0, 0, Programma.paneWidth, 20*Programma.paneHeight/24);
			}
			else{
				mainImage.setBounds(0, 3*Programma.paneHeight/24, Programma.paneWidth, 17*Programma.paneHeight/24);
			}
		}
		recover();	
	}
	
	public void setAns(String...strings ){
		if(strings.length == buttonList.size()){
			for(int i =0; i < strings.length; i++){
				buttonList.get(i).setButtonText(strings[i] );
			}
		}
		else{
			System.err.println("number of answers does not match the number of buttons");
		}
		
	}
	public void setCommands(String...strings){
		if(strings.length == buttonList.size()){
			for(int i = 0; i < strings.length; i++){
				buttonList.get(i).setButtonCommand(strings[i]);
				//buttonList.get(i).setMnemonic(KeyEvent.VK_A);
			}
		}
		else{
			System.err.println("number of commands does not match the number of buttons");
		}
	}
	public void setOneCommand(String string, int index){
		if(index < buttonList.size()){
			buttonList.get(index).setButtonCommand(string);
		}
		else{
			System.err.println("index too big");
		}
	}
	public void setOneAns(String string, int index){
		if(index < buttonList.size()){
			buttonList.get(index).setButtonText(string);
		}
		else{
			System.err.println("index too big");
		}
	}
	
	public void setPictures(String...strings ){
		if(picNum == 1 && strings.length == 1){
			
			if(isMainTextHidden){
				mainImage = new EasyImage(strings[0], Programma.paneWidth, 20*Programma.paneHeight/24 );
				mainImage.setLocation(0, 0);
			}
			else{
				mainImage = new EasyImage(strings[0], Programma.paneWidth, 17*Programma.paneHeight/24 );
				mainImage.setLocation(0, 3*Programma.paneHeight/24);
			}
			add(mainImage);
			/*
			BufferedImage i;
			boolean isPic = true;

			try {
				i = ImageIO.read(new File(strings[0]));
				
				Image im = (Image) i;
				Image ima;
			
				//ima = im.getScaledInstance(this.getWidth() - 16, -3, Image.SCALE_SMOOTH);
				
				if((int)(1000*im.getWidth(null)/im.getHeight(null)) < (int)(1000*Programma.paneWidth/(16*Programma.paneHeight/24))){
					ima = im.getScaledInstance(Programma.paneWidth, -3, Image.SCALE_SMOOTH);
				}
				else{
					ima = im.getScaledInstance(-3, (16*Programma.paneHeight/24), Image.SCALE_SMOOTH);
				}

				
				picture = new JLabel(new ImageIcon(ima), JLabel.CENTER);
				picture.setSize(ima.getWidth(null), ima.getHeight(null));
				picture.setLocation(0, 4*Programma.paneHeight/24);
				
				add(picture);
			}
			catch (IOException e){
				System.err.println(("Picture doesn't load"));
				e.printStackTrace();
			} */
		}
		else if(strings.length == buttonList.size() && strings.length == picNum){
			for(int i =0; i < strings.length; i++){
				buttonList.get(i).setPicture(strings[i]);
			}
		}
		else{
			System.err.println("number of pictures does not match the number of buttons or 1");
		}
		
		
	}
	
	/**
	 * Sets the number of questions. 
	 * @param q
	 */
	public void setQ(int q){
		if(q == queNum){
			return;
		}
		int oldQueNum = queNum;
		queNum = q;
		
		if(buttonList.size() > q){
			for(int i = buttonList.size() - 1; buttonList.size() > q; i--){
				buttonList.remove(i);
			}
		}
		else{
			for(int i = 0; buttonList.size() < q; i++){
				buttonList.add(new EasyButton2(" "," "));
				buttonList.get(oldQueNum + i).setOpaque(true);
				buttonList.get(oldQueNum + i).setButtonFont(Programma.helvetica48);
			}
			
			
		}
		for(int i = 0; i < buttonList.size(); i++){
			if(picNum == 1){
				buttonList.get(i).setLocation((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25, 21*Programma.paneHeight/24);
				buttonList.get(i).setButtonSize(((24-queNum)/queNum)*Programma.paneWidth/24, 5*Programma.paneHeight/50);
			}
			else if(picNum > 1){
				buttonList.get(i).setBounds((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25 , 5*Programma.paneHeight/24, ((24-queNum)/queNum)*Programma.paneWidth/24, 3*Programma.paneHeight/4);
			}
		}
		recover();
	}
	
	public void repaintButtons(){
		for(EasyButton2 b : buttonList){
			b.setRightBorder();
		}
	}
	
	/**
	 * I don't know why, but this function is currently not working
	 * @param text
	 */
	public void removeText(String text){
		
		for(int i = 0; i < buttonList.size(); i++){
			System.out.println("button: _" + buttonList.get(i).getText() + "_ \t text: _" + text + "_\t" + (buttonList.get(i).getText() == text) );
			if(buttonList.get(i).getText() == text){
				buttonList.remove(i);
				queNum--;
				break;
			}
		}
		
		for(int i = 0; i < buttonList.size(); i++){
			if(picNum == 1){
				buttonList.get(i).setLocation((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25, 21*Programma.paneHeight/24);
				buttonList.get(i).setButtonSize(((24-queNum)/queNum)*Programma.paneWidth/25, 5*Programma.paneHeight/50);
			}
			else if(picNum > 1){
				buttonList.get(i).setBounds((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25 , 5*Programma.paneHeight/24, ((24-queNum)/queNum)*Programma.paneWidth/24, 3*Programma.paneHeight/4);
			}
		}
		//System.out.println( "buttonList size:" + buttonList.size() );
		recover();
	}
	
	public void removeCommand(String command){
		//int index = 10;
		
		for(int i = 0; i < buttonList.size(); i++){
			//System.out.println("button: _" + buttonList.get(i).getCommand() + "_ \t text: _" + command + "_\t" + (buttonList.get(i).getCommand() == command) );
			if(buttonList.get(i).getCommand() == command){
				//index = i;
				queNum--;
				buttonList.remove(i);
				i--;
			}
		}
		//System.out.println("remove: " + index);		
		for(int i = 0; i < buttonList.size(); i++){
			if(picNum == 1){
				buttonList.get(i).setLocation((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25, 21*Programma.paneHeight/24);
				buttonList.get(i).setButtonSize(((24-queNum)/queNum)*Programma.paneWidth/25, 5*Programma.paneHeight/50);
			}
			else if(picNum > 1){
				buttonList.get(i).setBounds((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25, 5*Programma.paneHeight/24, ((24-queNum)/queNum)*Programma.paneWidth/24, 3*Programma.paneHeight/4);
			}
		}
		//System.out.println( "buttonList size:" + buttonList.size() );
		recover();
	}
	
	public void addButton(String text, String command){
		
		buttonList.add(new EasyButton2(text, command));
		buttonList.get(queNum).setOpaque(true);
		buttonList.get(queNum).setButtonFont(Programma.helvetica48);
		
		queNum++;
		for(int i = 0; i < buttonList.size(); i++){
			if(picNum == 1){
				buttonList.get(i).setLocation((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25, 21*Programma.paneHeight/24);
				buttonList.get(i).setButtonSize(((24-queNum)/queNum)*Programma.paneWidth/25, 5*Programma.paneHeight/50);
			}
			else if(picNum > 1){
				buttonList.get(i).setBounds((1 + i +i*(24-queNum)/queNum)*Programma.paneWidth/25 , 5*Programma.paneHeight/24, ((24-queNum)/queNum)*Programma.paneWidth/24, 3*Programma.paneHeight/4);
			}
		}
		recover();
	}
	
	public void soundButton(String sound, int index){
		buttonList.get(index).setMouseCommand(sound);
	}
	
	public void resetBorders(){
		for(EasyButton2 button : buttonList){
			button.setRightBorder();
		}
		
	}

}
