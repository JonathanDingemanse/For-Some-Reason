
package start;

import sound.SoundMixer;
import utils.ThreadPool;




public class Launcher {
	static ThreadPool pool; 
	//static MusicPlayer player;
	

	public static void main(String[] args) {
		
		pool = new ThreadPool(8);
		pool.runTask(new ProgrammaRunTool());
		pool.runTask(new SoundMixer());
		pool.runTask(new Loader());
		pool.join();
		
		// TODO Auto-generated method stub
		
	}

	public static void terminate(){
		pool.close();
		System.exit(0);
	}

}
