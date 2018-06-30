package start;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import components.ColorText;
import components.EasyImage;
import layout.Theme;

public class Credits implements ActionListener {
	
	public static EasyImage background;
	public static Timer timer;
	public static int index = 0;
	public static Container pane;

	public static ColorText ult;
	//public static JLabel ult = new JLabel("<html><CENTER>The ultimate escape game!!<CENTER><html>", JLabel.CENTER);
	//public static JLabel ult2 = new JLabel("<html><CENTER>The ultimate escape game!!<CENTER><html>", JLabel.CENTER);
	public static ColorText job;
	//public static JLabel job1 = new JLabel("<html><CENTER>Coding & Design<CENTER><html>", JLabel.CENTER);
	//public static JLabel job2 = new JLabel("<html><CENTER>Coding & Design<CENTER><html>", JLabel.CENTER);
	public static ColorText name;
	//public static JLabel name1 = new JLabel("<html><CENTER>Jonathan<br>Dingemanse<CENTER><html>", JLabel.CENTER);
	//public static JLabel name2 = new JLabel("<html><CENTER>Jonathan<br>Dingemanse<CENTER><html>", JLabel.CENTER);
		
	public Credits(Container screen){
		timer = new Timer(1000, this);
		timer.setInitialDelay(3500);
		pane = screen;
	}
	
	public void run(){
		index = 0;

		if(timer.isRunning()){
			timer.stop();
		}
		timer.restart();
		index = 0;

		ult = new ColorText("<html><CENTER>The ultimate escape game!!<CENTER><html>", Theme.cycleColorLeft(Programma.currentTheme.title2Color),  Theme.cycleColorLeft(Programma.currentTheme.title1Color).darker(), Programma.helvetica20, 4*Programma.paneWidth/1920);
		ult.setSize(Programma.paneWidth/2, Programma.paneHeight/4);
		ult.setLocation(Programma.paneWidth/4, 3*Programma.paneHeight/4);

		/*ult.setForeground(Theme.cycleColorLeft(Programma.currentTheme.title2Color));
		ult.setOpaque(false);
		ult.setFont(Programma.helvetica20);
		ult.setLocation(Programma.paneWidth/4, 3*Programma.paneHeight/4);
		ult.setSize(Programma.paneWidth/2, Programma.paneHeight/4);


		ult2.setForeground(Theme.cycleColorLeft(Programma.currentTheme.title1Color).darker());
		ult2.setOpaque(false);
		ult2.setFont(Programma.helvetica20);
		ult2.setLocation(Programma.paneWidth/4, 3*Programma.paneHeight/4);
		ult2.setSize(Programma.paneWidth/2 + 4*Programma.paneWidth/1920, Programma.paneHeight/4 + 4*Programma.paneWidth/1920);*/

		job = new ColorText("<html><CENTER>Coding & Design<CENTER><html>", Theme.cycleColorRight(Programma.currentTheme.title1Color), Theme.cycleColorRight(Programma.currentTheme.title2Color).darker(), Programma.helvetica16, 5*Programma.paneWidth/1920);
		job.setSize(3*Programma.paneWidth/4, 3*Programma.paneHeight/4);
		job.setLocation(Programma.paneWidth/4, Programma.paneHeight/4);

		name = new ColorText("<html><CENTER>Jonathan<br>Dingemanse<CENTER><html>", Programma.currentTheme.title1Color, Programma.currentTheme.title2Color, Programma.helvetica8, 5*Programma.paneWidth/1920);
		name.setSize(Programma.paneWidth, Programma.paneHeight/2);
		name.setLocation(0, Programma.paneHeight/8);

		/*name1.setFont(Programma.helvetica8);
		name2.setFont(Programma.helvetica8);
		name1.setLocation(0, Programma.paneHeight/8);
		name2.setLocation(5*Programma.paneWidth/1920, Programma.paneHeight/8 + 5*Programma.paneWidth/1920);
		name1.setSize(Programma.paneWidth, Programma.paneHeight/2);
		name2.setSize(Programma.paneWidth, Programma.paneHeight/2);
		name1.setForeground(Programma.currentTheme.title1Color);
		name2.setForeground(Programma.currentTheme.title2Color);
		
		/*job1.setLocation(Programma.paneWidth/4, Programma.paneHeight/4);
		job2.setLocation(Programma.paneWidth/4 + 5*Programma.paneWidth/1920, Programma.paneHeight/4 + 5*Programma.paneWidth/1920);
		job1.setSize(3*Programma.paneWidth/4, 3*Programma.paneHeight/4);
		job2.setSize(3*Programma.paneWidth/4, 3*Programma.paneHeight/4);
		job1.setForeground(Theme.cycleColorRight(Programma.currentTheme.title1Color));
		job2.setForeground(Theme.cycleColorRight(Programma.currentTheme.title2Color).darker());
		job1.setFont(Programma.helvetica16);
		job2.setFont(Programma.helvetica16);*/
	}
	
	
	public static void main(){
		background = new EasyImage("Images/crashing plane.jpg", Programma.paneWidth, Programma.paneHeight);
		background.setLocation(0, 0);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(index){
		
		case 0:
			pane.removeAll();
			pane.add(Programma.title);
			pane.add(background);
			pane.repaint();
			break;
		
		case 1:
			pane.remove(background);
			pane.add(ult);
			pane.add(background);
			pane.repaint();
			break;
		
		case 4:
			pane.removeAll();
			ult.setLocation(0,0);
			ult.setText("<html><CENTER>Made by:<CENTER><html>");
			pane.add(name);
			pane.add(job);
			pane.add(ult);
			pane.add(background);
			pane.repaint();
			break;

		case 6:
			name.setText("<html><CENTER>Albert<br>de Kogel<CENTER><html>");
			job.setText("<html><CENTER>Storyline<CENTER><html>");
			pane.repaint();
			break;
		
		case 8:
			name.setText("<html><CENTER>Corn&eacute;<br>Haasjes<CENTER><html>");
			job.setText("<html><CENTER>eSpeak<CENTER><html>");
			pane.repaint();
			break;
		
		case 10:
			pane.removeAll(); ult.setLocation(Programma.paneWidth/4,0); ult.setText("<html><CENTER>Music by:<CENTER><html>");
			name.setText("<html><CENTER>Coldplay<br>Hans Zimmer<br>K-391<br>Evard Grieg<br>Eiffel 65<br>De Raggende Mannen<br>Camille Saint-Sans<br>Jonathan Dingemanse<CENTER><html>");
			name.text1.setFont(Programma.helvetica20);
			name.text2.setFont(Programma.helvetica20);
			name.setSize(Programma.paneWidth, 3*Programma.paneHeight/4);
			name.setLocation(0, Programma.paneHeight/8);
			pane.add(name);
			pane.add(ult);
			pane.add(background);
			pane.repaint();
			break;
		
		case 12:
			ult.setText("<html><CENTER>Tested by:<CENTER><html>");
			name.setText("<html><CENTER>Bart-Wim Stunnenberg<br>Mark Wehrheim<br>Matthijs Dingemanse<br>Corn&eacute; Haasjes<br>Albert de Kogel<CENTER><html>");
			pane.repaint();
			break;
		
		case 14: Programma.gameContainer.continueGame();
		timer.stop();
		}
		index++;
	}

}
