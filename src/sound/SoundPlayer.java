package sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Timer;
import start.Programma;
import java.math.*;

public class SoundPlayer implements ActionListener {
    
    //private ArrayList<String> musicFiles;
    //private int currentSongIndex;
    Clip clip;
    //Clip bufferClip;
    long clipTime;
    String currentSound = " ";
    int songTime = 0;
    //Timer randomTimer;
    boolean isOpen;
    boolean isContinuously;
    File soundFile;
    //final ArrayList<String> musicNames;
    //final int[] musicTimes = {251,99,219,161,565,226,173,123,117};
    public ArrayList<String> soundsList = new ArrayList<String>(); 
    FloatControl gainControl;
    int soundVolume;
    String secondSong;
    Timer timer;
    int type;
    public static final int SOUND = 0;
    public static final int BACKGROUND = 1;

    
    
    public SoundPlayer(int type){
    	this.type = type;

    	loadSound("Sound/silence.wav");
    }
    	
    private boolean loadSound(String fileName){
        try{
        	soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            soundFile.delete();
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			setRightVolume();
            //setVolume(soundVolume);
            //FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
            //gainControl.setValue(-10);
            //volumeControl.setValue(100);
            isOpen = clip.isOpen();
            clipTime = 0;
            return true;
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
       
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

  
    public void run(){}


    	
    public void playSound(String fileName){
    	if(clip.isRunning()){
    		stop();
    	}
    	if(loadSound(fileName)){
			setRightVolume();
    		clip.start();
			setRightVolume();
    	}
    	else{
    		System.out.println("file not found");
    	}
    	isContinuously = false;
    	currentSound = fileName;

    }
    
    
    public void playSoundContinuously(String fileName){
    	if(clip.isRunning()){
    		stop();
    	}
    	if(loadSound(fileName)){
			setRightVolume();
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    	}
    	else{
    		System.out.println("file not found");
    	}
    	isContinuously = true;
    	currentSound = fileName;
    }
    
    public void play2SoundsSecondContinuously(String fileName0, String fileName1){
    	if(clip.isRunning()){
    		stop();
    	}
    	if(loadSound(fileName0)){
			setRightVolume();
    		clip.start();
    	}
    	else{
    		System.out.println("file not found");
    	}
    	isContinuously = true;
    	currentSound = fileName0;
    	secondSong = fileName1;
    	
    	timer = new Timer((int)clip.getMicrosecondLength()/1000, this);
    	timer.setInitialDelay((int)clip.getMicrosecondLength()/1000);
    	System.out.println("Delay" + (int)clip.getMicrosecondLength()/1000);
    	timer.setRepeats(false);
    	timer.start();
    }
    
    
    public void playSoundContinuouslyFromPosition(String fileName, long position){
    	if(clip.isRunning()){
    		stop();
    	}
    	if(loadSound(fileName)){
			setRightVolume();
    		if(position > 0){
    			clip.setMicrosecondPosition(position);
    		}
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    	}
    	else{
    		System.out.println("file not found");
    	}
    	isContinuously = true;
    	currentSound = fileName;
    }
    
    public void playMultipleSounds(String[] sounds){
    	isContinuously = false;
    	if(clip.isRunning()){
    		stop();
    	}
    	playSound(sounds[0]);
    	for(int i = 1; i < sounds.length; i++){
    		soundsList.add(sounds[i]);
    	}
    	timer = new Timer((int)clip.getMicrosecondLength()/1000, this);
    	timer.setInitialDelay((int)clip.getMicrosecondLength()/1000);
    	timer.setRepeats(false);
    	timer.start();
    }
    
    
    /*
    public void playRandom() {
    	//isRandom = true;
    	
    	int songNumber;
    	if(musicNames.size() > 1){
    		songNumber = (int )(Math.random() * (randomMusic.size() ));
    	}
    	else{
    		songNumber = 0;
    	}
    	
    	/*for(int i = 0; i < musicNames.size(); i++){
    		if(randomMusic.get(songNumber) == musicNames.get(i)){
    			songTime = musicTimes[i];
    			//System.out.println(songTime);
    			System.out.println(i);
    			break;
    		}
    	} 
    	
    	playSound("Music/" + randomMusic.get(songNumber) + ".wav");
    	clip.start();
    	//System.out.println(songTime);
    	
    	currentSong = randomMusic.get(songNumber);
    	randomTimer = new Timer((int)(clip.getMicrosecondLength()/1000 +1),this);
    	randomTimer.setInitialDelay((int)(clip.getMicrosecondLength()/1000 +1));
    	randomTimer.setRepeats(false);
    	randomTimer.restart();
    	//Programma.createAndShowGUI("settings");
    	
    	//System.out.println(randomTimer.getDelay());
	 	//System.out.println(randomTimer.getInitialDelay());
    	
    }
    
    public void changeRandom(String...songs){
    	randomMusic.clear();
    	for(String song : songs){ randomMusic.add(song);}
    }
    public void selectRandom(String song){
    	/*
    	for(int i = 0; i < musicNames.size(); i++){
    		if(song == musicNames.get(i)){
    			songTime = musicTimes[i];
    			//System.out.println(songTime);
    			//System.out.println(i);
    			break;
    		}
    	} 
    	
    	playSound("Music/" + song + ".wav");
    	clip.start();
    	
    	
    	currentSong = song;
    	randomTimer = new Timer((int)(clip.getMicrosecondLength()/1000 +1),this);
    	randomTimer.setInitialDelay((int)(clip.getMicrosecondLength()/1000 +1));
    	randomTimer.setRepeats(false);
    	randomTimer.restart();
    }
    
    public void select(String songName){
    	randomTimer.stop();
    	isRandom = false;
    	currentSong = songName;
    	playSound("Music/" + songName + ".wav");
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void newMusic(String songName, long time){
    	randomTimer.stop();
    	System.out.println(randomTimer.isRunning());
    	isRandom = false;
    	currentSong = songName;
    	playSound("Music/" + songName + ".wav");
    	clip.setMicrosecondPosition(time);
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    } */
   
    public void stop(){
    	clip.stop();
    	clip.flush();
    	clip.drain();
    	clip.close();
    	System.gc();
    }
    
    public void pause(){
    	clipTime =  clip.getMicrosecondPosition();
    	clip.stop();
    	
    }
    
    public void resume(){
    	clip.setMicrosecondPosition(clipTime);
    	if(isContinuously){
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    	}
    	else{
    		clip.start();
    	}
    	
    	
    } 
    
    public void terminate(){
    	System.exit(0);
    }
    
    public long getClipPosition(){
    	return clip.getMicrosecondPosition();
    }
    public String getCurrentSound(){
    	return currentSound;
    }

    public boolean isRunning(){
    	if(clip != null){
    		return clip.isRunning();
    	}
    	else{
    		return false;
    	}
    }
    
    public long getSoundLength(){
    	return clip.getMicrosecondLength();
    }
    
    public long getMicrosToGo(){
    	return clip.getMicrosecondLength() - clip.getMicrosecondPosition();
    }
    
    public void setGain(int gain){
    	//System.out.println("\t" + gain);
    	gainControl.setValue(gain);
    	/*if(gain >= 6 && gain <= -70){
    		
    	}
    	else if(gain < 6){
    		gainControl.setValue(6);
    	}
    	else{
    		gainControl.setValue(-70);
    	}*/
    	
    }
    
    public void setVolume(int volume){
    	soundVolume = volume;
    	if(clip != null){
        	setGain((int)( (65/Math.log10(11)) * Math.log10(volume/1000 + 1) -60 ));   		
    	}

    }

	public void setRightVolume(){
		if(type == SOUND){
			setVolume(SoundMixer.soundVolume*SoundMixer.masterVolume*SoundMixer.soundRelativeVolume/100);
		}
		else if(type == BACKGROUND){
			setVolume(SoundMixer.backgroundVolume*SoundMixer.masterVolume*SoundMixer.backgroundRelativeVolume/100);
		}
	}
    
    public void increaseVolume(int step){
    	soundVolume += step;
    	setVolume(soundVolume);
    }
    

    
    public void actionPerformed(ActionEvent e){
    	
    	if(isContinuously){
    		this.playSoundContinuously(secondSong);
    	}
    	else{
    		playSound(soundsList.get(0));
    		soundsList.remove(0);
    		if(soundsList.size() > 0){
    			timer = new Timer((int)clip.getMicrosecondLength()/1000, this);
    	    	timer.setInitialDelay((int)clip.getMicrosecondLength()/1000);
    	    	timer.setRepeats(false);
    	    	timer.start();
    		}
    		
    	}
    } 
    
    
    //public.get
    


    /*

    public static void pause(){
    	
    	clip.stop();
    	
    }
    */


}



	
	
	
	
	
	
	/*
	private static void startScreenMusic(){
		new Thead(){
			AudioPlayer ap = AudioPlayer.player;
			
			try{
				AudioStream musicStream = new AudioStream(new FileImputStream("Music/Moderne_Rondo.wav"));
				AudioData themeMusic = musicStream.getData();
				ContinuousAudioDataStream loop = new ContinuousAudioDataStream(themeMusic);
				ap.start(loop);
			}catch(IOException error){}
		}
		}
		*/
		
		
		
	
	

