package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sound.SoundMixer;
import start.Programma;

public class Meteor implements ActionListener {
	PauseTimer timer;
	int itt = 0;
	
	public Meteor(){
		timer = new PauseTimer(100, this);
		timer.setInitialDelay(0);
	}
	
	public void start(){
		timer.start();
	}
	
	public void pause(){
		timer.stop();
	}
	
	public void resume(){
		timer.restart();
	}
	public void stop(){
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		itt++;
		if(itt == 450){
			SoundMixer.playSoundContinuously("Sound/meteor_second.wav");
		}
		else if(itt == 600){
			Programma.createAndShowGUI("@meteor");
			SoundMixer.stopBackground();
			SoundMixer.playSound("Sound/explosion.wav");
			timer.stop();
			Programma.gameContainer.space.timer.stop();
			
		}
		else if(itt % 20 == 0){
			System.out.println("meteor in: " + itt);
		}

		
	}

}
