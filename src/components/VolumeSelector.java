package components;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import sound.SoundMixer;
import start.Programma;

public class VolumeSelector extends FSRComponent implements MouseMotionListener {
	JLabel mainText;
	JLabel volumeNumber;
	JLabel volumeNumber2;
	int vPart;
	int type;
	ArrayList<JPanel> bars = new ArrayList<JPanel>();
	public static final int MASTER_VOLUME = 0;
	public static final int MUSIC_VOLUME = 1;
	public static final int SOUND_VOLUME = 2;
	public static final int BACKGROUND_VOLUME = 3;

	
	public VolumeSelector(String text, int width, int height, int kind){
		this.setSize(width, height);
		
		type = kind;
		mainText = new JLabel(text);
		mainText.setSize(width - 20, height/3);
		mainText.setLocation(10, 10);
		mainText.setFont(Programma.helvetica48);
		mainText.setHorizontalAlignment(JLabel.CENTER);
		add(mainText);
		addMouseMotionListener(this);
		
		vPart = 0;

		
		switch(type){
		case 0: volumeNumber = new JLabel(Integer.toString(SoundMixer.masterVolume)); vPart = SoundMixer.masterVolume*10; break;
		case 1: volumeNumber = new JLabel(Integer.toString(SoundMixer.musicVolume)); vPart = SoundMixer.musicVolume*10; break;
		case 2: volumeNumber = new JLabel(Integer.toString(SoundMixer.soundVolume)); vPart = SoundMixer.soundVolume*10; break;
		case 3: volumeNumber = new JLabel(Integer.toString(SoundMixer.backgroundVolume)); vPart = SoundMixer.backgroundVolume*10; break;
		}
		
		volumeNumber.setSize(width/8, height/3);
		volumeNumber.setLocation(8, height/3);
		volumeNumber.setFont(Programma.helvetica64);
		volumeNumber.setHorizontalAlignment(JLabel.CENTER);
		add(volumeNumber);
		
		volumeNumber2 = new JLabel(" ");
		volumeNumber2.setSize(width/8, height/6 - 5);
		volumeNumber2.setLocation(8, height/3);
		volumeNumber2.setFont(Programma.helvetica64);
		volumeNumber2.setHorizontalAlignment(JLabel.CENTER);

		
		int y = height/2;
		int x = width/50;
		
		for(int i = 0; i < 25; i++){
			JPanel r = new JPanel();
			r.setSize(x, (i+1)*y/26);
			r.setLocation(width/8 + 3*i*width/(25*4), 5*height/6 - (i+1)*y/26 - 2);
			if(i*40 < vPart){
				r.setBackground(Color.GREEN);
			}
			else{
				r.setBackground(Color.GRAY);
			}
			bars.add(r);
		}
		
		for(JPanel bar : bars){
			this.add(bar);
		}
		recolor();
		
	}
	public void recolor(){
		setBackground(Programma.currentTheme.buttonBgColor);
        mainText.setForeground(Programma.currentTheme.foregroundColor);
        mainText.setBackground(Programma.currentTheme.buttonBgColor);
        volumeNumber.setBackground(Programma.currentTheme.buttonBgColor);
        volumeNumber.setForeground(Programma.currentTheme.foregroundColor);
        volumeNumber2.setBackground(Programma.currentTheme.buttonBgColor);
        volumeNumber2.setForeground(Programma.currentTheme.foregroundColor);
        setBorder(EasyButton.border);
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseEntered(e);
		add(volumeNumber2);
		
	}
	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseExited(e);
		remove(volumeNumber2);
		
		for(JPanel bar : bars){
			if( bar.getLocation().x < getWidth()/8 + 3*getWidth()*vPart/4000 ){
				bar.setBackground(Color.GREEN);
			}
			else{
				bar.setBackground(Color.GRAY);
			}
			bar.repaint();
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e){
		//super.mouseReleased(e);
		//System.out.println("volume: " + 100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
		switch(type){
		case 0: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setMasterVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setMasterVolume(0);
		}
		else{
			SoundMixer.setMasterVolume(100);
		}
			volumeNumber.setText(Integer.toString(SoundMixer.masterVolume)); vPart = SoundMixer.masterVolume*10; break;
		case 1: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setMusicVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setMusicVolume(0);
		}
		else{
			SoundMixer.setMusicVolume(100);
		}
			volumeNumber.setText(new Integer(SoundMixer.musicVolume).toString()); vPart = SoundMixer.musicVolume*10; break;
		
		case 2: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setSoundVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setSoundVolume(0);
		}
		else{
			SoundMixer.setSoundVolume(100);
		}
			volumeNumber.setText(new Integer(SoundMixer.soundVolume).toString()); vPart = SoundMixer.soundVolume*10; break;
		
		case 3: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setBackgroundVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setBackgroundVolume(0);
		}
		else{
			SoundMixer.setBackgroundVolume(100);
		}
			volumeNumber.setText(new Integer(SoundMixer.backgroundVolume).toString()); vPart = SoundMixer.backgroundVolume*10; break;
			
		}
		mouseMoved(e);
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		switch(type){
		case 0: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setMasterVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setMasterVolume(0);
		}
		else{
			SoundMixer.setMasterVolume(100);
		}
			 break;
			 
			 
		case 1: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setMusicVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setMusicVolume(0);
		}
		else{
			SoundMixer.setMusicVolume(100);
		}
			break;
			
		
		case 2: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setSoundVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setSoundVolume(0);
		}
		else{
			SoundMixer.setSoundVolume(100);
		}
			break;
		
			
		case 3: if(e.getX() >= getWidth()/8 && e.getX() <= 7*getWidth()/8){
				SoundMixer.setBackgroundVolume(100*(e.getX()-getWidth()/8)/(3*getWidth()/4));
			}
		else if(e.getX() < getWidth()/8){
			SoundMixer.setBackgroundVolume(0);
		}
		else{
			SoundMixer.setBackgroundVolume(100);
		}
			break;
		}
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		for(JPanel bar : bars){
			if( bar.getLocation().x <= getWidth()/8 + 3*getWidth()*vPart/4000 && bar.getLocation().x  >= e.getX()  ){
				bar.setBackground(Color.RED);
			}
			else if(bar.getLocation().x > getWidth()/8 + 3*getWidth()*vPart/4000 && bar.getLocation().x  < e.getX() ){
				bar.setBackground(Color.BLUE);
			}
			else if( bar.getLocation().x <= getWidth()/8 + 3*getWidth()*vPart/4000 ){
				bar.setBackground(Color.GREEN);
			}
			else{
				bar.setBackground(Color.GRAY);
			}
			bar.repaint();
		}
		
		if(e.getX() <= getWidth()/8){
			volumeNumber2.setLocation(getWidth()/8 - volumeNumber2.getWidth()/2, 5*getHeight()/6);
			volumeNumber2.setText("0");
		}
		else if(e.getX() >= 7*getWidth()/8){
			volumeNumber2.setLocation(7*getWidth()/8 - volumeNumber2.getWidth()/2, 5*getHeight()/6);
			volumeNumber2.setText("100");
		}
		else{
			volumeNumber2.setLocation(e.getX() - volumeNumber2.getWidth()/2, 5*getHeight()/6);
			volumeNumber2.setText(new Integer(100*(e.getX()-getWidth()/8)/(3*getWidth()/4) ).toString() );
		}
		
		
		
		
	}

}
