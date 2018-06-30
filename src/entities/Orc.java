package entities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import components.EasyImage;
import start.Programma;

public class Orc extends AttackingAnimal {
	//EasyImage image;
	//EasyImage zoomImage;
	//Timer attack;
	
	public Orc(int height, int type){
		super(1990 + (int)(Math.random()*(20 + 2*Programma.difficultyLevel)), height, "Images/Orc_" + type + ".png", 1000, 3 + 3*Programma.difficultyLevel/2);
		switch(type){
		case 0: setHead(200, 73, 398, 1500, 66); break;
		case 1: setHead(227, 104, 482, 1500, 91); break;
		case 2: setHead(290, 109, 633, 1470, 108); break;
		case 3: setHead(420, 75, 804, 930, 69); break;
		}
		//image = new EasyImage("Images/Orc_" + type + ".png", -1, height);
		//zoomImage = new EasyImage("Images/Orc_" + type + ".png", -1, 2*height);
		//this.setSize(image.getSize());
		//add(image);
		
		//attack = new Timer(1000,this);
		//attack.restart();
		//this.addMouseListener(this);
		//this.repaint();
	}
	
	public void moveWhenHit(){
		setLocation(getLocation().x + (int)(50*Math.random()) - 25, getLocation().y);
	}


	/*public void zoom(boolean zoom){
		//removeAll();
		//this.setOpaque(true);
		if(zoom){
			remove(image);
			this.setSize(zoomImage.getSize());
			this.setLocation(this.getLocation().x*2, this.getLocation().y*2);
			add(zoomImage);
		}
		else{
			remove(zoomImage);
			this.setSize(image.getSize());
			this.setLocation((this.getLocation().x)/2, this.getLocation().y/2);
			add(image);
		}
		//System.out.println("location: " + this.getLocation().x + "     "  + this.getLocation().y);
		repaint();
		
	}

	
	/*public void actionPerformed(ActionEvent arg0) {
		if(Programma.gameContainer.player.health > 0 && Programma.gameContainer.player.isActive){
			Programma.gameContainer.player.decreaseHealth(8);
		}
	}*/

	
	
}
