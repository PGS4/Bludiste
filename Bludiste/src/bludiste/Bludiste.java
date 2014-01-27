package bludiste;

import javax.swing.JFrame;

public class Bludiste extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bludiste() {
		add(new Board());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Labyrint");
	}

	public static void main(String[] args) {
		new Bludiste();
	}
}
