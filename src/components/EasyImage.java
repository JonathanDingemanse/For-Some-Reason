package components;

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
//import start.Programma;

public class EasyImage extends JLabel {
	public BufferedImage im;
	public static final int ALIGN = 5678; 
	
	public EasyImage(String path, int width, int height ){
		//this.setOpaque(true);
		

		
		//BufferedImage im;

		try {
			//startScreenBackground = ImageIO.read(new File(path));

			im = ImageIO.read(new File(path));

			//this(im, width, height);

			//im = (Image) startScreenBackground;

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
			this.setHorizontalAlignment(CENTER);
			
			if(width < 0 || height < 0){
				this.setSize(im2.getWidth(null), im2.getHeight(null));
			}
			else{
				this.setSize(width, height);
			}
			//im.getScaledInstance(, arg1, arg2)
			im2.flush();

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

	public EasyImage(BufferedImage bi, int width, int height){
		im = bi;
		this.setHorizontalAlignment(CENTER);

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
		this.setHorizontalAlignment(CENTER);

		if(width < 0 || height < 0){
			this.setSize(im2.getWidth(null), im2.getHeight(null));
		}
		else{
			this.setSize(width, height);
		}
		//im.getScaledInstance(, arg1, arg2)
		im2.flush();
	}


	public void scaleImage(double factor){
        Image im2;
		im2 = im.getScaledInstance((int)(getWidth()*factor), (int)(getHeight()*factor), Image.SCALE_FAST);
        this.setSize(im2.getWidth(null), im2.getHeight(null));
        System.gc();
        this.setIcon(new ImageIcon(im2));
		System.gc();
    }
	
	public JLabel copy(){
		//JLabel l = new JLabel(im);
		JLabel l = new JLabel(this.getIcon());
		l.setSize(this.getSize());
		return l;
	}

	public EasyImage getScaledInstance(int width, int height){
		return new EasyImage(im, width, height);
	}

	public EasyImage getScaledInstance(double factor){
		return getScaledInstance((int)(getWidth()*factor), (int)(getHeight()*factor));
	}

	public EasyImage getVerticalInvertedInstance(){
		//BufferedImage imt = im.getSubimage(0,0, im.getWidth(), im.getHeight());
		BufferedImage imt = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < im.getWidth(); x++){

			for(int y = 0; y < im.getHeight(); y++){
				imt.setRGB(x, y, im.getRGB(im.getWidth()-1-x, y) );
			}
		}
		return new EasyImage(imt, this.getWidth(), this.getHeight());
	}

	public EasyImage getTransponedInstance(){
		//BufferedImage imt = im.getSubimage(0,0, im.getWidth(), im.getHeight());
		BufferedImage imt = new BufferedImage(im.getHeight(), im.getWidth(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < im.getWidth(); x++){

			for(int y = 0; y < im.getHeight(); y++){
				imt.setRGB(y, x, im.getRGB(x, y));
			}
		}
		return new EasyImage(imt, this.getHeight(), this.getWidth());
	}

	public void delete(){
		removeAll();
		setIcon(null);
		im.flush();
		System.gc();
	}
	/*
	public JLabel getScaledInstance(int width, int height){
		Image ima;
		/*
		if(width == ALIGN){
			width = im.getWidth(null)*im.getHeight(null)/height;
		}
		if(width < 0 || height < 0){
			ima = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}
		else if((1000*im.getWidth(null)/im.getHeight(null)) < (int)(1000*width/height)){
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

	public JLabel getScaledInstance(int scale){
		return getScaledInstance(scale*this.getWidth(), scale*this.getWidth());
	}*/


}
