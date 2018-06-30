package keyActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.JPanel;


public class KeyAction extends JPanel{
	
	public KeyAction(int key ){
		InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = this.getActionMap();
		im.put(KeyStroke.getKeyStroke(key, 0), "troll");
		KeyAction2 keyAc = new KeyAction2(key);
		
		am.put("troll", keyAc);
    	setLayout(null);
    	setBounds(0,0,0,0);
	}


}
