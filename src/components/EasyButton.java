package components;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import sound.SoundMixer;
import start.Programma;

public class EasyButton extends JLabel
								implements MouseListener {

	//private JButton b1;
	//private Color color3, color4;
	//private boolean colorChance = false;
	
	//private static Color lightBlue = new Color(10,100,255);
	//private Color 
	String mouseCommand = " ";
	String music = "Pirates Of The Caribbean Theme Song";
	public static Border border = BorderFactory.createLineBorder(Color.BLACK,8);
	public static Border mouseBorder = BorderFactory.createLineBorder(Color.BLACK,10);
	public static Border selectedBorder = BorderFactory.createLineBorder(Color.blue,10);
	public static Border semiSelectedBorder = BorderFactory.createLineBorder(Color.magenta,10);
	private static Border borderD = BorderFactory.createLineBorder(Color.BLACK,4);
	private static Border mouseBorderD = BorderFactory.createLineBorder(Color.BLACK,4);
	private static Border selectedBorderD = BorderFactory.createLineBorder(Color.blue,5);
	private static Border semiSelectedBorderD = BorderFactory.createLineBorder(Color.magenta,5);
	public static Border mouseSelectedBorder = BorderFactory.createCompoundBorder(selectedBorderD, mouseBorderD); 
	public static Border doubleSelectedBorder = BorderFactory.createCompoundBorder(selectedBorderD, semiSelectedBorderD);
	public static Border nonDoubleSelectedBorder = BorderFactory.createCompoundBorder(selectedBorderD, borderD);
	public static Border mouseSemiSelectedBorder;
	protected boolean actionOnRelease = false;
	String command;
	
	
	
	
	public EasyButton(String name, String com) {
		setOpaque(true);
		command = com;
		setText( name);
        
		setHorizontalAlignment(JLabel.CENTER);
		//this = new JButton(name);
    	setLayout(null);
    	setFocusable(true);
    	//addKeyListener(this);
    	//setIgnoreRepaint(true);
    	setSelected(false);
    	setBorder(border);
    	addMouseListener(this);
        //setActionCommand(command);
        //addActionListener(this);
    	

        setSize(300,185);
        //setFocusPainted(false);
        //setSize(300,185);
        //add(b1);
        //this.repaint();
        return;       
    }
	
	public static void main(String[] args) {
	}
	
	public void setButtonFont(Font font){
		setFont(font);
	}
	public void setButtonSize(int width, int height){
		setSize(width,height);
		setSize(width, height);
	}
	public void setButtonText(String string){
		setText( string  );
	}
	public void setButtonCommand(String string){
		command = string;
	}
	public void setMouseCommand(String string){
		mouseCommand = string;
    	if(mouseCommand == SoundMixer.getCurrentMusic()){
			setBorder(selectedBorder);
		}
	}


	public void setSelected(boolean select){
		if(select){
			setBorder(selectedBorder);
		}
		else{
			setBorder(border);
		}
	}
	
	public void setSemiSelected(boolean select){
		if(select){
			setBorder(semiSelectedBorder);
		}
		else{
			setBorder(border);
		}
	}
	
	public static void resetLayout(){
		
		border = BorderFactory.createLineBorder(Programma.currentTheme.buttonBorderColor, 8*Programma.paneHeight/1080);
		mouseBorder = BorderFactory.createLineBorder(Programma.currentTheme.buttonBorderPrColor, 10*Programma.paneHeight/1080);
		selectedBorder = BorderFactory.createLineBorder(Programma.currentTheme.musicSelectedColor, 10*Programma.paneHeight/1080);
		semiSelectedBorder = BorderFactory.createLineBorder(Programma.currentTheme.musicListColor, 10*Programma.paneHeight/1080);
		borderD = BorderFactory.createLineBorder(Programma.currentTheme.buttonBorderColor, 6*Programma.paneHeight/1080);
		mouseBorderD = BorderFactory.createLineBorder(Programma.currentTheme.buttonBorderPrColor,7*Programma.paneHeight/1080);
		selectedBorderD = BorderFactory.createLineBorder(Programma.currentTheme.musicSelectedColor, 7*Programma.paneHeight/1080);
		semiSelectedBorderD = BorderFactory.createLineBorder(Programma.currentTheme.musicListColor, 7*Programma.paneHeight/1080);
		doubleSelectedBorder = BorderFactory.createCompoundBorder(selectedBorderD, semiSelectedBorderD);
		nonDoubleSelectedBorder = BorderFactory.createCompoundBorder(selectedBorderD, borderD);
		mouseSelectedBorder = BorderFactory.createCompoundBorder(selectedBorderD, mouseBorderD);
		mouseSemiSelectedBorder = BorderFactory.createCompoundBorder(semiSelectedBorderD, mouseBorderD);
		//BorderFactory.createEtchedBorder(arg0, arg1);
	}
	
	public void setRightLayout(){
		setBackground(Programma.currentTheme.buttonBgColor);
		setForeground(Programma.currentTheme.foregroundColor);
		
		/*if(SoundMixer.isSongInRandomList(mouseCommand) && SoundMixer.isMusicRandom() && SoundMixer.getCurrentMusic() == mouseCommand){
			setBorder(doubleSelectedBorder);
		}
		else if(SoundMixer.getCurrentMusic() == mouseCommand && SoundMixer.musicProfile == "select"){
			setBorder(selectedBorder);
		}
		else if(SoundMixer.getCurrentMusic() == mouseCommand){
			setBorder(nonDoubleSelectedBorder);
		}
		/*else if(this.getMousePosition() != null){
			setBorder(mouseBorder);
		}
		else if(SoundMixer.isSongInRandomList(mouseCommand) && SoundMixer.isMusicRandom()){
			setBorder(semiSelectedBorder);
		}
		else */
		if(this.getMousePosition() != null){
			setBorder(mouseBorder);
		}	
		else{
			setBorder(border);
		}
		//setLayout(null);
    	//setFocusable(true);
    	//addMouseListener(this);
		this.repaint();
	}
	
	public void setButtonBorder(Border border){
		setBorder(border);
	}

	public void setMnemonic(int key) {
		//ActionMap am = this.getActionMap();
		
		
		//setFocusTraversalKeysEnabled(true);
		//setMnemonic(key);	
	}
	/*
	public void setPicture(String string){
		BufferedImage i;

		try {
			i = ImageIO.read(new File(string));
			
			Image im = (Image) i;
			Image ima;
			
			ima = im.getScaledInstance(this.getWidth() - 16, -3, Image.SCALE_SMOOTH);
			
			setIcon(new ImageIcon(ima));
			
			this.setVerticalAlignment(TOP);
			this.setIconTextGap(800);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(("picture doesn't load"));
			e.printStackTrace();
		}
	} */

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		setSelected(false);
		setBorder(mouseBorder);
		if(actionOnRelease){
			setBackground(Programma.currentTheme.buttonBgPrColor);
		}
		/*if(mouseCommand != " "  && mouseCommand != SoundMixer.getCurrentMusic()){
			//System.out.println(mouseCommand);
			//System.out.println(SoundMixer.getCurrentMusic());
			SoundMixer.alternativeMusic(mouseCommand);
		}
		else if(mouseCommand == SoundMixer.getCurrentMusic() ){
			setBorder(mouseSelectedBorder);
		}
		*/

		//System.out.println(SoundMixer.getCurrentMusic());
		//System.out.println(mouseCommand);

	}

	public void mouseExited(MouseEvent e) {
		//setSelected(false);
		setRightLayout();
		// TODO Auto-generated method stub
		//b1.setBackground(Color.ORANGE);
		//b1.setBorder(border);
		/*if(mouseCommand != " " && mouseCommand != SoundMixer.getCurrentMusic()){ //
			SoundMixer.resumeMusic();
			//System.out.println("PreHear has to stop");
		}
		
		//e.getButton();
		//e.getButton();
		//System.out.println("Button: " + e.getButton());
		

		//Programma.createAndShowGUI("settings");
		/*
		else if(mouseCommand == SoundMixer.getCurrentMusic()){
			b1.setBorder(selectedBorder);
		}
		else if(SoundMixer.isSongInRandomList(mouseCommand)){
			b1.setBorder(semiSelectedBorder);
		}*/
		//System.out.println(SoundMixer.getCurrentMusic());
		//System.out.println(mouseCommand);
		
	}

	public void mousePressed(MouseEvent e) {
		setBackground(Programma.currentTheme.buttonBgPrColor);
		actionOnRelease = true;
	}

	public void mouseReleased(MouseEvent e) {
		if(getMousePosition()!= null){
			setBackground(Programma.currentTheme.buttonBgColor);
			Programma.createAndShowGUI(command);
			//this.setFocusable(true);
			//this.repaint();
			//System.out.println("Continue game");
			/*if(mouseCommand != " "){
				//music = mouseCommand;
				SoundMixer.mainMusic(mouseCommand);
				//setBorder(selectedBorder);
				Programma.createAndShowGUI("chooseMusic");
				setRightLayout();
			}*/
			//else if(mouseCommand == SoundMixer.getCurrentMusic()){}
		}
		actionOnRelease = false;
	}



}
