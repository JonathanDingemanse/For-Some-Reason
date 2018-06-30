package entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import start.Programma;

public class Kangaroo extends Animal implements ActionListener {
	
	public Kangaroo(int height, int base){
		super(1500, height,  "Images/kangaroo.png");
		//fps = new Timer(33, this);
		//fps.start();
		isMoving = true;
		this.setLocation(this.getLocation().x, base);
		this.base = base;
		vY = -100;
		aY = 10;
	}
	
	public void moveFPS(){
		super.moveFPS();
		if(this.getLocation().y >= base){
			this.setLocation(this.getLocation().x, base);
			//vY = -vY;
			vY = -85 + (int)(Math.random()*20);
			if(this.getLocation().x < Programma.paneWidth/10){
				vX = (int)(Math.random()*20);
			}
			else if(this.getLocation().x + this.getWidth() > 9*Programma.paneWidth/10){
				vX = (int)(Math.random()*20) - 20;
			}
			else{
				vX = (int)(Math.random()*20) - 10;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//vY += aY;
		//this.setLocation(this.getLocation().x + vX, this.getLocation().y + vY);
		
		
		
	}
	

}
