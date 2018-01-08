package domain;

import ui.Observer;

public interface Observable {
	void notifyUI();
	void registerObserver(Observer observer);
	void unregisterObserver(Observer observer);
}
