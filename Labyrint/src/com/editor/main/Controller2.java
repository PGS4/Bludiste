package com.editor.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Controller2 implements ActionListener {
	private Timer main = new Timer(25,this);
	
	
	public Controller2(){
		main.start();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
