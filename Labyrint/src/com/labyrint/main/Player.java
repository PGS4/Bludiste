package com.labyrint.main;

import javax.swing.ImageIcon;

public class Player extends MovableObject {
	public Player(int x, int y) {
		super(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/star.png"));
		image = ii.getImage();
	}
}
