package components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;
import start.Programma;

public class Message extends JLabel {
	
	public static final int IN = 1023;
	public static final int OUT = 1024;
	public boolean isIncomming;
	Color messageGreen = new Color(144, 238, 144);
	
	
	public Message(String string, int inout){
		super("<html><CENTER>&#32;" + string + "<CENTER><html>", LEFT);
		if(inout == OUT){
			setHorizontalAlignment(RIGHT);
			setText("<html><CENTER>" + string + " " + "&#32;<CENTER><html>");
		}
		setVerticalAlignment(CENTER);
		setOpaque(true);
		setFont(Programma.helvetica48);
		//this.setLayout(new FlowLayout());
		//setSize(string.length()*Programma.paneWidth/120 + 10, Programma.paneHeight/22);
		//Graphics g = this.getGraphics();
		FontMetrics met = this.getFontMetrics(Programma.helvetica48);
		//int height = met.getHeight();
		int width = met.stringWidth(string + " ");
		
		if(Programma.dpiScale > 1.01){
            setSize(width + 15, Programma.paneHeight/22);
        }
        else{
            setSize(width + 3, Programma.paneHeight/22);
        }

		
		if(inout == IN){
			this.setBackground(Color.WHITE);
			isIncomming = true;
		}
		else if(inout == OUT){
			this.setBackground(messageGreen);
		}
	}
	
	public void setY(int y){
		if(isIncomming){
			this.setLocation(Programma.paneWidth/38, y);
		}
		else{
			this.setLocation(37*Programma.paneWidth/38 - this.getWidth(), y);
		}
	}


}
