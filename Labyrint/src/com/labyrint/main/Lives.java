package com.labyrint.main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Lives {
	private Image image;
	public Lives(){
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/life.png"));
		image = ii.getImage();
	}
	public Image getImage(){
		return image;
	}
}
