package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import start.Programma;


public class EasyCombo extends JPanel
						implements ActionListener, MouseListener, PopupMenuListener {
	private JComboBox list;
	private JComboBox cb;
	JLabel label;
	int boxHeight;
	
	public EasyCombo(String[] string){
		
			//setLayout(null);
	        
	        
	        //Create the combo box, select the item at index 4.
	        //Indices start at 0, so 4 specifies the pig.
	        list = new JComboBox(string);
	        list.setSelectedIndex(0);
	        list.addActionListener(this);
	        list.setOpaque(true);
	        list.setSize(100,30);
	        //list.setBackground(Color.pink);
	        list.setVisible(true);
	        list.addPopupMenuListener(this);
	        setSize(100,30);
	        add(list);
	        return;

	        	    }
	
	public EasyCombo(String[] string, int width, int height ){
		
		//setLayout(null);
		setOpaque(true);
		setLayout(null);
        addMouseListener(this);
        //Create the combo box, select the item at index 4.
        //Indices start at 0, so 4 specifies the pig.
		setFocusable(true);
		
        list = new JComboBox(string);
        
        //list.setLayout(null);
        //list.setLayout(getLayout());
        //list.setFocusable(true);
        list.setSelectedIndex(0);
        list.addActionListener(this);
        list.setOpaque(true);
        list.setSize(width,height);
        list.setVisible(true);
		list.addPopupMenuListener(this);
        //list.addMouseListener(this);
        //list.addPopupMenuListener(this);
        
        setSize(width,height);
        add(list);
        //setOpaque(true);
        //setVisible(true);
        return;
        	    }
	
	public EasyCombo(String[] string, int width, int height, Color color, Font font ){
		
		//setLayout(null);

        list = new JComboBox(string);
        //list.setLayout(getLayout());
        //list.setFocusable(true);
        list.setSelectedIndex(0);
        list.addActionListener(this);
        list.setOpaque(true);
        list.setSize(width,height);
        setSize(width,height);
        list.setBackground(color);
        list.setFont(font);
        list.setVisible(true);
        add(list);
        //setOpaque(true);
        //setVisible(true);
        //list.setSelectedIndex(0);
        return;
        }
	
	public void setComboFont(Font font){
		list.setFont(font);
	}
	
	public void setSelectedIndex(int index){
		list.setSelectedIndex(index);
	}
	
	public void setBoxSize(int width, int height){
		this.setSize(width, height);
		list.setSize(6*width/7, 2*height/7);
		list.setLocation(width/2 - list.getWidth()/2, height/2);
		boxHeight = height;
	}
	public void setText(String string){
		label = new JLabel( string, JLabel.CENTER);
		label.setSize(this.getWidth() - 20, 8*this.getHeight()/20 - 20);
		label.setLocation(10,10 + this.getHeight()/10);
		label.setFont(Programma.helvetica32);
		add(label);
	}

	public void actionPerformed(ActionEvent e) {
		cb = (JComboBox)e.getSource();
	    String string = (String)cb.getSelectedItem();
	    //list.setLayout(null);
	    list.setSelectedIndex(list.getSelectedIndex());
	    Programma.createAndShowGUI(string);
	    
	    if(this.getMousePosition() == null){
	    	setBorder(EasyButton.border);
	    }
	    System.out.println("difficulty: " + Programma.difficultyLevel);
	}
	public void setSelected(boolean b){
		if(b){
			setBorder(EasyButton.mouseBorder);
		}
		else{
			setBorder(EasyButton.border);
		}
	}
	
	public void recolor(){
		setBackground(Programma.currentTheme.buttonBgColor);
		label.setBackground(Programma.currentTheme.buttonBgColor);
		label.setForeground(Programma.currentTheme.foregroundColor);
		//list.setBackground(Programma.currentTheme.buttonBgColor);
		//list.setForeground(Programma.currentTheme.foregroundColor);
	    setBorder(EasyButton.border);
	    repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setBorder(EasyButton.mouseBorder);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if( getMousePosition() == null && !list.isPopupVisible()){
			setBorder(EasyButton.border);
		}
		
		
		//setBorder(EasyButton.border);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(Programma.currentTheme.buttonBgPrColor);
		list.showPopup(); 
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(Programma.currentTheme.buttonBgColor);
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		setSelected(false);
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
		setSelected(false);
	}
}
