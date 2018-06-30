package components;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import start.Programma;

public class FSRComponent extends JPanel implements MouseListener {
	
	public FSRComponent(){
		setOpaque(true);
		setLayout(null);
        setBackground(Programma.currentTheme.buttonBgColor);
        setForeground(Programma.currentTheme.foregroundColor);
        addMouseListener(this);
        setBorder(EasyButton.border);
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setBorder(EasyButton.mouseBorder);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setBorder(EasyButton.border);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
