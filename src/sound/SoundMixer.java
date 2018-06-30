package sound;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

import start.Programma;

public class SoundMixer implements Runnable {
	public static MusicPlayer alternativePlayer;
	public static boolean isPlayingAlternative = false;
	public static MusicPlayer musicPlayer;
	public static SoundPlayer soundPlayer;
	public static SoundPlayer gameMusicPlayer;
	public static SoundPlayer gameMusicPlayer1;
	public static boolean isGameMusicPlayer1 = false;
	public static boolean isMusicEnabled = true;
	public static boolean isSoundEnabled = true;
	public static String musicProfile = "random";
	static long clipTime;
	public static int masterVolume = 50;
	public static int musicVolume = 70;
	public static int backgroundVolume = 100;
	public static int soundVolume = 80;
	public static int backgroundRelativeVolume = 100;
	public static int soundRelativeVolume = 100;
	static int fadeCounter;
    //boolean isFadeIn;
    //boolean musicFade;
	static int fadeResolution = 200;
	static int fadeType = 0; 
	static int musicFadeFactor = 1;
	static int soundFadeFactor = 1;
	
	static Timer fadeTimer;
    //int musicGain;
    //int soundGain;
	
	public void run() {
		// TODO Auto-generated method stub
		
		//soundGain = (int)( (65/Math.log10(11)) * Math.log10(soundVolume*masterVolume/1000 + 1) -60 );
		//musicGain = (int)((65/Math.log10(11)) * Math.log10(musicVolume*masterVolume/1000 + 1) -60);
		//System.out.println("sound gain: " + soundGain);
		//System.out.println("music gain: " + musicGain);

		
		//Timer beginDelay = new Timer(500,this);
		//beginDelay.setInitialDelay(500);
		//beginDelay.setRepeats(false);
		//beginDelay.start();			
		musicPlayer = new MusicPlayer("Clocks","FSR_theme","I'm Blue","In the Hall of the Mountain King","introduction et rondo capriccioso","Kinessa Song","Pirates Of The Caribbean Theme Song","poep in je hoofd","Ranger","Viva_La_Vida", "K_391_Summertime");
		musicPlayer.run();
		musicPlayer.setVolume(masterVolume*musicVolume);
		//musicPlayer.pause();
		alternativePlayer = new MusicPlayer("Clocks");
		alternativePlayer.run();
		alternativePlayer.stop();
		soundPlayer = new SoundPlayer(SoundPlayer.SOUND);
		gameMusicPlayer = new SoundPlayer(SoundPlayer.BACKGROUND);
		gameMusicPlayer1 = new SoundPlayer(SoundPlayer.BACKGROUND);

		//fadePlayer = new SoundPlayer();

		musicPlayer.setVolume(masterVolume*musicVolume);
		alternativePlayer.setVolume(masterVolume*musicVolume);
		soundPlayer.setVolume(masterVolume*soundVolume);
		gameMusicPlayer.setVolume(masterVolume*backgroundVolume);
		gameMusicPlayer1.setVolume(masterVolume*backgroundVolume);

		//fadePlayer.setVolume(masterVolume*musicVolume);
		
    	fadeTimer = new Timer(1000, new Fade());
	}
	
	public static void begin(){

		//musicPlayer.resume();
	}
	
	public static void pauseMusic(){
		if(musicPlayer.isRunning()){
			//alternativePlayer.stop();
			musicPlayer.pause();
		}
	}
	
