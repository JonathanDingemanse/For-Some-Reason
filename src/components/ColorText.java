package components;

import javax.swing.*;
import java.awt.*;

public class ColorText extends JPanel {
    public JLabel text1;
    public JLabel text2;
    int shift;

    public ColorText(String text, Color color1, Color color2, Font font, int shift){
        this.shift = shift;
        setLayout(null);
        setOpaque(false);

        text1 = new JLabel(text, JLabel.CENTER);
        text1.setFont(font);
        text1.setForeground(color1);

        text2 = new JLabel(text, JLabel.CENTER);
        text2.setFont(font);
        text2.setForeground(color2);
        text2.setLocation(shift, shift);
        add(text1);
        add(text2);
        repaint();
    }

    public void  setColors(Color color1, Color color2){
        text1.setForeground(color1);
        text2.setForeground(color2);
        repaint();
    }

    public void setText(String text){
        text2.setText(text);
        text1.setText(text);
    }

    @Override
    public void setSize(int width, int height){
        super.setSize(width, height);
        text1.setSize(width - shift, height - shift);
        text2.setSize(width - shift, height - shift);
    }


}
