package entities;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Entity extends JPanel {
	final int MAX_HEALTH;
	public int health;
	public boolean isDead = false;
	//Timer attack;

	
	public Entity(int MaxHealth){
		MAX_HEALTH = MaxHealth;
		health = MaxHealth;
		setLayout(null);
		setFocusable(true);
		setOpaque(false);
	}
	
	public void heal(int healing){
		health += healing;
		if (health > MAX_HEALTH){
			health = MAX_HEALTH;
		}
	}
	
	public void decreaseHealth(int damage){
		//decreaseHealth(damage);
		health -= damage;
		if(health <= 0){
			isDead = true;
		}
	}
	
	//public void decreaseHealth(int damage){}
	

}
