package game.fivechess.commons;

import javax.swing.text.html.AccessibleHTML.TableElementInfo.TableAccessibleContext;

import commons.User;


public class ChessUser extends User {
	
	//is ready?
	private boolean ready;
	
	public boolean isReady() {
		return ready;
	}
	
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	public String toString() {
		return this.getName();
	}
	
	//
	public boolean hasSitDown(Table[][] tables) {
		
	}
}