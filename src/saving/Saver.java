package saving;

import components.GameSaveList;
import start.Launcher;
import start.Programma;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class Saver implements Runnable {
    public static GameSave currentGame;
    public static Saver saver = new Saver();

    public static boolean isSaving = false;

    public static GameSaveList list;

    public static ArrayList<GameSave> gameSaves = new ArrayList<>();

    public Saver(){

    }

    public void run(){
        isSaving = true;
        currentGame.saveCurrentGame();
        if(Programma.isQuit){
            System.out.println("Saving has ended.");
            Launcher.terminate();
            System.exit(0);
        }
        isSaving = false;
    }


    public static void main(String...args){
        GameSaveLoader.searchAndLoadGameSaves();

        currentGame = new GameSave("currentGame");

        list = new GameSaveList(gameSaves);


    }

    public static void save(){
        Thread thread = new Thread(saver);
        thread.start();
    }


}
