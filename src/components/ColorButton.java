package components;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import start.SpaceScreen;

public class ColorButton extends EasyButton{
	public SpaceScreen screen;
	public Color backgroundColor; 
	public Color backgroundColorClicked;
	public Color borderColor;
	public Color mouseBorderColor;
	public Border border;
	public Border mouseBorder;
	
	public ColorButton(String com, Color color, int width, SpaceScreen scrn){
		super("", com);
		this.setLayout(null);
		screen = scrn;
		backgroundColor = color.darker(); 
		backgroundColorClicked =  color;
		borderColor = color;
		mouseBorderColor = color.brighter();
		
		this.setSize(width, width);
		border = BorderFactory.createLineBorder(borderColor,8);
		mouseBorder = BorderFactory.createLineBorder(mouseBorderColor,10);
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
	
	@Override
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
