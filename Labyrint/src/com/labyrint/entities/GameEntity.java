package com.labyrint.entities;

import java.awt.Image;
import java.awt.Rectangle;

public class GameEntity{
	int x, y;
	Image image;
	int width, height;
	int direction, blockX, blockY;
	public GameEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
	
	public int getWidth(){
		width = image.getWidth(null);
		return width;
	}
	
	public int getHeight(){
		height = image.getHeight(null);
		return height;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,getWidth(),getHeight());
	}
	public int getBlockX2(){
		blockX = ((x+getWidth()) - ((x+getWidth())%32))/32;
		return blockX;
	}
	public int getBlockY2(){
		blockY = ((y+getHeight()) - ((y+getHeight())%32))/32;
		return blockY;
	}
	public int getBlockX(){
		blockX = ((x) - ((x)%32))/32;
		return blockX;
	}
	public int getBlockY(){
		blockY = ((y) - ((y)%32))/32;
		return blockY;
	}
}
