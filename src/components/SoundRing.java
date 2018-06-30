package components;

import sound.SoundMixer;
import start.Programma;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static start.Programma.paneHeight;
import static start.Programma.paneWidth;

public class SoundRing extends JPanel implements ActionListener {
    Graphics2D g;
    Timer timer = new Timer(33, this);

    public SoundRing(){
        setOpaque(true);
        setFocusable(false);
        setLayout(null);
        setSize(paneWidth, paneHeight);
        //setLocation(200, 200);
        //setBackground(Color.RED);
        //paint(this.getGraphics());

    }
    public void start(){
        timer.restart();
    }
    public void stop(){
        timer.stop();
    }


    public void paint(Graphics z){
        g = (Graphics2D)z;

        g.setStroke(new BasicStroke(paneWidth/96));
        g.setColor(Programma.currentTheme.buttonBorderColor);

        float factor;

        if(SoundMixer.isPlayingAlternative){
            factor = SoundMixer.alternativePlayer.getLevel();
            System.out.print("Alternative ");
        }
        else {
            factor = SoundMixer.musicPlayer.getLevel();
            System.out.print("Music ");
        }


        int width = (int)(4*factor*paneWidth/30);
        int height = (int)(4*factor*paneWidth/30);

        int x = 5*paneWidth/20 + width/2;
        int y = 21*paneHeight/80 + height/2;

        System.out.println("Soundring " + x + " " + y + " " + width + " " + height );

        g.drawOval(x,y, width, height);
        g.drawOval(200, 200, 300, 300);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
