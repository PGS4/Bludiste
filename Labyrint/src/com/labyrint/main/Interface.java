package com.labyrint.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.*;

import straightedge.geom.vision.VisionData;

import com.labyrint.entities.Enemy;
import com.labyrint.entities.Exit;
import com.labyrint.entities.Player;
import com.labyrint.entities.Zed;
import com.labyrint.graphics.Lives;
import com.labyrint.graphics.View;

public class Interface extends JPanel implements ActionListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -681939843334668445L;
	private Color pozadi = new Color(30, 30, 30);
	private Exit exit;
	private Model model;
	private static Player player;
	private int lives;
	private int levels;
	private ArrayList<Zed> zdi;
	private Lives live;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private static boolean start = false;
	private static boolean pause = false;
	JButton dale = new JButton("Pokraèovat");
	JButton newGame = new JButton("Nová hra");
	JButton editor = new JButton("Editor map");
	JButton end = new JButton("Konec");
	JButton scenario = new JButton("Hrát scénáø");
	JButton custom = new JButton("Hrát vytvoøené mapy");
	private int count = 0;
	private static boolean launchEditor = false;
	private boolean chooseMap = false;
	private Timer time = new Timer(15, this);
	private Timer fps = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			fpsDisplay = fpsCounter;
			fpsCounter = 0;
		}

	});
	private int fpsDisplay = 0;
	private int fpsCounter = 0;

	public Interface() {
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		player = Model.getPlayer();
		live = new Lives();
		model = new Model();
	}

	public void paint(Graphics g) {
		super.paint(g);
		addKeyListener(new TAdapter());
		Graphics2D g2d = (Graphics2D) g;
		View pohled = new View();
		newGame.addActionListener(this);
		editor.addActionListener(this);
		end.addActionListener(this);
		dale.addActionListener(this);
		scenario.addActionListener(this);
		custom.addActionListener(this);
		dale.setBounds(250, 64, 300, 64);
		newGame.setBounds(250, 64 + 2 * 64, 300, 64);
		editor.setBounds(250, 64 + 4 * 64, 300, 64);
		end.setBounds(250, 64 + 6 * 64, 300, 64);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (start == false) {
			add(dale);
			dale.setEnabled(false);
			add(newGame);
			add(editor);
			add(end);
		}
		if (chooseMap == true) {
			removeAll();
			scenario.setBounds(250, 64 + 2 * 64, 300, 64);
			custom.setBounds(250, 64 + 4 * 64, 300, 64);
			add(scenario);
			add(custom);
		}
		if (start == true) {
			zdi = Model.getZdi();
			exit = Model.getExit();
			levels = Model.getLevels();
			exit = Model.getExit();
			enemies = Model.getEnemies();
			lives = Model.getLives();
			float backGroundGrey = 1f / 150f;
			if (pause == true) {
				dale.setEnabled(true);
				add(dale);
				add(newGame);
				add(editor);
				add(end);
			} else {
				removeAll();
				if (levels < 4 && lives > 0) {

					// vykreslení pohledu
					g2d.setColor(Color.GRAY);
					if (View.getFov().visiblePolygon != null) {
						VisionData visionData = View.getFov();
						Point2D.Double center = new Point2D.Double(
								visionData.eye.x, visionData.eye.y);
						float[] dist = { 0.0f, 1.0f };
						float a = 0.9f;
						float c = backGroundGrey;
						Color[] colors = { new Color(a, a, a, (float) 0.4),
								new Color(c, c, c, (float) 0.2) };
						RadialGradientPaint paint = new RadialGradientPaint(
								center,
								(float) visionData.maxEyeToBoundaryPolygonPointDist,
								dist, colors);// , CycleMethod.REFLECT);
						g2d.setPaint(paint);
						g2d.fill(View.getFov().visiblePolygon);
					}

					// vykreslení nepøátel
					for (int i = 0; i < enemies.size(); i++) {
						Enemy enemy = enemies.get(i);
						g2d.drawImage(enemy.getImage(), enemy.getX(),
								enemy.getY(), null);
						/*
						 * Font cislo = new Font("Arial", Font.BOLD, 14);
						 * g2d.setFont(cislo);g2d.setColor(Color.WHITE);
						 * g2d.drawString(String.valueOf(i), enemy.getX(),
						 * enemy.getY());
						 */
					}

					// vykreslení zdí
					for (int i = 0; i < zdi.size(); i++) {
						Zed azed = zdi.get(i);
						g2d.drawImage(azed.getImage(), azed.getX(),
								azed.getY(), null);
					}

					// zakryté pole
					g2d.setColor(new Color(backGroundGrey, backGroundGrey,
							backGroundGrey, (float) 1));
					g2d.fill(View.getOutView());

					// vykreslení hráèe a exitu
					g2d.drawImage(player.getImage(), player.getX(),
							player.getY(), null);
					g2d.drawImage(exit.getImage(), exit.getX(), exit.getY(),
							null);

					// vykreslení životù
					for (int i = 0; i < lives; i++) {
						g2d.drawImage(live.getImage(), (i + 1) * 32,
								getHeight() - 30, null);
					}

				} else {
					Font endCredits = new Font("Times New Roman", Font.BOLD, 50);
					g2d.setFont(endCredits);
					g2d.setColor(Color.RED);
					g2d.drawString("KONEC HRY!!!", 230, 304);
				}
			}
		}
		g2d.setColor(Color.WHITE);
		Font fpsDisp = new Font("Times New Roman", Font.BOLD, 18);
		g2d.setFont(fpsDisp);
		g2d.drawString("FPS: " + fpsDisplay, 18, 18);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public static boolean getStart() {
		return start;
	}

	public static boolean getPause() {
		return pause;
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
			if (key == KeyEvent.VK_ESCAPE) {
				pause = true;
				count = 0;
			}
		}
	}

	public static Player getMovablePlayer() {
		return player;
	}

	public static boolean editorLaunched() {
		return launchEditor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		fpsCounter += 1;
		try {
			if (e.getActionCommand().equals("Nová hra")) {
				chooseMap = true;
				pause = false;
				start = false;
			}
			if (e.getActionCommand().equals("Konec")) {
				System.exit(0);
			}
			if (e.getActionCommand().equals("Pokraèovat")) {
				pause = false;
			}
			if (e.getActionCommand().equals("Editor map")) {
				start = false;
				launchEditor = true;
			}
			if (e.getActionCommand().equals("Hrát scénáø")) {
				start = true;
				chooseMap = false;
				count += 1;
				if (count == 1) {
					model.startNewGame();
				}
				pause = false;
			}
			if (e.getActionCommand().equals("Hrát vytvoøené mapy")) {
				System.out.println("TADY NIC NENI!");
			}
		} catch (Exception er) {
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		time.start();
		fps.start();
	}
}
