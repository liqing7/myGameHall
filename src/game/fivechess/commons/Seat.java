package game.fivechess.commons;

import java.awt.Rectangle;


public class Seat {
	
	//user on seat
	private ChessUser user;
	
	//seat range
	private Rectangle range;
	
	//seat side
	private String side;
	
	//seat width
	public final static int SEAT_WIDTH = 30;
	
	//seat height
	public final static int SEAT_HEIGHT = 30;
	
	public final static String LEFT = "left";
	
	public final static String RIGHT = "right";
	
	public Seat(ChessUser user, Rectangle range, String side) {
		this.user = user;
		this.range = range;
		this.side = side;
	}
	
	public ChessUser getUser() {
		return user;
	}

	public void setUser(ChessUser user) {
		this.user = user;
	}

	public Rectangle getRange() {
		return range;
	}

	public void setRange(Rectangle range) {
		this.range = range;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

}