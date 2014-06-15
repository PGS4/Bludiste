package com.labyrint.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.editor.main.Interface2;
import com.editor.main.ItemsWindow;
import com.editor.main.Model2;

public class Labyrint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9025982398559903990L;
	private final Timer editorCheck;
	private static Interface game;

	public Labyrint() {
		add(game);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(800, 635);
		setTitle("Labyrint");
		setLocationRelativeTo(null);
		setVisible(true);
		editorCheck = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Interface.editorLaunched() == true) {
					Model2 editorModel = new Model2();
					editorModel.newMap();
					Interface2 editorWindow = new Interface2();
					setTitle("Editor map");
					remove(game);
					setJMenuBar(editorWindow.addMenu());
					setSize(800,655);
					add(editorWindow);
					JFrame items = new JFrame();
					ItemsWindow itemy = new ItemsWindow();
					items.add(itemy);
					items.setSize(800,128);
					items.validate();
					items.setTitle("Pøedmìty");
					items.setVisible(true);
					items.setResizable(false);
					items.setDefaultCloseOperation(EXIT_ON_CLOSE);
					items.setLocation(560, 870);
					validate();
					editorCheck.stop();
				}

			}
		});
		editorCheck.start();
	}

	public static void main(String[] args) {
		game = new Interface();
		Controller control = new Controller();
		Thread controller = new Thread(control);
		controller.setDaemon(true);
		Thread graphics = new Thread(game);
		graphics.setDaemon(true);
		graphics.start();
		controller.start();
		new Labyrint();
	}
}
// TODO list: 
// Vytvoøení menu - 80% 
// Opravení duplikace tlaèítek - hotovo!
// Vytvoøení editoru 
// Vyladìní pohledu - hotovo!