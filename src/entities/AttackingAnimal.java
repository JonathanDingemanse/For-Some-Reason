package entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import start.Programma;

public class AttackingAnimal extends Animal implements ActionListener {
	Timer attack;
	int attackPeriod;
	int attackSize;
	
	public AttackingAnimal(int health, int height, String imageFile, int attackPeriod, int attackSize){
		super(health, height, imageFile);
		this.attackPeriod = attackPeriod;
		this.attackSize = attackSize;
		attack = new Timer(attackPeriod, this);
		attack.setInitialDelay(attackPeriod/4);
		attack.restart();
	}
	
	public void decreaseHealth(int damage){
		super.decreaseHealth(damage);
		if(isDead){
			if(attack != null){
				attack.stop();
			}
		}
	}

	@Override
	public void stop(){
		attack.stop();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(Programma.gameContainer.player.health > 0 && Programma.gameContainer.player.isActive){
			Programma.gameContainer.player.decreaseHealth(attackSize);
			//System.out.println(attackSize);

		}
	}
}
