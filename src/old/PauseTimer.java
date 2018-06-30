package old;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class PauseTimer extends Timer{
	long beginTime = System.currentTimeMillis();
	int delay;
	int remainingTime;
	ActionListener listener;
	
	
	public PauseTimer(int Delay, ActionListener Listener){
		super(Delay,Listener);
		delay = Delay;
		listener = Listener;
		beginTime = System.currentTimeMillis();
	}
	
	public void pause(){
		remainingTime =  delay - (int) (System.currentTimeMillis() - beginTime);
		System.out.println(remainingTime);
		super.stop();
	}
	
	public void resume(){
		super.setInitialDelay(remainingTime);
		super.restart();
	}

}
