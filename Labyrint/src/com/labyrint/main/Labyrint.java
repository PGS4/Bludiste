package com.labyrint.main;

import javax.swing.JFrame;

public class Labyrint extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9025982398559903990L;
	
	public Labyrint(){
		Model model = new Model();
		model.levels();
		add(new Interface());
		Controller control = new Controller();
		Thread controller = new Thread(control);
		controller.start();
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
