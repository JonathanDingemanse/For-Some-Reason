package old;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import sound.MusicPlayer;
import sound.SoundMixer;
import start.Programma;

public class EasyButtonMaker2 extends JLabel
								implements MouseListener, KeyListener {

	//private JButton b1;
	//private Color color3, color4;
	//private boolean colorChance = false;
	private static Color darkOrange = new Color(255,140,0);
	//private Color 
	String mouseCommand = " ";
	String command = " ";
	//String music = "Pirates Of The Caribbean Theme Song";
	static Border border = BorderFactory.createLineBorder(darkOrange,8);
	static Border mouseBorder = BorderFactory.createLineBorder(Color.RED,10);
	static Border selectedBorder = BorderFactory.createLineBorder(Color.blue,10);
	static Border semiSelectedBorder = BorderFactory.createLineBorder(Color.cyan,10);
	
	
	public EasyButtonMaker2(String name, String click, Color color){
		setOpaque(true);
		setText(name);
		setHorizontalAlignment(CENTER);
        //setFont(Programma.helvetica32);
        setBackground(Color.orange);
        setFocusable(true);
        setBorder(border);
        setSize(300,185);
        addMouseListener(this);
        //mouseCommand = mouse;
        command = click;
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

	}
	
	public void setButtonFont(Font font){
		setFont(font);
	}
	public void setButtonSize(int width, int height){
		setSize(width, height);
	}
	public void setButtonText(String string){
		setText(string);
	}
	public void setMouseCommand(String string){
		mouseCommand = string;
    	if(mouseCommand == SoundMixer.getCurrentMusic()){
			setBorder(selectedBorder);
		}
	}
	public void setButtonBorder(Border border){
		setBorder(border);
	}	public void setRightBorder(){
		if(SoundMixer.getCurrentMusic() == mouseCommand){
			setBorder(selectedBorder);
		}
		else if(SoundMixer.isSongInRandomList(mouseCommand) && SoundMixer.isMusicRandom()){
			setBorder(semiSelectedBorder);
		}
		else{
			setBorder(border);
		}
	}
	

	public void setSelected(boolean select){
		if(select){
			setBorder(selectedBorder);
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			Programma.createAndShowGUI("escape");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
		setSelected(false);
		// TODO Auto-generated method stub
		setBorder(mouseBorder);
		//Launcher.play();
		if(mouseCommand != " "  && mouseCommand != SoundMixer.getCurrentMusic()){
			//System.out.println(mouseCommand);
			//System.out.println(SoundMixer.getCurrentMusic());
			SoundMixer.alternativeMusic(mouseCommand);
		}
		else if(mouseCommand == SoundMixer.getCurrentMusic()){
			setBorder(selectedBorder);
		}

		//System.out.println(SoundMixer.getCurrentMusic());
		//System.out.println(mouseCommand);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setSelected(false);
		// TODO Auto-generated method stub
		//b1.setBackground(Color.ORANGE);
		//b1.setBorder(border);
		if(mouseCommand != " " && mouseCommand != SoundMixer.getCurrentMusic()){
			SoundMixer.resumeMusic();
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		Programma.createAndShowGUI(command);
		//b1.setBackground(Color.GREEN);
		setBackground(darkOrange);
		
		//b1.setBorder(border);
		if(mouseCommand != " " && mouseCommand != SoundMixer.getCurrentMusic()){
			//music = mouseCommand;
			SoundMixer.mainMusic(mouseCommand);
			setBorder(selectedBorder);
			Programma.createAndShowGUI("settings");
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		setBackground(Color.ORANGE);
		//b1.setBorder(border);
	

	}

}
