package bludiste;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Zed {
	private String obr = "/zed.png";
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	public Zed(int x, int y) {
		ImageIcon obraz = new ImageIcon(this.getClass().getResource(obr));
		image = obraz.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
		
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	public Image getImage() {
		return image;
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