	public static void resumeMusic(){
		
		if(isMusicEnabled && alternativePlayer.isRunning()){
			if(alternativePlayer.isRunning()){
				alternativePlayer.stop();
			}
			
			musicPlayer.resume();
		}
		else if(isMusicEnabled && !musicPlayer.isRunning()){
			musicPlayer.resume();
		}

	}
	public static void mainMusic(String string){
		isPlayingAlternative = false;
		
		if(musicProfile == "select"){
			if(isMusicEnabled && musicPlayer.currentSong == string){
			}
			/*else if(isMusicEnabled && player2.currentSong == string){
				player.stop();
				player2.resume();
				//System.out.println("asdf");
			} */
			else if(isMusicEnabled){
				clipTime = alternativePlayer.getClipTime();
				alternativePlayer.stop();
				musicPlayer.stop();
				musicPlayer.newMusic(string, clipTime);
				//System.out.println("troll");
			}	
		}else if(musicProfile == "semi-random"){
			//System.out.println((!alternativePlayer.isRunning() && musicPlayer.isRunning()));
			if((isMusicEnabled && musicPlayer.currentSong == string) || (!alternativePlayer.isRunning() && musicPlayer.isRunning() )){
			}
			else if(isMusicEnabled){
				//boolean isCurrentSongInSongs = false;
				//for(String song : songs){ randomList.add(song);}
				//player2.changeRandom(songs);


				clipTime = alternativePlayer.getClipTime();
				alternativePlayer.stop();
				musicPlayer.stop();
				musicPlayer.selectRandom(string, clipTime);


				/*
				for(int i = 0; i < player2.randomMusic.size(); i++ ){
					if(string == player2.currentSong){
						isCurrentSongInSongs = true;
						//break;
					}
				}
				if(!isCurrentSongInSongs){
					player2.stop();
					//player2.changeRandom(songs);
					player2.selectRandom(string);
						
				}*/
			}	
		}else{
			boolean isCurrentSongInSongs = false;
			
			
			if(musicPlayer.currentSong == string && musicPlayer.randomMusic.size() > 1 ){
				
				if(isMusicEnabled  ){
					musicPlayer.stop();
				}

				for(int i = 0; i < musicPlayer.randomMusic.size(); i++ ){
					if(string == musicPlayer.randomMusic.get(i) && musicPlayer.randomMusic.size() > 1 ){
						musicPlayer.randomMusic.remove(i);
							
						break;
					}
				}
				if(isMusicEnabled){
					musicPlayer.playRandom();
				}
			}
			else{
				
					//for(String song : songs){ randomList.add(song);}
					//player2.changeRandom(songs);
					//player.stop();
				
					for(int i = 0; i < musicPlayer.randomMusic.size(); i++ ){
						if(string == musicPlayer.randomMusic.get(i) && musicPlayer.randomMusic.size() > 1){
							isCurrentSongInSongs = true;
							//System.out.println("song in random list ");
							musicPlayer.randomMusic.remove(i);
							//System.out.println("remove song");
							break;
						}
					
				}
				
				if(!isCurrentSongInSongs){
					musicPlayer.randomMusic.add(string);
					//player2.selectRandom(string);
					//System.out.println("add song");
				}
				
			}
		}
		//player2 = new MusicPlayer("Pirates Of The Caribbean Theme Song");
		//player2.run();
	}
	/*
	public static void randomMusic(String song){
		//ArrayList<String> randomList = new ArrayList<String>();
		
		boolean isCurrentSongInSongs = false;
		//for(String song : songs){ randomList.add(song);}
		//player2.changeRandom(songs);
		
		for(int i = 0; i < player2.randomMusic.size(); i++ ){
			if(song == player2.currentSong){
				isCurrentSongInSongs = true;
				break;
			}
		}
		if(!isCurrentSongInSongs){
			player2.stop();
			//player2.changeRandom(songs);
			player2.playRandom();
			//System.out.println("loll");
		}
	} */
	
	public static void soundEffect(String string){
		if(isMusicEnabled){
			isPlayingAlternative = true;
			alternativePlayer = new MusicPlayer(string);
			alternativePlayer.run();
		}
		
		
	}
	
