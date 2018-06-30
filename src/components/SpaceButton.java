package components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import sound.SoundMixer;
import start.Programma;
import start.SpaceScreen;

public class SpaceButton extends EasyButton{
	public SpaceScreen screen;
	public static Color backgroundColor = Color.BLUE;
	public static Color backgroundColorClicked = Color.CYAN;
	public static Color borderColor = Color.CYAN;
	public static Color mouseBorderColor = Color.WHITE;
	public static Border border = BorderFactory.createLineBorder(borderColor,8);
	public static Border mouseBorder = BorderFactory.createLineBorder(mouseBorderColor,8);
	
	public SpaceButton(String name, String com, SpaceScreen scrn){
		super(name, com);
		screen = scrn;
		setRightBorder();
	}
	
	public void setRightBorder(){
		setBackground(backgroundColor);
		if(this.getMousePosition() != null){
			setBorder(mouseBorder);
		}	
		else{
			setBorder(border);
		}
		this.repaint();
	}
	
	public void mouseEntered(MouseEvent arg0) {
		setRightBorder();
	}

	@Override
	public void mouseExited(MouseEvent e) {	
		setRightBorder();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		setBackground(backgroundColorClicked);
		actionOnRelease = true;
	}
	
	public void mouseReleased(MouseEvent arg0) {
		if(getMousePosition()!= null){
			setBackground(backgroundColor);
			screen.spaceButton(command);
		}
		actionOnRelease = false;
	}

	
	

}
