package start;

import components.ColorText;
import javafx.scene.layout.Pane;
import layout.Theme;
import sound.SoundMixer;

import javax.swing.*;
import java.awt.*;

public class ProgrammaRunTool implements Runnable {
	 static JFrame loadFrame;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		loadFrame = new JFrame("For Some Reason is loading");
		loadFrame.setBackground(Color.BLACK);
		loadFrame.setUndecorated(true);
		loadFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		loadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loadFrame.setLayout(null);
		loadFrame.setVisible(true);

		//loadFrame.repaint();

		Programma.paneWidth = loadFrame.getWidth();
		Programma.paneHeight = loadFrame.getHeight();

		Programma.loadFonts();
		Programma.deriveFontsLoadScreen();
		ColorText loadTitle = new ColorText("<html><center> For Some <br> Reason <center><html>", Color.RED, Theme.darkOrange, Programma.helvetica8, 7*Programma.paneWidth/1920 );
		loadTitle.setSize(Programma.paneWidth, Programma.paneHeight);
		//loadTitle.setLocation(0, 0);
		loadTitle.setBackground(Color.BLACK);
		loadTitle.setOpaque(true);

		loadFrame.add(loadTitle);
		loadFrame.repaint();
		//SoundMixer.begin();

		main(null);
	}
	public static void main(String[] args){
		Programma.main(null);		
		//System.out.println("sdlfk");
	}

}
