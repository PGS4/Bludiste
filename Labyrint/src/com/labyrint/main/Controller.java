package com.labyrint.main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Controller implements ActionListener {
	private Model model;
	private Interface grafika;
	private Player player;
	private int levels;
	private ArrayList<Zed> zdi;
	private Zed zed;
	private Rectangle r1, r2, r3;
	private Exit exit;

	public Controller() {
		model = new Model();
		grafika = new Interface();
		Timer timer = new Timer(5, this);
		timer.start();
	}

	public void checkCollisions() {
		player = Interface.getMovablePlayer();
		levels = Model.getLevels();
		zdi = Model.getZdi();
		exit = Model.getExit();
		if (levels < 3) {
			r1 = player.getBounds();
			for (int i = 0; i < zdi.size(); i++) {
				zed = zdi.get(i);
				r2 = zed.getBounds();
				if (r1.intersects(r2)) {
					if ((player.getX() + player.getWidth() - 2) < zed.getX()) {
						player.x = player.getX() - 1;
					}
					if (player.getX() > (zed.getX() + zed.getWidth() - 2)) {
						player.x = player.getX() + 1;
					}
					if ((player.getY() + player.getHeight() - 2) < zed.getY()) {
						player.y = player.getY() - 1;
					}
					if (player.getY() > (zed.getY() + zed.getHeight() - 2)) {
						player.y = player.getY() + 1;
					}
				}
			}
			r3 = exit.getBounds();
			if (r1.intersects(r3)) {
				model.setNewLevel();
				grafika.repaint();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		checkCollisions();
		player.move();
	}
}
