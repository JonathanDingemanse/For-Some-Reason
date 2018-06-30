package start;

import entities.Troll;

import java.awt.*;

public class TrollScreen extends ShooterScreen {

    public TrollScreen (){
        addImage("Images/throne_room.jpg");
        Troll troll = new Troll();
        troll.origialLocation = new Point(9*Programma.paneWidth/20, 2*Programma.paneHeight/3);
        addAnimal(troll, 9*Programma.paneWidth/20, 2*Programma.paneHeight/3);
    }


    @Override
    public boolean hasPlayerShotEverything(){
        if(animals.size() == 1){
            Troll troll = (Troll) animals.get(0);
            return (troll.health <= 0) && troll.attackIndex == 8;
        }
        else return false;
    }
}
