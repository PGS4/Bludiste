package bludiste;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private int x;
	private int y;
	private int[][] pos = { { 16, 16 }, { 3*16, 16 }, { 5*16, 16 }, { 7*16, 16 }, { 9*16, 16 }, { 11*16, 16 }, { 13*16, 16 }, 
			{ 15*16, 16 }, { 17*16, 16 }, { 19*16, 16 }, { 21*16, 16 }, { 23*16, 16 }, { 25*16, 16 }, { 27*16, 16 }, { 29*16, 16 }, { 31*16, 16 }
			, { 33*16, 16 }, { 35*16, 16 }, { 37*16, 16 }, { 39*16, 16 }, {41*16, 16 }, { 43*16, 16 }, { 45*16, 16 }, { 47*16, 16 }, { 49*16, 16 }, { 51*16, 16 }
			, { 53*16, 16 }, { 55*16, 16 }, { 57*16, 16 }, { 27*16, 13*16 }, { 27*16, 11*16 }, { 27*16, 9*16 }, { 27*16, 7*16 }, { 27*16, 5*16 }, { 27*16, 3*16 }};

	Color pozadi = new Color(199, 199, 199);

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		player = new Player();
		initZed();

		timer = new Timer(5, this);
		timer.start();
	}

	public void initZed() {
		zdi = new ArrayList<Zed>();
		for (int i = 0; i < pos.length; i++) {
			zdi.add(new Zed(pos[i][0], pos[i][1]));
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
		for (int i = 0; i < zdi.size(); i++) {
			Zed azed = (Zed) zdi.get(i);
			g2d.drawImage(azed.getImage(), azed.getX(), azed.getY(), this);
		}
		g2d.drawRect(0, 0, 800, 600);

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
				if (player.getX() < (azed.getX()-(azed.getWidth()/2))) {
					player.x = player.getX() - 1;
				}
				if (player.getX() > (azed.getX()+(azed.getWidth()/2))) {
					player.x = player.getX() + 1;
				}
				if (player.getY() < (azed.getY()+(azed.getHeight()/2))) {
					player.y = player.getY() - 1;
				}
				if (player.getY() > (azed.getY()-(azed.getHeight()/2))) {
					player.y = player.getY() + 1;
				}
			}
		}
	}

	public int playerX() {
		return x;
	}

	public int playerY() {
		return y;
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
