package com.labyrint.main;

import javax.swing.JFrame;

public class Labyrint extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9025982398559903990L;
	
	public Labyrint(){
		add(new Interface());
		//add(new com.editor.main.Interface());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(800, 635);
		setTitle("Labyrint");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Labyrint();
		Controller control = new Controller();
		Thread controller = new Thread(control);
		controller.start();
	}
}
/* TODO list:
	Vytvoøení menu - 75%
	Opravení duplikace tlaèítek
	Vytvoøení editoru
	Vyladìní pohledu
	*/