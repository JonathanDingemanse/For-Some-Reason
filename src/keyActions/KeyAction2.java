package keyActions;

import java.awt.event.ActionEvent;

import start.Launcher;
import start.Programma;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;


public class KeyAction2 extends AbstractAction {
	int key;

    public KeyAction2(int Key) {
    	key = Key;
    }

    public static void doKey(int Key){
    	
    	switch(Key){
    	case KeyEvent.VK_ESCAPE:
    		//System.out.println("Escape button pressed, is load screen?: " + Programma.gameContainer.isLoadScreen);
    		if(!Programma.gameContainer.isLoadScreen){
        	    Programma.createAndShowGUI("escape");
        	}
    		break;
    		
    	case KeyEvent.VK_M:
    		Programma.createAndShowGUI("enableMusic");
    		break;
    		
    	case KeyEvent.VK_R: 
    		if(Programma.isDieScreen){
    			Programma.createAndShowGUI("resumeAfterDeath"); 
    		}
    		else if(Programma.isNavScreen){
    			Programma.createAndShowGUI("resume"); 
    		}
    		else{
    			Programma.gameContainer.reloadShooter();
    		}
    		break;
    	
    	case KeyEvent.VK_P: Programma.createAndShowGUI("play"); break;

    	case KeyEvent.VK_Q: Launcher.terminate(); break;
    	
    	case KeyEvent.VK_L: Programma.gameContainer.lockScopeShooter(); break;

    	case KeyEvent.VK_1: 
		case KeyEvent.VK_2: 
		case KeyEvent.VK_3:
		case KeyEvent.VK_4:
		case KeyEvent.VK_NUMPAD1:
		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_NUMPAD3:
		case KeyEvent.VK_NUMPAD4:
		
		case KeyEvent.VK_W: 
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
		case KeyEvent.VK_D: 
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
		case KeyEvent.VK_S: 
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
		case KeyEvent.VK_A: 
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT: Programma.gameContainer.runKey(Key); break;
		
		case KeyEvent.VK_ENTER: Programma.createAndShowGUI("continue");
    	}
    }
    
    
    public void actionPerformed(ActionEvent e) {
    	doKey(key);
    }
}


