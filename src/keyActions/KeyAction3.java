package keyActions;

import java.awt.event.KeyEvent;

import javax.swing.*;

public class KeyAction3 extends JPanel{
	public static final int NAV = 511;
	public static final int GAME = 512;
	public static final int GAME_RESTRICTED = 513;
	
	public KeyAction3(int gameOrNav){
    	setLayout(null);
    	setBounds(-11,-11,2,2);
		
		KeyAction musicKey = new KeyAction(KeyEvent.VK_M);
    	add(musicKey);
    	
    	KeyAction R = new KeyAction(KeyEvent.VK_R);
    	add(R);
    	//KeyAction windoos = new KeyAction(KeyEvent.VK_WINDOWS);
		//add(windoos);
    	
    	if(gameOrNav == NAV){
    		//KeyAction resume = new KeyAction(KeyEvent.VK_R);
    		//add(resume);
    		
    		KeyAction play = new KeyAction(KeyEvent.VK_P);
    		add(play);

			KeyAction quit = new KeyAction(KeyEvent.VK_Q);
			add(quit);
    	}
    	else if(gameOrNav == GAME){
    		KeyAction esc = new KeyAction(KeyEvent.VK_ESCAPE);
			add(esc);
    		
    		KeyAction L = new KeyAction(KeyEvent.VK_L);
    		add(L);
    		
    		KeyAction one = new KeyAction(KeyEvent.VK_1);
    		add(one);
    		KeyAction numpadOne = new KeyAction(KeyEvent.VK_NUMPAD1);
    		add(numpadOne);
    		
    		KeyAction two = new KeyAction(KeyEvent.VK_2);
    		add(two);
    		KeyAction numpadTwo = new KeyAction(KeyEvent.VK_NUMPAD2);
    		add(numpadTwo);
    		
    		KeyAction three = new KeyAction(KeyEvent.VK_3);
    		add(three);
    		KeyAction numpadThree = new KeyAction(KeyEvent.VK_NUMPAD3);
    		add(numpadThree);
    		
    		KeyAction four = new KeyAction(KeyEvent.VK_4);
    		add(four);
    		KeyAction numpadFour = new KeyAction(KeyEvent.VK_NUMPAD4);
    		add(numpadFour);
    		
    		KeyAction a = new KeyAction(KeyEvent.VK_A);
    		add(a);
    		KeyAction left = new KeyAction(KeyEvent.VK_LEFT);
    		add(left);
    		KeyAction kp_left = new KeyAction(KeyEvent.VK_KP_LEFT);
    		add(kp_left);
    		
    		KeyAction w = new KeyAction(KeyEvent.VK_W);
    		add(w);
    		KeyAction up = new KeyAction(KeyEvent.VK_UP);
    		add(up);
    		KeyAction kp_up = new KeyAction(KeyEvent.VK_KP_UP);
    		add(kp_up);
    		
    		KeyAction d = new KeyAction(KeyEvent.VK_D);
    		add(d);
    		KeyAction right = new KeyAction(KeyEvent.VK_RIGHT);
    		add(right);
    		KeyAction kp_right = new KeyAction(KeyEvent.VK_KP_RIGHT);
    		add(kp_right);
    		
    		KeyAction s = new KeyAction(KeyEvent.VK_S);
    		add(s);
    		KeyAction down = new KeyAction(KeyEvent.VK_DOWN);
    		add(down);
    		KeyAction kp_down = new KeyAction(KeyEvent.VK_KP_DOWN);
    		add(kp_down);
    		
    		KeyAction enter = new KeyAction(KeyEvent.VK_ENTER);
    		add(enter);
    		
    	}
    	else if(gameOrNav == GAME_RESTRICTED){
			KeyAction L = new KeyAction(KeyEvent.VK_L);
			add(L);

			KeyAction enter = new KeyAction(KeyEvent.VK_ENTER);
			add(enter);
		}
		
	}

}
