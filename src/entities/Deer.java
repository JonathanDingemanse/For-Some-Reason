package entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import start.Programma;

public class Deer extends Animal implements ActionListener {
	
	public Deer(int height){
		super(700, height, "Images/deer.png");
	}
	
	public void moveWhenHearShots(){
		isMoving = true;
		vX = 25;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setLocation(this.getLocation().x + 25, this.getLocation().y);
		if(getLocation().x > Programma.paneWidth){
			decreaseHealth(800);
		}
	}
}
