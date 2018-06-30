package saving;

import components.EasyImage;
import components.GameSaveButton;
import entities.Player;
import start.Loader;
import start.Programma;
import start.Content;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static start.Programma.paneHeight;
import static start.Programma.paneWidth;
import static start.Programma.selectedGameSave;

import static java.nio.file.StandardOpenOption.*;

public class GameSave {
    Path path;
    public boolean isValid = false;

    public int loadIndex;

    public boolean hasMetHelgaBefore;
    public boolean hasGun;
    public boolean hasHealing;
    public boolean hasHedgehog;
    public boolean hasGamePC;
    public int ammo;

    public String position;
    public String checkpoint;

    public BufferedImage image;
    public EasyImage easyImage;

    public long playTime;
    public int deathCount;
    public String dateLastPlayed = "";

    public String name;

    public GameSaveButton button;



    public GameSave(String name){
        path = Paths.get("./Saves/" + name + ".fsrsg");
        this.name = name;
        if(name == "currentGame"){
            return;
        }
        if(Files.notExists(path)){
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE_NEW, APPEND))) {
                isValid = true;
                //out.write(data, 0, data.length);
            } catch (FileAlreadyExistsException e){
                System.err.println("file already exists! \n" + e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        button = new GameSaveButton(this);
    }

    public GameSave(Path path){
        this.path = path;
        String string = path.getFileName().toString();
        name = string.substring(0, string.length() - 6);
        try (DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(path.toUri()))))){
            System.out.println("DataInputStream was succesfull created.");

            position = input.readUTF();
            checkpoint = input.readUTF();
            loadIndex = input.readInt();

            System.out.println("SaveGame " + name + ":  ");
            System.out.println(position + "  " + checkpoint + "   " + loadIndex);

            dateLastPlayed = input.readUTF();
            playTime = input.readLong();
            deathCount = input.readInt();

            System.out.println(dateLastPlayed + "  " + playTime + "   " + deathCount);

            hasMetHelgaBefore = input.readBoolean();
            hasGun = input.readBoolean();
            hasHealing = input.readBoolean();
            hasHedgehog = input.readBoolean();
            hasGamePC = input.readBoolean();

            int imageWidth = input.readInt();
            int imageHeight = input.readInt();

            image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

            for(int x = 0; x < imageWidth; x++){
                for(int y = 0; y < imageHeight; y++){
                    image.setRGB(x, y, input.readInt());
                }
            }
            easyImage = new EasyImage(image, Programma.paneWidth/3, Programma.paneHeight/3);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        button = new GameSaveButton(this);
    }




    public void saveCurrentGame(){
        if(!Programma.isGameRunning){
            return;
        }
        Player player = Programma.gameContainer.player;
        Content game = Programma.gameContainer;

        loadIndex = Loader.index;

        hasMetHelgaBefore = player.hasMetHelgaBefore;
        hasGun = player.hasGun;
        hasHealing = player.hasHealing;
        hasHedgehog = player.hasHedgehog;
        hasGamePC = player.hasGamePC;
        ammo = player.ammo;

        position = game.position;
        checkpoint = game.checkpoint;

        easyImage = new EasyImage(Programma.takeScreenShot(), Programma.paneWidth/3, Programma.paneHeight/3);
        image = easyImage.im;

        deathCount = Programma.deathCount;
        playTime = Programma.playTime;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateLastPlayed = dateFormat.format(date);
        flush();

    }

    public void flush(){

        /*if(Files.exists(path)){
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE_NEW, APPEND))) {
                //out.write(data, 0, data.length);
            } catch (FileAlreadyExistsException e){
                System.err.println("file already exists! \n" + e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }*/

        try (DataOutputStream output = new DataOutputStream(Files.newOutputStream(path, WRITE, TRUNCATE_EXISTING))) {

            output.writeUTF(position);
            output.writeUTF(checkpoint);
            output.writeInt(loadIndex);

            output.writeUTF(dateLastPlayed);
            output.writeLong(playTime);
            output.writeInt(deathCount);

            output.writeBoolean(hasMetHelgaBefore);
            output.writeBoolean(hasGun);
            output.writeBoolean(hasHealing);
            output.writeBoolean(hasHedgehog);
            output.writeBoolean(hasGamePC);

            output.writeInt(image.getWidth());
            output.writeInt(image.getHeight());

            for(int x = 0; x < image.getWidth(); x++){
                for(int y = 0; y < image.getHeight(); y++){
                    output.writeInt(image.getRGB(x, y));
                }
            }
        }catch (NoSuchFileException e){
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void delete(){

        try {
            Files.delete(path);
            System.out.println(" File deleted!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.flush();
        easyImage.delete();
    }

    public void play(){
        Player player = Programma.gameContainer.player;
        Content game = Programma.gameContainer;

        if(loadIndex == 2){
            Loader.load(Loader.LOAD_1_AND_2);
        }
        else {
            Loader.load(loadIndex);
        }

        Programma.deathCount = deathCount;
        Programma.beginGameTime = System.currentTimeMillis();
        Programma.playTime = playTime;
        Programma.currentScreen = "game";
        Programma.isGameRunning = true;
        Programma.isNavScreen = false;

        //loadIndex = Loader.index;
        player.reset();

        player.hasMetHelgaBefore = hasMetHelgaBefore;
        player.hasGun = hasGun;
        player.hasHealing = hasHealing;
        player.hasHedgehog = hasHedgehog;
        player.hasGamePC = hasGamePC;
        player.ammo = ammo;

        //position = game.position;
        game.checkpoint = checkpoint;


    }

}
