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
	public Zed() {
		ImageIcon obraz = new ImageIcon(this.getClass().getResource(obr));
		image = obraz.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		x = 384;
		y = 284;
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
