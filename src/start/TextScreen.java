package start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import components.*;

public class TextScreen extends GameScreen implements ActionListener {
	JPanel horLine2;
	//String nextScreen;
	//JPanel backGr;
	//JLabel picture;
	JLabel mainImage;
	Timer timer;
	//EasyButton continueButton = new EasyButton()
	static EasyButton continueButton = new EasyButton("Continue", "continue");
	static JLabel loading;
	boolean directContinue = false;
	
	public TextScreen(String string){
		super(0,1);
		continueButton.setBounds(19*Programma.paneWidth/24, 21*Programma.paneHeight/24, Programma.paneWidth/6, 2*Programma.paneHeight/24);
		continueButton.setFont(Programma.helvetica32);
		continueButton.setCursor(null);
		this.setFocusable(true);
		remove(super.horLine);
		remove(super.backGr);
		remove(super.mainText);
		super.mainText.setBounds(0, 20*	Programma.paneHeight/24 + super.horLine2.getHeight(), Programma.paneWidth, 4*Programma.paneHeight/24 - super.horLine2.getHeight());
		super.mainText.setText("<html><center>" + string + "<center><html>");
		add(super.mainText);

		/*
		this.setLayout(null);
		this.setOpaque(true);
		this.setBounds(0, 0, Programma.paneWidth, Programma.paneHeight);
		this.setBackground(Programma.darkGreen);
		
		horLine2 = new JPanel();
		horLine2.setLayout(null);
		horLine2.setOpaque(true);
		horLine2.setBounds(0, 20*Programma.paneHeight/24, Programma.paneWidth, 15);
		horLine2.setBackground(Programma.darkOrange);
		add(horLine2);
		
		//backGr = new JPanel();
		//backGr.setLayout(null);
		//backGr.setOpaque(true);
		//backGr.setBackground(Programma.darkGreen);
		//add(backGr);
		
		mainText = new JLabel("lol lol lol", JLabel.CENTER);
		mainText.setOpaque(true);
		mainText.setFont(Programma.helvetica32);
		mainText.setBounds(0, 20*	Programma.paneHeight/24, Programma.paneWidth, 4*Programma.paneHeight/24);
		mainText.setBackground(Programma.darkGreen);
		add(mainText);
		
		
		/*

		
		horLine = new JPanel();
		horLine.setLayout(null);
		horLine.setOpaque(true);
		horLine.setBounds(0, 20*Programma.paneHeight/24, Programma.paneWidth, 15);
		horLine.setBackground(Programma.darkOrange);
		
		mainText = new JLabel("<html><CENTER>" + string + "<CENTER><html>", JLabel.CENTER);
		mainText.setOpaque(true);
		mainText.setFont(Programma.helvetica32);
		mainText.setBounds(0, 20*Programma.paneHeight/24 + 15, Programma.paneWidth, 4*Programma.paneHeight/24 -15);
		mainText.setBackground(Programma.darkGreen);
		
		EasyImage label = new EasyImage("Images/een tropisch eiland .png", 200 ,200);
		label.setLocation(200, 400);
		JLabel label2 = label.getScaledInstance(-3, 200);
		label2.setLocation(600, 400);
		
		add(label);
		add(label2);
		
		add(mainText);
		add(horLine);
		*/
	}
	public static void main(String[] args){
		//loading = new EasyImage("Images/Gear.gif", 2*Programma.paneHeight/24, 2*Programma.paneHeight/24);
		//loading.setLocation(21*Programma.paneHeight/24, 21*Programma.paneHeight/24);
		Icon icon = new ImageIcon("Images/Gear.gif");
		loading = new JLabel("", JLabel.CENTER);
		loading.setBounds(21*Programma.paneWidth/24, 16*Programma.paneHeight/18, icon.getIconWidth(), icon.getIconHeight());
		loading.setIcon(icon);
	}
	
	public void addLoading(){
		//this.add(loading);
		//super.mainText.repaint();
		//this.recover();
	//	this.remove(super.mainText);
		//this.add(loading);
		//this.add(super.mainText);
	}
	
	public void recover(){
		removeAll();
		add(super.horLine2);
		add(super.mainText);
		add(super.mainImage);
		repaint();
	}
	
	public void setText(String string){
		super.mainText.setText("<html><center>" + string + "<center><html>");
	}
	
	public void addContinue(){
		//try{
			//	this.remove(loading);
		//}catch(Exception e){}
		removeAll();
		
		//Programma.continueButton.setRightLayout();
		//remove(super.mainText);
		continueButton.setRightLayout();
		super.mainText.setBounds(0, 20*	Programma.paneHeight/24  + super.horLine2.getHeight() , 19*Programma.paneWidth/24, 4*Programma.paneHeight/24 - super.horLine2.getHeight());
		add(continueButton);
		add(super.horLine2);
		add(super.mainText);
		add(super.mainImage);
		repaint();
		
		//Programma.frame.getContentPane().add(Programma.continueButton);
		//Programma.continueButton.repaint();
		
	}
	public void removeContinue(){
		remove(continueButton);
		super.mainText.setBounds(0, 20*	Programma.paneHeight/24  + super.horLine2.getHeight(), Programma.paneWidth, 4*Programma.paneHeight/24 - super.horLine2.getHeight());
		repaint();
	}
	
	public void setImage(String string){
		super.mainImage = new EasyImage(string, Programma.paneWidth, 20*Programma.paneHeight/24 );
		super.mainImage.setLocation(0, 0);
		add(super.mainImage);
	}
	
	public void continueAfterDelay(String position, int delay){
		timer = new Timer(300, this);
		timer.setInitialDelay(delay);
		//timer.setRepeats(false);
		directContinue = true;
		timer.start();
		//nextScreen = position;
	}
	
	public void addContinueIfReady(){
		timer = new Timer(300, this);
		timer.setInitialDelay(300);
		//timer.setRepeats(false);
		directContinue = false;
		timer.start();
		
		//nextScreen = position;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Loader.isLoadingReady){
			if(directContinue){
				Programma.createAndShowGUI("continue");
			}
			else{
				addContinue();
			}
			timer.stop();
		}
	}
	
	
	
	
}