	public static void alternativeMusic(String string){
		if(isMusicEnabled){
			musicPlayer.pause();
			//player.newMusic(string, 0);
			alternativePlayer.select(string);
			//player.run();
		}	
		//System.out.println("after launch: \n is music enabled: " + isMusicEnabled + " \n is alternative running: " + alternativePlayer.isRunning() + " \n is music running: " + musicPlayer.isRunning() );
	}
	public static void enableMusic(){
		isMusicEnabled = !isMusicEnabled;
		if(!isMusicEnabled){
			musicPlayer.pause();
		}
		else{
			//player.stop();
			if(!(Programma.isGameRunning && isSoundEnabled)){
				musicPlayer.resume();
			}	
		}
	}
	public static void enableSound(){
		isSoundEnabled = !isSoundEnabled; 
		if(!isSoundEnabled){
			
			if(!musicPlayer.isRunning()){
				gameMusicPlayer.pause();
				gameMusicPlayer1.pause();
				if(isMusicEnabled){
					musicPlayer.resume();
				}
			}
			
		}
		else{
			if(Programma.isGameRunning){
				if(isGameMusicPlayer1){
					gameMusicPlayer1.resume();
				}
				else{
					gameMusicPlayer.resume();
				}
				if(musicPlayer.isRunning()){
					musicPlayer.pause();
				}
			}
		}
		System.out.print("sound: " + isSoundEnabled);
		System.out.print(" \t  music: " + isMusicEnabled);
		System.out.println(" \t running: " + musicPlayer.isRunning());

		
	}
	
	
	public static String getCurrentMusic(){
		return musicPlayer.getCurrentSong();

	}
	public static void setMusicProfile(String profile){
		musicProfile = profile;
		if(musicProfile == "select"){
			musicPlayer.isRandom = false;
		}
		else if(musicProfile == "random" || musicProfile == "semi-random"){
			musicPlayer.isRandom = true;
		}
		
	}
    public static boolean isSongInRandomList(String song){
    	if(musicPlayer == null){
    		return false;
    	}
    	boolean b = false;
    	if(musicPlayer.isRandom){
    		for(int i = 0; i < musicPlayer.randomMusic.size(); i++ ){
    			if(musicPlayer.randomMusic.get(i) == song){
    				b = true;
    				break;
    			}
    		}
        	return b;
    	}
    	else{
    		return false;
    	}
    }
    public static boolean isMusicRandom(){
    	return musicPlayer.isRandom;
    }
    public static void setGameSound(boolean gameSound){
    	if(gameSound && isSoundEnabled && musicPlayer.isRunning()){
    		musicPlayer.pause();
    	}
    	else if(isMusicEnabled && !musicPlayer.isRunning()){
    		musicPlayer.resume();
    	}
    }
    
    private static void handleRelVolSound(int...relVol){
    	if(relVol.length > 0){
    		if(relVol[0] < 101 && relVol[0] > 0){
    			//soundPlayer.setVolume(backgroundVolume*masterVolume*relVol[0]/100);
    			soundRelativeVolume = relVol[0];
    		}
    	}
    	else{
    		//soundPlayer.setVolume(soundVolume*masterVolume);
    		soundRelativeVolume = 100;
    	}
    }
    
    private static void handleRelVolGame(int...relVol){
    	if(relVol.length > 0){
    		if(relVol[0] < 100 && relVol[0] > 0){
    			//gameMusicPlayer.setVolume(backgroundVolume*masterVolume*relVol[0]/100);
    			backgroundRelativeVolume = relVol[0];
    		}
    	}
    	else{
    		//gameMusicPlayer.setVolume(backgroundVolume*masterVolume);
    		backgroundRelativeVolume = 100;
    	}
    }
    
    public static void playSound(String fileName, int...relativeVolume){
    	stopSoundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	//soundPlayer.setVolume(soundVolume*masterVolume);
		handleRelVolSound(relativeVolume);
    	soundPlayer.playSound(fileName);
    }
    
    public static void playSoundContinuously(String fileName, int...relativeVolume){
    	stopSoundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	//handleRelVolSound(relativeVolume);
		handleRelVolSound(relativeVolume);
    	soundPlayer.playSoundContinuously(fileName);

    	//soundPlayer.setVolume(soundVolume*masterVolume);
    }
    
    public static void playMultipleSounds(String[] sounds, int...relativeVolume){
    	if(!isSoundEnabled){
    		return;
    	}
    	handleRelVolSound(relativeVolume);
    	soundPlayer.playMultipleSounds(sounds);
		//handleRelVolSound(relativeVolume);
    	//soundPlayer.setVolume(soundVolume*masterVolume);
    }
    
    public static void stopSound(){
    	stopSoundTimer();
    	if(soundPlayer.isRunning()){
    		soundPlayer.stop();
    	}
    	
    }
    public static void playBackground(String fileName, int...relativeVolume){
    	if(!isSoundEnabled){
    		return;
    	}
    	else if(fileName != gameMusicPlayer.getCurrentSound() || !gameMusicPlayer.isRunning()){
    		stopBackgroundTimer();
    		gameMusicPlayer.stop();
        	gameMusicPlayer1.stop();
			handleRelVolGame(relativeVolume);
        	gameMusicPlayer.playSoundContinuously(fileName);
        	//gameMusicPlayer.setVolume(backgroundVolume*masterVolume);
        	isGameMusicPlayer1 = false;
    	}
    }
    
