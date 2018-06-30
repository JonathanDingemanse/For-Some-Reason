package keyActions;

import start.Programma;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyCommands extends JPanel implements KeyListener {
    public static final int NAV = 511;
    public static final int GAME = 512;
    int gameOrNav;
    public boolean repeat = false;

    public KeyCommands(int gameOrNav){
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        this.gameOrNav = gameOrNav;

    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key Typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyChar() + "  " + repeat);
        if(repeat){
            return;
        }
        System.out.println("Key pressed: " + e.getKeyChar());
        switch(e.getExtendedKeyCode()){
            case KeyEvent.VK_M:
                Programma.createAndShowGUI("enableMusic");
                break;

            case KeyEvent.VK_S:
                Programma.createAndShowGUI("enableSound");
                break;
        }


        if (gameOrNav == GAME){
            switch(e.getExtendedKeyCode()){
                case KeyEvent.VK_ESCAPE:
                    if(Programma.getCurrentScreen() != "main" && Programma.getCurrentScreen() != "settings" && Programma.getCurrentScreen() != "about"){
                        Programma.createAndShowGUI("escape");
                    }
                    break;

                case KeyEvent.VK_R:
                    Programma.gameContainer.reloadShooter();

                    break;

                case KeyEvent.VK_L: //Programma.scopeLock = !Programma.scopeLock; break;

                //case KeyEvent.VK_R:  break;

                case KeyEvent.VK_1:
                case KeyEvent.VK_2:
                case KeyEvent.VK_3:
                case KeyEvent.VK_4:
                case KeyEvent.VK_NUMPAD1:
                case KeyEvent.VK_NUMPAD2:
                case KeyEvent.VK_NUMPAD3:
                case KeyEvent.VK_NUMPAD4:
                    break;

                    //Programma.gameContainer.runKey(e.getKeyCode()); break;

                case KeyEvent.VK_ENTER:
                    Programma.createAndShowGUI("continue");
                    break;
            }
        }
        else  if (gameOrNav == NAV){
            switch(e.getExtendedKeyCode()){
                case KeyEvent.VK_P: Programma.createAndShowGUI("play"); break;

                case KeyEvent.VK_R:
                    if(Programma.isDieScreen){
                        Programma.createAndShowGUI("resumeAfterDeath");
                    }
                    else {
                        Programma.createAndShowGUI("resume");
                    }
                    break;

                case KeyEvent.VK_Q:
                    Programma.createAndShowGUI("quit");
                    break;
            }



        }
        repeat = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        repeat = false;
    }
}
