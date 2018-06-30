package components;

import sound.SoundMixer;
import start.Programma;

import javax.swing.border.Border;
import java.awt.event.MouseEvent;

public class MusicButton extends EasyButton {

    public MusicButton(String name, String com){
        super(name, com);
    }

    @Override
    public void setRightLayout(){
        setBackground(Programma.currentTheme.buttonBgColor);
        setForeground(Programma.currentTheme.foregroundColor);

        if(SoundMixer.isSongInRandomList(mouseCommand) && SoundMixer.isMusicRandom() && SoundMixer.getCurrentMusic() == mouseCommand){
            setBorder(doubleSelectedBorder);
        }
        else if(SoundMixer.getCurrentMusic() == mouseCommand && SoundMixer.musicProfile == "select"){
            setBorder(selectedBorder);
        }
        else if(SoundMixer.getCurrentMusic() == mouseCommand){
            setBorder(nonDoubleSelectedBorder);
        }
		/*else if(this.getMousePosition() != null){
			setBorder(mouseBorder);
		}  */
        else if(SoundMixer.isSongInRandomList(mouseCommand) && SoundMixer.isMusicRandom()){
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



    @Override
    public void mouseEntered(MouseEvent e) {
        setSelected(false);
        // TODO Auto-generated method stub
        setBorder(mouseBorder);
        if(actionOnRelease){
            setBackground(Programma.currentTheme.buttonBgPrColor);
        }
        if(mouseCommand != " "  && mouseCommand != SoundMixer.getCurrentMusic()){
            //System.out.println(mouseCommand);
            //System.out.println(SoundMixer.getCurrentMusic());
            SoundMixer.alternativeMusic(mouseCommand);
        }
        else if(mouseCommand == SoundMixer.getCurrentMusic() ){
            setBorder(mouseSelectedBorder);
        }

        //System.out.println(SoundMixer.getCurrentMusic());
        //System.out.println(mouseCommand);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        setSelected(false);
        // TODO Auto-generated method stub
        //b1.setBackground(Color.ORANGE);
        //b1.setBorder(border);
        if(mouseCommand != " " && mouseCommand != SoundMixer.getCurrentMusic()){ //
            SoundMixer.resumeMusic();
            //System.out.println("PreHear has to stop");
        }

        //e.getButton();
        //e.getButton();
        //System.out.println("Button: " + e.getButton());

        setRightLayout();
        //Programma.createAndShowGUI("settings");
		/*
		else if(mouseCommand == SoundMixer.getCurrentMusic()){
			b1.setBorder(selectedBorder);
		}
		else if(SoundMixer.isSongInRandomList(mouseCommand)){
			b1.setBorder(semiSelectedBorder);
		}*/
        //System.out.println(SoundMixer.getCurrentMusic());
        //System.out.println(mouseCommand);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(getMousePosition()!= null){
            setBackground(Programma.currentTheme.buttonBgColor);
            Programma.createAndShowGUI(command);
            //this.setFocusable(true);
            //this.repaint();
            //System.out.println("Continue game");
            if(mouseCommand != " "){
                //music = mouseCommand;
                SoundMixer.mainMusic(mouseCommand);
                //setBorder(selectedBorder);
                Programma.createAndShowGUI("chooseMusic");
                setRightLayout();
            }
            //else if(mouseCommand == SoundMixer.getCurrentMusic()){}
        }
        actionOnRelease = false;
    }
}
