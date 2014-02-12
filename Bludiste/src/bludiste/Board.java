package bludiste;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	/**
         * 
         */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Player player;
	private ArrayList<Zed> zdi;
	public static Dimension d;
	Color pozadi = new Color(199, 199, 199);
	private String[] level = null;
	private Exit exit;
	private int levels = 1;
	private Reader in;
	private BufferedReader br;
	private int int_symbol;
	private char symbol;
	private ArrayList<String> lines = new ArrayList<String>();

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		player = new Player();
		levels();
		d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		timer = new Timer(5, this);
		timer.start();
	}

	private void levels() {
		try {
			if (levels < 3) {
				in = new InputStreamReader(this.getClass().getResourceAsStream(
						"/level" + levels + ".txt"));
				br = new BufferedReader(in);
				while ((int_symbol = br.read()) != -1) {
					symbol = (char) int_symbol;
					if (String.valueOf(symbol).contains("#")
							|| String.valueOf(symbol).contains("!")
							|| String.valueOf(symbol).contains(".")) {
						lines.add(String.valueOf(symbol));
					}
				}
				level = (String[]) lines.toArray(new String[lines.size()]);
				System.out.println(level.length);
				initLevel();
				in.close();
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void initLevel() {
		zdi = new ArrayList<Zed>();
		for (int i = 0; i < level.length; i++) {
			if (level[i].contains("#")) {
				int x = (i % (800 / 32)) * 32;
				int y = ((i - (x / 32)) / (800 / 32)) * 32;
				zdi.add(new Zed(x, y));
				System.out.println(x + " a " + y);
			} else if (level[i].contains("!")) {
				int x = (i % (800 / 32)) * 32;
				int y = ((i - (x / 32)) / (800 / 32)) * 32;
				System.out.println(x + " a " + y);
				System.out.println(level[i]);
				exit = new Exit(x, y);
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (levels < 3) {
			g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
			for (int i = 0; i < zdi.size(); i++) {
				Zed azed = (Zed) zdi.get(i);
				g2d.drawImage(azed.getImage(), azed.getX(), azed.getY(), this);
			}
			g2d.drawImage(exit.getImage(), exit.getX(), exit.getY(), this);
			g2d.drawRect(0, 0, (int) d.getWidth(), (int) d.getHeight());
		} else {
			g2d.drawString("KONEC HRY!!!", 350, 304);
		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		player.move();
		checkCollissions();
		repaint();

	}

	public void checkCollissions() {

		Rectangle r1 = player.getBounds();
		for (int j = 0; j < zdi.size(); j++) {
			Zed azed = (Zed) zdi.get(j);
			Rectangle r2 = azed.getBounds();
			if (r1.intersects(r2)) {
				if ((player.getX() + player.getWidth() - 2) < azed.getX()) {
					player.x = player.getX() - 1;
				}
				if (player.getX() > (azed.getX() + azed.getWidth() - 2)) {
					player.x = player.getX() + 1;
				}
				if ((player.getY() + player.getHeight() - 2) < azed.getY()) {
					player.y = player.getY() - 1;
				}
				if (player.getY() > (azed.getY() + azed.getHeight() - 2)) {
					player.y = player.getY() + 1;
				}
			}
		}
		Rectangle r3 = exit.getBounds();
		if (r1.intersects(r3)) {
			levels += 1;
			player.x = 40;
			player.y = 40;
			zdi.clear();
			lines.clear();
			levels();
		}
	}

	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}
}