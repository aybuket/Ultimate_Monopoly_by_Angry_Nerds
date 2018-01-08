package domain.bot;

import java.util.ArrayList;
import ui.BotUI;
import ui.Observer;
import domain.Observable;

public class Bot extends Thread implements Observer, Observable{

	String name;
	boolean moneyGain;
	boolean moneyLose;
	boolean jail;
	boolean inactive;
	boolean bankrupt;
	boolean winner;
	private static Bot instance;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public static Bot getInstance() {
		if (instance == null) {
			instance = new Bot("GameBot");
		}
		return instance;
	}

	public Bot(String name) {
		super(name);
		registerObserver(BotUI.getInstance());
	}

	public void run() {
		while(true) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void moneyGainReact() {
		if(moneyGain)
		System.out.println("oo zenginiz");
		notifyUI();
	}

	public void moneyLoseReact() {
		System.out.println("tüh para gitti");
		notifyUI();
	}

	public void jailReact() {
		System.out.println("düştüm mapus damlarına, öğüt veren çok olur");
		notifyUI();
	}

	public void inactiveReact() {
		System.out.println("bot fark etti inactivityi");
		notifyUI();
	}

	public void bankruptReact() {
		System.out.println("battık anacım");
		notifyUI();
	}

	public void winnerReact() {
		System.out.println("heyt bee kazandın");
		notifyUI();
	} 
	
	@Override
	public void update(Object object, String message) {
		if (message.equals("moneyGained")) {
			if((boolean)object) {
				moneyGain = (boolean)object;
				moneyGainReact();
				moneyGain = false;
			}
		}
		if (message.equals("moneyLost")) {
			if((boolean)object) {
				moneyLose = (boolean)object;
				moneyLoseReact();
				moneyLose = false;
			}
		}
		if (message.equals("jailed")) {
			if((boolean)object) {
				jail = (boolean)object;
				jailReact();
				jail = false;
			}
		}
		if (message.equals("bankrupt")) {
			if((boolean)object) {
				bankrupt = (boolean)object;
				bankruptReact();
				bankrupt = false;
			}
		}
		if (message.equals("winner")) {
			if((boolean)object) {
				winner = (boolean)object;
				winnerReact();	
				winner = false;
			}
		}
		if (message.equals("reactInactive")){
			if((boolean)object) {
				inactive = (boolean)object;
				inactiveReact();
				inactive = false;
			}
		}

	}
	
	public String toString() {
		String str = "Name: "+getName()+"\n";
		return str;
	}

	@Override
	public void notifyUI() {
		for(Observer obs : observers) {
			obs.update(new boolean[] {moneyGain, moneyLose, jail, bankrupt, winner, inactive}, "animate");
		}	
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		int observerIndex = observers.indexOf(observer);
		observers.remove(observerIndex);	
	}
}
