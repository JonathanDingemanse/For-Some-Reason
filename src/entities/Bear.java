package entities;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import components.EasyImage;
import start.Programma;

public class Bear extends AttackingAnimal{
	//EasyImage image;
	
	public Bear(int height){
		super(8000, height, "Images/bear.png", 1200, 2 + 4*Programma.difficultyLevel);
		setHead(96, 75, 335, 328, 60);
		super.headShotDamage = 8000;
		
		
		//image = new EasyImage("Images/bear.png", -1, height);
		//setSize(image.getSize());
		//add(image);

		//repaint();	
	}



}
