package bludiste;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
	private String pl = "/star.png";
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	private Zed zed;
	public Player() {
		zed = new Zed();
		ImageIcon ii = new ImageIcon(this.getClass().getResource(pl));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		
		x = 40;
		y = 60;
	}

	public void move() {
		y += dy;
		x += dx;
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
	public void checkCollissions() {
		 Rectangle r1 = getBounds();
		 Rectangle r2 = zed.getBounds();
		 if(r1.intersects(r2)){
			if(x<zed.getX()){
				x = x-5;
			}
			if(x>(zed.getX()+zed.getWidth()-2)){
				x = x+5;
			}
			if(y<zed.getY()){
				y = y-5;
			}
			if(y>(zed.getY()+zed.getHeight()-2)){
				y = y+5;
			}
		 }
	 }

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {

			dx = -1;
		}
		if (key == KeyEvent.VK_RIGHT){
			dx = +1;
		}
		if (key == KeyEvent.VK_UP){
			dy = -1;
		}
		if (key == KeyEvent.VK_DOWN){
			dy = +1;
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			dx = 0;
		}
		if(key == KeyEvent.VK_DOWN){
			dy = 0;
		}
		if(key == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		if(key == KeyEvent.VK_UP){
			dy = 0;
		}
	}
	public int getWidth() {
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Rectangle getBounds(){
		return new Rectangle (x, y, width, height);
	}
}
