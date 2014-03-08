package com.labyrint.main;

import javax.swing.ImageIcon;

public class Exit extends SolidObject{
	public Exit(int x, int y){
		super(x,y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/exit.png"));
		image = ii.getImage();
	}
}
