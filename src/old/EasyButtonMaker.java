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

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import sound.MusicPlayer;
import sound.SoundMixer;
import start.Programma;

public class EasyButtonMaker extends JPanel
								implements ActionListener, MouseListener, KeyListener {

	private JButton b1;
	private Color color3, color4;
	private boolean colorChance = false;
	private static Color darkOrange = new Color(255,140,0);
	private static Color lightBlue = new Color(10,100,255);
	//private Color 
	String mouseCommand = " ";
	String music = "Pirates Of The Caribbean Theme Song";
	static Border border = BorderFactory.createLineBorder(darkOrange,8);
	static Border mouseBorder = BorderFactory.createLineBorder(Color.RED,10);
	static Border selectedBorder = BorderFactory.createLineBorder(Color.blue,10);
	static Border semiSelectedBorder = BorderFactory.createLineBorder(Color.magenta,10);
	
	public EasyButtonMaker(String name, String command, String focusCommand, int width, int height, Color color, Color colorForground) {
    	setLayout(null);
    	b1.setFocusable(true);
    	

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
       
        b1.setSize(width,height);
        b1.setBackground(color);
        b1.setForeground(colorForground);
       // b1.setFont(font);
        setSize(width, height);
        add(b1);
        //color3 = color;
        //color4 = color2;
        //colorChance = true;
        return;
    }
	
	public EasyButtonMaker(String command) {
		
		
		
    	setLayout(null);

        b1 = new JButton();
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(300,185);
        b1.setFocusPainted(false);
        setSize(300,185);
        add(b1); 
        return;       
    }
	
	public EasyButtonMaker(String name, String command) {
    	//setFocusable(true);
    	//addKeyListener(this);
    	setLayout(null);
        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(300,185);
        setSize(300,185);
        add(b1);
        return;       
    }
	
	public EasyButtonMaker(String command, int width, int height) {
    	setLayout(null);
    	setFocusable(true);
    	addKeyListener(this);

        b1 = new JButton();
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(width,height);
        setSize(width, height);
        add(b1);
        return;       
    }

	public EasyButtonMaker(String name, String command, int width, int height) {
    	setLayout(null);
    	setFocusable(true);
    	addKeyListener(this);

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(width,height);
        setSize(width, height);
        add(b1);
        return;       
    }
	
	public EasyButtonMaker(String name, String command, Color color) {
		
		
		InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = this.getActionMap();
		//im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
		//KeyAction2 key = new KeyAction2("escape");
		
		//am.put("escape", key);
		
		b1 = new JButton(name);
    	setLayout(null);
    	
    	setFocusable(true);
    	//addKeyListener(this);
    	b1.setIgnoreRepaint(true);
    	b1.setSelected(false);
    	b1.setBorder(border);
    	b1.addMouseListener(this);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setBackground(color);
        b1.setSize(300,185);
        b1.setFocusPainted(false);
        setSize(300,185);
        add(b1);
        return;       
    }
	
	public EasyButtonMaker(String name, String command, int width, int height, Color color, Color color2) {
    	setLayout(null);
    	setFocusable(true);
    	addKeyListener(this);

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(width,height);
        b1.setBackground(color);
        setSize(width, height);
        add(b1);
        color3 = color;
        color4 = color2;
        colorChance = true;
        return;
        
        
    }

	public EasyButtonMaker(String name, Color colorForground, String command, int width, int height, Color color, Color color2) {
    	setLayout(null);

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        
        b1.setSize(width,height);
        b1.setBackground(color);
        b1.setForeground(colorForground);
        setSize(width, height);
        add(b1);
        color3 = color;
        color4 = color2;
        colorChance = true;
        return;
    }
	

	
	public EasyButtonMaker(String name, Color colorForground, String command, int width, int height, Color color) {
    	setLayout(null);

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(width,height);
        b1.setBackground(color);
        b1.setForeground(colorForground);
        setSize(width, height);
        add(b1);
        return;
    }
	
	public EasyButtonMaker(String name, Color colorForground, String command, int width, int height, Color color, Font font) {
    	setLayout(null);

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(width,height);
        b1.setBackground(color);
        b1.setForeground(colorForground);
        b1.setFont(font);
        setSize(width, height);
        add(b1);
        return;
    }
	
	public EasyButtonMaker(String name, Color colorForground, String command, int width, int height) {
    	setLayout(null);

        b1 = new JButton(name);
        b1.setActionCommand(command);
        b1.addActionListener(this);
        b1.setSize(width,height);
        b1.setForeground(colorForground);
        setSize(width, height);
        add(b1);
        return;
    }

	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

	}
	
	public void setButtonFont(Font font){
		b1.setFont(font);
	}
	public void setButtonSize(int width, int height){
		b1.setSize(width,height);
		setSize(width, height);
	}
	public void setButtonText(String string){
		b1.setText(string);
	}
	public void setMouseCommand(String string){
		mouseCommand = string;
    	if(mouseCommand == SoundMixer.getCurrentMusic()){
			b1.setBorder(selectedBorder);
		}
	}
	public void setFocusable(boolean focus){
		b1.setFocusable(focus);
		b1.setRolloverEnabled(focus);
		b1.setFocusPainted(false);
		
	}

	public void setSelected(boolean select){
		if(select){
			b1.setBorder(selectedBorder);
		}
	}
	
	public void setSemiSelected(boolean select){
		if(select){
			b1.setBorder(semiSelectedBorder);
		}
		else{
			b1.setBorder(border);
		}
	}
	public void setRightBorder(){
		if(SoundMixer.getCurrentMusic() == mouseCommand){
			b1.setBorder(selectedBorder);
		}
		else if(SoundMixer.isSongInRandomList(mouseCommand) && SoundMixer.isMusicRandom()){
			b1.setBorder(semiSelectedBorder);
		}
		else{
			b1.setBorder(border);
		}
	}
	public void setButtonBorder(Border border){
		b1.setBorder(border);
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		Programma.createAndShowGUI(e.getActionCommand());
		
	    	   //Programma.createAndShowGUI(e.getActionCommand());
	       
		
		
		
	}

	public void setMnemonic(int key) {
		b1.setFocusTraversalKeysEnabled(true);
		b1.setMnemonic(key);
		
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		/*if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			Programma.createAndShowGUI("escape");
		}*/
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
		b1.setSelected(false);
		// TODO Auto-generated method stub
		b1.setBorder(mouseBorder);
		//Launcher.play();
		if(mouseCommand != " "  && mouseCommand != SoundMixer.getCurrentMusic()){
			//System.out.println(mouseCommand);
			//System.out.println(SoundMixer.getCurrentMusic());
			SoundMixer.alternativeMusic(mouseCommand);
		}
		else if(mouseCommand == SoundMixer.getCurrentMusic()){
			b1.setBorder(selectedBorder);
		}

		//System.out.println(SoundMixer.getCurrentMusic());
		//System.out.println(mouseCommand);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		b1.setSelected(false);
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
		//b1.setBackground(Color.GREEN);
		b1.setBackground(darkOrange);
		
		//b1.setBorder(border);
		if(mouseCommand != " " && mouseCommand != SoundMixer.getCurrentMusic()){
			//music = mouseCommand;
			SoundMixer.mainMusic(mouseCommand);
			b1.setBorder(selectedBorder);
			Programma.createAndShowGUI("settings");
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		b1.setBackground(Color.ORANGE);
		//b1.setBorder(border);
	

	}

}
