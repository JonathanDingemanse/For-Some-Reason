package old;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EasyImage extends JLabel {
	Image im;
	public static final int ALIGN = 5678; 
	
	public EasyImage(String path,int width, int height ){
		this.setOpaque(true);
		
		this.setHorizontalAlignment(CENTER);
		
		BufferedImage startScreenBackground;

		try {
			startScreenBackground = ImageIO.read(new File(path));
			
			im = (Image) startScreenBackground;
			Image im2;
			if(width < 0 || height < 0){
				im2 = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			}
			else if((int)(1000*im.getWidth(null)/im.getHeight(null)) < (int)(1000*width/height)){
				im2 = im.getScaledInstance(width, -3, Image.SCALE_SMOOTH);
				//System.out.println((int)(1000*im.getWidth(null)/im.getHeight(null)));
			}
			else{
				im2 = im.getScaledInstance(-3, height, Image.SCALE_SMOOTH);
			}
			
			this.setIcon(new ImageIcon(im2));	
			
			if(width < 0 || height < 0){
				this.setSize(im2.getWidth(null), im2.getHeight(null));
			}
			else{
				this.setSize(width, height);
			}
			//im.getScaledInstance(, arg1, arg2)
			
			//picLabel = new JLabel(new ImageIcon(im2));
			//int w = im2.getWidth(null);
		    //int h = im2.getHeight(null);
		    //picLabel.setOpaque(true);
			//picLabel.setSize(w, h);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(("picture doesn't load"));
			e.printStackTrace();
		}
		
	}
	
	public JLabel getScaledInstance(int width, int height){
		Image ima;
		/*
		if(width == ALIGN){
			width = im.getWidth(null)*im.getHeight(null)/height;
		} */
		if(width < 0 || height < 0){
			ima = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}
		else if((int)(1000*im.getWidth(null)/im.getHeight(null)) < (int)(1000*width/height)){
			ima = im.getScaledInstance(width, -3, Image.SCALE_SMOOTH);
			//System.out.println((int)(1000*im.getWidth(null)/im.getHeight(null)));
		}
		else{
			ima = im.getScaledInstance(-3, height, Image.SCALE_SMOOTH);
		}
		JLabel instance = new JLabel(new ImageIcon(ima), CENTER );
		instance.setOpaque(true);
		if(width < 0 || height < 0){
			instance.setSize(ima.getWidth(null), ima.getHeight(null));
		}
		else{
			instance.setSize(width, height);
		}
		//instance.setHorizontalAlignment(CENTER);
		return instance;
		
	}


}
