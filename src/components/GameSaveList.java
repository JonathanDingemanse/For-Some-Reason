package components;

import saving.GameSave;
import start.Programma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class GameSaveList extends JPanel implements MouseWheelListener {
    ArrayList<GameSaveButton> buttons = new ArrayList<GameSaveButton>();

    public GameSaveList(ArrayList<GameSave> gameSaves){
        for(GameSave save : gameSaves){
            buttons.add(save.button);
        }
        setLayout(null);
        setOpaque(false);
        setFocusable(true);
        setLocation(5*Programma.paneWidth/10, 3*Programma.paneHeight/20 + 12*Programma.paneWidth/1920);
        setSize(4*Programma.paneWidth/10 - 12*Programma.paneWidth/1920, 11*Programma.paneHeight/20 - 12*Programma.paneWidth/1920);
        addMouseWheelListener(this);
        recover();
    }

    public void setRightLayout(){
        for(GameSaveButton button : buttons){
            button.setRightLayout();
        }
    }

    public void recover(){
        removeAll();
        int x = Programma.paneWidth/30;
        int y = 0;
        for(int i = 0; i < buttons.size(); i++){
            //System.out.println("is button null: " + (buttons.get(i).mySave == null));
            buttons.get(i).setLocation(x, y);
            add(buttons.get(i));
            y = y + 5*buttons.get(i).getHeight()/4;
        }
        repaint();
    }

    public void remove(EasyButton b){
        buttons.remove(b);
        recover();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(buttons.isEmpty()){
            return;
        }
        int dy = e.getUnitsToScroll();
        //System.out.println("onderkant button: " + (getY() + getHeight() - 5*Programma.paneHeight/40) + "  " + buttons.get(buttons.size() - 1).getY() + "  " + (!(dy < 0 && buttons.get(buttons.size() - 1).getY() <= getY() + getHeight() - 5*Programma.paneHeight/40 )));

        Point point = new Point(buttons.get(buttons.size() - 1).getX(), buttons.get(buttons.size() - 1).getY() + buttons.get(buttons.size() - 1).getHeight());

        if(this.contains(buttons.get(0).getLocation()) && contains(point)){
            return;
        }
        else if(buttons.get(0).getY() >= 0 && dy < 0 ){
            return;
        }
        else if(buttons.get(buttons.size() - 1).getY() < this.getHeight() - 5*buttons.get(0).getHeight()/4  && dy > 0 ){
            return;
        }


        for (GameSaveButton button : buttons){
            button.setLocation(button.getX(), button.getY() - 6*dy);
        }
        repaint();

    }
}
