package start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooleanTimeInverter implements ActionListener {
	boolean b;
	
	public BooleanTimeInverter(boolean bool){
		b = bool;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//b = !b;
		Programma.gameContainer.shooterScreens.get(Programma.gameContainer.screenIndex).isReloadingSnipe = false;
		//System.out.println("Snipe again!!");
	}

}
