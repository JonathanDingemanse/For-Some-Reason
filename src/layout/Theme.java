package layout;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import components.EasyButton;
import start.*;

public class Theme implements MouseListener{
	public static Color darkOrange = new Color(255,140,0);
	public static Color lightBlue = new Color(1,100,255);
	public static Color darkGreen = new Color(0,200,0);
	public static Color seaGreen = new Color(0,120,50);
	public static Color darkerGreen = new Color(0,170,0);
	public static Color softerBlue = new Color(0,120,200);
	public static Color softBlue = new Color(0,150,220);
	
	public Color backgroundColor;
	public Color foregroundColor;
	public Color buttonBgColor;
	public Color buttonBgPrColor;
	public Color buttonBorderColor;
	public Color buttonBorderPrColor;
	public Color title1Color;
	public Color title2Color;
	public Color devStatusColor;
	public Color musicListColor;
	public Color musicSelectedColor;
	
	public static Theme cyber;
	public static Theme classic;
	public static Theme gamingRed;
	public static Theme darkPurple;
	public static Theme monochrome;
	public static Theme bright;
	
	public String name;
	
	public JLabel demo;
	public Border demoBorder;
	public Border demoMouseBorder;

	
	public Theme(String name, Color background, Color foreground, Color buttonBg, Color buttonBgPr, Color buttonBorder, Color buttonBorderPr,
				 Color title1, Color title2, Color devStatus, Color musicList, Color musicSelected){
		backgroundColor = background;
		foregroundColor = foreground;
		buttonBgColor = buttonBg;
		buttonBgPrColor = buttonBgPr;
		buttonBorderColor = buttonBorder;
		buttonBorderPrColor = buttonBorderPr;
		title1Color = title1;
		title2Color = title2;
		devStatusColor = devStatus;
		musicListColor = musicList;
		musicSelectedColor = musicSelected;
		this.name = name;
		
		demoBorder = BorderFactory.createLineBorder(buttonBorderColor, 8*Programma.paneHeight/1080);
		demoMouseBorder = BorderFactory.createLineBorder(buttonBorderPrColor, 10*Programma.paneHeight/1080);
		demo = new JLabel(name, JLabel.CENTER);
		demo.setOpaque(true);
		demo.setLayout(null);
		demo.setSize(4*Programma.paneWidth/20, 7*Programma.paneHeight/40);
		//.setLocation(2*Programma.paneWidth/3, Programma.paneHeight/10);
		demo.setBackground(buttonBgColor);
		demo.setForeground(foregroundColor);
		demo.setBorder(demoBorder);
		demo.addMouseListener(this);
		demo.setFont(Programma.helvetica20);
		demo.repaint();
		
	}
	
	public Theme(String name, Color background, Color foreground, Color buttonBg, Color buttonBgPr, Color buttonBorder, Color buttonBorderPr,
			 Color title1, Color title2, Color devStatus){
		
		this(name, background, foreground, buttonBg, buttonBgPr, buttonBorder, buttonBorderPr, title1, title2, devStatus, Color.MAGENTA.darker(), Color.BLUE);
	}
	
	public void apply(){
		Programma.currentTheme = this;
		resetAllLayout();
	}
	
	public static void resetAllLayout(){
		EasyButton.resetLayout();
		Programma.resetLayout(2);
		
		for(GameScreen screen : Programma.gameContainer.gameScreens){
			screen.recolor();
		}
		for(TextScreen screen : Programma.gameContainer.textScreens){
			screen.recolor();
		}
		for(TalkScreen screen : Programma.gameContainer.talkScreens){
			screen.recolor();
		}
		Programma.gameContainer.introScreen.recolor();
	}
	
	public static void start(){
		cyber = new Theme("Cyber", Color.YELLOW, Color.BLACK, Color.YELLOW, Color.YELLOW, Color.BLACK, Color.BLACK, Color.YELLOW, Color.BLACK, Color.DARK_GRAY, darkOrange, Color.BLUE);
		classic = new Theme("Classic", darkGreen, Color.BLACK, Color.ORANGE, darkOrange, darkOrange, Color.RED, Color.RED, darkOrange, Color.BLUE);
		gamingRed = new Theme("Dark Red", Color.BLACK, Color.RED, Color.BLACK, Color.BLACK, Color.RED, Color.RED, Color.RED, Color.BLACK, Color.DARK_GRAY, darkerGreen, Color.BLUE);
		darkPurple = new Theme("Magenta", Color.BLACK, Color.MAGENTA.darker(), Color.BLACK, Color.BLACK, Color.MAGENTA.darker(), Color.MAGENTA, Color.MAGENTA.darker(), Color.BLACK, Color.DARK_GRAY, darkerGreen, Color.BLUE);
		monochrome = new Theme("Gray", Color.LIGHT_GRAY, Color.BLACK, Color.LIGHT_GRAY, Color.WHITE, Color.DARK_GRAY, Color.BLACK, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.GRAY, darkerGreen, Color.BLUE);
		bright = new Theme("Blue", softBlue, Color.WHITE, softBlue, Color.CYAN, softerBlue, Color.WHITE, softerBlue, softBlue, Color.BLUE);
		//custom = new Theme("Classic", darkGreen, Color.BLACK, Color.ORANGE, darkOrange, darkOrange, Color.RED, Color.RED, darkOrange, Color.BLACK);

		
		
		/*
		Theme darkPurple
		Theme sweet
		Theme bright
		*/
	}
	
	
	public static Color cycleColorLeft(Color c){
		return new Color(c.getGreen(), c.getBlue(), c.getRed());
	}
	
	public static Color cycleColorRight(Color c){
		return new Color(c.getBlue(), c.getRed(), c.getGreen());
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		demo.setBorder(demoMouseBorder);
		Programma.showTheme(Programma.frame.getContentPane(), this);
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		demo.setBorder(demoBorder);
		EasyButton.resetLayout();
		Programma.resetLayout(1);
		Programma.createAndShowGUI("chooseTheme");
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		demo.setBackground(buttonBgPrColor);
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		demo.setBackground(buttonBgColor);
		apply();
		Programma.createAndShowGUI("chooseTheme");
	}

}
