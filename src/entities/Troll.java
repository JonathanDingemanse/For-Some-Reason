package entities;

import components.EasyImage;
import sound.SoundMixer;
import start.Programma;
import start.TrollLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Troll extends Animal implements ActionListener, Runnable {
    int refreshIndex = 0;
    public int attackIndex = -1;
    Timer trollTimer;
    TrollLoader loadZoom;
    Thread zoomThread;
    TrollLoader loadNormal;
    Thread normalThread;
    int imIndex = 0;
    final double MAGNIFICATION = 1.08;

    public Point origialLocation;

    public ArrayList<EasyImage> images = new ArrayList<EasyImage>();
    public ArrayList<EasyImage> zoomImages = new ArrayList<EasyImage>();
    EasyImage imageM;
    EasyImage zoomImageM;
    EasyImage imageR;
    EasyImage zoomImageR;
    EasyImage imageB;
    EasyImage zoomImageB;
    EasyImage imageT;
    EasyImage zoomImageT;

    public Troll(){
        super(42000*Programma.difficultyLevel, Programma.paneHeight/10,"Images/troll.png");
        setHead(1245, 845, 2149, 2959, 340);
        super.headShotDamage = 13000;
        long time = System.currentTimeMillis();

        imageR = image;
        zoomImageR = zoomImage;

        imageB = image;
        zoomImageB = zoomImage;


        EasyImage im = imageR.getScaledInstance(MAGNIFICATION);
        images.add(im);
        //EasyImage iZm = zoomImage.getScaledInstance(1.05);
        //zoomImages.add(iZm);
        Thread zoomLoader = new Thread(this);
        zoomLoader.start();

        for(int i = 0; i < 30; i++){
            //im.delete();
            EasyImage im2;
            im2 = images.get(i).getScaledInstance(MAGNIFICATION);
            images.add(im2);
        }

        /*for(int i = 0; i < 30; i++){
            iZm.delete();
            iZm = zoomImages.get(i).getScaledInstance(1.05);
            zoomImages.add(iZm);
        }*/

        //imageM = image.getVerticalInvertedInstance();
        imageM = images.get(images.size() - 1).getVerticalInvertedInstance();
        imageT = images.get(images.size() - 1).getTransponedInstance();
        //zoomImageM = zoomImage.getVerticalInvertedInstance();

        trollTimer = new Timer(4400,this);
        trollTimer.setInitialDelay(2700);

        long loadTime = System.currentTimeMillis() - time;

        System.out.println("Loading of troll lasted " + loadTime + " milliseconds");

        /*loadNormal = new TrollLoader(this, TrollLoader.NORMAL);
        loadZoom = new TrollLoader(this, TrollLoader.ZOOM);
        normalThread = new Thread(loadNormal);
        zoomThread = new Thread(loadZoom);
        normalThread.start();
        zoomThread.start();*/

    }

    @Override
    public void start(){
        if(!trollTimer.isRunning()){
            trollTimer.setDelay(4400);
            trollTimer.setInitialDelay(2700);
            trollTimer.restart();
        }
        super.health = 50000*Programma.difficultyLevel;
        this.removeAll();
        image = imageB;
        zoomImage = zoomImageB;
        if(isZoom){
            add(zoomImage);
            this.setSize(zoomImage.getSize());
        }
        else {
            add(image);
            this.setSize(image.getSize());
        }
        this.setLocation(origialLocation);

        attackIndex = -1;
        imIndex = 0;
    }


    @Override
    public void decreaseHealth(int damage) {
        //super.decreaseHealth(damage);
        super.health -= damage;

    }

    @Override
    public void moveFPS(){
        super.moveFPS();



        //super.moveFPS();



        //
        //
        // refreshIndex++;
        repaint();
    }


    public void run(){
        long time = System.currentTimeMillis();
        EasyImage iZm = zoomImage.getScaledInstance(MAGNIFICATION);
        zoomImages.add(iZm);
        for(int i = 0; i < 30; i++){
            EasyImage iZm2;
            iZm2 = zoomImages.get(i).getScaledInstance(MAGNIFICATION);
            zoomImages.add(iZm2);
        }

        long loadTime = System.currentTimeMillis() - time;
        zoomImageM = zoomImages.get(zoomImages.size() - 1).getVerticalInvertedInstance();
        zoomImageT = zoomImages.get(zoomImages.size() - 1).getTransponedInstance();
        System.out.println("Loading of zoomed troll lasted " + loadTime + " milliseconds");
    }

    public void conformSizeToImage(){
        if(super.isZoom){
            /*int dx = (zoomImage.getWidth() - getWidth()) / 2;
            int dy = (zoomImage.getHeight() - getHeight()) / 2;
            setSize(zoomImage.getWidth(), zoomImage.getHeight());
            setLocation(getX()- dx,getY() - dy);
            add(zoomImage);*/
            conform(zoomImage);
        }
        else {
            /*
            int dx = (image.getWidth() - getWidth()) / 2;
            int dy = (image.getHeight() - getHeight()) / 2;
            setSize(image.getWidth(), image.getHeight());
            setLocation(getX()- dx,getY() - dy);
            add(image);*/
            conform(image);
        }
    }

    private void conform(EasyImage im){
        int dx = (im.getWidth() - getWidth()) / 2;
        int dy = (im.getHeight() - getHeight()) / 2;
        setSize(im.getWidth(), im.getHeight());
        setLocation(getX()- dx,getY() - dy);
        add(im);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //super.image.scaleImage(1.1);
        //super.zoomImage.scaleImage(1.1);

        System.out.println("Clip position \" in the hall of the mountain king\": " + SoundMixer.gameMusicPlayer.getClipPosition() + " attackIndex: " + attackIndex);

        if(SoundMixer.gameMusicPlayer.getClipPosition() < 130000000){
            this.removeAll();
            //image.delete();
            //zoomImage.delete();
            image = images.get(imIndex);
            //image = image.getScaledInstance(1.05);
            //zoomImage = zoomImage.getScaledInstance(1.05);
            zoomImage = zoomImages.get(imIndex);
            imIndex++;
            System.out.println("Troll health: " + health);
            //refreshIndex++;
            conformSizeToImage();
            setHead(1245, 845, 2149, 2959, 340);
            /*if(super.isZoom){
                int dx = (zoomImage.getWidth() - getWidth()) / 2;
                int dy = (zoomImage.getHeight() - getHeight()) / 2;
                setSize(zoomImage.getWidth(), zoomImage.getHeight());
                setLocation(getX()- dx,getY() - dy);
                add(zoomImage);
            }
            else {
                int dx = (image.getWidth() - getWidth()) / 2;
                int dy = (image.getHeight() - getHeight()) / 2;
                setSize(image.getWidth(), image.getHeight());
                setLocation(getX()- dx,getY() - dy);
                add(image);
            }*/
        }
        else{

            //this.removeAll();
            switch (attackIndex){
                case -1:
                    imageR = image;
                    zoomImageR = zoomImage;
                    Programma.gameContainer.player.hasHealing = false;
                    trollTimer.stop();
                    trollTimer.setInitialDelay((int) (134740 - SoundMixer.gameMusicPlayer.getClipPosition()/1000));
                    //trollTimer.setDelay(1120);
                    trollTimer.restart();
                    break;

                case 0:
                    trollTimer.stop();
                    trollTimer.setInitialDelay((int) (135740 - SoundMixer.gameMusicPlayer.getClipPosition()/1000));
                    trollTimer.setDelay(1120);
                    trollTimer.restart();
                    break;

                case 2:
                    /*if((int)(SoundMixer.gameMusicPlayer.getClipPosition()/1000) < 139900){
                        trollTimer.stop();
                        trollTimer.setInitialDelay((int) (139900 - SoundMixer.gameMusicPlayer.getClipPosition()/1000));
                    }
                    else{
                        trollTimer.stop();
                        trollTimer.setInitialDelay(3100);
                        trollTimer.setDelay(960);
                        trollTimer.restart();
                    }
                    break;*/

                case 4:

                    trollTimer.stop();
                    trollTimer.setInitialDelay(3100);
                    trollTimer.setDelay(960);
                    trollTimer.restart();
                    break;

                case 6:
                    trollTimer.stop();
                    trollTimer.setInitialDelay(5110);


                    //trollTimer.setDelay(930);
                    trollTimer.restart();
                    break;

                case 7:

                    if(this.health >= 0){
                        Programma.createAndShowGUI("*You were killed by<br>The Mountain King");
                    }
                    else{
                        Programma.gameContainer.player.hasHealing = true;
                        removeAll();
                        image = imageT;
                        zoomImage = zoomImageT;
                        conformSizeToImage();
                        Programma.gameContainer.addContinue();
                        super.vY = 30*Programma.paneWidth/1920;
                    }
                    trollTimer.stop();


            }

            if(attackIndex > 0 && attackIndex < 7){
                Programma.gameContainer.player.decreaseHealth(15);
                this.removeAll();
                if(attackIndex % 2 == 0){
                    image = imageR;
                    zoomImage = zoomImageR;
                }
                else{
                    image = imageM;
                    zoomImage = zoomImageM;
                }

                if(isZoom){
                    this.add(zoomImage);
                }
                else{
                    this.add(image);
                }



            }
            attackIndex++;
        }
        this.repaint();
        /*
        if(super.isZoom){
            int dx = (zoomImage.getWidth() - getWidth()) / 2;
            int dy = (zoomImage.getHeight() - getHeight()) / 2;
            setSize(zoomImage.getWidth(), zoomImage.getHeight());
            setLocation(getX()- dx,getY() - dy);
        }
        else {
            int dx = (image.getWidth() - getWidth()) / 2;
            int dy = (image.getHeight() - getHeight()) / 2;
            setSize(image.getWidth(), image.getHeight());
            setLocation(getX()- dx,getY() - dy);
        }*/
        //trollTimer.setDelay(trollTimer.getDelay() - 5);
        Programma.gameContainer.repaintScreen();



    }


}
