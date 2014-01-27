package bludiste;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Player player;
	private Zed zed;

	Color pozadi = new Color(199,199,199);
	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		player = new Player();
		zed = new Zed();

		timer = new Timer(5, this);
		timer.start();
	}

	 public void paint(Graphics g) {
	        super.paint(g);

	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
	        g2d.drawImage(zed.getImage(), zed.getX(), zed.getY(), this);
	        g2d.drawRect(0, 0, 800, 600);

	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
	    }

	 
	 public void actionPerformed(ActionEvent e) {
		player.move();
		player.checkCollissions();
		repaint();
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
