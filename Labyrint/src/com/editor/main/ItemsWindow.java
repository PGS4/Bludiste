package com.editor.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ItemsWindow extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4778403440639383296L;
	private static String selectedValue;
	
	public ItemsWindow(){
		selectedValue = "#";
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		EditorItem zed = new EditorItem(this.getClass().getResource("/zed.png"), "#", 32, 32);
		EditorItem enemy = new EditorItem(this.getClass().getResource("/enemy.png"), "E", 32*3, 32);
		EditorItem exit = new EditorItem(this.getClass().getResource("/exit.png"), "!", 32*5, 32);
		g2d.drawImage(zed.getImage(), zed.getX(), zed.getY(), null);
		g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
		g2d.drawImage(exit.getImage(), exit.getX(), exit.getY(), null);
		zed.addActionListener(this);
		enemy.addActionListener(this);
		exit.addActionListener(this);
		add(zed);
		add(exit);
		add(enemy);
		g2d.setColor(new Color(0.4f,0.4f,1.0f,0.4f));
		if(selectedValue == "#"){
			g2d.fillRect(zed.getX(), zed.getY(), zed.getWidth(), zed.getHeight());
		}
		if(selectedValue == "E"){
			g2d.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
		}
		if(selectedValue == "!"){
			g2d.fillRect(exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("E")){
			selectedValue = "E";
		}
		if(e.getActionCommand().equals("#")){
			selectedValue = "#";
		}
		if(e.getActionCommand().equals("!")){
			selectedValue = "!";
		}
		repaint();
	}
	
	public static String getSelectedValue(){
		return selectedValue;
	}
}