    public static void play2SoundsSecondContinuouslyBackground(String fileName0, String fileName1, int...relativeVolume){
    	if(!isSoundEnabled){
    		return;
    	}
    	else if(fileName0 != gameMusicPlayer.getCurrentSound() || !gameMusicPlayer.isRunning()){
    		gameMusicPlayer.stop();
        	gameMusicPlayer1.stop();
			handleRelVolGame(relativeVolume);
        	gameMusicPlayer.play2SoundsSecondContinuously(fileName0, fileName1);
        	//gameMusicPlayer.setVolume(backgroundVolume*masterVolume);
        	isGameMusicPlayer1 = false;
    	}
    }
    
    public static void stopBackground(){
		gameMusicPlayer.stop();
    }
    
    public static void setMasterVolume(int volume){
    	masterVolume = volume;
    	
		musicPlayer.setVolume(masterVolume*musicVolume);
		//gameMusicPlayer.setVolume(masterVolume*backgroundVolume);
		alternativePlayer.setVolume(masterVolume*musicVolume);
		handleRelVolGame(backgroundRelativeVolume);
		handleRelVolSound(soundRelativeVolume);
		gameMusicPlayer.setVolume(backgroundVolume*masterVolume*backgroundRelativeVolume/100);
		soundPlayer.setVolume(soundVolume*masterVolume*soundRelativeVolume/100);
		//soundPlayer.setVolume(masterVolume*soundVolume);
    	//gameMusicPlayer1.setVolume(masterVolume*backgroundVolume);

		
		//fadePlayer.setVolume(masterVolume*musicVolume);
    }
    
    public static void setRelativeBackgroundVolume(int percentage){
    	if(percentage < 100 && percentage > 0){
    		gameMusicPlayer.setVolume(backgroundVolume*masterVolume*percentage/100);
    	}
    }

    public static void setMusicVolume(int volume){
    	musicVolume = volume;
		musicPlayer.setVolume(masterVolume*musicVolume);
		alternativePlayer.setVolume(masterVolume*musicVolume);
	
		//fadePlayer.setVolume(masterVolume*musicVolume);
    }
    
    public static void setBackgroundVolume(int volume){
    	backgroundVolume = volume;
    	handleRelVolGame(backgroundRelativeVolume);
		gameMusicPlayer.setVolume(backgroundVolume*masterVolume*backgroundRelativeVolume/100);

    	//System.out.println("Relative volume: " + backgroundRelativeVolume);
    	//gameMusicPlayer.setVolume(masterVolume*backgroundVolume*backgroundRelativeVolume/100);
    	//gameMusicPlayer1.setVolume(masterVolume*backgroundVolume*backgroundRelativeVolume/100);
    }
    
    public static void setSoundVolume(int volume){
    	soundVolume = volume;
    	handleRelVolSound(soundRelativeVolume);
		soundPlayer.setVolume(soundVolume*masterVolume*soundRelativeVolume/100);
    	//soundPlayer.setVolume(masterVolume*soundVolume);
    }
    
    public static long getSoundMicrosToGo(){
    	return soundPlayer.getMicrosToGo();
    }
    
    public static void fadeOutMusic(int time){
    	fadeType = 0;
    	musicFadeFactor =  -1;
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    }
    
    public static void fadeInMusic(int time){
    	if(!isMusicEnabled){
    		return;
    	}
    	fadeType = 0;
    	musicFadeFactor =  1;
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
    	musicPlayer.setVolume(0);
    	resumeMusic();
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    }
    
    public static void fadeSoundToMusic(int time){
    	stopBackgroundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	fadeType = 2;
    	soundFadeFactor = -1;
    	musicFadeFactor =  1;
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
		musicPlayer.setVolume(0);
    	resumeMusic();
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    }
    
    public static void fadeMusicToNewSound(String sound, int time){
    	stopBackgroundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	fadeType = 2;
    	soundFadeFactor =  1;
    	musicFadeFactor = -1;
    	gameMusicPlayer.playSoundContinuously(sound);
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
    	gameMusicPlayer.setVolume(0);
    	gameMusicPlayer1.setVolume(0);

    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    	//System.out.println("delay: " + time/fadeResolution);
    }
    
