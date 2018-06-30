package sound;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Fade implements ActionListener {
	public static final int IN = 1;
	public static final int OUT = -1;
	int fadeCounter= 0;
	int musicFadeFactor = 1;
	int time = 0;
	int step;
	int step1;
	int step2;
	
	public Fade(){
		step = SoundMixer.masterVolume*SoundMixer.musicVolume/SoundMixer.fadeResolution;
		if(SoundMixer.fadeType == 3){
			if(SoundMixer.isGameMusicPlayer1){
				step2 = -1*SoundMixer.gameMusicPlayer1.soundVolume/SoundMixer.fadeResolution;
				step1 = step;
			}
			else{
				step1 = -1*SoundMixer.gameMusicPlayer.soundVolume/SoundMixer.fadeResolution;
				step2 = step;
			}
		}
		
	}

	
	public void actionPerformed(ActionEvent e) {
		fadeCounter++;
		if(fadeCounter <= SoundMixer.fadeResolution){
			
			switch(SoundMixer.fadeType){
			case 0: SoundMixer.musicPlayer.increaseVolume(step*SoundMixer.musicFadeFactor);break;
			case 2: SoundMixer.musicPlayer.increaseVolume(step*SoundMixer.musicFadeFactor);
			case 1: 
				if(SoundMixer.isGameMusicPlayer1){
					SoundMixer.gameMusicPlayer1.increaseVolume(step*SoundMixer.soundFadeFactor);
				}
				else{
					SoundMixer.gameMusicPlayer.increaseVolume(step*SoundMixer.soundFadeFactor);
				} break;
			case 3: SoundMixer.gameMusicPlayer.increaseVolume(step1);
					SoundMixer.gameMusicPlayer1.increaseVolume(step2); break;
			
			case 4:
				if(SoundMixer.isGameMusicPlayer1){
					SoundMixer.gameMusicPlayer.increaseVolume(-1*step);
				}
				else{
					SoundMixer.gameMusicPlayer1.increaseVolume(-1*step);
				} break;
			}
		}
		else{
			if(SoundMixer.musicFadeFactor < 0 && SoundMixer.fadeType <= 2){
				SoundMixer.pauseMusic(); // disables music
			}
			else if(SoundMixer.musicFadeFactor > 0 && SoundMixer.fadeType <= 2){
				SoundMixer.gameMusicPlayer.stop(); // disables music
				SoundMixer.gameMusicPlayer1.stop(); 
			}
			else if(SoundMixer.fadeType > 2){
				if(SoundMixer.isGameMusicPlayer1){
					SoundMixer.gameMusicPlayer.stop();
					SoundMixer.gameMusicPlayer.setVolume(SoundMixer.masterVolume*SoundMixer.musicVolume);
					SoundMixer.gameMusicPlayer1.setVolume(SoundMixer.masterVolume*SoundMixer.musicVolume);

				}
				else{
					SoundMixer.gameMusicPlayer1.stop();
					SoundMixer.gameMusicPlayer.setVolume(SoundMixer.masterVolume*SoundMixer.musicVolume);
					SoundMixer.gameMusicPlayer1.setVolume(SoundMixer.masterVolume*SoundMixer.musicVolume);

				}

			}
			SoundMixer.fadeTimer.stop();
		}
	}
		
}
