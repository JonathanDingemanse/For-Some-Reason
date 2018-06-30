package components;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import start.Programma;

public class Disco extends JPanel implements ActionListener {
	Graphics2D g;
	Graphics z;
	int x;
	int y;
	int j = 0;
	int k = 0;
	//int strokeWidth = 5;
	ArrayList<Color> colors = new ArrayList<Color>();
	Timer timer;
	
	public Disco (){
		
		x = Programma.paneWidth;
		y = Programma.paneHeight;
		//this.z = z;

		//g.setColor(Color.WHITE);
		//g.setStroke(new BasicStroke(200));
		//g.fillOval(300, 300, 300, 300);
		colors.add(Color.RED);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		colors.add(Color.CYAN);
		colors.add(Color.BLUE);
		colors.add(Color.MAGENTA);
		timer = new Timer(10, this);
		timer.start();
		
		setSize(1920, 1080);
		setFocusable(true);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics z){
		//g = (Graphics2D)this.getGraphics();
		
		//System.out.println("TROOOLLLLLLLLLLLLLLL");
		int strokeWidth = 1;
		g = (Graphics2D)z;
		//g.setStroke(new BasicStroke(strokeWidth));
		
		//g.setColor(Color.WHITE);
		for(int i = 0; i*strokeWidth < (int)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/2; i++){
			strokeWidth = 1*(i + 1);
			g.setStroke(new BasicStroke(2*strokeWidth));
			g.setColor(colors.get((i + j) % 6));
			g.drawOval(x/2 - i*strokeWidth + strokeWidth*(k % 6)/2, y/2 - i*strokeWidth + strokeWidth*(k % 6)/2, 2*i*strokeWidth - strokeWidth*(k % 6), 2*i*strokeWidth - strokeWidth*(k % 6));
		}
		k++;
		if(k % 3 == 0){
			j++;
		}
		
		//g.drawLine(0, 0, 1920, 1080);
		//g.drawLine(1920, 0, 0, 1080);
		//g.drawOval(900, 900, 200, 300);
		//g.fillOval(0, 0, 300, 300);
		//g.drawOval(x, y, width, height);
		//double a = 0.0;
		/*
		while(true){
			a += 0.1;
			g.drawOval((int) Math.cos(a),(int) Math.sin(a), 200, 200);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(a);
		}*/
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.repaint();
	}



}
