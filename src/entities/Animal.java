package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import components.EasyImage;
import start.Programma;

public class Animal extends Entity {
	public EasyImage image;
	public EasyImage zoomImage;
	Timer fps;
	int vX = 0;
	int vY = 0;
	int aY = 0;
	int base = 0;
	int VY0 = 0;
	int vY0 = 0;
	boolean isMoving = false;
	boolean isZoom = false;
	Point headPoint;
	int headRadius;
	int headShotDamage = 5000;
	
	public Animal(int health, int height, String imageFile){
		super(health);
		image = new EasyImage(imageFile, -1, height);
		zoomImage = new EasyImage(imageFile, -1, 2*height);
		setSize(image.getSize());
		add(image);
	}
	
	public void zoom(boolean zoom){
		isZoom = zoom;
		if(zoom){
			remove(image);
			this.setSize(zoomImage.getSize());
			this.setLocation(this.getLocation().x*2, this.getLocation().y*2);
			add(zoomImage);
			//vX = vX*2;
			//vY = vY*2;
			///aY = aY/2;
			base = base*2;
		}
		else{
			remove(zoomImage);
			this.setSize(image.getSize());
			this.setLocation((this.getLocation().x)/2, this.getLocation().y/2);
			add(image);
			//vX = vX/2;
			//vY = vY/2;
			//aY = aY*2;
			base = base/2;
		}
		//System.out.println("location: " + this.getLocation().x + "     "  + this.getLocation().y);
		repaint();
	}

	public void start(){}
	
	public void setHead(int x, int y, int width, int height, int r){
		headPoint = new Point(2*x*getWidth()/width, 2*y*getHeight()/height);
		headRadius = 2*r*getHeight()/height;
	}
	
	public void decreaseHealth(int damage){
		super.decreaseHealth(damage);
		if(isDead){
			this.removeAll();
			this.repaint();
		}
	}
	
	public boolean headshot(Point p){
		if(headPoint == null){
			return false;
		}
		Point hP = new Point(this.getLocationOnScreen().x + headPoint.x, this.getLocationOnScreen().y + headPoint.y);
		int r = (int)Math.sqrt( Math.pow((p.x - hP.x), 2) + Math.pow((p.y - hP.y), 2));
		//System.out.println("headshot: " + headPoint.x + "   " +  headPoint.y + "   " +  hP.x + "   " + hP.y + "   " + r );
		if(r <= headRadius){
			decreaseHealth(headShotDamage);
			System.out.println("hs");
			return true;
		}
		else{
			return false;
		}
	}
	
	public void moveFPS(){
		if(isMoving){
			vY += aY;
			if(isZoom){
				this.setLocation(this.getLocation().x + 2*vX, this.getLocation().y + 2*vY);
			}
			else{
				this.setLocation(this.getLocation().x + vX, this.getLocation().y + vY);
				if(getLocation().x > Programma.paneWidth){
					decreaseHealth(800);
				}
			}
		}
	}
	
	public void moveWhenHit(){}
	
	public void moveWhenHearShots(){}


    public void stop() {
    }
}
