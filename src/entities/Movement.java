package entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import start.ShooterScreen;

public class Movement implements ActionListener{
	
	ShooterScreen screen;
	
	public Movement(ShooterScreen screen){
		this.screen = screen;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for(Animal ani : screen.animals){
			ani.moveFPS();
		}
		screen.renew();
	}

}
