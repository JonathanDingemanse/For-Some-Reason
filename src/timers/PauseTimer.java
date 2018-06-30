package timers;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class PauseTimer extends Timer{
	long beginTime;
	long delay;
	long remainingTime;
	ActionListener listener;
	
	
	public PauseTimer(int Delay, ActionListener Listener){
		super(Delay,Listener);
		delay = Delay;
		listener = Listener;
		beginTime = System.currentTimeMillis();
	}
	
	public void pause(){
		//System.out.println("troll " + remainingTime
		
		stop();
		remainingTime =  delay - (int) (System.currentTimeMillis() - beginTime);
		
		//remainingTime =  delay - (int) (System.currentTimeMillis() - beginTime);
		//System.out.println("Pause " + remainingTime);
	}
	
	public void resume(){
		if(remainingTime  > 0){
			setInitialDelay((int)remainingTime);
		}	
		//System.out.println("Resume " + remainingTime);
		restart();
		beginTime = System.currentTimeMillis();
		//remainingTime = 20000;
	}
	public void stop(){
		super.stop();
		//System.out.println("stopped");
		try {
			super.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
