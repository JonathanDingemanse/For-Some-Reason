package start;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Programma;
import components.*;

public class WordScreen extends JPanel {
	public JLabel label;
	public EasyImage picture;
	public static EasyImage explosion;
	public static EasyImage waterSplash;
	public static EasyImage bloodSplash;

	
	public WordScreen(String text){
		this.setSize(Programma.paneWidth, Programma.paneHeight);
		this.setLocation(0,0);
		this.setLayout(null);
		
		//bloodSplash = new EasyImage("Images/blood-splash.png", Programma.paneWidth, Programma.paneHeight);
		
		label = new JLabel(text, JLabel.CENTER);
		label.setLayout(null);
		if(text.length() > 7){
			label.setFont(Programma.helvetica8);
		}
		else{
			label.setFont(Programma.helvetica4);
		}
		label.setSize(Programma.paneWidth, Programma.paneHeight);
		label.setLocation(0,0);
		Programma.continueButton.setRightLayout();
		add(Programma.continueButton);
		this.add(label);
		this.repaint();
	}
	
	public static void main(String[] args){
		explosion = new EasyImage("Images/explosion_minimal.png", Programma.paneWidth, Programma.paneHeight);
		waterSplash = new EasyImage("Images/water-splash.png", Programma.paneWidth, Programma.paneHeight);
		bloodSplash = new EasyImage("Images/blood-splash.png", Programma.paneWidth, Programma.paneHeight);
	}

	public void setText(String text){
		label.setText(text);
		label.repaint();
		this.repaint();
	}
	
	public void setTextColor(Color color){
		label.setForeground(color);
		label.repaint();
		this.repaint();
	}

	public void setTextFont(Font font){
		label.setFont(font);
		label.repaint();
		this.repaint();
	}
	
	public void addExplosion(){
		picture = explosion;
		this.add(picture);
		this.repaint();
	}
	
	public void addWater(){
		picture = waterSplash;
		this.add(picture);
		this.repaint();
	}
	
	public void addBlood(){
		picture = bloodSplash;
		this.add(picture);
		this.repaint();
	}
	
	public void addContinue(){
		Programma.continueButton.setRightLayout();
		Programma.continueButton.repaint();
		this.removeAll();
		this.add(Programma.continueButton);
		add(label);
		if(picture != null){
			add(picture);
		}
		this.repaint();
	}

}
