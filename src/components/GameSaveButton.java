package components;

import saving.GameSave;
import saving.Saver;
import sound.SoundMixer;
import start.Programma;

import java.awt.event.MouseEvent;

public class GameSaveButton extends EasyButton {
    public GameSave mySave;

    public GameSaveButton(GameSave save){
        super(save.name, save.name);
        mySave = save;
        setSize(Programma.paneWidth/3, Programma.paneHeight/10);
        setFont(Programma.helvetica32);
        setRightLayout();
        setOpaque(true);
    }

    @Override
    public void setRightLayout(){
        if(actionOnRelease){
            setBackground(Programma.currentTheme.buttonBgPrColor);
        }
        else {
            setBackground(Programma.currentTheme.buttonBgColor);
        }
        setForeground(Programma.currentTheme.foregroundColor);


        if(mySave == Saver.currentGame && getMousePosition() != null){
            setBorder(mouseSelectedBorder);
        }
        else if((mySave == Programma.selectedGameSave && getMousePosition() != null)){
            setBorder(mouseSemiSelectedBorder);
        }
        else if(mySave == Saver.currentGame && mySave == Programma.selectedGameSave){
            setBorder(doubleSelectedBorder);
        }
        else if(mySave == Saver.currentGame){
            setBorder(selectedBorder);
        }
        else if(mySave == Programma.selectedGameSave){
            setBorder(semiSelectedBorder);
        }
        else if(this.getMousePosition() != null){
            setBorder(mouseBorder);
        }
        else{
            setBorder(border);
        }
        //setLayout(null);
        //setFocusable(true);
        //addMouseListener(this);
        this.repaint();
    }

    public void paintHeader(){
        Programma.header.repaint();
        if(this.getY() > 0 && this.getY() < 2*Programma.paneHeight/10){

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setRightLayout();
        paintHeader();
    }
    @Override
    public void mouseExited(MouseEvent e) {
        setRightLayout();
        paintHeader();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        setRightLayout();
        paintHeader();
        //System.out.println(((double)getY())/Programma.paneHeight);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(getMousePosition()!= null){
            setBackground(Programma.currentTheme.buttonBgColor);
            Programma.selectedGameSave = mySave;
            Programma.createAndShowGUI("load");
            //this.setFocusable(true);
            //this.repaint();
            //System.out.println("Continue game");

            //else if(mouseCommand == SoundMixer.getCurrentMusic()){}
        }
        actionOnRelease = false;
        setRightLayout();
    }


}
