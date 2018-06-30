package start;

import java.util.ArrayList;
import components.*;

import javax.swing.JLabel;

import start.GameScreen;

public class TalkScreen extends GameScreen {
	ArrayList<Message> messages;
	
	public TalkScreen(String name, String...thingsToSay){
		super(thingsToSay.length, 1);
		setAns(thingsToSay);
		//String[] textCommands = thingsToSay;
		
		setCommands(thingsToSay);
		setMainText(name);
		
		messages = new ArrayList<Message>();
		
		//Message m = new Message("sdfklsdfklsfdklsdfj", Message.out);
		//m.setLocation(400,400);
		//this.add(m);
		//add(m);
		//messages.add(m);
		//messages.get(messages.size()-1).setLocation(400,400);   //Programma.paneWidth - messages.get(messages.size()-1).getWidth(), Programma.paneHeight/2);
		//add(messages.get(messages.size()-1));
		repaint();
		
		//Message message = new Message("mjam wauw mjam wauw mjam wauw mjam wauw", Message.out);
		//message.setLocation(400, 500);
		//add(message);
	}
	
	public void addMessage(String text, int inout){
		removeAll();
		if(!isMainTextHidden){
			add(horLine);
			add(mainText);
		}				
		Message m = new Message(text, inout);
		//m.setY( 20*Programma.paneHeight/24 - messages.get(0).getHeight() - 20);
		//System.out.println(m.getLocation().y);
		//m.setLocation(400,400);
		//this.add(m);
		//add(m);
		messages.add(m);
		int j;
		
		for(int i = messages.size() - 1; i >= 0; i-- ){
			j = messages.size() - 1 -i;
			messages.get(i).setY(20*Programma.paneHeight/24 - messages.get(i).getHeight()*(j+1) - 20*j -20);
			if(messages.get(i).getLocation().y < 0 && isMainTextHidden){
				messages.remove(i);
			}
			else if(messages.get(i).getLocation().y < 2*Programma.paneHeight/22){
				messages.remove(i);
			}
			else{
				this.add(messages.get(i));
			}
			//System.out.print(20*Programma.paneHeight/24 - messages.get(i).getHeight()*(i+1) - 20*i -20);
			//System.out.println("    " + i);
			
		}
		//messages.get(messages.size()-1).setLocation(400,400);   //Programma.paneWidth - messages.get(messages.size()-1).getWidth(), Programma.paneHeight/2);
		
		for(int i = 0; i < buttonList.size(); i++){
			add(buttonList.get(i));
		}
		
		backGr.setBounds(0, 20*Programma.paneHeight/24 , Programma.paneWidth, Programma.paneHeight);
		this.add(horLine2);
		

		//add(esc);
		//add(gameKeys);
		add(backGr);
		add(mainImage);
		repaint();
	}
	
	public void recover(){
		this.removeAll();
		for(int i = 0; i < buttonList.size(); i++){
			add(buttonList.get(i));
		}
		for(int i = 0; i < messages.size(); i++){
			int j = messages.size() - 1 -i;
			messages.get(i).setY(20*Programma.paneHeight/24 - messages.get(i).getHeight()*(j+1) - 20*j -20);
			add(messages.get(i));
		}
		if(picNum == 1){
			backGr.setBounds(0, 20*Programma.paneHeight/24 , Programma.paneWidth, Programma.paneHeight);
			this.add(horLine2);
		}
		//add(esc);
		//add(gameKeys);
		if(!isMainTextHidden){
			add(mainText);
			add(horLine);
		}
		add(backGr);
		if(picNum == 1 && mainImage != null	){
			add(mainImage);
		}
		//this.add(Programma.picLabel);
		this.repaint(); 
	}
	
	public void setComAndAns(String[] commands, String [] answers){
		if(commands.length == answers.length && answers.length < 5){
			setQ(answers.length);
			setAns(answers);
			setCommands(commands);
		}
	}
	
	public void setThingsToSay(String...thingsToSay){
		setQ(thingsToSay.length);
		setAns(thingsToSay);
		setCommands(thingsToSay);
	}
	
	public void reset(){
		messages.clear();
	}
		
	


}
