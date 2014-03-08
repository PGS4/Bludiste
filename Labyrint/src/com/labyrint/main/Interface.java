package com.labyrint.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;


public class Interface extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -681939843334668445L;
	private Color pozadi = new Color(199, 199, 199);
	private Exit exit;
	private static Player player;
	private int levels;
	private Model model;
	private ArrayList<Zed> zdi;
	public Interface(){
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		model = new Model();
		player = Model.getPlayer();
		Timer time = new Timer(5,this);
		time.start();
	}
	public void paint(Graphics g){
		super.paint(g);
		addKeyListener(new TAdapter());
		Graphics2D g2d = (Graphics2D) g;
		zdi = Model.getZdi();
		exit = Model.getExit();
		levels = Model.getLevels();
		exit = Model.getExit();
		System.out.println(levels);
		if (levels < 3) {
			g2d.drawImage(player.getImage(), player.getX(), player.getY(), null);
			for(int i = 0; i<zdi.size(); i++){
				Zed azed = zdi.get(i);
				g2d.drawImage(azed.getImage(), azed.getX(), azed.getY(), null);
				g2d.drawRect(azed.getX(), azed.getY(), azed.getWidth(), azed.getHeight());
			}
			g2d.drawImage(exit.getImage(), exit.getX(), exit.getY(),null);
			g2d.drawRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		} else {
			g2d.drawString("KONEC HRY!!!", 350, 304);
		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				if (player.getDx() == -1) {
					player.setDx(0);
				}
			}
			if (key == KeyEvent.VK_DOWN) {
				if (player.getDy() == +1) {
					player.setDy(0);
				}
			}
			if (key == KeyEvent.VK_RIGHT) {
				if (player.getDx() == +1) {
					player.setDx(0);
				}
			}
			if (key == KeyEvent.VK_UP) {
				if (player.getDy() == -1) {
					player.setDy(0);
				}
			}
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				player.setDx(-1);
			}
			if (key == KeyEvent.VK_RIGHT) {
				player.setDx(+1);
			}
			if (key == KeyEvent.VK_UP) {
				player.setDy(-1);
			}
			if (key == KeyEvent.VK_DOWN) {
				player.setDy(+1);
			}
		}
	}
	public static Player getMovablePlayer(){
		return player;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
}
