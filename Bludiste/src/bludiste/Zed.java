package bludiste;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Zed {
	private String obr = "/zed.png";
	private float x;
	private float y;
	private int width;
	private int height;
	private Image image;
	public Zed(float x, float y) {
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
		return new Rectangle((int)x, (int)y, width, height);
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
