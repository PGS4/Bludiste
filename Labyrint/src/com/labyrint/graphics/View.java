package com.labyrint.graphics;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.labyrint.entities.Player;
import com.labyrint.main.Interface;
import com.labyrint.main.Model;

public class View {

	private static Player player;
	private static ArrayList<String> lines = new ArrayList<String>();

	public static Area getClipingRight() {
		int l = 0;
		Area cliping;
		player = Interface.getMovablePlayer();
		lines = Model.getLines();
		while (!String.valueOf(
				lines.get(player.getBlockY2()).charAt(
						player.getBlockX() + l + 1)).contains("#")) {
			l += 1;
		}
		if (l > 0) {
			Rectangle2D pokus = new Rectangle(player.getX(), player.getY() - 9,
					l * 32 + 20 + player.getWidth()
							- (player.getX() - player.getBlockX() * 32), 40);
			cliping = new Area(pokus);
			return cliping;
		} else {
			Rectangle2D nic = new Rectangle(player.getX(), player.getY(),
					player.getWidth(), player.getHeight());
			return new Area(nic);
		}
	}

	public static Area getClipingUp() {
		int l = 0;
		Area cliping;
		player = Interface.getMovablePlayer();
		lines = Model.getLines();
		try {
			while (!String.valueOf(
					lines.get(player.getBlockY() - l - 1).charAt(
							player.getBlockX())).contains("#")) {
				l += 1;
			}
			Rectangle2D pokus = new Rectangle(player.getX() - 9, player.getY()
					+ 6 - (l * 32) - player.getHeight()
					- (player.getY() - player.getBlockY() * 32), 45, l * 32 + 5
					+ player.getHeight());
			cliping = new Area(pokus);
			return cliping;
		} catch (Exception e) {
			return new Area();
		}
	}

	public static Area getClipingDown() {
		int l = 0;
		Area cliping;
		player = Interface.getMovablePlayer();
		lines = Model.getLines();
		while (!String.valueOf(
				lines.get(player.getBlockY() + l + 1)
						.charAt(player.getBlockX())).contains("#")) {
			l += 1;
		}
		Rectangle2D pokus = new Rectangle(player.getX() - 9, player.getY() - 8,
				45, l * 32 + 32 + player.getHeight()
						- (player.getY() - player.getBlockY() * 32));
		cliping = new Area(pokus);
		return cliping;
	}

	public static Area getClipingLeft() {
		int l = 0;
		Area cliping;
		player = Interface.getMovablePlayer();
		lines = Model.getLines();
		try {
			while (!String.valueOf(
					lines.get(player.getBlockY()).charAt(
							player.getBlockX() - l - 1)).contains("#")) {
				l += 1;
			}
			Rectangle2D pokus = new Rectangle(player.getX() - (l * 32) + 6
					- player.getWidth()
					- (player.getX() - player.getBlockX() * 32),
					player.getY() - 9, l * 32 + player.getWidth(), 45);
			cliping = new Area(pokus);
			return cliping;
		} catch (Exception e) {
			return new Area();
		}
	}

	public static Area getCliping() {
		player = Interface.getMovablePlayer();
		Area cliping = new Area();
		Ellipse2D.Double playerEllipse = new Ellipse2D.Double(
				player.getX() - 50, player.getY() - 50,
				100 + player.getHeight(), 100 + player.getWidth());
		Area playerArea = new Area(playerEllipse);
		cliping.add(getClipingDown());
		cliping.add(getClipingLeft());
		cliping.add(getClipingRight());
		cliping.add(getClipingUp());
		cliping.add(playerArea);
		return cliping;
	}

	public static Area getOutView() {
		Area outView = new Area();
		Rectangle2D map = new Rectangle(0, 0, 800, 635);
		Area mapArea = new Area(map);
		outView.add(mapArea);
		outView.subtract(getCliping());
		return outView;
	}
}
