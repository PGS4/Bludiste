package com.labyrint.main;

import javax.swing.JFrame;

public class Labyrint extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9025982398559903990L;
	
	public Labyrint(){
		add(new Interface());
		Interface grafika = new Interface();
		Model model = new Model();
		Controller control = new Controller();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(800, 635);
		setTitle("Labyrint");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Labyrint();
	}
}
