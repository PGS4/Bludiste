package com.labyrint.main;

import javax.swing.ImageIcon;


public class Zed extends SolidObject{
	public Zed(int x, int y){
		super(x,y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/zed.png"));
		image = ii.getImage();
	}
}
