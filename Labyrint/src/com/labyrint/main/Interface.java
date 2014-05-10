package com.labyrint.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JPanel;

import com.labyrint.entities.Enemy;
import com.labyrint.entities.Exit;
import com.labyrint.entities.Player;
import com.labyrint.entities.Zed;
import com.labyrint.graphics.Lives;
import com.labyrint.graphics.View;

public class Interface extends JPanel implements ActionListener {
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
	private int count=0;

	public Interface() {
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		player = Model.getPlayer();
		live = new Lives();
		model = new Model();
		Timer time = new Timer(25, this);
		time.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		addKeyListener(new TAdapter());
		Graphics2D g2d = (Graphics2D) g;
		newGame.addActionListener(this);
		editor.addActionListener(this);
		end.addActionListener(this);
		dale.addActionListener(this);
		if (start == false) {
			add(dale);
			dale.setEnabled(false);
			add(newGame);
			add(editor);
			add(end);
		}
		if (start == true) {
			zdi = Model.getZdi();
			exit = Model.getExit();
			levels = Model.getLevels();
			exit = Model.getExit();
			enemies = Model.getEnemies();
			lives = Model.getLives();
			if (pause == true) {
				System.out.println("Pauza");
				dale.setEnabled(true);
				add(dale);
				add(newGame);
				add(editor);
				add(end);
			} else {
				removeAll();
				if (levels < 4 && lives > 0) {
					g2d.setColor(Color.GRAY);
					g2d.fill(View.getCliping());
					g2d.drawImage(player.getImage(), player.getX(),
							player.getY(), null);
					for (int i = 0; i < zdi.size(); i++) {
						Zed azed = zdi.get(i);
						g2d.drawImage(azed.getImage(), azed.getX(),
								azed.getY(), null);
					}
					for (int i = 0; i < enemies.size(); i++) {
						Enemy enemy = enemies.get(i);
						g2d.drawImage(enemy.getImage(), enemy.getX(),
								enemy.getY(), null);
						Font cislo = new Font("Arial", Font.BOLD, 14);
						g2d.setFont(cislo);
						g2d.setColor(Color.WHITE);
						g2d.drawString(String.valueOf(i), enemy.getX(),
								enemy.getY());
					}
					g2d.drawImage(exit.getImage(), exit.getX(), exit.getY(),
							null);
					g2d.setColor(Color.BLACK);
					g2d.fill(View.getOutView());
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
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public static boolean getStart() {
		return start;
	}
	public static boolean getPause(){
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
				count=0;
			}
		}
	}

	public static Player getMovablePlayer() {
		return player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		try {
			if (e.getActionCommand().equals("Nová hra")) {
				start = true;
				System.out.println("Problem");
				count+=1;
				if(count==1){
					model.startNewGame();
				}
				pause = false;
			}
			if (e.getActionCommand().equals("Konec")) {
				System.exit(0);
			}
			if (e.getActionCommand().equals("Pokraèovat")) {
				pause = false;
			}
		} catch (Exception er) {
		}
		// System.out.println(player.getBlockY());
	}
}