    public static void fadeInSound(String sound, int time){
    	stopBackgroundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	fadeType = 1;
    	soundFadeFactor =  1;
    	gameMusicPlayer1.stop();
    	gameMusicPlayer.setVolume(0);
    	gameMusicPlayer.playSoundContinuously(sound);
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    }
    
    public static void fadeOutSound(int time){
    	stopBackgroundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	fadeType = 1;
    	soundFadeFactor =  -1;
    	
    	/*if(isGameMusicPlayer1){
    		soundFadeFactor = 1;
    	}
    	else{
    		soundFadeFactor = -1;
    	} */
    	
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    }
    
    public static void fadeOutSoundAndStartNewSound(String sound, int time){
    	stopBackgroundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	fadeType = 4;
    	//soundFadeFactor =  -1;
    	
    	if(isGameMusicPlayer1){
    		gameMusicPlayer.playSoundContinuously(sound);
			gameMusicPlayer.setVolume(SoundMixer.masterVolume*SoundMixer.backgroundVolume);
    	}
    	else{
    		gameMusicPlayer1.playSoundContinuously(sound);
    		gameMusicPlayer1.setVolume(SoundMixer.masterVolume*SoundMixer.backgroundVolume);
    	}
    	
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    	isGameMusicPlayer1 = !isGameMusicPlayer1;

    }
    
    public static void fadeSoundToNewSound(String sound, int time){
    	//fadePlayer.playSoundContinuouslyFromPosition(gameMusicPlayer.getCurrentSound(), gameMusicPlayer.getClipPosition());
    	stopBackgroundTimer();
    	if(!isSoundEnabled){
    		return;
    	}
    	
    	fadeType = 3;
    	if(fadeTimer.isRunning()){
    		fadeTimer.stop();
    	}
    	
    	if(isGameMusicPlayer1){
    		soundFadeFactor = 1;
    		gameMusicPlayer.playSoundContinuously(sound);
    		gameMusicPlayer.setVolume(0);
    	}
    	else{
    		soundFadeFactor = -1;
    		gameMusicPlayer1.setVolume(0);
    		gameMusicPlayer1.playSoundContinuously(sound);
    		gameMusicPlayer1.setVolume(0);
    	}
    	
		//gamemusicPlayer.setVolume(gameMusicPlayer.soundVolume);
		
    	fadeTimer = new Timer(time/fadeResolution, new Fade());
    	fadeTimer.start();
    	isGameMusicPlayer1 = !isGameMusicPlayer1;

    }
    public static boolean isMusicRunning(){
    	return musicPlayer.isRunning();
    }
    
    public static String getBackGroundSound(){
    	if(isGameMusicPlayer1){
        	return gameMusicPlayer1.getCurrentSound();
    	}
    	else{
        	return gameMusicPlayer.getCurrentSound();
    	}
    }
    
    public static void stopSoundTimer(){
    	if(soundPlayer.timer != null){
    		if(soundPlayer.timer.isRunning()){
    			soundPlayer.timer.stop();
    		}
    	}
    }
    
    public static void stopBackgroundTimer(){
    	if(gameMusicPlayer.timer != null){
    		if(gameMusicPlayer.timer.isRunning()){
    			gameMusicPlayer.timer.stop();
    		}
    	}
    }
    
    public static void stopAllTimers(){
    	stopSoundTimer();
    	stopBackgroundTimer();
    }

    public static void terminate(){
		stopAllTimers();

		gameMusicPlayer.stop();
		gameMusicPlayer1.stop();
		musicPlayer.stop();
		alternativePlayer.stop();

		gameMusicPlayer = null;
		gameMusicPlayer1 = null;
		musicPlayer = null;
		alternativePlayer = null;
	}
    	
	public void actionPerformed(ActionEvent e) {
		/*fadeCounter++;
		if(fadeCounter <= fadeResolution){
		//	int step = masterVolume*musicVolume/fadeResolution;
			switch(fadeType){
			case 0: musicPlayer.increaseVolume(step*musicFadeFactor);break;
			case 2: musicPlayer.increaseVolume(step*musicFadeFactor);
			case 1: soundPlayer.increaseVolume(step*soundFadeFactor);break;
			}
		}
		else{
			musicPlayer.setVolume(masterVolume*musicVolume);
			fadeTimer.stop();
		}
		*/
			
		
	}
	
	
}
