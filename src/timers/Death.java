package timers;
import start.Programma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Death implements ActionListener {
	String reasonOfDeath;
	PauseTimer timer;
	public boolean isDying;
	
	public Death(String reason, int millis){
		isDying = true;
		reasonOfDeath = reason;
		timer = new PauseTimer(millis, this);
		timer.setInitialDelay(millis);
		timer.setRepeats(false);
		timer.restart();
	}
	public void die(){
		Programma.createAndShowGUI("*" + reasonOfDeath);
	}
	public void dontDie(){
		timer.stop();
		isDying = false;
	}
	public void pauseDying(){
		timer.pause();
	}
	public void resumeDying(){
		timer.resume();
	}
	public void restart(){
		timer.restart();
	}
	public void stop(){
		timer.stop();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Programma.createAndShowGUI("*" + reasonOfDeath);
		
	}
	
}
