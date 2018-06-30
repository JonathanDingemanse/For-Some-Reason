package start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import entities.Orc;
import timers.PauseTimer;

public class Spawner implements ActionListener {
	ShooterScreen panel;
	int animal;
	int count = 0;
	int amount;
	int x1;
	int x2;
	int y;
	int height;
	boolean isSpawning;
	PauseTimer spawn;
	public static final int DEER = 0;
	public static final int BEAR = 1;
	public static final int ORC = 2;
	public static final int KANGAROO = 3;
	
	//public Spawner(JPanel Panel, int height, int x1, int x2, int y){
	//	
	//}
	
	public Spawner(ShooterScreen Panel, int animal, int rate, int initialDelay, int Amount, int Height, int X1, int X2, int Y){
		spawn = new PauseTimer(rate, this);
		spawn.setInitialDelay(initialDelay);
		spawn.start();
		this.animal = animal;
		panel = Panel;
		amount = Amount;
		x1 = X1;
		x2 = X2;
		y = Y;
		height = Height;
		isSpawning = true;
		System.out.println("new spawner");
		
		
	}
	public void stop(){
		spawn.stop();
		isSpawning = false;
	}
	
	public void pause(){
		spawn.pause();
	}
	public void resume(){
		spawn.resume();
	}


	public void actionPerformed(ActionEvent e) {
		System.out.println(count);
		if(count >= amount ){
			spawn.stop();
			isSpawning = false;
			//System.out.println("spawning has stopped");
			Programma.gameContainer.repaintScreen();
		}
		else{
			int x = (int)((x2 - x1)*Math.random()) + x1;
			System.out.println("spawn: " + animal);
			switch(animal){
			case 0: panel.addDeer(height, x, y); break;
				
			case 1: panel.addBear(height, x, y); break;
			
			case 2: int type = (int)(5*Math.random()); panel.addOrc(height, type, x, y); break;
				
			case 3: panel.addKangaroo(height, x, y); break;
				
			}
			
		//Orc orc = new Orc(height, type);
		//
		//orc.setLocation(x,y);
		//panel.add(orc);
		//panel.renew();
			count++;
		}

	}

}
