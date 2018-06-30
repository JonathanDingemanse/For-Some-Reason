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

public class MusicPlayer implements ActionListener {
    
    //private ArrayList<String> musicFiles;
    //private int currentSongIndex;
    Clip clip;
    //Clip bufferClip;
    long clipTime;
    String currentSong = "FSR_theme";
    int songTime = 0;
    Timer randomTimer;
    boolean isOpen;
    boolean isRandom = true;
    File soundFile;
    final ArrayList<String> musicNames;
    //final int[] musicTimes = {251,99,219,161,565,226,173,123,117};
    public ArrayList<String> randomMusic;
    FloatControl gainControl;
    int soundVolume;
   
    
    public MusicPlayer(String... files){
        //musicFiles = new ArrayList<String>();
        musicNames = new ArrayList<String>();
        randomMusic = new ArrayList<String>();
        
        playSound("Sound/silence.wav");


       // musicTimes = ;
        //musicTimes.set(173,1);
        //for(String file : files)
            //musicFiles.add("Music/" + file + ".wav");
        for(String file : files)
            musicNames.add(file);
    }
    	
    
    private void playSound(String fileName){
        try {
			soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            soundFile.delete();
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            if(clip != null){
            	clip.stop();
            }
            clip = (Clip) AudioSystem.getLine(info);

            clip.open(ais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			setVolume(SoundMixer.masterVolume*SoundMixer.musicVolume);
            //setVolume(SoundMixer.masterVolume*SoundMixer.musicVolume);
            //FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
            //gainControl.setValue(6);
            //volumeControl.setValue(100);
            //isOpen = clip.isOpen();
            clipTime = 0;
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
       
        }catch(Exception e){
            e.printStackTrace();
        }
    }

  
    public void run() {
    	changeRandom("Clocks","FSR_theme","I'm Blue","In the Hall of the Mountain King","introduction et rondo capriccioso","Kinessa Song","Pirates Of The Caribbean Theme Song","poep in je hoofd","Ranger","Viva_La_Vida", "K_391_Summertime");
		selectRandom("FSR_theme");
	 	
		/*
    	int songNumber = (int )(Math.random() * ((musicFiles.size())));
    	playSound(musicFiles.get(songNumber));
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    	currentSong = musicNames.get(songNumber); */
    }
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
    	} */
    	
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
    public void selectRandom(String song,  long... time){
    	/*
    	for(int i = 0; i < musicNames.size(); i++){
    		if(song == musicNames.get(i)){
    			songTime = musicTimes[i];
    			//System.out.println(songTime);
    			//System.out.println(i);
    			break;
    		}
    	} */
    	
    	playSound("Music/" + song + ".wav");
    	if(time.length == 1){
    		clip.setMicrosecondPosition(time[0]);
    	}
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
    	stop();
    	playSound("Music/" + songName + ".wav");
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    	//System.out.println("Level " + clip.getLevel());
    }
    
    public void newMusic(String songName, long time){
    	randomTimer.stop();
    	System.out.println(randomTimer.isRunning());
    	isRandom = false;
    	currentSong = songName;
    	playSound("Music/" + songName + ".wav");
    	clip.setMicrosecondPosition(time);
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
   
    public void stop(){
    	//clipTime =  clip.getMicrosecondPosition();
    	randomTimer.stop();
    	clip.stop();
    	clip.flush();
    	clip.drain();
    	clip.close();
    	System.gc();
    	isOpen = clip.isOpen();
    }
    
    public void pause(){
    	clipTime =  clip.getMicrosecondPosition();
    	clip.stop();
    	
    	if(isRandom){
    		randomTimer.stop();
    	}
    	

    }
    
    public void resume(){
    	

    	
    	clip.setMicrosecondPosition(clipTime);
    	//clip.loop(Clip.LOOP_CONTINUOUSLY);
    	//System.out.print(isRandom + "    " + clip.isRunning()+ "   ");
    	
    	
    	if(isRandom){
    		//clip.stop();
        	//for(int i = 0; i < musicNames.size(); i++ ){
        		//if(musicNames.get(i) == currentSong){
    		
    //    	randomTimer = new Timer((int)(clip.getMicrosecondLength()/1000 +1),this);
      //  	randomTimer.setInitialDelay((int)(clip.getMicrosecondLength()/1000 +1));
        //			songTime = musicTimes[i];
        			randomTimer = new Timer((int)(clip.getMicrosecondLength()/1000 +1) - (int)clipTime/1000,this);
                	randomTimer.setInitialDelay((int)(clip.getMicrosecondLength()/1000 +1) - (int)clipTime/1000);
                	randomTimer.setRepeats(false);
                	randomTimer.restart();
                	clip.start();
                	//System.out.println(clip.isRunning());
                	
        		//}
        	//}
        	//playRandom();
    	}
    	else{
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    	}
    }

    /*public void setClip(Clip clip){
    	if(this.clip != null){
    		stop();
		}
		this.clip = clip;
	}

    public Clip getAndReleaseClip(){
    	Clip output = clip;
    	playSound("Sound/silence.wav");
    	return output;
	}*/
    
    public void terminate(){
    	System.exit(0);
    }
    
    public long getClipTime(){
    	return clip.getMicrosecondPosition();
    }
    public String getCurrentSong(){
    	return currentSong;
    }

    public boolean isRunning(){
    	return clip.isRunning();
    }
    
    public long getSongLength(){
    	return clip.getMicrosecondLength();
    }
    
    public long getMicrosToGo(){
    	return clip.getMicrosecondLength() - clip.getMicrosecondPosition();
    }

    public float getLevel(){ return Math.abs(clip.getLevel()); }
    
    public void setGain(int gain){
    	//boolean b = false;
    	//long time = 0;
    	/*if(clip.isRunning()){
    		b = true;
    		time = clip.getMicrosecondPosition();
    		clip.stop();
    	}*/
    	gainControl.setValue(gain);
    	//playSound("Music/" + currentSong + ".wav");
    	/*if(b){
    		clip.setMicrosecondPosition(time);
    		if(isRandom){
    			clip.start();
    		}
    		else{
    			clip.loop(Clip.LOOP_CONTINUOUSLY);
    		}
    	} */
    }
    
    public void setVolume(int volume){
    	soundVolume = volume;
    	setGain((int)( (65/Math.log10(11)) * Math.log10(volume/1000 + 1) -60 ));
    }
    public void increaseVolume(int step){
    	soundVolume += step;
    	setVolume(soundVolume);
    }

    public void actionPerformed(ActionEvent e){
    	stop();
    	randomTimer.stop();
    	if(isRandom){
    		
    		playRandom();
    		if(Programma.getCurrentScreen() == "chooseMusic"){
    			Programma.createAndShowGUI("chooseMusic");
    		}
        	//System.out.println("random");
    	}
    	else{
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
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
		
		
		
	
	

