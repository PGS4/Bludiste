package com.labyrint.entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Enemy extends MovableObject implements ActionListener{
	public Enemy(int x, int y, String name){
		super(x,y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/enemy.png"));
		image = ii.getImage();
		init();
		Timer main = new Timer(5, this);
		main.start();
	}
	public void init(){
		Random rand = new Random();
		direction=rand.nextInt(4);
		if(direction == 0){
			dx = 1;
			dy = 0;
		}
		if(direction == 1){
			dx = -1;
			dy = 0;
		}
		if(direction == 2){
			dx = 0;
			dy = 1;
		}
		if(direction == 3){
			dx = 0;
			dy = -1;
		}		
	}
	public void changeDirection(int problem){
		Random rand = new Random();
		direction=rand.nextInt(4);
		while(problem == direction){
			direction = rand.nextInt(4);
		}
		if(direction == 0){
			dx = 1;
			dy = 0;
		}
		if(direction == 1){
			dx = -1;
			dy = 0;
		}
		if(direction == 2){
			dx = 0;
			dy = 1;
		}
		if(direction == 3){
			dx = 0;
			dy = -1;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		move();
	}
}
