package bludiste;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Exit {
	private Image image;
	private String obr = "/exit.png";
	private int width;
	private int height;
	private int x;
	private int y;
	public Exit(int x, int y){
		ImageIcon ii = new ImageIcon(this.getClass().getResource(obr));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Image getImage(){
		return image;
	}
}
