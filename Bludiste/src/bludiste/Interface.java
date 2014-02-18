package bludiste;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Interface extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color pozadi = new Color(199, 199, 199);
	private Exit exit;
	private int levels;
	
	// private String symbol;
	
	private Timer timer;
	private ArrayList<Zed> zdi;
	public static Dimension d;
	private Player player;
	private Board board;
	private Map map;
	
	public Interface() {
		setFocusable(true);
		setBackground(pozadi);
		setDoubleBuffered(true);
		board = new Board();
		player = board.getPlayer();
		map = new Map();
		d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		timer = new Timer(5, this);
		timer.start();
	}
	
	
	// výpoèet grafiky
	public void paint(Graphics g) {
		super.paint(g);
		addKeyListener(new TAdapter());
		Graphics2D g2d = (Graphics2D) g;
		zdi = board.getZdi();
		levels = board.getLevels();
		exit = board.getExit();
		
		if (levels < 3) {
			g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);	// vypocet
																					// pro
																					// hrace
			for (int i = 0; i < zdi.size(); i++) {
				Zed azed = (Zed) zdi.get(i);
				g2d.drawImage(azed.getImage(), azed.getX(), azed.getY(), this); // vypocet
																				// pro
																				// zdi
			}
			/*
			 * for (int i = 0; i<enemies.size(); i++){ Enemy enemy = (Enemy)
			 * enemies.get(i); g2d.drawImage(enemy.getImage(), enemy.getX(),
			 * enemy.getY(), null); }
			 */
			g2d.drawImage(exit.getImage(), exit.getX(), exit.getY(), this); // vypocet
																			// pro
																			// exit
			g2d.drawRect(0, 0, (int) d.getWidth(), (int) d.getHeight());
		} else {
			g2d.drawString("KONEC HRY!!!", 350, 304);
		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		board.checkCollissions();
		player.move();
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
