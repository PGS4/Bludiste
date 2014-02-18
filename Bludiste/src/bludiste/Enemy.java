/*package bludiste;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy implements Runnable {
	Thread nepritel;
	private Image image;
	private String obr = "/enemy.png";
	public int x;
	public int y;
	private int width;
	private int height;
	private int smer;
	public int dx;
	public int dy;

	public Enemy(int x, int y, String name) {
		nepritel = new Thread(name);
		nepritel.run();
		nepritel.getName();
		ImageIcon ii = new ImageIcon(this.getClass().getResource(obr));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
		
	}

	public void move() {
		getMovement();
		x += dx;
		y += dy;
	}

	public void getMovement() {
		Random rand = new Random();
		smer = rand.nextInt(4) + 1;
			try {
				nepritel.wait(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Error: " + e);
			}
		if (smer == 1) {
			dx = -1;
			dy = 0;
		}
		if (smer == 2) {
			dx = 1;
			dy = 0;
		}
		if (smer == 3) {
			dx = 0;
			dy = -1;
		}
		if (smer == 4) {
			dx = 0;
			dy = 1;
		}
	}
	
	public void notifyEnemy(){
		nepritel.notify();
	}
	
	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		move();
	}
	public void destroyEnemy(){
		try {
			nepritel.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}*/