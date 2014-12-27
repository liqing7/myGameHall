package game.fivechess.commons;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import commons.User;
import commons.XStreamUtil;


public class Table {
	
	//begin x pos
	private int beginX;
	
	//begin y pos
	private int beginY;
	
	//table image
	private String tableImage;
	
	//table no.
	private int tableNumber;
	
	//image width
	public final static int DEFAULT_IMAGE_WIDTH = 140;
	
	//image height
	public final static int DEFAULT_IMAGE_HEIGHT = 140;
	
	//Table range
	private Rectangle rangeRectangle;
	
	//left seat
	private Seat leftSeat;
	
	//right seat
	private Seat rightSeat;
	
	public Table(int beginX, int beginY, int tableNumber) {
		this.beginX = beginX;
		this.beginY = beginY;
		
		this.tableNumber = tableNumber;
		this.rangeRectangle = new Rectangle(beginX, beginY, DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
		
		this.leftSeat = new Seat(null, new Rectangle(getLeftSeatBeginX(), getLeftSeatBeginY(), 
				Seat.SEAT_WIDTH, Seat.SEAT_HEIGHT), Seat.LEFT);
		this.rightSeat = new Seat(null, new Rectangle(getRightSeatBeginX(), getRightSeatBeginY(), 
				Seat.SEAT_WIDTH, Seat.SEAT_HEIGHT), Seat.RIGHT);
	}
	
	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public String getTableImage() {
		return tableImage;
	}

	public void setTableImage(String tableImage) {
		this.tableImage = tableImage;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	//get right seat begin x pos
	public int getRightSeatBeginX() {
		return this.beginX + 101;
	}
	
	//get right seat begin y pos
	public int getRightSeatBeginY() {
		return this.beginY + 52;
	}

	//get left seat begin x pos
	public int getLeftSeatBeginX() {
		return this.beginX + 12;
	}
	
	//get left seat begin y pos
	public int getLeftSeatBeginY() {
		return this.beginY + 52;
	} 
}