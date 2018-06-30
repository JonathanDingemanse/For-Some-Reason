package entities;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import timers.*;
import components.*;
import start.Programma;

public class Player extends Entity implements ActionListener {
	Death thirstiness;
	Death hunger;
	JPanel hpbar;
	JLabel label;
	boolean isActive;
	public boolean hasHealing =  true;
	public PauseTimer healing;
	public boolean hasHedgehog = false;
	public boolean hasGun = true;
	public boolean hasGamePC = false;
	public boolean hasMetHelgaBefore = false;
	
	public Border border;
	public Border hpborder = BorderFactory.createLineBorder(Color.GRAY,5);
	static int PLAYER_HEALTH = 100;
	int disHeal = 0;
	
	public final int MAX_AMMO = 100;
	public int ammo = MAX_AMMO;
	public ArrayList<JLabel> bullets = new ArrayList<JLabel>();
	
	public Player(){
		super(PLAYER_HEALTH);
		isActive = true;
		//hunger = new Death("You starved to death", 90000000);
		//thirstiness = new Death("You died by thirst", 70000000);
		//health = 1000;
		//System.out.println(health);
		border = BorderFactory.createLineBorder(Programma.currentTheme.buttonBorderColor,8);
		//super.decreaseHealth(1000);
		//System.out.println(health);
		this.setLayout(null);
		healing = new PauseTimer(200,this);
		
		//hunger.pauseDying();
		setOpaque(true);
		setBackground(Color.BLACK);
		setBorder(border);
		setSize(Programma.paneWidth/10, Programma.paneHeight/11);
		setLocation(Programma.paneWidth/25, 24*Programma.paneHeight/25 - this.getHeight());
		
		label = new JLabel("Health: " + health, JLabel.CENTER);	
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
		label.setFont(Programma.helvetica64);
		label.setSize(4*this.getWidth()/5, this.getHeight()/3);
		label.setLocation(this.getWidth()/10, this.getHeight()/8);
		add(label);
		
		hpbar = new JPanel();
		hpbar.setOpaque(true);
		//hpbar.setBorder(hpborder);
		//hpbar.setBackground(Color.GREEN);
		hpbar.setBackground(new Color((MAX_HEALTH - health)*255/MAX_HEALTH, health*255/MAX_HEALTH, 0));
		hpbar.setSize(3*health*this.getWidth()/(4*MAX_HEALTH), this.getHeight()/3);
		System.out.println(this.getWidth());
		hpbar.setLocation(this.getWidth()/8, 3*this.getHeight()/6);
		add(hpbar);
		
		JPanel hpbarBackground = new JPanel();
		hpbarBackground.setOpaque(true);
		hpbarBackground.setBorder(hpborder);
		hpbarBackground.setBackground(Color.DARK_GRAY);
		hpbarBackground.setSize(3*this.getWidth()/4 +10, this.getHeight()/3 +10);
		hpbarBackground.setLocation(this.getWidth()/8 -5, 3*this.getHeight()/6 -5);
		add(hpbarBackground);
		healing.start();
		this.repaint();
		
		//EasyImage bullet = new EasyImage("Images/bullet.png",-1, Programma.paneHeight/108);
		//int w = bullet.getWidth();
		
		
		
		
		//thirstiness.pauseDying();
		
	}
	public void recolor(){ BorderFactory.createLineBorder(Programma.currentTheme.buttonBorderColor,8);  }
	
	public void setActive(boolean active){
		
		if(active && !isActive){
			if(thirstiness != null){
				thirstiness.resumeDying();
			}

			healing.start();
			hasHealing =  true;
		}
		else if(isActive && !active){
			if(thirstiness != null){
				thirstiness.pauseDying();
			}
			healing.stop();
			hasHealing =  false;
		}
		isActive = active;
		
		//System.out.println(isActive);
	}

	public int constrain(int x, int a, int b){
		if(x < a){
			return a;
		}
		else if(x > b){
			return b;
		}
		else {
			return x;
		}
	}
	
	public void heal(int healing){
		super.heal(healing);



		int red = (MAX_HEALTH - health)*255/MAX_HEALTH;

		hpbar.setSize(3*health*this.getWidth()/(4*MAX_HEALTH), this.getHeight()/3);
		hpbar.setBackground(new Color(constrain((MAX_HEALTH - health)*255/MAX_HEALTH, 0, 255), constrain(health*255/MAX_HEALTH,0, 255), 0));
		label.setText("Health: " + health);
	}
	public void decreaseHealth(int damage){
		super.decreaseHealth(damage);
		disHeal = 8;
		
		if(health <= 0){			
			Programma.gameContainer.stopShooterScreen();
			if(hasGun){
				Programma.createAndShowGUI("*You were killed");
			}
			else{
				Programma.createAndShowGUI("*You were killed by a bear.<br>Hint: you have to find a weapon somewere in the game.");
			}
			
		}
		else{
			hpbar.setSize(3*health*this.getWidth()/(4*MAX_HEALTH), this.getHeight()/3);		
			hpbar.setBackground(new Color((MAX_HEALTH - health)*255/MAX_HEALTH, health*255/MAX_HEALTH, 0));
			label.setText("Health: " + health);
			
		}
	}
	public void revive(){
		if(healing != null){
			healing.stop();
		}
		else {
			healing = new PauseTimer(200,this);
		}
		healing.restart();
		health = PLAYER_HEALTH;
		hpbar.setSize(3*health*this.getWidth()/(4*MAX_HEALTH), this.getHeight()/3);		
		hpbar.setBackground(new Color((MAX_HEALTH - health)*255/MAX_HEALTH, health*255/MAX_HEALTH, 0));
		label.setText("Health: " + health);
		hasHealing = true;
		isActive = true;
		isDead = false;
		ammo = MAX_AMMO;
		this.repaint();
	}
	
	public void reset(){
		hasHedgehog = false;
		hasGun = false;
		hasGamePC = false;
		hasMetHelgaBefore = false;
		revive();
	}
	public void stop(){
		hunger.stop();
		thirstiness.stop();
		health = 0;
		removeAll();
		this.repaint();
	}
	/*public void repaint(){
		//hpbar.setSize(3*health*this.getWidth()/(4*maxHealth), this.getHeight()/3);
		//hpbar.setBackground(new Color((maxHealth - health)*255/maxHealth, health*255/maxHealth, 0));
		label.setText("Health: " + super.health);
		this.repaint();
	} */
	
	public void eat(){
		hunger.restart();
	}
	
	public void drink(){
		thirstiness.restart();
	}
	
	public void drinkSea(){
		if(thirstiness != null){
			thirstiness.stop();
			thirstiness.dontDie();
		}
		thirstiness = new Death("You died by thirst. <br> (drinking salt water wasn't that smart)", 1700);
		thirstiness.restart();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(health < MAX_HEALTH && hasHealing && disHeal == 0){

			heal(3);
		}

		//hasHealing = true;
		if(disHeal > 0){
			disHeal--;
		}
		else if(disHeal < 0){
			disHeal = 0;
		}

		//System.out.println("                   " + health);
	}
	
	

}
