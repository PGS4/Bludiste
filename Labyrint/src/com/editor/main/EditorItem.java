package com.editor.main;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EditorItem extends JButton{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Image img;
		private ImageIcon obr;
		private String value;
		private int width, height;
		private int x, y;
		public EditorItem(URL obrurl, String value, int x, int y){
			super(value);
			this.value = value;
			this.x = x;
			this.y = y;
			obr = new ImageIcon(obrurl);
			img = obr.getImage();
			width = img.getWidth(null);
			height = img.getHeight(null);
			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
			setBounds(x, y, width, height);
		}
		
		public Image getImage(){
			return img;
		}
		
		public String getValue(){
			return value;
		}
		
		public int getWidth(){
			return width;
		}
		
		public int getHeight(){
			return height;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
}
