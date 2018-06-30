package components;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import sound.MusicPlayer;
import sound.SoundMixer;
import start.Programma;

public class EasyButton2 extends JPanel
								implements MouseListener {

	//private JButton b1;
	//private Color color3, color4;
	//private boolean colorChance = false;
	//private static Color darkOrange = new Color(255,140,0);
	//private static Color lightBlue = new Color(10,100,255);
	//private Color 
	String mouseCommand = " ";
	String music = "Pirates Of The Caribbean Theme Song";
	//static Border border = BorderFactory.createLineBorder(darkOrange,8);
	//static Border mouseBorder = BorderFactory.createLineBorder(Color.RED,10);
	//static Border selectedBorder = BorderFactory.createLineBorder(Color.blue,10);
	//static Border semiSelectedBorder = BorderFactory.createLineBorder(Color.magenta,10);
	private boolean actionOnRelease = false;
	String command;
	boolean isPic = false;
	JLabel label;
	EasyImage picture;
	JLabel picture2;
	//JLabel borderLabel = new JLabel("  ");
	
	
	
	public EasyButton2(String name, String com) {
		setOpaque(true);
		command = com;
		label = new JLabel("<html><CENTER>" + name + "<CENTER><html>", JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(Programma.currentTheme.buttonBgColor);
		//setText(name);
        setBackground(Programma.currentTheme.buttonBgColor);
		//setHorizontalAlignment(JLabel.CENTER);
		
		
		//this = new JButton(name);
    	setLayout(null);
    	
    	setFocusable(true);
    	//addKeyListener(this);
    	setIgnoreRepaint(true);
    	setSelected(false);
    	setBounds(0, 0, this.getWidth(), this.getHeight());
    	setBorder(EasyButton.border);
    	addMouseListener(this);
        //setActionCommand(command);
        //addActionListener(this);

        setSize(300,185);
        label.setSize(280,165);
        label.setLocation(10,10);
       // add(borderLabel);
        add(label);
        return;       
    }
	
	public static void main(String[] args) {
	}
	
	public void setButtonFont(Font font){
		label.setFont(font);
	}
	public void setButtonSize(int width, int height){
		setSize(width,height);
		label.setSize(width - 20, height - 20);
	}
	public void setButtonText(String string){
		label.setText("<html><CENTER>" + string + "<CENTER><html>");
	}
	public void setButtonCommand(String string){
		command = string;
	}
	public void setMouseCommand(String string){
		mouseCommand = string;
	}


	public void setSelected(boolean select){
		if(select){
			setBorder(EasyButton.selectedBorder);
		}
		else{
			setBorder(EasyButton.border);
		}
	}
	
	public void setSemiSelected(boolean select){
		if(select){
			setBorder(EasyButton.semiSelectedBorder);
		}
		else{
			setBorder(EasyButton.border);
		}
	}
	public void setRightBorder() {
		setBackground(Programma.currentTheme.buttonBgColor);
		label.setBackground(Programma.currentTheme.buttonBgColor);
		label.setForeground(Programma.currentTheme.foregroundColor);
		if (SoundMixer.getCurrentMusic() == mouseCommand) {
			setBorder(EasyButton.selectedBorder);
		} else {
			if (this.getMousePosition() != null) {
				setBorder(EasyButton.mouseBorder);
			} else {
				setBorder(EasyButton.border);
			}
			if (isPic) {
				remove(picture2);
				add(picture);
			}
		}
	}
	
	public void setPicture(String string){
		//BufferedImage i;
		//add(borderLabel);
		isPic = true;
		
		picture = new EasyImage(string,this.getWidth() - 16, 5*this.getWidth()/6);
		picture.setLocation(8, 8);
		
		picture2 = new EasyImage(string,this.getWidth() - 20, 5*this.getWidth()/6);
		picture2.setLocation(10, 10);
		add(picture);
		
		/*
		try {
			i = ImageIO.read(new File(string));
			
			Image im = (Image) i;
			Image ima;
			Image imag;

			
			ima = im.getScaledInstance(this.getWidth() - 16, -3, Image.SCALE_SMOOTH);
			imag = im.getScaledInstance(this.getWidth() - 20, -3, Image.SCALE_SMOOTH);
			
			picture = new JLabel(new ImageIcon(ima), JLabel.CENTER);
			picture.setSize(ima.getWidth(null), ima.getHeight(null));
			picture.setLocation(8, 8);
			
			picture2 = new JLabel(new ImageIcon(ima), JLabel.CENTER);
			picture2.setSize(imag.getWidth(null), imag.getHeight(null));
			picture2.setLocation(10, 10);
			
			remove(label);
			label.setBounds(10, picture.getHeight() +10 , this.getWidth() - 20, this.getHeight() - picture.getHeight() -20);
			//System.out.println(this.getHeight());
			add(picture);
			add(label);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(("picture doesn't load"));
			e.printStackTrace();
		} 
		*/
		remove(label);
		label.setBounds(10, picture.getHeight() +10 , this.getWidth() - 20, this.getHeight() - picture.getHeight() -20);
		
		add(label);
	}
	
	public String getText(){
		return label.getText().substring(14, label.getText().length() - 14);
	}	
	
	public String getCommand(){
		return command;
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
		setSelected(false);
		// TODO Auto-generated method stub
		if(isPic){
			remove(picture);
			add(picture2);
		}
		
		setBorder(EasyButton.mouseBorder);
		if(actionOnRelease){
			setBackground(Programma.currentTheme.buttonBgPrColor);
			
		}
		/*if(mouseCommand != " "  && mouseCommand != SoundMixer.getCurrentMusic()){
			//System.out.println(mouseCommand);
			//System.out.println(SoundMixer.getCurrentMusic());
			SoundMixer.alternativeMusic(mouseCommand);
		}
		else if(mouseCommand == SoundMixer.getCurrentMusic() ){
			setBorder(selectedBorder);
		}*/
		if(mouseCommand != " "){
			SoundMixer.playSound(mouseCommand);
		}
		
	}

	public void mouseExited(MouseEvent arg0) {
		setSelected(false);
		// TODO Auto-generated method stub
		//b1.setBackground(Color.ORANGE);
		//b1.setBorder(border);
		if(mouseCommand != " "){
			//music = mouseCommand;
			SoundMixer.stopSound();
		}
		if(isPic){
			remove(picture2);
			add(picture);
		}
		setRightBorder();
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
		//remove(label);
		if(label.contains(e.getPoint())){
			setBackground(Programma.currentTheme.buttonBgPrColor);
			label.setBackground(Programma.currentTheme.buttonBgPrColor);
		}
		//add(label);
		actionOnRelease = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		if(getMousePosition()!= null){
			setBackground(Programma.currentTheme.buttonBgColor);
			label.setBackground(Programma.currentTheme.buttonBgColor);
			Programma.createAndShowGUI(command);


		}

		actionOnRelease = false;
	}



}
