package timers;

import start.Loader;
import start.Programma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSaveLoadTimer implements ActionListener {
    Timer timer;

    public GameSaveLoadTimer(){
        timer = new Timer(50, this);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Loader.isLoadingReady){
            timer.stop();
            Programma.gameContainer.content(Programma.gameContainer.position);
        }
    }
}
